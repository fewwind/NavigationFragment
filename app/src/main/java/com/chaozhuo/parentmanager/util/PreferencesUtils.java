package com.chaozhuo.parentmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class PreferencesUtils {

    private PreferencesUtils() {
        throw new AssertionError();
    }


    /**
     * put string preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putString(Context context, String key, String value) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }


    public static boolean removeString(Context context, String key) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        return editor.commit();
    }


    /**
     * get string preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     * @see #getString(Context, String, String)
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }


    /**
     * get string preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     */
    public static String getString(Context context, String key,
                                   String defaultValue) {
        final SharedPreferences settings = getSp(context);
        return settings.getString(key, defaultValue);
    }


    public static boolean contains(Context context, String key) {
        final SharedPreferences settings = getSp(context);
        return settings.contains(key);
    }


    public static boolean putStringSet(Context context, String key,
                                       Set<String> value) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }


    public static Set<String> getStringSet(Context context, String key) {
        final SharedPreferences settings = getSp(context);
        return settings.getStringSet(key, new HashSet<String>());
    }


    /**
     * put int preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putInt(Context context, String key, int value) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }


    /**
     * get int preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }


    /**
     * get int preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     */
    public static int getInt(Context context, String key, int defaultValue) {
        final SharedPreferences settings = getSp(context);
        return settings.getInt(key, defaultValue);
    }


    /**
     * put long preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putLong(Context context, String key, long value) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }


    /**
     * get long preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }


    /**
     * get long preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     */
    public static long getLong(Context context, String key, long defaultValue) {
        final SharedPreferences settings = getSp(context);
        return settings.getLong(key, defaultValue);
    }


    /**
     * put float preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putFloat(Context context, String key, float value) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }


    /**
     * get float preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }


    /**
     * get float preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        final SharedPreferences settings = getSp(context);
        return settings.getFloat(key, defaultValue);
    }


    /**
     * put boolean preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        final SharedPreferences settings = getSp(context);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }


    /**
     * get boolean preferences, default is false
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }


    /**
     * get boolean preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defaultValue) {
        final SharedPreferences settings = getSp(context);
        return settings.getBoolean(key, defaultValue);
    }


    public static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences("tv_sp", Context.MODE_PRIVATE);
    }

}
