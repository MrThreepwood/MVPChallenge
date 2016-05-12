package com.coopinc.mvpchallenge.ui.kingdoms;

import android.app.Fragment;

import com.coopinc.mvpchallenge.data.models.KingdomModel;

import java.util.List;

public interface IKingdomListFragment {
    void setToolbarTitle(String title);
    void hideProgressBar();
    void showError(String error);
    void setData(List<KingdomModel> kingdoms);
    void goToKingdom(Fragment fragment);
    void logout(Class authActivityClass);
}
