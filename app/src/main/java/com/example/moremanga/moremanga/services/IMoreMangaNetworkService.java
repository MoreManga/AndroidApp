package com.example.moremanga.moremanga.services;

import com.example.moremanga.moremanga.models.MangaItem;

import java.util.List;

public interface IMoreMangaNetworkService {
    List<MangaItem> GetAllManga(int _page, int _perPage);
}
