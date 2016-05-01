package com.coopinc.mvpchallenge.ui.kingdoms;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class KingdomListFragment extends Fragment {

    @Bind(R.id.card_list) RecyclerView recyclerView;
    @Bind(R.id.progress_bar) ProgressBar progressBar;
    @Bind(R.id.empty_text) TextView tvEmpty;
    @Bind(R.id.swiper) SwipeRefreshLayout swiper;

    private List<KingdomBriefModel> kingdoms = new ArrayList<KingdomBriefModel>();
    private KingdomListAdapter adapter;
    private IKingdomListPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new KingdomListPresenter(this, (KingdomsActivity) getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kingdom_list, container, false);
        ButterKnife.bind(this, view);
        tvEmpty.setVisibility(View.GONE);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new KingdomListAdapter(kingdoms, presenter);
        recyclerView.setAdapter(adapter);
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
        presenter.onResume(kingdoms.isEmpty());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        swiper.setRefreshing(false);
    }

    public void showError(String error) {

    }

    public void setData(List<KingdomBriefModel> kingdoms) {
        this.kingdoms = kingdoms;
        adapter.setKingdoms(kingdoms);
        adapter.notifyDataSetChanged();
    }
}
