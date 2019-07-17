package com.codvision.demo;

import android.app.Application;

import com.codvision.compatible.pstore.PstoreProxyImpl;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.demo
 * @ClassName: Pstore
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-17 18:03
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 18:03
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Pstore extends PstoreProxyImpl {

    public Pstore(Application vApplication) {
        super(vApplication);
    }

    @Override
    public <T> T getUser() {
        return null;
    }
}
