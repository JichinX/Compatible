package com.codvision.demo;

import android.app.Application;

import com.codvision.compatible.CompatInit;
import com.codvision.compatible.proxy.PStoreProxy;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.demo
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-17 18:01
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 18:01
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class App extends Application {
    public static PStoreProxy mStoreProxy;

    @Override
    public void onCreate() {
        super.onCreate();
        mStoreProxy = CompatInit.init(new Pstore(this));
    }
}
