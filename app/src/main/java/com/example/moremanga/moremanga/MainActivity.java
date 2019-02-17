package com.example.moremanga.moremanga;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moremanga.moremanga.Models.MangaItem;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<MangaItem> ListOfManga;
    private int IN_ONE_ROW = 3;

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

        try {
            LoadManga();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void LoadManga() throws IOException {
        LinearLayout layout = (LinearLayout) findViewById(R.id.MainList);
        LinearLayout row = GetRow();
        int count = 0;

        for (int i = 0 ; i < ListOfManga.size(); i++) {
            count++;
            CreateMangaItem(row, ListOfManga.get(i));

            if ((i + 1) % IN_ONE_ROW == 0 && i != 0) {
                count = 0;
                layout.addView(row);

                row = GetRow();
            }
        }

        if (count > 0) {
            layout.addView(row);
        }
    }

    private  LinearLayout GetRow() {
        LinearLayout row = new LinearLayout(getApplicationContext());
        row.setOrientation(LinearLayout.HORIZONTAL);

        return row;
    }

    private void CreateMangaItem(LinearLayout row, MangaItem item) throws IOException {
        LinearLayout mangaHolder = new LinearLayout(getApplicationContext());
        mangaHolder.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 430, 1.0f);
        mangaHolder.setLayoutParams(params);
        mangaHolder.setPadding(15, 15,15,15);

        LinearLayout mangaInnerHolder = new LinearLayout(getApplicationContext());
        mangaInnerHolder.setOrientation(LinearLayout.VERTICAL);
        LayoutParams innerparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        mangaInnerHolder.setLayoutParams(innerparams);

        TextView nameHolder = new TextView(getApplicationContext());
        nameHolder.setText(item.Name);
        nameHolder.setGravity(Gravity.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        p.leftMargin = 10; // in PX
        nameHolder.setLayoutParams(p);

        ImageView imageView = new ImageView(getApplicationContext());
        if (item.PosterImgUrl != null) {
            Picasso.with(getApplicationContext()).load(item.PosterImgUrl).into(imageView);
        }

        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,350);
        imageView.setLayoutParams(imageParams);
        mangaInnerHolder.addView(imageView);

        mangaHolder.addView(mangaInnerHolder);
        mangaInnerHolder.addView(nameHolder);

        row.addView(mangaHolder);
    }
}
