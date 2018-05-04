package com.atguigu.fastec.example;

import com.atguigu.latte.activities.ProxyActivity;
import com.atguigu.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {
//public class MainActivity extends Activity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
}
