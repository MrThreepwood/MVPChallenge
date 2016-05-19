package com.coopinc.mvpchallenge.ui.kingdom_detail;

import com.coopinc.mvpchallenge.ui.IBasePresenter;

public interface IKingdomPagerPresenter extends IBasePresenter {
    void onCreateView(String kingdomId);

    void fragmentChange(IDetailFragment currentFragment);
}
