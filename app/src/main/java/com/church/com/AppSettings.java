package com.church.com;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppSettings {

    public static boolean isLogin(Context paramContext) {
        return getPrefs(paramContext).getBoolean("isLogin", false);
    }
  public static boolean isAutoStartEnable(Context paramContext) {
        return getPrefs(paramContext).getBoolean("isEnable", false);
    }

    public static boolean isSetProfile(Context paramContext) {
        return getPrefs(paramContext).getBoolean("isSetProfile", false);
    }

    public static void setLogin(Context paramContext, boolean islogin) {
        getPrefs(paramContext).edit().putBoolean("isLogin", islogin).commit();
    }
  public static void setAutoStartEnable(Context paramContext, boolean islogin) {
        getPrefs(paramContext).edit().putBoolean("isEnable", islogin).commit();
    }

    public static void setProfileOnce(Context paramContext, boolean isSetProfile) {
        getPrefs(paramContext).edit().putBoolean("isSetProfile", isSetProfile).commit();
    }

    public static String getPassword(Context paramContext) {
        return getPrefs(paramContext).getString("password", null);
    }

    public static void setPassword(Context paramContext, String paramString) {
        getPrefs(paramContext).edit().putString("password", paramString).commit();
    }

    private static SharedPreferences getPrefs(Context paramContext) {
        return PreferenceManager.getDefaultSharedPreferences(paramContext);
    }

    public static String getUserId(Context context) {
        return getPrefs(context).getString("userId",null);
    }

    public static void setUserId(Context context, String paramString) {
        getPrefs(context).edit().putString("userId", paramString).commit();
    }

    public static int getShopId(Context context) {
        return getPrefs(context).getInt("shopId", 0);
    }

    public static void setShopId(Context context, int paramString) {
        getPrefs(context).edit().putInt("shopId", paramString).commit();
    }

    public static String getUserName(Context context) {
        return getPrefs(context).getString("username", null);
    }

    public static void setUserName(Context context, String paramString) {
        getPrefs(context).edit().putString("username", paramString).commit();
    }

    public static String getUserToken(Context context) {
        return getPrefs(context).getString("usertoken", null);
    }

    public static void setUserToken(Context context, String paramString) {
        getPrefs(context).edit().putString("usertoken", paramString).commit();
    }



   /* public static void saveArrayList(Context context, ArrayList<UserListData> list, String key) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        getPrefs(context).edit().putString(key, json).commit();

    }

    public static ArrayList<UserListEntity> getUserListEntityArrayList(Context context, String key) {
        Gson gson = new Gson();
        String json = getPrefs(context).getString(key, null);
        Type type = new TypeToken<ArrayList<UserListEntity>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static ArrayList<UserListData> getArrayList(Context context, String key) {
        Gson gson = new Gson();
        String json = getPrefs(context).getString(key, null);
        Type type = new TypeToken<ArrayList<UserListData>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
    */

}
