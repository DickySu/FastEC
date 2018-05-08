package com.atguigu.latteec.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.atguigu.latte.delegates.bottom.BottomItemDelegate;
import com.atguigu.latteec.ec.R;

/**
 * Created by su on 2018/5/8.
 */
public class SortDelegate extends BottomItemDelegate { //内容fragment


    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }


}