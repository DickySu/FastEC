package com.atguigu.fastec.example.generators;

import com.atguigu.latte.wechat.templates.WXEntryTemplate;
import com.example.annotations.EntryGenerator;

/**
 * Created by su on 2018/5/7.
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.atguigu.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}