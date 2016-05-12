package com.coopinc.mvpchallenge.data.models;

import java.util.List;

public class KingdomModel {
    private String id;
    private String name;
    private String image;
    private String climate;
    private String population;
    private List<QuestModel> quests;

    public KingdomModel(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getImage(){
        return image;
    }

    public String getClimate() {
        return climate;
    }

    public String getPopulation() {
        return population;
    }
}
