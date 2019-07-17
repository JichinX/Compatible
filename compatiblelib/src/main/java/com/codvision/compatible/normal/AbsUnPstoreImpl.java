package com.codvision.compatible.normal;

import com.codvision.compatible.base.IPstore;
import com.codvision.compatible.pstore.LocationCallback;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.compatible.normal
 * @ClassName: UnPstoreProxyImpl
 * @Description: 非省厅环境下相应接口的实现
 * @Author: boss
 * @CreateDate: 2019-07-17 16:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 16:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class AbsUnPstoreImpl<T> implements IPstore<T> {
    @Override
    public boolean isVpnOK() {
        return true;
    }

    @Override
    public void logCreation() {
        //市局版本无需处理。。。
    }

    @Override
    public void logDelete() {
        //市局版本无需处理。。。
    }

    @Override
    public void logRetrieve(String queryCondition, int result) {
        //市局版本无需处理。。。
    }

    @Override
    public void logUpdate(String queryCondition, int result) {
        //市局版本无需处理。。。
    }

    @Override
    public void logLogin(String queryCondition, int result) {
        //市局版本无需处理。。。
    }

    @Override
    public void registerStoreExitReceiver() {

    }
}
