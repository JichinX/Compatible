package com.codvision.compatible.pstore;

import android.content.Context;

import com.codvision.compatible.pstore.util.NetUtil;

/**
 * Des:Compatible - com.codvision.compatible.pstore
 *
 * @author xujichang
 * @date 2019-04-17 - 14:50
 * <p>
 * modify:
 */
public abstract class PstoreProxyImpl implements PstoreProxy {
    @Override
    public boolean isVpnOK(Context context) {
        return NetUtil.isVpnUsed(context);
    }
}
