package com.atguigu.fastec.example;

import com.atguigu.latte.activities.ProxyActivity;
import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latteec.ec.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {
//public class MainActivity extends Activity {

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
        return new LauncherScrollDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
}
