package com.atguigu.fastec.example;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.atguigu.latte.activities.ProxyActivity;
import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latteec.ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity {
//public class MainActivity extends Activity {

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
        return new SignUpDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
