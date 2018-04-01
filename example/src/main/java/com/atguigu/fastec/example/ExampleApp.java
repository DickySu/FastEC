package com.atguigu.fastec.example;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.atguigu.latte.app.Latte;
import com.atguigu.latteec.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by su on 2018/4/1.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("sujh","fdgdfg");
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("你的本地服务器地址")
                .configure();
        Toast.makeText(Latte.getApplicationContext(),"561116",Toast.LENGTH_SHORT).show();
    }

}
