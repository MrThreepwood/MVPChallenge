package com.coopinc.mvpchallenge.data.domain.character;

import com.coopinc.mvpchallenge.data.ChallengeApi;
import com.coopinc.mvpchallenge.data.models.CharacterModel;
import com.coopinc.mvpchallenge.data.service.character.ICharacterCallback;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CharacterDomain implements ICharacterDomain {
    private ChallengeApi api;

    public CharacterDomain(ChallengeApi api) {
        this.api = api;
    }

    @Override
    public void getCharacter(final ICharacterCallback service, String id) {
        api.getCharacter(id, new Callback<CharacterModel>() {
            @Override
            public void success(CharacterModel characterModel, Response response) {
                service.onCharacterSuccess(characterModel);
            }

            @Override
            public void failure(RetrofitError error) {
                service.onCharacterFailure(error.getMessage());
            }
        });
    }
}
