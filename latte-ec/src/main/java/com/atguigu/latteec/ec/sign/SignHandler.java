package com.atguigu.latteec.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.latte.app.AccountManager;
import com.atguigu.latteec.ec.database.DatabaseManager;
import com.atguigu.latteec.ec.database.UserProfile;

/**
 * Created by su on 2018/5/6.
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        //通过联网的数据 转换为json对象
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        //通过json对象新建一个用户实体
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //将用户实体存入数据库
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        if(signListener!=null){
            signListener.onSignInSuccess();//回调成功的方法
        }
    }


    public static void onSignUp(String response, ISignListener signListener) {
        //通过联网的数据 转换为json对象
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId")+2;
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        //通过json对象新建一个用户实体
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //将用户实体存入数据库
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        if(signListener!=null){
            signListener.onSignUpSuccess();//回调成功的方法
        }
    }

}
