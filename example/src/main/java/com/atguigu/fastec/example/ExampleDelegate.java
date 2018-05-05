package com.atguigu.fastec.example;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.net.RestClient;
import com.atguigu.latte.net.RestCreator;
import com.atguigu.latte.net.callback.IError;
import com.atguigu.latte.net.callback.IFailure;
import com.atguigu.latte.net.callback.ISuccess;
import com.atguigu.latte.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
        testRxRestClient2();
//        testRxRestClient();
//        testRestClient();
    }

    public void testRestClient(){

        RestClient.builder()
                .url("http://192.168.212.2:80/chap13/index.jsp")
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
                        Log.i("sujh_net","success----"+response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i("sujh_net","onFailure");
                    }
                })
                .loader(getContext())  //没有传样式就用默认的加载对话框
                .build()
                .get();

    }

    public void testRxRestClient(){
        String url="http://192.168.212.2:80/chap13/index.jsp";
        WeakHashMap<String,Object> params =new WeakHashMap<>();
        final Observable<String> observable = RestCreator.getRxRestService().get(url,params);
        observable.subscribeOn(Schedulers.io()) //执行联网在IO线程
                .observeOn(AndroidSchedulers.mainThread())  //处理数据在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(),"s:"+s,Toast.LENGTH_SHORT).show();
                        Log.i("sujh_Rx",s);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void testRxRestClient2(){
        RxRestClient.builder().url("http://192.168.212.2:80/chap13/index.jsp").build()
                .get()
                .subscribeOn(Schedulers.io()) //执行联网在IO线程
                .observeOn(AndroidSchedulers.mainThread())  //处理数据在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(),"s:"+s,Toast.LENGTH_SHORT).show();
                        Log.i("sujh_Rx",s);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
