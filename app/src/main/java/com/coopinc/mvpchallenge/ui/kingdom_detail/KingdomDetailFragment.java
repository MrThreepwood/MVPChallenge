package com.coopinc.mvpchallenge.ui.kingdom_detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

public class KingdomDetailFragment extends Fragment implements IDetailFragment {

    @State
    KingdomModel kingdom;

    @Bind(R.id.kingdom_image)
    ImageView ivKingdomImage;
    @Bind(R.id.climate_text)
    TextView tvClimate;
    @Bind(R.id.population_text)
    TextView tvPopulation;
    @Bind(R.id.quest_list)
    RecyclerView rvQuests;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kingdom_detail, container, false);
        ButterKnife.bind(this, v);
        if (kingdom == null) {
            kingdom = (KingdomModel) getArguments().getSerializable(KingdomPagerPresenter.KINGDOM_KEY);
        }
        Picasso.with(getActivity()).load(kingdom.getImage()).fit().into(ivKingdomImage);
        tvPopulation.setText(kingdom.getPopulation());
        tvClimate.setText(kingdom.getClimate());
        KingdomDetailAdapter kingdomDetailAdapter = new KingdomDetailAdapter(kingdom.getQuests());
        rvQuests.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvQuests.setAdapter(kingdomDetailAdapter);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setupModel() {
        kingdom = (KingdomModel) getArguments().getSerializable(KingdomPagerPresenter.KINGDOM_KEY);
    }

    @Override
    public String getTitle() {
        return kingdom.getName();
    }

    @Override
    public boolean isQuest() {
        return false;
    }
}
