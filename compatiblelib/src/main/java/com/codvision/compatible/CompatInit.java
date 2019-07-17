package com.codvision.compatible;

import com.codvision.compatible.base.IPstore;
import com.codvision.compatible.proxy.PStoreProxy;

/**
 * Des:FireEye - com.codvision.compatible
 *
 * @author xujichang
 * @date 2019/3/12
 * <p>
 * modify:
 */
public class CompatInit {

    public static <T> PStoreProxy<T> init(IPstore<T> vPstore) {
        PStoreProxy<T> loProxy = new PStoreProxy<>(vPstore);
        //处理错误
        loProxy.iniExceptionHandler();
        return loProxy;
    }
}
