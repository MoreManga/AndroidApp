package com.example.moremanga.moremanga;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.moremanga.moremanga.pages.MainPage;
import com.example.moremanga.moremanga.pages.SettingsPage;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private boolean lastMockServerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToMain(true);

        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    drawerLayout.closeDrawers();

                    switch (menuItem.getItemId()) {
                        case R.id.nav_main:
                            goToMain(lastMockServerState);
                            break;
                        case R.id.nav_settings:
                            goToLayout(new SettingsPage());
                            break;
                    }

                    return true;
                }
            });

        Switch sw = ((Switch)((LinearLayout)navigationView.getMenu().findItem(R.id.mockServer_switch).getActionView()).getChildAt(0));
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goToMain(isChecked);
            }
        });
    }

    private void goToMain(boolean state) {
        lastMockServerState = state;

        MainPage fr = new MainPage();
        Bundle bundl = new Bundle();
        bundl.putBoolean("mockServerState", state);
        fr.setArguments(bundl);

        goToLayout(fr);
    }

    private void goToLayout(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.dynamic_fragment_frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
