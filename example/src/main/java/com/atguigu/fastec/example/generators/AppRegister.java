package com.atguigu.fastec.example.generators;

import com.atguigu.latte.wechat.templates.AppRegisterTemplate;
import com.example.annotations.AppRegisterGenerator;


@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.atguigu.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister { //这些类都是不必要的 可以不写 只是为了测试
}
