package com.codvision.compatible.util;

import android.content.Context;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Des:Compatible - com.codvision.compatible.util
 *
 * @author xujichang
 * @date 2019-04-17 - 10:11
 * <p>
 * modify:
 */
public class NetUtil {
    private static NetUtil instance;

    public static NetUtil getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static NetUtil instance = new NetUtil();
    }

    private static final String TAG = "NetUtil";

    /**
     * 判断当前网络是否是VPN网络
     *
     * @return
     */
    public static boolean isVpnUsed(Context context) {
        try {
            Enumeration<NetworkInterface> mInterfaces = NetworkInterface.getNetworkInterfaces();
            if (null != mInterfaces) {
                for (NetworkInterface mInterface : Collections.list(mInterfaces)) {
                    if (!mInterface.isUp() || mInterface.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    String name = mInterface.getName();

                    if ("tun0".equals(name) || "ppp0".equals(name)) {
                        return true;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private static void updateNetInfo() {

    }
}
