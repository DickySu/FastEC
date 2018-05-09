package com.atguigu.latteec.ec.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by su on 2018/5/9.
 */
public class MultipleItemEntity implements MultiItemEntity {
    //BaseRecyclerViewAdapterHelper需要数据实现接口MultiItemEntity

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();//有序
    //上面两个集合放入这个
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields); //get方法是SoftReference的 得到对象 存入所有
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }

}