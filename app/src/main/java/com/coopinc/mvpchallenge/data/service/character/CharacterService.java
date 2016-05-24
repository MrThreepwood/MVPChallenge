package com.coopinc.mvpchallenge.data.service.character;

import com.coopinc.mvpchallenge.data.domain.character.ICharacterDomain;
import com.coopinc.mvpchallenge.data.events.character.CharacterFailureEvent;
import com.coopinc.mvpchallenge.data.events.character.CharacterSuccessEvent;
import com.coopinc.mvpchallenge.data.models.CharacterModel;

import org.greenrobot.eventbus.EventBus;

public class CharacterService implements ICharacterCallback {
    private ICharacterDomain ICharacterDomain;

    public CharacterService(ICharacterDomain ICharacterDomain) {
        this.ICharacterDomain = ICharacterDomain;
    }

    public void getCharacter(String id) {
        ICharacterDomain.getCharacter(this, id);
    }

    @Override
    public void onCharacterSuccess(CharacterModel character) {
        EventBus.getDefault().post(new CharacterSuccessEvent(character));
    }

    @Override
    public void onCharacterFailure(String error, String url) {
        EventBus.getDefault().post(new CharacterFailureEvent(error, url));
    }
}
