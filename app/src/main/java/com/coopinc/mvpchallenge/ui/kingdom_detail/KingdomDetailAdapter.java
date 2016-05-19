package com.coopinc.mvpchallenge.ui.kingdom_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.models.QuestModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomDetailAdapter extends RecyclerView.Adapter<KingdomDetailAdapter.QuestCardHolder>{

    List<QuestModel> quests;

    public KingdomDetailAdapter (List<QuestModel> quests) {
        this.quests = quests;
    }

    @Override
    public QuestCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_quest, parent, false);
        return new QuestCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestCardHolder holder, int position) {
        holder.tvQuestName.setText(quests.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return quests.size();
    }

    public class QuestCardHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.quest_name)
        TextView tvQuestName;

        public QuestCardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
