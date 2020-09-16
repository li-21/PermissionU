package com.libinbin.permission.ui.mine;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.libinbin.permission.CommonBar;
import com.libinbin.permission.PermissionUtils;
import com.libinbin.permission.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class MineFragment extends Fragment implements View.OnClickListener {

    private MineViewModel mineViewModel;
    private View root;

    CommonBar ps_storage;
    CommonBar ps_camera;
    CommonBar ps_location;
    CommonBar ps_phone;
    CommonBar ps_sms;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mineViewModel =
                ViewModelProviders.of(this).get(MineViewModel.class);
        root = inflater.inflate(R.layout.fragment_mine, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ps_storage = root.findViewById(R.id.ps_storage);
        ps_storage.setOnClickListener(this);
        ps_camera = root.findViewById(R.id.ps_camera);
        ps_camera.setOnClickListener(this);
        ps_location = root.findViewById(R.id.ps_location);
        ps_location.setOnClickListener(this);
        ps_phone = root.findViewById(R.id.ps_phone);
        ps_phone.setOnClickListener(this);
        ps_sms = root.findViewById(R.id.ps_sms);
        ps_sms.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PermissionUtils.checkPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ps_storage.setTvRightStyle(getResources().getString(R.string.tv_right), getResources().getColor(R.color.title_text2));
        } else {
            ps_storage.setTvRightStyle(getResources().getString(R.string.tv_right_no), getResources().getColor(R.color.link_color));
        }
        if (PermissionUtils.checkPermission(getActivity(), Manifest.permission.CAMERA)) {
            ps_camera.setTvRightStyle(getResources().getString(R.string.tv_right), getResources().getColor(R.color.title_text2));
        } else {
            ps_camera.setTvRightStyle(getResources().getString(R.string.tv_right_no), getResources().getColor(R.color.link_color));
        }
        if (PermissionUtils.checkPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ps_location.setTvRightStyle(getResources().getString(R.string.tv_right), getResources().getColor(R.color.title_text2));
        } else {
            ps_location.setTvRightStyle(getResources().getString(R.string.tv_right_no), getResources().getColor(R.color.link_color));
        }
        if (PermissionUtils.checkPermission(getActivity(), Manifest.permission.CALL_PHONE)) {
            ps_phone.setTvRightStyle(getResources().getString(R.string.tv_right), getResources().getColor(R.color.title_text2));
        } else {
            ps_phone.setTvRightStyle(getResources().getString(R.string.tv_right_no), getResources().getColor(R.color.link_color));
        }
        if (PermissionUtils.checkPermission(getActivity(), Manifest.permission.READ_SMS)) {
            ps_sms.setTvRightStyle(getResources().getString(R.string.tv_right), getResources().getColor(R.color.title_text2));
        } else {
            ps_sms.setTvRightStyle(getResources().getString(R.string.tv_right_no), getResources().getColor(R.color.link_color));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ps_storage:
            case R.id.ps_camera:
            case R.id.ps_location:
            case R.id.ps_phone:
            case R.id.ps_sms:
                goSystemStetting();
                break;
            default:
                break;
        }
    }

    public void goSystemStetting() {
        Intent intent = getAppDetailSettingIntent();
        startActivity(intent);
    }


    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getActivity().getPackageName());
        }
        return localIntent;
    }
}