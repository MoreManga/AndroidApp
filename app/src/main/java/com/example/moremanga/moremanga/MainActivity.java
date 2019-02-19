package com.example.moremanga.moremanga;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.moremanga.moremanga.adapters.MangaItemAdapter;
import com.example.moremanga.moremanga.models.MangaItem;
import com.example.moremanga.moremanga.databinding.MainActivityBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<MangaItem> ListOfManga;
    private int IN_ONE_ROW = 3;

    private MainActivityBinding binding;
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public MainActivity(){
        ListOfManga = new ArrayList<MangaItem>();

        ListOfManga.add(new MangaItem("One Punch Man", "http://static.readmanga.me/uploads/pics/00/99/917_o.jpg"));
        ListOfManga.add(new MangaItem("Naruto", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Logo_diario_AS.svg/1200px-Logo_diario_AS.svg.png" ));
        ListOfManga.add(new MangaItem("Berserk", "https://www.heart.org/-/media/images/health-topics/congenital-heart-defects/50_1683_44a_asd.jpg?h=551&w=572&la=en&hash=60A4E57B316F13921A743143171BD2EFC7912F93"));
        ListOfManga.add(new MangaItem("Ganz"));
        ListOfManga.add(new MangaItem("Ya tvoi rot shatal"));
        ListOfManga.add(new MangaItem("Omaewa mo shindeiru"));
        ListOfManga.add(new MangaItem("Nani?"));
        ListOfManga.add(new MangaItem("Fate"));
        ListOfManga.add(new MangaItem("Greate teatcher Onidzuka"));
        ListOfManga.add(new MangaItem("Porn Hub"));
        ListOfManga.add(new MangaItem("Boku no piko"));
        ListOfManga.add(new MangaItem("Boku no hir academy"));
        ListOfManga.add(new MangaItem("Victim Girls"));

        for (int i = 0 ; i < 200; i++) {
            ListOfManga.add(new MangaItem("name " + i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rc = findViewById(R.id.recycler_view);
        MangaItemAdapter t = new MangaItemAdapter();
        t.setData(ListOfManga);

        rc.setLayoutManager(new GridLayoutManager(this, IN_ONE_ROW));
        rc.setAdapter(t);
        rc.setHasFixedSize(true);
    }
}
