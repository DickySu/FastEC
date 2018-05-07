package com.atguigu.latteec.ec.main;

import android.graphics.Color;

import com.atguigu.latte.delegates.bottom.BaseBottomDelegate;
import com.atguigu.latte.delegates.bottom.BottomItemDelegate;
import com.atguigu.latte.delegates.bottom.BottomTabBean;
import com.atguigu.latte.delegates.bottom.ItemBuilder;
import com.atguigu.latteec.ec.main.index.IndexDelegate;
import com.atguigu.latteec.ec.main.sort.SortDelegate;
import com.tencent.mm.opensdk.utils.Log;

import java.util.LinkedHashMap;

/**
 * Created by su on 2018/5/8.
 */
public class EcBottomDelegate extends BaseBottomDelegate {

    public EcBottomDelegate(){
        android.util.Log.i("sujh_fragment","EcBottomDelegate--create");
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        Log.i("sujh_fragment","EcBottomDelegate--oncreate");
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new SortDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() { //默认索引值
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}