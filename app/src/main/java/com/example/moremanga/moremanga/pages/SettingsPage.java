package com.example.moremanga.moremanga.pages;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moremanga.moremanga.R;
import com.example.moremanga.moremanga.models.SettingItem;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SettingsPage extends Fragment {
    public Map<String, SettingItem> SettingsList;
    private String prefName = "appMainSetups";
    private View view;
    private Context context;

    public SettingsPage() {
        SettingsList = new ArrayMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_page, container, false);
        context = view.getContext();

        AddSetting("TestStr", "String");
        AddSetting("TestBool", "Boolean");
        AddSetting("TestInt", "Int");

        return view;
    }

    public void AddSetting(String name, String type) {
        SettingItem settingItem = new SettingItem(
                name,
                type,
                GetSharedValue(name, type));

        SettingsList.put("setting_" + name, settingItem);
    }

    public String GetSharedValue(String key, String type) {
        SharedPreferences preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);
        String value = preferences.getString(key, "");

        if (value == "" && type == "Boolean") {
            value = "false";
        }
        else if (value == "" && type == "Int") {
            value = "0";
        }

        return value;
    }

    public void SaveChanges() {
        SharedPreferences preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        for (Map.Entry<String, SettingItem> entry : SettingsList.entrySet())
        {
            edit.putString(entry.getValue().Name, entry.getValue().Value);
        }

        edit.commit();
    }
}