package com.coopinc.mvpchallenge.data.models;

public class KingdomBriefModel {
    String id;
    String name;
    String image;

    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getImage(){
        return image;
    }
    public KingdomBriefModel(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
