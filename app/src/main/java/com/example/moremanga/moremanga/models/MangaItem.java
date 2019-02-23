package com.example.moremanga.moremanga.models;

import java.util.Date;
import java.util.List;

public class MangaItem {
    public int Id;
    public String Name;
    public String PosterImgUrl;
    public String Description;
    public String Author;
    public String PublishYear;
    public String Publisher;
    public String Tranlator;
    public String Status;
    public List<String> Genre;
    public int ChaptersCount;
    public Date LastUpdateDate;
    public Date CreatedUpdateDate;

    public MangaItem (String name) {
        Name = name;
        PosterImgUrl = null;
    }

    public MangaItem (String name, String posterImgUrl) {
        Name = name;
        PosterImgUrl = posterImgUrl;
    }
}
