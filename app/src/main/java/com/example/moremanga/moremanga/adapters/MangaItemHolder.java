package com.example.moremanga.moremanga.adapters;

import android.support.v7.widget.RecyclerView;

import com.example.moremanga.moremanga.models.MangaItem;
import com.example.moremanga.moremanga.databinding.MangaItemSmallBinding;

public class MangaItemHolder extends RecyclerView.ViewHolder {
    MangaItemSmallBinding binding;

    public MangaItemHolder(MangaItemSmallBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(MangaItem mangaItem) {
        binding.setMangaitem(mangaItem);
        binding.executePendingBindings();
    }

}