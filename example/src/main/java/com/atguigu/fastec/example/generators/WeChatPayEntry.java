package com.atguigu.fastec.example.generators;

import com.atguigu.latte.wechat.templates.WXPayEntryTemplate;
import com.example.annotations.PayEntryGenerator;


@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.atguigu.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
