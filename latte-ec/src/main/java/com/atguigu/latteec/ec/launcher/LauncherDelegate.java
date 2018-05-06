package com.atguigu.latteec.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.atguigu.latte.delegates.LatteDelegate;
import com.atguigu.latte.ui.launcher.ScrollLauncherTag;
import com.atguigu.latte.util.storage.LattePreference;
import com.atguigu.latte.util.timer.BaseTimerTask;
import com.atguigu.latte.util.timer.ITimerListener;
import com.atguigu.latteec.ec.R;
import com.atguigu.latteec.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by su on 2018/5/6.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {  //绑定点击事件  点击后直接跳过倒计时
        if (mTimer != null) {  //点击后取消定时器
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private Timer mTimer = null;
    private int mCount = 5; //倒数五秒

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this); //this已经实现接口ITimerListener
        mTimer.schedule(task, 0, 1000); //每隔一秒执行
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {  //五秒后取消掉定时器
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            //第一次打开就显示banner的引导页
//            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
            //以下这行自己改写的 可能有bug 因为上一行会导致在banner引导界面可以按返回
            getSupportDelegate().replaceFragment(new LauncherScrollDelegate(),false);
        } else {
            //检查用户是否登录了APP

        }
    }
}
