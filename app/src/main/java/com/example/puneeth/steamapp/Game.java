package com.example.puneeth.steamapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private String gameName, numberPlayer, image_url, description,score,supportedLanguages,website,releaseDate;
    private ArrayList<String> urls;

    public Game(){

    }

    public Game(String gameName, String numberPlayer, String image_url, String description,String score, String supportedLanguages, String website, String releaseDate,ArrayList<String> urls){
        this.gameName=gameName;
        this.numberPlayer=numberPlayer;
        this.image_url=image_url;
        this.description=description;
        this.score=score;
        this.supportedLanguages=supportedLanguages;
        this.website=website;
        this.releaseDate=releaseDate;
        this.urls=urls;
    }

    public String getGameName() {
        return gameName;
    }

    public String getNumberPlayer() {
        return numberPlayer;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getScore() {
        return score;
    }

    public String getSupportedLanguages() {
        return supportedLanguages;
    }

    public String getWebsite() {
        return website;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setNumberPlayer(String numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setSupportedLanguages(String supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }
}
