package com.atguigu.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.atguigu.latte.R;
import com.atguigu.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;


public abstract class ProxyActivity extends SupportActivity{

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);//准备一个帧布局来放
        container.setId(R.id.delegate_container);//帧布局给个ID 这里不能直接写数字
        setContentView(container);//给activity设置布局
        if (savedInstanceState == null) { //首次加载
            loadRootFragment(R.id.delegate_container, setRootDelegate());//用fragment替换掉帧布局
        }
    }

    @Override
    protected void onDestroy() {   //单activity退出就垃圾回收
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

