package com.atguigu.latteec.ec.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.latte.app.Latte;
import com.atguigu.latte.net.RestClient;
import com.atguigu.latte.net.callback.ISuccess;

/**
 * Created by su on 2018/5/9.
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;//刷新控件

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout) {
        return new RefreshHandler(swipeRefreshLayout);
    }
    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false); //一秒后消失
            }
        }, 1000);
    }


    public void firstPage(String url) {
        RestClient.builder()  //加载第一页
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        final JSONObject object = JSON.parseObject(response);

                    }
                })
                .build()
                .get();
    }

}