package com.coopinc.mvpchallenge.ui.kingdom_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.ui.BaseFragment;
import com.coopinc.mvpchallenge.ui.kingdom_list.KingdomListPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomPagerFragment extends BaseFragment<IKingdomPagerPresenter> {

    private String kingdomId;
    private String kingdomName;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private KingdomPagerAdapter kingdomPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kingdomId = getArguments().getString(KingdomListPresenter.KINGDOM_ID_KEY);
        kingdomName = getArguments().getString(KingdomListPresenter.KINGDOM_NAME_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kingdom_pager, container, false);
        ButterKnife.bind(this, v);
        setTitle(kingdomName);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        presenter.onCreateView(kingdomId);
        return v;
    }

    @NonNull
    @Override
    public IKingdomPagerPresenter onCreatePresenter() {
        return new KingdomPagerPresenter(this);
    }

    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void pagerSetup(List<IDetailFragment> fragments) {
        kingdomPagerAdapter = new KingdomPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(kingdomPagerAdapter);
        viewPager.addOnPageChangeListener(new KingdomPageChangeListener());
    }

    public class KingdomPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            IDetailFragment currentFragment = (IDetailFragment) kingdomPagerAdapter.getItem(position);
            currentFragment.setupModel();
            presenter.fragmentChange(currentFragment);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
