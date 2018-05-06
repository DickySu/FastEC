package com.atguigu.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by su on 2018/5/6.
 */

public class LauncherHolder implements Holder<Integer> {  //banner的使用 需要准备一个holder

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {   //返回图片控件
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {  //设置数据
        mImageView.setBackgroundResource(data);
    }
}
