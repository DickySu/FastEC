package com.atguigu.latteec.ec.recycler;

import java.util.LinkedHashMap;

/**
 * Created by su on 2018/5/9.
 */
public class MultipleEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() { //建造者模式
        return new MultipleItemEntity(FIELDS);
    }

}