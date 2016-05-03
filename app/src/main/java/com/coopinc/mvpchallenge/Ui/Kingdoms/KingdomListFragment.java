package com.coopinc.mvpchallenge.ui.kingdoms;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.coopinc.mvpchallenge.data.models.KingdomBriefModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomListFragment extends Fragment implements IKingdomListFragment {

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

    private List<KingdomBriefModel> kingdoms = new ArrayList<KingdomBriefModel>();
    private KingdomListAdapter adapter;
    private IKingdomListPresenter presenter;
    private LinearLayoutManager llm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new KingdomListPresenter(this, (KingdomsActivity) getActivity());
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new KingdomListAdapter(kingdoms, presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kingdom_list, container, false);
        ButterKnife.bind(this, view);
        tvEmpty.setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        toolbar.setOnMenuItemClickListener(new MenuItemOnClickListener());
        if(recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
        }
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public List<KingdomBriefModel> getLocalKingdoms() {
        return kingdoms;
    }

    @Override
    public void setToolbarTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        swiper.setRefreshing(false);
    }

    @Override
    public void setData(List<KingdomBriefModel> kingdoms) {
        this.kingdoms = kingdoms;
        tvEmpty.setVisibility(View.GONE);
        adapter.setKingdoms(kingdoms);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        tvEmpty.setText(error + "\nPlease pull down to try again.");
        tvEmpty.setVisibility(View.VISIBLE);
    }

    public class MenuItemOnClickListener implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (getString(R.string.logout).contentEquals(menuItem.getTitle())) {
                presenter.logout();
                return true;
            }
            return false;
        }
    }
}
