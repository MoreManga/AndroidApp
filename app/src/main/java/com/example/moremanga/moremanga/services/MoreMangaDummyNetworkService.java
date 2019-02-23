package com.example.moremanga.moremanga.services;

import com.example.moremanga.moremanga.models.MangaItem;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MoreMangaDummyNetworkService implements IMoreMangaNetworkService {
    private List<MangaItem> listOfAllManga;

    public MoreMangaDummyNetworkService() {
        initializeDummyData();
    }

    private void initializeDummyData() {
        listOfAllManga = new ArrayList<MangaItem>();

        listOfAllManga.add(new MangaItem("One Punch Man", "http://static.readmanga.me/uploads/pics/01/04/997.jpg"));
        listOfAllManga.add(new MangaItem("Naruto", "http://static.readmanga.me/uploads/pics/00/13/990.jpg" ));
        listOfAllManga.add(new MangaItem("Berserk", "http://static.mintmanga.com/uploads/pics/00/56/386.jpg"));
        listOfAllManga.add(new MangaItem("Ganz", "http://img2.desu.me/manga/rus/gantz/vol36_ch372/gantz_vol36_ch372_p001.jpg"));
        listOfAllManga.add(new MangaItem("Attack on Titan", "http://static.mintmanga.com/uploads/pics/00/54/106.jpg"));
        listOfAllManga.add(new MangaItem("One peace", "http://static.readmanga.me/uploads/pics/00/76/197.jpg"));
        listOfAllManga.add(new MangaItem("Tales of Demons and Gods", "http://static.readmanga.me/uploads/pics/00/92/182.jpg"));
        listOfAllManga.add(new MangaItem("Bleach", "http://static.readmanga.me/uploads/pics/00/77/317.jpg"));
        listOfAllManga.add(new MangaItem("Battle Through the Heavens", "http://static.readmanga.me/uploads/pics/00/97/616_o.jpg"));
        listOfAllManga.add(new MangaItem("Nice to Meet You, Kami-sama", "http://static.readmanga.me/uploads/pics/00/98/179_o.jpg"));
        listOfAllManga.add(new MangaItem("The Breaker: New waves", "http://static.readmanga.me/uploads/pics/00/08/030.jpg"));
        listOfAllManga.add(new MangaItem("Noblesse", "http://static.readmanga.me/uploads/pics/00/80/778.jpg"));
        listOfAllManga.add(new MangaItem("Fairy tail", "http://static.readmanga.me/uploads/pics/01/11/125.jpg"));
        listOfAllManga.add(new MangaItem("Dororo", "http://static.readmanga.me/uploads/pics/01/07/739.jpg"));
        listOfAllManga.add(new MangaItem("Goblin Slayer", "http://static.mintmanga.com/uploads/pics/00/62/863_o.jpg"));
        listOfAllManga.add(new MangaItem("Tower of God", "http://static.readmanga.me/uploads/pics/00/99/917.jpg"));

        for (int i = 0 ; i < 100; i++) {
            listOfAllManga.add(new MangaItem("No name manga " + i, "https://www.freeiconspng.com/uploads/no-image-icon-6.png"));
        }
    }

    public List<MangaItem> GetAllManga(int _page, int _perPage) {
        if(_perPage > 50) {
            _perPage = 50;
        }

        List<List<MangaItem>> pages = Lists.partition(listOfAllManga, _perPage);

        _page -= 1;
        if (_page > pages.size()) {
            _page = pages.size() - 1;
        }

        if (_page < 0) {
            _page = 0;
        }

        return pages.get(_page);
    }
}
