package com.atguigu.latteec.ec.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import com.atguigu.latte.delegates.bottom.BottomItemDelegate;
import com.atguigu.latteec.ec.R;
import com.atguigu.latteec.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * Created by su on 2018/5/8.
 */
public class IndexDelegate extends BottomItemDelegate { //内容fragment

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }


}