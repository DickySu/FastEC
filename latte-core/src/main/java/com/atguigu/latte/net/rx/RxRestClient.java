package com.atguigu.latte.net.rx;

import android.content.Context;

import com.atguigu.latte.net.HttpMethod;
import com.atguigu.latte.net.RestCreator;
import com.atguigu.latte.ui.loader.LatteLoader;
import com.atguigu.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public final class RxRestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL; //请求的拼接起来的网址
//    private final IRequest REQUEST;//请求开始跟结束的回调接口
//    private final ISuccess SUCCESS; //请求成功的回调接口
//    private final IFailure FAILURE;//请求失败的回调接口
//    private final IError ERROR;//请求错误的回调接口
//    private final String DOWNLOAD_DIR;
//    private final String EXTENSION;
//    private final String NAME;
    private final RequestBody BODY;//请求体
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    RxRestClient(String url, Map<String, Object> params,
//                 String downloadDir, String extension,
//                 String name, IRequest request,
//                 ISuccess success, IFailure failure, IError error,
                 RequestBody body, File file, Context context,
                 LoaderStyle loaderStyle)
    {
        this.URL = url;
        PARAMS.putAll(params);
//        this.DOWNLOAD_DIR = downloadDir;
//        this.EXTENSION = extension;
//        this.NAME = name;
//        this.REQUEST = request;
//        this.SUCCESS = success;
//        this.FAILURE = failure;
//        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }


    private Observable<String> request(HttpMethod method) {
        //得到retrofit对象
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null; //回调对象

//        if (REQUEST != null) { //如果有加载框就可以显示了
//            REQUEST.onRequestStart();
//        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY); //post原始数据
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }

        return observable;
//        if (observable != null) {
//            call.enqueue(getRequestCallback());//子线程
//        }
    }

//    private Callback<String> getRequestCallback() {
//        return new RequestCallbacks(
//                REQUEST,
//                SUCCESS,
//                FAILURE,
//                ERROR,
//                LOADER_STYLE
//        );
//    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() { //暴露给外部使用的
        if (BODY == null) {   //发送普通post
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) { //发送带body的post，参数必须为空
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);//发送带body的post
        }
    }

    public final Observable<String> put() {//暴露给外部使用的
        if (BODY == null) { //发送普通put
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {  //发送带body的put，参数必须为空
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);//发送带body的put
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }


    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable
                = RestCreator.getRxRestService().download(URL,PARAMS);

        return responseBodyObservable;
    }

}