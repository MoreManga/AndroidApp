package com.example.moremanga.moremanga.models;

public class MangaItem {
    public int Id;
    public String Name;
    public String PosterImgUrl;

    public MangaItem (String name) {
        Name = name;
        PosterImgUrl = null;
    }

    public MangaItem (String name, String posterImgUrl) {
        Name = name;
        PosterImgUrl = posterImgUrl;
    }
}
