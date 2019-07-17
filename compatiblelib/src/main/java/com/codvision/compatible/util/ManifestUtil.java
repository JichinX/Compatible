package com.codvision.compatible.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.compatible.util
 * @ClassName: ManifestUtil
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-17 18:07
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 18:07
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ManifestUtil {
    public static String getMetaData(Context vContext, String key) {
        ApplicationInfo loActivityInfo = null;
        try {
            loActivityInfo = vContext.getPackageManager().getApplicationInfo(vContext.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException vE) {
            vE.printStackTrace();
        }
        if (null == loActivityInfo || null == loActivityInfo.metaData) {
            throw new RuntimeException("未获取到 Meta-data 信息");
        }
        String result = loActivityInfo.metaData.getString(key);
        if (null == result) {
            throw new RuntimeException("未获取到 Meta-data 中" + key + " 的value信息");
        }
        return result;
    }
}
