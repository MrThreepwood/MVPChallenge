package com.coopinc.mvpchallenge.ui.kingdom_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.coopinc.mvpchallenge.ui.BaseActivity;
import com.coopinc.mvpchallenge.ui.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomListFragment extends BaseFragment<KingdomListPresenter> implements IKingdomListFragment {

    @Bind(R.id.card_list)
    RecyclerView recyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.empty_text)
    TextView tvEmpty;
    @Bind(R.id.swiper)
    SwipeRefreshLayout swiper;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private KingdomListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new KingdomListAdapter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kingdom_list, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreateView();
        toolbar.inflateMenu(R.menu.overflow_menu);
        toolbar.setOnMenuItemClickListener(new MenuItemOnClickListener());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        return view;
    }

    @NonNull
    @Override
    public KingdomListPresenter onCreatePresenter() {
        return new KingdomListPresenter(this);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        swiper.setRefreshing(false);
    }

    @Override
    public void setData(List<KingdomModel> kingdoms) {
        if(tvEmpty != null) {
            tvEmpty.setVisibility(View.GONE);
        }
        adapter.setKingdoms(kingdoms);
    }

    @Override
    public void showError(String error) {
        String formattedError = error + "\nPlease pull down to try again.";
        tvEmpty.setText(formattedError);
        tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToKingdom(Fragment kingdomFragment) {
        ((BaseActivity)getActivity()).nextFragment(kingdomFragment, true);
    }

    @Override
    public void logout(Class authActivityClass) {
        BaseActivity parent = ((BaseActivity) getActivity());
        parent.nextActivity(authActivityClass);
        parent.finish();
    }

    public class MenuItemOnClickListener implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.logout) {
                presenter.logout();
                return true;
            }
            return false;
        }
    }
}
