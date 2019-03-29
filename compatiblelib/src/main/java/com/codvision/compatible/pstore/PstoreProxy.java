package com.codvision.compatible.pstore;

import android.content.Context;

/**
 * Des:FireEye - com.codvision.compatible.pstore
 *
 * @author xujichang
 * @date 2019/3/12
 * <p>
 * modify:
 */
public interface PstoreProxy {

    boolean isVpnOK(Context context);

    //=====================================================日志====================================================
    void LogCreation(Context context);

    void LogDelete(Context context);

    void saveLog(Context context, String regId, String appKey, String module, int operateTypeCode, int operateResultCode, int logTypeCode, String operateCondition);

    void LogRetrieve(Context context, String queryCondition, int result);

    void LogUpdate(Context context, String queryCondition, int result);

    void LogLogin(Context context, String queryCondition, int result);

    void obtainLocation(Context context, LocationCallback callback);

    <T> T getUser(Context context);


    public static class Result {
        public static final int CODE_FAILURE = 0;
        public static final int CODE_SUCCESS = 1;
    }

    public static class LogType {
        public static final int CODE_USER_OPERATION = 1;
        public static final int CODE_API_CALLED = 2;
    }
}
