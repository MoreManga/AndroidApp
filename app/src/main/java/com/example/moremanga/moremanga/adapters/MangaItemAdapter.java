package com.example.moremanga.moremanga.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moremanga.moremanga.R;
import com.example.moremanga.moremanga.models.MangaItem;
import com.example.moremanga.moremanga.databinding.MangaItemSmallBinding;

import java.util.LinkedList;
import java.util.List;

public class MangaItemAdapter extends RecyclerView.Adapter<MangaItemHolder> {

    private List<MangaItem> items = new LinkedList<>();

    public void setData(List<MangaItem> data) {
        items.clear();
        items.addAll(data);
    }

    @Override
    public MangaItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MangaItemSmallBinding binding = DataBindingUtil.inflate(inflater, R.layout.manga_item_small, parent, false);
        return new MangaItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaItemHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}