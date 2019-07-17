package com.codvision.compatible.pstore;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.location.LocationManager;
import android.os.Handler;

import com.codvision.compatible.CompatInit;
import com.codvision.compatible.base.CompatibleConst;
import com.codvision.compatible.base.IPstore;
import com.codvision.compatible.util.ManifestUtil;
import com.codvision.compatible.util.NetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.com.cybertech.models.gaode.AddressQueryResult;
import cn.com.cybertech.models.gaode.CellLocationQueryResult;
import cn.com.cybertech.models.gaode.Location;
import cn.com.cybertech.pdk.AppException;
import cn.com.cybertech.pdk.GaodeService;
import cn.com.cybertech.pdk.LocationInfo;
import cn.com.cybertech.pdk.OperationLog;
import cn.com.cybertech.provider.PstoreContract;

/**
 * Des:Compatible - com.codvision.compatible.pstore
 * 省厅环境下的相关方法的实现
 *
 * @author xujichang
 * @date 2019-04-17 - 14:50
 * <p>
 * modify:
 */
public abstract class PstoreProxyImpl<T> implements IPstore<T> {

    protected static final String HYPHEN = " - ";
    protected static final String EQUALS_SIGN = " = ";
    protected static final String LINE_BREAK = "\n";

    private Application mApplication;
    private String mRegId;
    private String mAppKey;
    private BroadcastReceiver mStoreExitReceiver;
    private ContentObserver mLocationObserver;
    private android.location.Location mLocation;

