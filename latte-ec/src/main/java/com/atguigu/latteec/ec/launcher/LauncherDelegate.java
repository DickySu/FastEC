package com.atguigu.latteec.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.atguigu.latte.delegates.LatteDelegate;
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
                        }
                    }
                }
            }
        });
    }
}
