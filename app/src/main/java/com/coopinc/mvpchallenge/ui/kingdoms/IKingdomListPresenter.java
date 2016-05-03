package com.coopinc.mvpchallenge.ui.kingdoms;

public interface IKingdomListPresenter {
    void onPause();

    void onResume();

    void nextFragment(String kingdomId);

    void refresh();

    void logout();
}
