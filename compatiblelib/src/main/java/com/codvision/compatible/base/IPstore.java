package com.codvision.compatible.base;

import android.content.Context;
import android.location.Location;

import com.codvision.compatible.pstore.LocationCallback;

/**
 * Des:FireEye - com.codvision.compatible.pstore
 *
 * @author xujichang
 * @date 2019/3/12
 * <p>
 * modify:
 */
public interface IPstore<T> {
    /**
     * 判断VPN链路
     *
     * @return
     */
    boolean isVpnOK();

    //=====================================================日志====================================================
    void logCreation();

    void logDelete();

    void logRetrieve(String queryCondition, int result);

    void logUpdate(String queryCondition, int result);

    void logLogin(String queryCondition, int result);

    /**
     * 获取位置信息
     *
     * @param callback
     */
    void obtainLocation(LocationCallback callback);

    /**
     * 获取用户信息
     *
     * @param <T>
     * @return
     */
    T getUser();

    void iniExceptionHandler();

    void registerStoreExitReceiver();

    void onLocationChanged(Location vLocation);

    Location getCurrentLocation();

    public static class Result {
        public static final int CODE_FAILURE = 0;
        public static final int CODE_SUCCESS = 1;
    }

    public static class LogType {
        public static final int CODE_USER_OPERATION = 1;
        public static final int CODE_API_CALLED = 2;
    }
}
