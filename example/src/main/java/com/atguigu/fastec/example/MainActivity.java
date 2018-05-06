package com.atguigu.fastec.example;

import com.atguigu.latte.activities.ProxyActivity;
import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latteec.ec.launcher.LauncherDelegate;

public class MainActivity extends ProxyActivity {
//public class MainActivity extends Activity {

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
        return new LauncherDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
}
