package com.atguigu.latte.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import com.atguigu.latte.R;
import com.atguigu.latte.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8; //用来控制对话框位置
    private static final int LOADER_OFFSET_SCALE = 10;
    //缓存变量 用来存放对话框 省得再次新建
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    //默认的样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type) { //传入枚举比较好
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        //我们这个控件不直接负载在view上 而是弹出一个对话框去承载
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //创建布局
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        //设置为对话框的布局
        dialog.setContentView(avLoadingIndicatorView);
        //得到屏幕宽高
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) { //设置宽高 居中
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog); //对话框放入内存变量中缓存
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

}
