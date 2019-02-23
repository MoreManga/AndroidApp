package com.example.moremanga.moremanga.services;

import com.example.moremanga.moremanga.models.MangaItem;

import java.util.List;

public class MoreMangaRealNetworkService implements IMoreMangaNetworkService {
    private NetworkService networkService;

    public MoreMangaRealNetworkService(NetworkService _networkService) {
        networkService = _networkService;
    }

    public List<MangaItem> GetAllManga(int _page, int _perPage) {
        return null;
    }
}
