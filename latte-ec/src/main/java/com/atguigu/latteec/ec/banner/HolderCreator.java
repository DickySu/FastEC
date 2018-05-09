package com.atguigu.latteec.ec.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by su on 2018/5/9.
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}