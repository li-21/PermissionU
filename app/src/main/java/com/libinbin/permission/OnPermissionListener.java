package com.libinbin.permission;

/**
 * @author LiBinBin
 * @Date : 2020/9/15
 * @Description : 权限返回结果监听
 */
public interface OnPermissionListener {
    /**
     * 同意权限
     */
    void onGranted();

    /**
     * 拒绝权限
     */
    void onRefused();

    /**
     * 不再询问
     */
//    void onDotAsk();

    /**
     * 权限已赋予
     */
//    void onAcquired();
}
