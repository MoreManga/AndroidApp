package com.example.moremanga.moremanga.Models;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MangaItem {
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

    public void CreateMangaItem(Context context, LinearLayout row) throws IOException {
        LinearLayout mangaHolder = new LinearLayout(context);
        mangaHolder.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 430, 1.0f);
        mangaHolder.setLayoutParams(params);
        mangaHolder.setPadding(15, 15,15,15);

        LinearLayout mangaInnerHolder = new LinearLayout(context);
        mangaInnerHolder.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams innerparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        mangaInnerHolder.setLayoutParams(innerparams);

        TextView nameHolder = new TextView(context);
        nameHolder.setText(this.Name);
        nameHolder.setGravity(Gravity.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        p.leftMargin = 10; // in PX
        nameHolder.setLayoutParams(p);

        ImageView imageView = new ImageView(context);
        if (this.PosterImgUrl != null) {
            Picasso.with(context).load(this.PosterImgUrl).into(imageView);
        }

        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,350);
        imageView.setLayoutParams(imageParams);
        mangaInnerHolder.addView(imageView);

        mangaHolder.addView(mangaInnerHolder);
        mangaInnerHolder.addView(nameHolder);

        row.addView(mangaHolder);
    }
}
