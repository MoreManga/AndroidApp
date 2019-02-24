package com.example.moremanga.moremanga.pages;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moremanga.moremanga.R;
import com.example.moremanga.moremanga.adapters.MangaItemAdapter;
import com.example.moremanga.moremanga.databinding.MainActivityBinding;
import com.example.moremanga.moremanga.models.MangaItem;
import com.example.moremanga.moremanga.services.IMoreMangaNetworkService;
import com.example.moremanga.moremanga.services.MoreMangaDummyNetworkService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Fragment {
    private int IN_ONE_ROW = 3;

    private MainActivityBinding binding;
    private IMoreMangaNetworkService moreMangaNetworkService;

    private List<MangaItem> loadedManga;

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public  MainPage() {
        moreMangaNetworkService = new MoreMangaDummyNetworkService();
        loadedManga = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        initialize(view);
        return view;
    }

    public void initialize(View view) {
        loadedManga.addAll(moreMangaNetworkService.GetAllManga(1, 100));

        RecyclerView rcView = view.findViewById(R.id.recycler_view);
        MangaItemAdapter adapter = new MangaItemAdapter();
        adapter.setData(loadedManga);
        rcView.setAdapter(adapter);

        rcView.setLayoutManager(new GridLayoutManager(view.getContext(), IN_ONE_ROW));
        rcView.setHasFixedSize(true);
    }
}