    public PstoreProxyImpl(Application vApplication) {
        mApplication = vApplication;
        mRegId = ManifestUtil.getMetaData(vApplication, CompatibleConst.KEY_REG_ID);
        mAppKey = ManifestUtil.getMetaData(vApplication, CompatibleConst.KEY_APP_KEY);
        mLocation = new android.location.Location(LocationManager.GPS_PROVIDER);
        mStoreExitReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (cn.com.cybertech.pdk.Intent.ACTION_PSTORE_EXIT.equals(action)) {
                    mApplication.onTerminate();
                }
            }
        };
        mLocationObserver = new ContentObserver(
                new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                Map<String, String> locationInfo = cn.com.cybertech.pdk.LocationInfo.getLocationInfo(mApplication);
                mLocation.reset();
                mLocation.setLatitude(Double.parseDouble(locationInfo.get(PstoreContract.LocationInfo.FIELD_LATITUDE)));
                mLocation.setLongitude(Double.parseDouble(locationInfo.get(PstoreContract.LocationInfo.FIELD_LONGITUDE)));
                onLocationChanged(mLocation);
            }
        };
    }

    @Override
    public boolean isVpnOK() {
        return NetUtil.isVpnUsed(mApplication);
    }

    @Override
    public void logCreation() {
        saveLog(mApplication, mRegId, mAppKey, "Module-CREATION", OperationLog.OperationType.CODE_CREATION,
                OperationLog.OperationResult.CODE_SUCCESS, OperationLog.LogType.CODE_API_CALLED,
                "CREATION CONDITION (" + SimpleDateFormat.getDateTimeInstance().format(new Date()) + ")");
    }

    @Override
    public void logDelete() {
        saveLog(mApplication, mRegId, mAppKey, "Module-DELETE", OperationLog.OperationType.CODE_DELETE,
                OperationLog.OperationResult.CODE_SUCCESS, OperationLog.LogType.CODE_API_CALLED,
                "DELETE CONDITION (" + SimpleDateFormat.getDateTimeInstance().format(new Date()) + ")");
    }

    @Override
    public void logRetrieve(String queryCondition, int result) {
        saveLog(mApplication, mRegId, mAppKey, "Module-RETRIEVE", OperationLog.OperationType.CODE_RETRIEVE,
                result, OperationLog.LogType.CODE_API_CALLED,
                "RETRIEVE CONDITION (" + queryCondition + ") at " + SimpleDateFormat.getDateTimeInstance().format(new Date()));
    }

    @Override
    public void logUpdate(String queryCondition, int result) {
        saveLog(mApplication, mRegId, mAppKey, "Module-UPDATE", OperationLog.OperationType.CODE_UPDATE,
                result, OperationLog.LogType.CODE_API_CALLED,
                "RETRIEVE CONDITION (" + queryCondition + ") at " + SimpleDateFormat.getDateTimeInstance().format(new Date()));
    }

    @Override
    public void logLogin(String queryCondition, int result) {
        saveLog(mApplication, mRegId, mAppKey, "Module-LOGIN", OperationLog.OperationType.CODE_LOGIN,
                result, OperationLog.LogType.CODE_API_CALLED,
                "RETRIEVE CONDITION (" + queryCondition + ") at " + SimpleDateFormat.getDateTimeInstance().format(new Date()));
    }

    @Override
    public void obtainLocation(final LocationCallback callback) {
        GaodeService gaodeService = new GaodeService(mApplication);
        gaodeService.locateByGpsOrBaseStations(new GaodeService.OnAddressQueryListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccessful(AddressQueryResult result, String raw) {
                if (result.getAddressResultList().size() > 0) {
                    //GPS定位
                    Location location = result.getAddressResultList().get(0).getLocation();
                    callback.onGotLocation(convertLocation(location));
                } else {
                    callback.onGotLocation(null);
                }
            }

            @Override
            public void onFailed(String error) {
                callback.onGotLocation(null);
            }
        }, new GaodeService.OnCellLocationQueryListener() {
            @Override
            public void onSuccessful(CellLocationQueryResult result, String raw) {
                if (result.isOK()) {
                    //基站定位
                    String locationStr = result.getCellLocationResult().getLocation();
                    Location location = new Location();
                    location.setLat(locationStr.split(",")[0]);
                    location.setLng(locationStr.split(",")[1]);
                    callback.onGotLocation(convertLocation(location));
                } else {
                    callback.onGotLocation(null);
                }
            }

            @Override
            public void onFailed(String error) {
                callback.onGotLocation(null);
            }
        });
    }

    private android.location.Location convertLocation(Location location) {
        mLocation.reset();
        mLocation.setLatitude(Double.parseDouble(location.getLat()));
        mLocation.setLongitude(Double.parseDouble(location.getLng()));
        onLocationChanged(mLocation);
        return mLocation;
    }

    @Override
    public void registerStoreExitReceiver() {
        //对退出的监听
        IntentFilter filter = new IntentFilter();
        filter.addAction(cn.com.cybertech.pdk.Intent.ACTION_PSTORE_EXIT);
        mApplication.registerReceiver(mStoreExitReceiver, filter);
    }

    public void saveLog(Context context, String regId, String appKey, String module,
                        int operateTypeCode, int operateResultCode, int logTypeCode, String operateCondition) {
        /**
         * @param context           Context
         * @param appKey            APP KEY
         * @param module            功能模块 可填写当时用户所操作的具体功能模块名称
         * @param operateTypeCode   操作类型代码 {@link OperationType}
         * @param operateResultCode 操作结果代码 {@link OperationResult}
         * @param logTypeCode       日志类型代码 {@link LogType}
         * @param operateCondition  记录用户操作/接口调用条件，操作类型为0-登录时，置空；为其它类型时，可记录用户进行操作时的数据筛选条件，填写数据操作SQL语句的where子句内容，如：name=‘张三
         */
        //noinspection ConstantConditions
        OperationLog.logging(context,
                //                regId,
                appKey,
                module,
                operateTypeCode,
                operateResultCode,
                logTypeCode,
                operateCondition
        );

        // 显示
        String log =
                "regId" + EQUALS_SIGN + regId + LINE_BREAK +
                        "appKey" + EQUALS_SIGN + appKey + LINE_BREAK +
                        "module" + EQUALS_SIGN + module + LINE_BREAK +
                        "operateTypeCode" + EQUALS_SIGN + operateTypeCode + LINE_BREAK +
                        "operateResultCode" + EQUALS_SIGN + operateResultCode + LINE_BREAK +
                        "logTypeCode" + EQUALS_SIGN + logTypeCode + LINE_BREAK +
                        "operateCondition" + EQUALS_SIGN + LINE_BREAK + operateCondition + LINE_BREAK;
    }

    @Override
    public void iniExceptionHandler() {
        AppException.getInstance().registerExceptionHandler(mApplication, mAppKey);
    }

    @Override
    public android.location.Location getCurrentLocation() {
        return mLocation;

    }
}
