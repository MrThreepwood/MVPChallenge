package com.coopinc.mvpchallenge.ui.kingdom_list;

import com.coopinc.mvpchallenge.ui.IBasePresenter;

public interface IKingdomListPresenter extends IBasePresenter {
    void onCreateView();
    void onKingdomSelected(String kingdomId, String kingdomName);
    void refresh();
    void logout();
}
