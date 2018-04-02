package com.atguigu.fastec.example;

import com.atguigu.latte.activities.ProxyActivity;
import com.atguigu.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }



}
