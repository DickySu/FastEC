package com.atguigu.latte.delegates.bottom;


import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.R;
import com.atguigu.latte.app.Latte;
import com.atguigu.latte.delegates.LatteDelegate;

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener{
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
            if(TOUCH_TIME != 0){
                TOUCH_TIME=0;
            }
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" +
                    Latte.getApplicationContext().getString(R.string.app_name),
                    Toast.LENGTH_SHORT).show();
        }
        return true;//不再处理事件
    }

    @Override
    public void onResume() {
        final View rootView = getView();
        if(rootView != null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
        super.onResume();
    }
}
