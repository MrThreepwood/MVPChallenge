package com.coopinc.mvpchallenge.ui.kingdom_detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.models.CharacterModel;
import com.coopinc.mvpchallenge.data.models.QuestModel;
import com.coopinc.mvpchallenge.ui.util.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

public class QuestDetailFragment extends Fragment implements IDetailFragment {

    @State
    QuestModel quest;

    @Bind(R.id.quest_image)
    ImageView ivQuestImage;
    @Bind(R.id.quest_description)
    TextView tvQuestDescription;
    @Bind(R.id.quest_giver_image)
    ImageView ivGiverImage;
    @Bind(R.id.quest_giver_name)
    TextView tvGiverName;
    @Bind(R.id.quest_giver_bio)
    TextView tvGiverBio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quest, container, false);
        ButterKnife.bind(this, v);
        if (quest == null) {
            quest = (QuestModel) getArguments().getSerializable(KingdomPagerPresenter.QUEST_KEY);
        }
        Picasso.with(getActivity()).load(quest.getImage()).fit().into(ivQuestImage);
        tvQuestDescription.setText(quest.getDescription());
        Picasso.with(getActivity()).load(quest.getGiver().getImage()).transform(new CircleTransform()).into(ivGiverImage);
        tvGiverName.setText(quest.getGiver().getName());
        tvGiverBio.setText(quest.getGiver().getBio());
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setupModel() {
        quest = (QuestModel) getArguments().getSerializable(KingdomPagerPresenter.QUEST_KEY);
    }

    @Override
    public String getTitle() {
        return quest.getName();
    }

    @Override
    public boolean isQuest() {
        return true;
    }

    public String getGiverId() {
        return quest.getGiver().getId();
    }

    public void setQuestGiver(CharacterModel giver) {
        if (tvGiverBio != null) {
            tvGiverBio.setText(giver.getBio());
        }
        quest.setGiver(giver);
    }
}
