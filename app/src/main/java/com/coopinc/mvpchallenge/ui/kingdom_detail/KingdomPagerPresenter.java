package com.coopinc.mvpchallenge.ui.kingdom_detail;

import android.os.Bundle;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.data.events.character.CharacterSuccessEvent;
import com.coopinc.mvpchallenge.data.events.character.CharacterFailureEvent;
import com.coopinc.mvpchallenge.data.events.kingdom_detail.KingdomDetailFailureEvent;
import com.coopinc.mvpchallenge.data.events.kingdom_detail.KingdomDetailSuccessEvent;
import com.coopinc.mvpchallenge.data.models.CharacterModel;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.coopinc.mvpchallenge.data.models.QuestModel;
import com.coopinc.mvpchallenge.data.service.character.CharacterService;
import com.coopinc.mvpchallenge.data.service.kingdom.KingdomService;
import com.coopinc.mvpchallenge.ui.BasePresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icepick.State;

public class KingdomPagerPresenter extends BasePresenter implements IKingdomPagerPresenter {

    public static final String KINGDOM_KEY = "kingdom";
    public static final String QUEST_KEY = "quest";

    private KingdomService kingdomService;
    private CharacterService characterService;

    private KingdomPagerFragment view;

    private IDetailFragment currentFragment;

    @State
    KingdomModel kingdom;
    private Map<String, List<QuestModel>> questsByGiverID = new HashMap<>();

    public KingdomPagerPresenter(KingdomPagerFragment view) {
        this.view = view;
        kingdomService = ChallengeApp.getKingdomService();
        characterService = ChallengeApp.getCharacterService();
    }

    @Override
    public void onCreateView(String kingdomId) {
        if (kingdom == null) {
            kingdomService.getKingdomDetail(kingdomId);
        } else {
            onKingdomDetailSuccess(new KingdomDetailSuccessEvent(kingdom));
        }
    }

    @Override
    public void fragmentChange(IDetailFragment currentFragment) {
        this.currentFragment = currentFragment;
        view.setTitle(currentFragment.getTitle());
        if (currentFragment.isQuest()) {
            QuestDetailFragment questFrag = (QuestDetailFragment) currentFragment;
            if (questsByGiverID.containsKey(questFrag.getGiverId())) {
                //We don't care which quest we grab, we've established they all have the same giver.
                QuestModel quest = questsByGiverID.get(questFrag.getGiverId()).get(0);
                questFrag.setQuestGiver(quest.getGiver());
            }
        }
    }

    @Subscribe
    public final void onKingdomDetailSuccess(KingdomDetailSuccessEvent kingdomDetailSuccessEvent) {
        view.hideProgressBar();
        kingdom = kingdomDetailSuccessEvent.getKingdom();
        List<IDetailFragment> fragments = new ArrayList<>();
        fragments.add(buildKingdomDetailFragment(kingdom));

        for (QuestModel quest : kingdom.getQuests()) {
            fragments.add(buildQuestDetailFragment(quest));
            String giverId = quest.getGiver().getId();
            //Two quests could have the same quest giver, check to make sure we don't ask for the same bio twice.
            if (!questsByGiverID.containsKey(giverId)) {
                List<QuestModel> quests = new ArrayList<>();
                quests.add(quest);
                questsByGiverID.put(giverId, quests);
                //If we don't have a saved bio from icepick, get the bio.
                if (quest.getGiver().getBio() == null) {
                    characterService.getCharacter(giverId);
                }
            } else {
                questsByGiverID.get(giverId).add(quest);
            }
        }
        view.pagerSetup(fragments);
    }

    @Subscribe
    public final void onKingdomDetailFailure(KingdomDetailFailureEvent kingdomDetailFailure) {
        String error = kingdomDetailFailure.getError();
    }

    @Subscribe
    public final void onCharacterSuccess(CharacterSuccessEvent characterSuccessEvent) {
        CharacterModel character = characterSuccessEvent.getCharacterModel();
        if (questsByGiverID.containsKey(character.getId())) {
            for (QuestModel quest : questsByGiverID.get(character.getId())) {
                quest.setGiver(character);
            }
        }
        //If the giver bio we just got is the current fragment, update now.
        if (currentFragment != null && currentFragment.isQuest()) {
            QuestDetailFragment questFrag = (QuestDetailFragment) currentFragment;
            if (questFrag.getGiverId().equals(character.getId())) {
                questFrag.setQuestGiver(character);
            }
        }
    }

    @Subscribe
    public final void onCharacterFailure(CharacterFailureEvent characterFailureEvent) {
        //TODO: Figure out how to get URL param from retrofit error.
    }

    private KingdomDetailFragment buildKingdomDetailFragment(KingdomModel kingdomModel) {
        Bundle args = new Bundle();
        args.putSerializable(KINGDOM_KEY, kingdomModel);
        KingdomDetailFragment kingdomDetailFragment = new KingdomDetailFragment();
        kingdomDetailFragment.setArguments(args);
        return kingdomDetailFragment;
    }

    private QuestDetailFragment buildQuestDetailFragment(QuestModel questModel) {
        Bundle args = new Bundle();
        args.putSerializable(QUEST_KEY, questModel);
        QuestDetailFragment questDetailFragment = new QuestDetailFragment();
        questDetailFragment.setArguments(args);
        return questDetailFragment;
    }
}
