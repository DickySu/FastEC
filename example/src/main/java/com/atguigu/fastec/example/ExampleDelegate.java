package com.atguigu.fastec.example;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.net.RestClient;
import com.atguigu.latte.net.callback.IError;
import com.atguigu.latte.net.callback.ISuccess;


public class ExampleDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRestClient();
    }

    public void testRestClient(){

        RestClient.builder().url("https://news.baidu.com")
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),""+response,Toast.LENGTH_SHORT);
                    }
                })
                .build()
                .get();

    }
}
