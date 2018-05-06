package com.atguigu.latteec.ec.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latteec.ec.R;
import com.atguigu.latteec.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by su on 2018/5/6.
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null; //用户名
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null; //邮箱
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null; //手机
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null; //密码
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null; //重复密码

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            Toast.makeText(getProxyActivity(),"验证通过",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    //校验输入格式
    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }
}
