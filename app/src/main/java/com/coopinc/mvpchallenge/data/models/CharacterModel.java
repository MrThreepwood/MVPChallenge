package com.coopinc.mvpchallenge.data.models;

import java.io.Serializable;

public class CharacterModel implements Serializable {
    private String id;
    private String name;
    private String image;
    private String profession;
    private String bio;
    private boolean isInError = false;

    public CharacterModel(String bio, String id, String image, String name, String profession) {
        this.bio = bio;
        this.id = id;
        this.image = image;
        this.name = name;
        this.profession = profession;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getProfession() {
        return profession;
    }

    public String getBio() {
        return bio;
    }

    public boolean isInError() {
        return isInError;
    }

    public void setBioError(String error) {
        this.isInError = true;
        bio = error;
    }

}
