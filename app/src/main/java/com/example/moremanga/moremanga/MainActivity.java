package com.example.moremanga.moremanga;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.moremanga.moremanga.adapters.MangaItemAdapter;
import com.example.moremanga.moremanga.databinding.MainActivityBinding;
import com.example.moremanga.moremanga.models.MangaItem;
import com.example.moremanga.moremanga.services.IMoreMangaNetworkService;
import com.example.moremanga.moremanga.services.MoreMangaDummyNetworkService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
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

    public MainActivity(){
        moreMangaNetworkService = new MoreMangaDummyNetworkService();
        loadedManga = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_launcher);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        loadedManga.addAll(moreMangaNetworkService.GetAllManga(1, 100));

        RecyclerView rcView = findViewById(R.id.recycler_view);
        MangaItemAdapter t = new MangaItemAdapter();
        t.setData(loadedManga);
        rcView.setLayoutManager(new GridLayoutManager(this, IN_ONE_ROW));
        rcView.setAdapter(t);
        rcView.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
