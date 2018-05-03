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
        Latte.init(this) //得到配置类 且初始化上下文
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("https://news.baidu.com")
                .configure();//一定要这个方法将CONFIG_READY设置为true
        //Iconify.with(new FontAwesomeModule());
        Toast.makeText(Latte.getApplicationContext(),"123",Toast.LENGTH_SHORT).show();
    }

}
