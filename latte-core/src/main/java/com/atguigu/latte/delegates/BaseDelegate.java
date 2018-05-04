package com.atguigu.latte.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public abstract class BaseDelegate extends SwipeBackFragment{

    public abstract Object setLayout();//给子类去实现

    private Unbinder mUnbinder;
    //抽象方法给子类去实现
    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=null;
        Log.i("sujh","onCreateView");
        if(setLayout() instanceof Integer){ //xml布局的话
            rootView=inflater.inflate((Integer) setLayout(),container,false);
        }else if(setLayout() instanceof  View){  //view对象的话
            rootView = (View) setLayout();
        }else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }

        if(rootView != null){
            Log.i("sujh","rootView != null");
            mUnbinder = ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState, rootView);//子类要实现此方法
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
