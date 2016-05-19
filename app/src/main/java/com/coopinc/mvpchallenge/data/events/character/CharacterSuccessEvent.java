package com.coopinc.mvpchallenge.data.events.character;

import com.coopinc.mvpchallenge.data.models.CharacterModel;

public class CharacterSuccessEvent {
    private CharacterModel characterModel;

    public CharacterSuccessEvent(CharacterModel characterModel) {
        this.characterModel = characterModel;
    }

    public CharacterModel getCharacterModel() {
        return characterModel;
    }
}
