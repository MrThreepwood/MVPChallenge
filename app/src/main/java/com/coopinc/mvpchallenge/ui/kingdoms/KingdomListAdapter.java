package com.coopinc.mvpchallenge.ui.kingdoms;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.models.KingdomBriefModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomListAdapter extends RecyclerView.Adapter<KingdomListAdapter.KingdomsViewHolder> {
    private List<KingdomBriefModel> kingdoms;
    private IKingdomListPresenter presenter;

    public KingdomListAdapter(List<KingdomBriefModel> kingdoms, IKingdomListPresenter presenter) {
        this.kingdoms = kingdoms;
        this.presenter = presenter;
    }

    @Override
    public int getItemCount() {
        return kingdoms.size();
    }

    @Override
    public void onBindViewHolder (KingdomsViewHolder kingdomsViewHolder, int i) {
        KingdomBriefModel kingdom = kingdoms.get(i);
        kingdomsViewHolder.vKingdomName.setText(kingdom.getName());
        if (kingdom.getImage() != null && !kingdom.getImage().isEmpty()) {
            Picasso.with(kingdomsViewHolder.view.getContext())
                    .load(kingdom.getImage())
                    .fit()
                    .into(kingdomsViewHolder.vImage);
        }
        Log.d("KingdomId", "onBindViewHolder " + kingdom.getId());
        kingdomsViewHolder.view.setTag(kingdom.getId());
    }

    @Override
    public KingdomsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_kingdom, viewGroup, false);
        return new KingdomsViewHolder(itemView);
    }

    public void setKingdoms(List<KingdomBriefModel> kingdoms) {
        this.kingdoms = kingdoms;
    }

    public class KingdomsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.kingdom_image) ImageView vImage;
        @Bind(R.id.kingdom_name) TextView vKingdomName;

        protected View view;

        public KingdomsViewHolder (View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String kingdomId = (String) view.getTag();
                    presenter.nextFragment(kingdomId);
                }
            });
        }

    }
}
