package com.atguigu.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.atguigu.latte.R;
import com.atguigu.latte.R2;
import com.atguigu.latte.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;


public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();//底部按钮集合
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>(); //内部集合
    //底部按钮跟内容fragment对应后放入双列集合
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;  //点击后按钮变色

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;
    //抽象方法给子类具体实现
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);
    public abstract int setIndexDelegate();
    @ColorInt
    public abstract int setClickedColor();//使用注解要求必须是颜色 实现就可以改变按钮颜色

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("sujh_fragment","BaseBottomDelegate--oncreate");
        mIndexDelegate = setIndexDelegate();//子类设置默认索引
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor(); //子类设置颜色
        }
        final ItemBuilder builder = ItemBuilder.builder();//传给子类去用 用这个添加元素
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);//按钮跟内容对应的集合
        //按钮跟内容分开 放入两个集合
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {

            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);//放入按钮集合
            ITEM_DELEGATES.add(value);//放入内容集合
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;//设置布局为内容+按钮
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final int size = ITEMS.size();
        Log.i("sujh_fragment",""+size);
        for (int i = 0; i < size; i++) {
            //上图下字的布局 添加到父布局中 父布局就是内容+按钮中的按钮ID
            LayoutInflater.from(getContext())
                    .inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            //得到第i个上图下字
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);//得到图控件
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);//得到字控件
            final BottomTabBean bean = TAB_BEANS.get(i);//按钮集合中的第i个数据
            //初始化数据
            itemIcon.setText(bean.getIcon()); //按钮集合中的第i个数据的图标
            itemTitle.setText(bean.getTitle());//按钮集合中的第i个数据的字
            if (i == mIndexDelegate) { //如果在当前索引 就改变颜色
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }
        //将内容布局集合转换为一个数组
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        //加载多个同级根fragment

        Log.i("sujh_fragment","数组长度"+delegateArray.length);
        Log.i("sujh_fragment","mIndexDelegate："+mIndexDelegate);
        Log.i("sujh_fragment","BaseBottomDelegate-getSupportDelegate="+getSupportDelegate());
        getSupportDelegate().loadMultipleRootFragment(
                R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    @Override
    public void onClick(View v) { //这个点击事件应该是只针对上图下字
        final int tag = (int) v.getTag();
        resetColor();//按钮颜色重置
        final RelativeLayout item = (RelativeLayout) v;  //强制转换为上图下字
        //得到图
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);//设置颜色
        //得到字
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);//设置颜色
        //更改内容布局的显示
        getSupportDelegate().showHideFragment(
                ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }


    private void resetColor() {
        final int count = mBottomBar.getChildCount(); //得到按钮个数
        for (int i = 0; i < count; i++) {
            //得到第i个按钮
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //得到第i个按钮的图
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);//设置颜色
            //得到第i个按钮的字
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);//设置颜色
        }
    }
}
