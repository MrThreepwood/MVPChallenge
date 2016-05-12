package com.coopinc.mvpchallenge.ui.kingdoms;

import com.coopinc.mvpchallenge.ui.IBasePresenter;

public interface IKingdomListPresenter extends IBasePresenter {
    void onCreateView();
    void onKingdomSelected(String kingdomId);
    void refresh();
    void logout();
}
