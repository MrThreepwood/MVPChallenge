package com.coopinc.mvpchallenge.data.domain.character;

import com.coopinc.mvpchallenge.data.service.character.ICharacterCallback;

/**
 * Created by joshuaswoyer on 5/18/16.
 */
public interface ICharacterDomain {
    void getCharacter(ICharacterCallback service, String id);
}
