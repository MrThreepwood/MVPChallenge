package com.coopinc.mvpchallenge.data.models;

import java.io.Serializable;

public class QuestModel implements Serializable {
    private String id;
    private String name;
    private String description;
    private String image;
    private CharacterModel giver;

    public QuestModel(String description, String id, String image, String name, CharacterModel giver) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.name = name;
        this.giver = giver;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public CharacterModel getGiver() {
        return giver;
    }

    public void setGiver(CharacterModel giver) {
        this.giver = giver;
    }

}
