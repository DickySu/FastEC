package com.atguigu.latte.delegates.bottom;

/**
 * Created by su on 2018/5/7.
 */
public final class BottomTabBean {  //主界面底部的按钮 上图下字

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }


}