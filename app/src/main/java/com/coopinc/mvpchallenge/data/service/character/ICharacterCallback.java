package com.coopinc.mvpchallenge.data.service.character;

import com.coopinc.mvpchallenge.data.models.CharacterModel;

public interface ICharacterCallback {
    void onCharacterSuccess(CharacterModel character);

    void onCharacterFailure(String error, String url);
}
