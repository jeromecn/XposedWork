package com.suifeng.xposedwork.hookclasses.hookclassloader;

import android.util.Log;

import com.suifeng.xposedwork.hookmodule.AbstractClassLoaderModule;
import com.suifeng.xposedwork.hookmodule.HookHelper;
import com.suifeng.xposedwork.hookmodule.HookMethodData;
import com.suifeng.xposedwork.hookmodule.HookType;
import com.suifeng.xposedwork.util.Logger;
import com.suifeng.xposedwork.util.filter.PackageNameFilter;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.XC_MethodHook;

public class Hook_PathClassLoader extends AbstractClassLoaderModule {

    public Hook_PathClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    public Hook_PathClassLoader(ClassLoader classLoader, PackageNameFilter filter) {
        super(classLoader, filter);
    }

    @Override
    protected void init() {
        className = PathClassLoader.class.getName();

        hookDatas.add(HookHelper.hookInit(
                String.class, String.class, ClassLoader.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Logger.logi("hook PathClassLoader3 :before path = " + param.args[0].toString());
                        super.beforeHookedMethod(param);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        hookPluginClasses(param);
                        super.afterHookedMethod(param);
                    }
                }));
        hookDatas.add(HookHelper.hookInit(
                String.class, ClassLoader.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Logger.logi("hook PathClassLoader2 :before path = " + param.args[0].toString());
                        super.beforeHookedMethod(param);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        String path = param.args[0].toString();
                        Logger.logi("hook PathClassLoader2 :after " + " path = " + path + "\n" + param.thisObject.toString());
                        hookPluginClasses(param);
                        super.afterHookedMethod(param);
                    }
                }));
    }
}
