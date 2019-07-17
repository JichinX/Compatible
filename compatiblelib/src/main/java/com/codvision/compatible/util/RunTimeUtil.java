package com.codvision.compatible.util;

import com.codvision.compatible.base.CompatibleException;
import com.codvision.compatible.base.IPstore;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.compatible.util
 * @ClassName: RunTimeUtil
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-17 17:12
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 17:12
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RunTimeUtil {
    public static void checkNull(Object vO) {
        if (null == vO) {
            throw new CompatibleException("对象为null");
        }
    }
}
