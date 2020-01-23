package com.wholesale.wholesalefriends.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wholesale.wholesalefriends.module.util.LogUtil;

import java.util.ArrayList;

public class SharedPreference

{

    public static void setSharedPreferenceStringArrList(Context pContext, String pKey, String[] pData) {
        if(pContext ==null)return;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(pContext);
        SharedPreferences.Editor editor = prefs.edit();

        try{
            editor.putInt(pKey + "size", pData.length);
            editor.commit();

            for (int i = 0; i < pData.length; i++) {
                SharedPreferences prefs1 =
                        PreferenceManager.getDefaultSharedPreferences(pContext);
                SharedPreferences.Editor editor1 = prefs1.edit();
                editor1.putString(pKey + i, (pData[i]));
                editor1.commit();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static String[] getSharedPreferenceStringArrList(Context pContext, String pKey) {
        if(pContext ==null)return null;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(pContext);
        SharedPreferences.Editor editor = prefs.edit();

        int size = prefs.getInt(pKey + "size", 0);
        String[] list =new String[size];
        try{


            for (int i = 0; i < size; i++) {
                list[i] = PreferenceManager.getDefaultSharedPreferences(pContext).getString(pKey + i, "");
            }
        }catch (Throwable e){
            e.printStackTrace();
        }


        return list;
    }

    public static void setSharedPreferenceStringList(Context pContext, String pKey,  String idx) {
        if(pContext ==null)return;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(pContext);
        SharedPreferences.Editor editor = prefs.edit();

        try{

            ArrayList<String > pData = new ArrayList<>();
            int size = prefs.getInt(pKey + "size", 0);
            LogUtil.e(pKey + "size"+" : "+size);

            for(int i=0; i<size;i++){
                pData.add( prefs.getString(pKey + i, ""));
            }

            pData.add(idx);
            editor.putInt(pKey + "size", pData.size());
            editor.commit();
            for (int i = 0; i < pData.size(); i++) {
                SharedPreferences prefs1 =
                        PreferenceManager.getDefaultSharedPreferences(pContext);
                SharedPreferences.Editor editor1 = prefs1.edit();
                editor1.putString(pKey + i, (pData.get(i)));
                editor1.commit();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void setSharedPreferenceStringList(Context pContext, String pKey, ArrayList<String> pData) {
        if(pContext ==null)return;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(pContext);
        SharedPreferences.Editor editor = prefs.edit();

        try{
            editor.putInt(pKey + "size", pData.size());
            editor.commit();

            for (int i = 0; i < pData.size(); i++) {
                SharedPreferences prefs1 =
                        PreferenceManager.getDefaultSharedPreferences(pContext);
                SharedPreferences.Editor editor1 = prefs1.edit();
                editor1.putString(pKey + i, (pData.get(i)));
                editor1.commit();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getSharedPreferenceStringList(Context pContext, String pKey) {
        if(pContext ==null)return null;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(pContext);

        ArrayList<String> list = new ArrayList<>();
        try{
            int size = prefs.getInt(pKey + "size", 0);


            for (int i = 0; i < size; i++) {
                list.add( prefs.getString(pKey + i, ""));
            }
        }catch (Throwable e){
            e.printStackTrace();
        }


        return list;
    }

    /**
     * <pre>
     * String 데이터를 저장합니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @param value 값
     */
    public static void putSharedPreference
    (Context context, String key, String value)
    {
        if(context ==null)return;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();


        editor.putString(key, value);
        editor.commit();
    }

    /**
     * <pre>
     * Boolean 데이터를 저장합니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @param value 값
     */
    public static void putSharedPreference
    (Context context, String key, boolean value)
    {
        if(context == null)return;
        SharedPreferences prefs =

                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * <pre>
     * Integer 데이터를 저장합니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @param value 값
     */
    public static void putSharedPreference
    (Context context, String key, int value)
    {
        if(context == null)return;
        SharedPreferences prefs =

                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(key, value);
        editor.commit();
    }


    public static void putSharedPreference
            (Context context, String key, float value)
    {
        if(context == null)return;
        SharedPreferences prefs =

                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putFloat(key, value);
        editor.commit();
    }

    public static float getFloatSharedPreference
            (Context context, String key)
    {
        if(context == null)return 0;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getFloat(key,0);
    }


    public static void putSharedPreference
            (Context context, String key, long value)
    {
        if(context == null)return;
        SharedPreferences prefs =

                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLongSharedPreference
            (Context context, String key)
    {
        if(context == null)return 0;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getLong(key,0);
    }

    /**
     * <pre>
     * String 데이터를 읽어옵니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @return 읽어온 값, 값이 없을 경우 null이 반환된다.
     */
    public static String getSharedPreference
    (Context context, String key)
    {
        if(context ==null)return null;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getString(key, null);
    }

    /**
     * <pre>
     * String 데이터를 읽어옵니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @return 읽어온 값, 값이 없을 경우 null이 반환된다.
     */
    public static String getSharedPreference
    (Context context, String key, String defaultValue)
    {
        if(context ==null)return null;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getString(key, defaultValue);
    }
    /**
     * <pre>
     * Boolean 데이터를 읽어옵니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @return 읽어온 값, 값이 없을 경우 false가 반환된다.
     */
    public static boolean getBooleanSharedPreference
    (Context context, String key)
    {
        if(context ==null)return false;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getBoolean(key, false);
    }

    public static boolean getDefaultBooleanSharedPreference
            (Context context, String key)
    {
        if(context ==null)return false;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getBoolean(key, true);
    }

    /**
     * <pre>
     * Boolean 데이터를 읽어옵니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @return 읽어온 값, 값이 없을 경우 false가 반환된다.
     */
    public static boolean getBooleanSharedPreference
    (Context context, String key, boolean result)
    {
        if(context ==null)return false;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getBoolean(key, result);
    }

    /**
     * <pre>
     * Int 데이터를 읽어옵니다.
     * </pre>
     *
     * @param context 컨텍스트
     * @param key 키
     * @return 읽어온 값, 값이 없을 경우 0이 반환된다.
     */
    public static int getIntSharedPreference
    (Context context, String key)
    {
        if(context ==null)return 0;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getInt(key, 0);
    }
    public static int getDefaultIntSharedPreference
            (Context context, String key)
    {
        if(context ==null)return -1;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getInt(key, -1);
    }
    public static int getIntSharedPreference
            (Context context, String key, int defaultVal)
    {
        if(context ==null)return defaultVal;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getInt(key, defaultVal);
    }

    public static void removeSharedPreference
            (Context context, String key)
    {
        if(context ==null)return;
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();


        editor.remove(key);
        editor.commit();
    }
}