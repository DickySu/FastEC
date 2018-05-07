package com.atguigu.fastec.example;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.atguigu.latte.app.Latte;
import com.atguigu.latte.net.interceptors.DebugInterceptor;
import com.atguigu.latteec.ec.database.DatabaseManager;
import com.atguigu.latteec.ec.icon.FontEcModule;
import com.facebook.stetho.Stetho;
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
                .withInterceptor(new DebugInterceptor("index",R.raw.test)) //拦截到这个单词就返回后面这个数据
                .configure();//一定要这个方法将CONFIG_READY设置为true
        //Iconify.with(new FontAwesomeModule());
        Toast.makeText(Latte.getApplicationContext(),"123",Toast.LENGTH_SHORT).show();
        DatabaseManager.getInstance().init(this);//数据库创建
        initStetho();//方便数据库的查看利用chrome
    }

    private void initStetho() {  //方便数据库的查看利用chrome
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
