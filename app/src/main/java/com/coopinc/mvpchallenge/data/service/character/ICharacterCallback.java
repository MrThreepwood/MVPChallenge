package com.coopinc.mvpchallenge.data.service.character;

import com.coopinc.mvpchallenge.data.models.CharacterModel;

/**
 * Created by joshuaswoyer on 5/18/16.
 */
public interface ICharacterCallback {
    void onCharacterSuccess(CharacterModel character);

    void onCharacterFailure(String error);
}
