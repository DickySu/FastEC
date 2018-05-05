package com.atguigu.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.atguigu.latte.app.Latte;
import com.atguigu.latte.net.callback.IRequest;
import com.atguigu.latte.net.callback.ISuccess;
import com.atguigu.latte.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;


final class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST; //请求开始跟结束的回调方法
    private final ISuccess SUCCESS; //成功的回调接口

    SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0]; //四个参数依次传
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];

        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";//如果没传值就默认这个目录
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) { //使用工具类去返回file
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);//如果是APK就再自动安装
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
