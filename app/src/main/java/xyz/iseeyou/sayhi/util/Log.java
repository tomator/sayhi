package xyz.iseeyou.sayhi.util;


import xyz.iseeyou.sayhi.App;

/**
 * Created by bici on 15/1/27.
 */
public class Log {

    private static boolean isDebugMode(){
        return App.getInstance().isDebugMode();
    }

    public static void d(int resId){
        if (isDebugMode()) {
            android.util.Log.d(Constants.TAG, App.getInstance().getString(resId));
        }
    }

    public static void d(String content){
        if (isDebugMode()) {
            android.util.Log.d(Constants.TAG, content);
        }
    }

    public static void v(String tag, String content) {
        if (isDebugMode()) {
            android.util.Log.v(tag, content);
        }
    }

    public static void d(String tag, String content) {
        if (isDebugMode()) {
            android.util.Log.d(tag, content);
        }
    }

    public static void w(String tag, String content) {
        if (isDebugMode()) {
            android.util.Log.w(tag, content);
        }
    }

    public static void i(String tag, String content) {
        if (isDebugMode()) {
            android.util.Log.i(tag, content);
        }
    }

    public static void e(String tag, String content) {
        if (isDebugMode()) {
            android.util.Log.e(tag, content);
        }
    }
}
