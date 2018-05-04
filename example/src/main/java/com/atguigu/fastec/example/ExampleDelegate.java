package com.atguigu.fastec.example;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.net.RestClient;
import com.atguigu.latte.net.callback.IError;
import com.atguigu.latte.net.callback.IFailure;
import com.atguigu.latte.net.callback.ISuccess;


public class ExampleDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
//        return R.layout.activity_main;
        Log.i("sujh_net","setLayout");
        return R.layout.delegate_example;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        Log.i("sujh_net","testRestClient");
        testRestClient();
    }

    public void testRestClient(){

        RestClient.builder().url("http://news.baidu.com/")
//                .params("","")
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(),""+msg,Toast.LENGTH_SHORT).show();
                        Log.i("sujh_net",""+msg);
                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),""+response,Toast.LENGTH_SHORT).show();
                        Log.i("sujh_net",""+response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i("sujh_net","onFailure");
                    }
                })
                .build()
                .get();

    }
}
