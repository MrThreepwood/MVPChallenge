package com.coopinc.mvpchallenge.ui.kingdoms;

public interface IKingdomListPresenter {
    void onPause();
    void onResume(boolean isKingdomsEmpty);
    void nextFragment(String kingdomId);
    void refresh();
}
