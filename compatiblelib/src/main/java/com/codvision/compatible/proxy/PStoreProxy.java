package com.codvision.compatible.proxy;

import android.location.Location;

import com.codvision.compatible.base.IPstore;
import com.codvision.compatible.pstore.LocationCallback;
import com.codvision.compatible.util.RunTimeUtil;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.compatible.proxy
 * @ClassName: PStoreProxy
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-17 17:05
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 17:05
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class PStoreProxy<T> implements IPstore<T> {

    private IPstore<T> mPstore;

    public PStoreProxy(IPstore<T> vPstore) {
        mPstore = vPstore;
    }

    @Override
    public boolean isVpnOK() {
        RunTimeUtil.checkNull(mPstore);
        return mPstore.isVpnOK();
    }

    @Override
    public void logCreation() {
        RunTimeUtil.checkNull(mPstore);
        mPstore.logCreation();
    }

    @Override
    public void logDelete() {
        RunTimeUtil.checkNull(mPstore);
        mPstore.logDelete();
    }

    @Override
    public void logRetrieve(String queryCondition, int result) {
        RunTimeUtil.checkNull(mPstore);
        mPstore.logRetrieve(queryCondition, result);
    }

    @Override
    public void logUpdate(String queryCondition, int result) {
        RunTimeUtil.checkNull(mPstore);
        mPstore.logUpdate(queryCondition, result);
    }

    @Override
    public void logLogin(String queryCondition, int result) {
        RunTimeUtil.checkNull(mPstore);
        mPstore.logLogin(queryCondition, result);
    }

    @Override
    public void obtainLocation(LocationCallback callback) {
        RunTimeUtil.checkNull(mPstore);
        mPstore.obtainLocation(callback);
    }

    @Override
    public T getUser() {
        RunTimeUtil.checkNull(mPstore);
        return mPstore.getUser();
    }

    @Override
    public void iniExceptionHandler() {
        RunTimeUtil.checkNull(mPstore);
        mPstore.iniExceptionHandler();
    }

    @Override
    public void registerStoreExitReceiver() {
        RunTimeUtil.checkNull(mPstore);
        mPstore.registerStoreExitReceiver();
    }

    @Override
    public void onLocationChanged(Location vLocation) {
        RunTimeUtil.checkNull(mPstore);
        mPstore.onLocationChanged(vLocation);
    }

    @Override
    public Location getCurrentLocation() {
        RunTimeUtil.checkNull(mPstore);
        return mPstore.getCurrentLocation();
    }
}
