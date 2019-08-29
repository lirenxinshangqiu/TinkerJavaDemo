package com.offcn.tinkerjavademo.app;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.offcn.tinkerjavademo.BuildConfig;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tinkerpatch.sdk.TinkerPatch;

@DefaultLifeCycle(application = "com.offcn.tinkerjavademo.app.MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class MyApplicationLike extends DefaultApplicationLike {

    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initTinker();
    }

    private void initTinker() {
        if (BuildConfig.TINKER_ENABLE) {
            //开始检查是否有补丁，这里配置的是每隔访问3小时服务器是否有更新。
            TinkerPatch.init(this)
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true)
                    .setFetchPatchIntervalByHours(1);

            // 获取当前的补丁版本
            Log.d("Tinker版本号", "current patch version is " + TinkerPatch.with().getPatchVersion());

            //每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
        }
    }


}
