package com.atguigu.latteec.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.ui.launcher.LauncherHolderCreator;
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
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05); //放入五张图片
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

    }
}
