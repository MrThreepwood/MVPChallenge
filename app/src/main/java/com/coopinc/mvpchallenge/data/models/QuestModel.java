package com.coopinc.mvpchallenge.data.models;

public class QuestModel {
    private String id;
    private String name;
    private String description;
    private String image;
    private CharacterModel questGiver;

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

    public CharacterModel getQuestGiver() {
        return questGiver;
    }

}
