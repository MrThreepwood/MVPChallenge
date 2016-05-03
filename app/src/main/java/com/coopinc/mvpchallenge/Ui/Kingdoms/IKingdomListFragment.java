package com.coopinc.mvpchallenge.ui.kingdoms;

import com.coopinc.mvpchallenge.data.models.KingdomBriefModel;

import java.util.List;

public interface IKingdomListFragment {
    List<KingdomBriefModel> getLocalKingdoms();

    void setToolbarTitle(String title);

    void hideProgressBar();

    void showError(String error);

    void setData(List<KingdomBriefModel> kingdoms);
}
