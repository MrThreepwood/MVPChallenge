package com.coopinc.mvpchallenge.ui.kingdoms;

import android.os.Bundle;
import android.widget.Toast;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.data.events.KingdomListFailureEvent;
import com.coopinc.mvpchallenge.data.events.KingdomListSuccessEvent;
import com.coopinc.mvpchallenge.data.service.KingdomService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class KingdomListPresenter implements IKingdomListPresenter {
    public static final String KINGDOM_FLAG = "kingdomId";

    private KingdomListFragment view;
    private KingdomsActivity viewParent;
    private KingdomService service;

    public KingdomListPresenter(KingdomListFragment view, KingdomsActivity viewParent) {
        this.view = view;
        this.viewParent = viewParent;
        this.service = ChallengeApp.getKingdomService();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume(boolean isKingdomsEmpty) {
        EventBus.getDefault().register(this);
        if (isKingdomsEmpty) {
            getKingdoms();
        }
    }

    @Override
    public void refresh() {
        getKingdoms();
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

    @Subscribe
    public final void onKingdomListSuccess(KingdomListSuccessEvent event) {
        view.setData(event.getKindoms());
        view.hideProgressBar();
    }

    @Subscribe
    public final void onKingdomListFailure(KingdomListFailureEvent event) {
        view.showError(event.getError());
    }

    private void getKingdoms() {
        service.getKindoms();
    }
}
