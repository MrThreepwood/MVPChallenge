package com.coopinc.mvpchallenge.ui.kingdoms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.data.events.KingdomListFailureEvent;
import com.coopinc.mvpchallenge.data.events.KingdomListSuccessEvent;
import com.coopinc.mvpchallenge.data.service.KingdomService;
import com.coopinc.mvpchallenge.ui.auth.AuthActivity;
import com.coopinc.mvpchallenge.ui.auth.LoginPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class KingdomListPresenter implements IKingdomListPresenter {
    public static final String KINGDOM_FLAG = "kingdomId";
    private static final String DEFAULT_TITLE = "email";

    private IKingdomListFragment view;
    private KingdomsActivity viewParent;
    private KingdomService service;

    public KingdomListPresenter(IKingdomListFragment view, KingdomsActivity viewParent) {
        this.view = view;
        this.viewParent = viewParent;
        this.service = ChallengeApp.getKingdomService();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        EventBus.getDefault().register(this);
        if (view.getLocalKingdoms().isEmpty()) {
            getKingdoms();
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
        view.setToolbarTitle(prefs.getString(LoginPresenter.EMAIL_PREFS_KEY, DEFAULT_TITLE));
    }

    @Override
    public void nextFragment(String kingdomId) {
        Bundle args = new Bundle();
        args.putString(KINGDOM_FLAG, kingdomId);
        Toast.makeText(ChallengeApp.getContext(), "Go to Quest Pager", Toast.LENGTH_LONG).show();
//        KingdomQuestPager f = new KingdomQuestPager();
//        f.setArguments(args);
//        viewParent.nextFragment(f);
    }

    @Override
    public void refresh() {
        getKingdoms();
    }

    @Override
    public void logout() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
        prefs.edit().remove(LoginPresenter.EMAIL_PREFS_KEY).commit();
        viewParent.nextActivity(AuthActivity.class);
    }

    @Subscribe
    public final void onKingdomListSuccess(KingdomListSuccessEvent event) {
        view.setData(event.getKindoms());
        view.hideProgressBar();
    }

    @Subscribe
    public final void onKingdomListFailure(KingdomListFailureEvent event) {
        if(view.getLocalKingdoms().isEmpty()) {
            view.showError(event.getError());
            view.hideProgressBar();
        } else {
            Toast.makeText(ChallengeApp.getContext(), "Error refreshing kingdoms.", Toast.LENGTH_LONG).show();
            view.hideProgressBar();
        }
    }

    private void getKingdoms() {
        service.getKindoms();
    }
}
