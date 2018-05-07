package com.atguigu.latte.app;

import android.app.Activity;
import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by su on 2018/4/1.
 * 配置信息的存储读取
 */

public class Configurator {
    //这边map之前的额key是String 现在也用Object 以免有人喜欢存int enum 对象等 省得转换为string
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>(); //全局属性值配置缓存
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();//字体图标库
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//拦截器缓存

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();//记得初始化字体图标库
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        Log.i("sujh_Config",""+isReady);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    public final Configurator withInterceptor(Interceptor interceptor) {//拦截器添加
        INTERCEPTORS.add(interceptor); //集合放入一个拦截器
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS); //注意这里不是放入一个 是整个集合放入配置中
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);//存入一堆拦截器
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);//注意这里不是放入一个 是整个集合放入配置中
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key) {//传入key 得到值
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key.name());
        if (value == null) {
            throw new NullPointerException(key.name().toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key.name());
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) { //配置微信
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) { //配置微信
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) { //微信回调需要使用到
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }
}

