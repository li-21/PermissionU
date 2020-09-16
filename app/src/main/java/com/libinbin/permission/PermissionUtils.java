package com.libinbin.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import io.reactivex.functions.Consumer;
/**
 * @author LiBinBin
 * @Date : 2020/9/15
 * @Description : 权限申请工具类
 */
public class PermissionUtils {

    public static void goSystemStetting(Context context) {
        Intent intent = getAppDetailSettingIntent(context);
        context.startActivity(intent);
    }

    private static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }

    public static boolean checkPermission(final Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void checkPermission(final Activity activity, final OnPermissionListener grantedListener, final String... permissions) {
        boolean havePermission = true;
        final ArrayList<Integer> types = new ArrayList<Integer>();
        for (String permission : permissions) {
            switch (permission) {
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                    if (!types.contains(PermissionType.STORAGE)) {
                        types.add(PermissionType.STORAGE);
                    }
                    break;
                case Manifest.permission.CAMERA:
                    if (!types.contains(PermissionType.CAMERA)) {
                        types.add(PermissionType.CAMERA);
                    }
                    break;
                case Manifest.permission.READ_PHONE_STATE:
                    if (!types.contains(PermissionType.PHONE)) {
                        types.add(PermissionType.PHONE);
                    }
                    break;
                case Manifest.permission.CALL_PHONE:
                    if (!types.contains(PermissionType.CALLPHONE)) {
                        types.add(PermissionType.CALLPHONE);
                    }
                    break;
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                    if (!types.contains(PermissionType.LOCATION)) {
                        types.add(PermissionType.LOCATION);
                    }
                    break;
                case Manifest.permission.READ_SMS:
                case Manifest.permission.SEND_SMS:
                    if (!types.contains(PermissionType.SMS)) {
                        types.add(PermissionType.SMS);
                    }
                    break;
                default:
                    break;
            }
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                havePermission = false;
            }
        }
        if (!havePermission) {
            PermissionDialogUtil permissionDialog = new PermissionDialogUtil(activity, types);
            permissionDialog.setOnSureClickListener(new PermissionDialogUtil.OnSureClickListener() {
                @Override
                public void onSureClick() {
                    types.clear();
                    RxPermissions rxPermission = new RxPermissions((FragmentActivity) activity);
                    rxPermission
                            .requestEachCombined(permissions)
                            .subscribe(new Consumer<Permission>() {
                                @Override
                                public void accept(Permission permission) throws Exception {
                                    if (permission.granted) {
                                        grantedListener.onGranted();
                                    } else if (permission.shouldShowRequestPermissionRationale) {
                                        grantedListener.onRefused();
                                    } else {
                                        DialogUtils.showDotAskDialog(activity, new DialogUtils.OnDialogClickListener() {
                                            @Override
                                            public void confirm() {
                                                goSystemStetting(activity);
                                            }

                                            @Override
                                            public void cancel() {
                                            }
                                        });
                                    }
                                }
                            });
                }
            });
            permissionDialog.show();

        } else {
            grantedListener.onGranted();
        }
    }

}
