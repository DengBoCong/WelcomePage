package com.example.power.welcomepage.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Power on 2018/11/2.
 */

/*我们使用SharedPreferences的数据储存方式进行存取，所以我们创建这个工具类进行封装操作，较为方便可行。
* 在这里我以我们要使用到的getBoolean和setBoolean进行举例讲解，其余类型原理一致*/

public class SharedPreferencesUtil {
    private static final String FILE_NAME = "welcomePage";
    public static final String FIRST_OPEN = "first_open";

    public static Boolean getBoolean(Context context, String strKey,
                                     Boolean strDefault){
        /*使用Context.MODE_PRIVATE模式创建shared preference文件，意味着只用本应用程序
        * 能够使用，还包括MODE_WORLD_READABLE或者MODE_WORLD_WRITEABLE 模式的，这两种
        * 模式下，其他任何app均可通过文件名访问该文件。*/
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE
        );
        /*在文件中获取一个Boolean类型值，strDefault是默认值，可以省略，其意义是查找的
        key不存在时函数的返回值。*/
        Boolean result = sharedPreferences.getBoolean(strKey, strDefault);

        return result;
    }

    public static Boolean getBoolean(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, false);
        return result;
    }

    public static void setBoolean(Context context, String strKey,
                                  Boolean strData){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE
        );
        /*往shared preference文件中写入值时，我们需要用到SharedPreferences.Editor*/
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.commit();
    }

    public static String getString(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        String result = setPreferences.getString(strKey, "");
        return result;
    }

    public static String getString(Context context, String strKey,
                                   String strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        String result = setPreferences.getString(strKey, strDefault);
        return result;
    }

    public static void setString(Context context, String strKey, String strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putString(strKey, strData);
        editor.commit();
    }

    public static int getInt(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, -1);
        return result;
    }

    public static int getInt(Context context, String strKey, int strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, strDefault);
        return result;
    }

    public static void setInt(Context context, String strKey, int strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putInt(strKey, strData);
        editor.commit();
    }

    public static long getLong(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        long result = setPreferences.getLong(strKey, -1);
        return result;
    }

    public static long getLong(Context context, String strKey, long strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        long result = setPreferences.getLong(strKey, strDefault);
        return result;
    }

    public static void setLong(Context context, String strKey, long strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putLong(strKey, strData);
        editor.commit();
    }
}
