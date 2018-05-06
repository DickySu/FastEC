package com.atguigu.latte.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.atguigu.latte.app.Latte;

import com.alibaba.fastjson.JSONObject;


public final class LattePreference {

    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());

    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference() { //得到包名_preferences.xml
        return PREFERENCES;
    }

    public static void setAppProfile(String val) { //设置APP_PREFERENCES_KEY
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {   //得到APP_PREFERENCES_KEY
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() { //得到APP_PREFERENCES_KEY并转换为json
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {  //移除APP_PREFERENCES_KEY
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {  //清除所有
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag) { //存放boolean
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {  //读取boolean
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {  //存放string
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) { //读取string
        return getAppPreference().getString(key, "");
    }

}
