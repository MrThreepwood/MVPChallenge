package com.coopinc.mvpchallenge.ui.kingdom_list;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.data.events.kingdom_list.KingdomListFailureEvent;
import com.coopinc.mvpchallenge.data.events.kingdom_list.KingdomListSuccessEvent;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.coopinc.mvpchallenge.data.service.kingdom.KingdomService;
import com.coopinc.mvpchallenge.ui.BasePresenter;
import com.coopinc.mvpchallenge.ui.auth.AuthActivity;
import com.coopinc.mvpchallenge.ui.auth.LoginPresenter;
import com.coopinc.mvpchallenge.ui.kingdom_detail.KingdomPagerFragment;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import icepick.State;

public class KingdomListPresenter extends BasePresenter implements IKingdomListPresenter {
    public static final String KINGDOM_ID_KEY = "kingdomId";
    public static final String KINGDOM_NAME_KEY = "kingdomName";
    private static final String DEFAULT_TITLE = "email";

    private IKingdomListFragment view;
    private KingdomService service;

    @State
    KingdomListHolder kingdomsHolder = new KingdomListHolder();

    public KingdomListPresenter(IKingdomListFragment view) {
        this.view = view;
        this.service = ChallengeApp.getKingdomService();
    }

    @Override
    public void onCreateView() {
        if (kingdomsHolder.kingdoms.isEmpty()) {
            getKingdoms();
        } else {
            view.hideProgressBar();
            view.setData(kingdomsHolder.kingdoms);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
        view.setToolbarTitle(prefs.getString(LoginPresenter.EMAIL_PREFS_KEY, DEFAULT_TITLE));
    }

    @Override
    public void onKingdomSelected(String kingdomId, String kingdomName) {
        Bundle args = new Bundle();
        args.putString(KINGDOM_ID_KEY, kingdomId);
        args.putString(KINGDOM_NAME_KEY, kingdomName);
        KingdomPagerFragment f = new KingdomPagerFragment();
        f.setArguments(args);
        view.goToKingdom(f);
    }

    @Override
    public void refresh() {
        getKingdoms();
    }

    @Override
    public void logout() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
        prefs.edit().remove(LoginPresenter.EMAIL_PREFS_KEY).commit();
        view.logout(AuthActivity.class);
    }

    @Subscribe
    public final void onKingdomListSuccess(KingdomListSuccessEvent event) {
        kingdomsHolder.kingdoms.clear();
        kingdomsHolder.kingdoms.addAll(event.getKindoms());
        view.setData(event.getKindoms());
        view.hideProgressBar();
    }

    @Subscribe
    public final void onKingdomListFailure(KingdomListFailureEvent event) {
        if(kingdomsHolder.kingdoms.isEmpty()) {
            view.showError(event.getError());
            view.hideProgressBar();
        } else {
            Toast.makeText(ChallengeApp.getContext(), "Error refreshing kingdoms.", Toast.LENGTH_LONG).show();
            view.hideProgressBar();
        }
    }

    private void getKingdoms() {
        service.getKingdoms();
    }

    public final class KingdomListHolder implements Serializable {
        public List<KingdomModel> kingdoms = new ArrayList<>();
    }
}
