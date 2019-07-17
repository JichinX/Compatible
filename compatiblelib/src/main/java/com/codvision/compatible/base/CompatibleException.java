package com.codvision.compatible.base;

/**
 * @ProjectName: Compatible
 * @Package: com.codvision.compatible.base
 * @ClassName: CompatibleException
 * @Description: java类作用描述
 * @Author: boss
 * @CreateDate: 2019-07-17 17:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019-07-17 17:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CompatibleException extends RuntimeException {
    public CompatibleException() {
    }

    public CompatibleException(String message) {
        super(message);
    }

    public CompatibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompatibleException(Throwable cause) {
        super(cause);
    }

    public CompatibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
