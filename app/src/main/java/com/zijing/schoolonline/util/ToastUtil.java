package com.zijing.schoolonline.util;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Application application;

    public ToastUtil() {
    }

    public static void init(Application application) {
        ToastUtil.application = application;
    }

    public static void s(String msg) {
        if (application == null) return;
        s(application, msg);
    }

    public static void l(String msg) {
        if (application == null) return;
        l(application, msg);
    }

    public static void s(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void l(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
