package com.strath.mydairyfarm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by oirere on 19/10/2018.
 */

public class SharedPref {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String SPREF = "shared_prefs";

    public static final String USER = "name";
    public static final String EMAIL = "email";

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SPREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public User getUser() {
        User user = new User();
        user.setEmail(sharedPreferences.getString(EMAIL, ""));
        user.setName(sharedPreferences.getString(USER, ""));
        return user;
    }

    public void setUser(User user) {
        editor.putString(USER, user.getName());
        editor.putString(EMAIL, user.getEmail());
        editor.apply();
    }

}
