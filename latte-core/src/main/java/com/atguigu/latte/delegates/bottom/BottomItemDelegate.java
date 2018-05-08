package com.atguigu.latte.delegates.bottom;


import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.atguigu.latte.R;
import com.atguigu.latte.delegates.LatteDelegate;

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener{
    // 再点一次退出程序时间设置
    private static final int EXIT_TIME = 2000;
    private long mLastClickTime = 0;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mLastClickTime > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mLastClickTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
            }
            return true;
        }
        return false;
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
