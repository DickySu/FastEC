package com.atguigu.latteec.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.latte.ui.launcher.ItemType;
import com.atguigu.latteec.ec.recycler.DataConverter;
import com.atguigu.latteec.ec.recycler.MultipleFields;
import com.atguigu.latteec.ec.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by su on 2018/5/9.
 */
public final class IndexDataConverter extends DataConverter {



    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //解析json得到一堆商品 getJsonData()是父类的方法
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);//得到一个商品
            //得到商品信息
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");//banner是一个数组

            final ArrayList<String> bannerImages = new ArrayList<>();//用来放多张图
            int type = 0;
            if (imageUrl == null && text != null) { //如果只有文字没有图
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) { //如果有图没文字
                type = ItemType.IMAGE;
            } else if (imageUrl != null) { //如果图文都有
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {  //如果有多张图
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();//数组长度
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);//加入集合
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                    .setField(MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);//父类中的ENTITIES  放商品集合
        }

        return ENTITIES;
    }



}