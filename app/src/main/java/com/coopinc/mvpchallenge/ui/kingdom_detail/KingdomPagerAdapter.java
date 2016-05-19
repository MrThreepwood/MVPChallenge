package com.coopinc.mvpchallenge.ui.kingdom_detail;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.List;

public class KingdomPagerAdapter extends FragmentPagerAdapter {

    private List<IDetailFragment> fragments;

    public KingdomPagerAdapter(FragmentManager fm, List<IDetailFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }
}
