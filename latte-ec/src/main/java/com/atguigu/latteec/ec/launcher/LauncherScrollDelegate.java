package com.atguigu.latteec.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.ui.launcher.LauncherHolderCreator;
import com.atguigu.latte.ui.launcher.ScrollLauncherTag;
import com.atguigu.latte.util.storage.LattePreference;
import com.atguigu.latteec.ec.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by su on 2018/5/6.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null; //在这里的泛型对应数据的类型
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();//集合放图片


    private void initBanner() {
        INTEGERS.clear();//返回再进来就十张图了奇怪，先清空吧
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05); //放入五张图片
        Log.i("sujh_INTEGERS","INTEGERS--"+INTEGERS);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)  //图片集合
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})  //点的样式
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this) //点击事件
                .setCanLoop(false);//不循环
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }


    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            //第一次进入后 就保存sharepreference 以后就不再显示了
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录

        }
    }
}
