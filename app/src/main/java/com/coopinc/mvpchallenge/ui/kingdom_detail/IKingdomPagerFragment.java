package com.coopinc.mvpchallenge.ui.kingdom_detail;

import java.util.List;

/**
 * Created by joshuaswoyer on 5/23/16.
 */
public interface IKingdomPagerFragment {
    void setTitle(String title);

    void hideProgressBar();

    void pagerSetup(List<IDetailFragment> fragments);

    void setError(String error);
}
