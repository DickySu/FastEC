package com.atguigu.latteec.ec.sign;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.net.RestClient;
import com.atguigu.latte.net.callback.IError;
import com.atguigu.latte.net.callback.IFailure;
import com.atguigu.latte.net.callback.ISuccess;
import com.atguigu.latte.wechat.LatteWeChat;
import com.atguigu.latte.wechat.callbacks.IWeChatSignInCallback;
import com.atguigu.latteec.ec.R;
import com.atguigu.latteec.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() { //登录按钮
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.212.2:80/chap13/user.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.i("USER_PROFILE", response);
                            Toast.makeText(getContext(),"登录",Toast.LENGTH_SHORT).show();
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Log.i("USER_PROFILE", "fail");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Log.i("USER_PROFILE", "error");
                        }
                    })
                    .build()
                    .post();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {  //微信登录按钮
        Toast.makeText(getContext(),"微信登录",Toast.LENGTH_SHORT).show();
        LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();//微信登录调用
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() { //跳转到注册界面
        getSupportDelegate().start(new SignUpDelegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    //数据校验
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

}
