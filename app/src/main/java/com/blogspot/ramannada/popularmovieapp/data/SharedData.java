package com.blogspot.ramannada.popularmovieapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by ramannada on 11/26/2017.
 */

public class SharedData {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SharedData sharedData;
    private static final String SHARED_DATA_NAME =
            "com.blogspot.ramannada.popularmovieapp.data.shareddata";
    private static final String LOGIN_STATUS = "login";

    public SharedData(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_DATA_NAME, 0);
        this.editor = sharedPreferences.edit();
    }
    public static void init(Context context) {
        sharedData = new SharedData(context);
    }

    public synchronized static SharedData getInstance() {
        return sharedData;
    }

    public void saveStatusLogin(Boolean b) {
        editor.putBoolean(LOGIN_STATUS, b);
        editor.commit();
    }
}
