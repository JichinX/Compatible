package com.codvision.compatible.pstore;

import android.location.Location;

/**
 * Des:FireEye - com.codvision.compatible.pstore
 *
 * @author xujichang
 * @date 2019/3/12
 * <p>
 * modify:
 */
public interface LocationCallback {
    void onGotLocation(Location location);
}
