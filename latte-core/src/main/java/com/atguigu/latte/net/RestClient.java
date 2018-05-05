package com.atguigu.latte.net;

import android.content.Context;

import com.atguigu.latte.net.callback.IError;
import com.atguigu.latte.net.callback.IFailure;
import com.atguigu.latte.net.callback.IRequest;
import com.atguigu.latte.net.callback.ISuccess;
import com.atguigu.latte.net.callback.RequestCallbacks;
import com.atguigu.latte.net.download.DownloadHandler;
import com.atguigu.latte.ui.loader.LatteLoader;
import com.atguigu.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public final class RestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL; //请求的拼接起来的网址
    private final IRequest REQUEST;//请求开始跟结束的回调接口
    private final ISuccess SUCCESS; //请求成功的回调接口
    private final IFailure FAILURE;//请求失败的回调接口
    private final IError ERROR;//请求错误的回调接口
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final RequestBody BODY;//请求体
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    RestClient(String url, Map<String, Object> params,
               String downloadDir, String extension,
               String name, IRequest request,
               ISuccess success, IFailure failure, IError error,
               RequestBody body, File file, Context context,
                       LoaderStyle loaderStyle)
    {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        //得到retrofit对象
        final RestService service = RestCreator.getRestService();
        Call<String> call = null; //回调对象

        if (REQUEST != null) { //如果有加载框就可以显示了
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY); //post原始数据
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());//子线程
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() { //暴露给外部使用的
        if (BODY == null) {   //发送普通post
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) { //发送带body的post，参数必须为空
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);//发送带body的post
        }
    }

    public final void put() {//暴露给外部使用的
        if (BODY == null) { //发送普通put
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {  //发送带body的put，参数必须为空
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);//发送带body的put
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload(); //调用方法去下载
    }

}