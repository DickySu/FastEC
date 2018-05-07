package com.atguigu.latte.wechat.templates;

import com.atguigu.latte.wechat.BaseWXEntryActivity;
import com.atguigu.latte.wechat.LatteWeChat;

/**
 * Created by su on 2018/5/7.
 */
public class WXEntryTemplate extends BaseWXEntryActivity {
//登录完后 回到这个界面

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);//不要有任何的动画效果
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}