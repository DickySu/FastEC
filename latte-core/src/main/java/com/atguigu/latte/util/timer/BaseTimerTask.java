package com.atguigu.latte.util.timer;

import java.util.TimerTask;


public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {  //TimerTask要实现的任务
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
