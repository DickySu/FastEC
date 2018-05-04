package com.atguigu.latte.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;


final class LoaderCreator {
    //用来缓存 省得每次都反射去创建影响性能
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {  //如果缓存没有就新建
            final Indicator indicator = getIndicator(type); //新建
            LOADING_MAP.put(type, indicator); //存入缓存map
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();//用来拼接包名
        if (!name.contains(".")) {
            //得到包名
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)  //拼接得到类名
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name); //拼接得到类名
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();  //反射得到对象
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
