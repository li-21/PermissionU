package com.libinbin.permission;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * @author LiBinBin
 * @Date : 2020/9/15
 * @Description : 权限枚举，根据项目实际情况自己添加。**注意枚举数字要与attrs中的实际位置相对应，例如SMS在attrs中处于第8个。
 */
public interface PermissionType {
    @IntDef({STORAGE, CAMERA, LOCATION, PHONE, CALLPHONE, SMS})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    int STORAGE = 0;
    int CAMERA = 1;
    int LOCATION = 2;
    int PHONE = 3;
    int CALLPHONE = 4;
    int SMS = 8;

}
