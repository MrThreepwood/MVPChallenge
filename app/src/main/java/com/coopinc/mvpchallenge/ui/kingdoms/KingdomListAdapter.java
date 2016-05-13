package com.coopinc.mvpchallenge.ui.kingdoms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.coopinc.mvpchallenge.ui.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomListAdapter extends RecyclerView.Adapter<KingdomListAdapter.KingdomsViewHolder> {
    private List<KingdomModel> kingdoms;
    private IKingdomListPresenter presenter;

    public KingdomListAdapter(IKingdomListPresenter presenter) {
        kingdoms = new ArrayList<>();
        this.presenter = presenter;
    }

    @Override
    public int getItemCount() {
        return kingdoms.size();
    }

    @Override
    public void onBindViewHolder (KingdomsViewHolder kingdomsViewHolder, int i) {
        KingdomModel kingdom = kingdoms.get(i);
        kingdomsViewHolder.vKingdomName.setText(kingdom.getName());
        if (kingdom.getImage() != null && !kingdom.getImage().isEmpty()) {
            Picasso.with(kingdomsViewHolder.itemView.getContext())
                    .load(kingdom.getImage())
                    .transform(new CircleTransform())
                    .into(kingdomsViewHolder.vImage);
        } else {
            kingdomsViewHolder.vImage.setImageResource(R.mipmap.ic_launcher);
        }
        kingdomsViewHolder.itemView.setTag(kingdom.getId());
    }

    @Override
    public KingdomsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_kingdom, viewGroup, false);
        return new KingdomsViewHolder(itemView);
    }

    public void setKingdoms(List<KingdomModel> kingdoms) {
        this.kingdoms.clear();
        this.kingdoms.addAll(kingdoms);
        notifyDataSetChanged();
    }

    public class KingdomsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.kingdom_image) ImageView vImage;
        @Bind(R.id.kingdom_name) TextView vKingdomName;

        public KingdomsViewHolder (View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String kingdomId = (String) view.getTag();
                    presenter.onKingdomSelected(kingdomId);
                }
            });
        }
    }
}
