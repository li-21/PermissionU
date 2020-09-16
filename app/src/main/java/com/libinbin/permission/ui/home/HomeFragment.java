package com.libinbin.permission.ui.home;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.libinbin.permission.OnPermissionListener;
import com.libinbin.permission.PermissionUtils;
import com.libinbin.permission.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private View root;
    private Button phone;
    private Button camera;
    private Button storage;
    private Button location;
    private Button sms;
    private Button group;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phone = root.findViewById(R.id.phone);
        phone.setOnClickListener(this);
        camera = root.findViewById(R.id.camera);
        camera.setOnClickListener(this);
        storage = root.findViewById(R.id.storage);
        storage.setOnClickListener(this);
        location = root.findViewById(R.id.location);
        location.setOnClickListener(this);
        sms = root.findViewById(R.id.sms);
        sms.setOnClickListener(this);
        group = root.findViewById(R.id.group);
        group.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone:
                PermissionUtils.checkPermission(getActivity(), new OnPermissionListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onRefused() {
                        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.CALL_PHONE);
                break;
            case R.id.camera:
                PermissionUtils.checkPermission(getActivity(), new OnPermissionListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onRefused() {
                        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.CAMERA);
                break;
            case R.id.storage:
                PermissionUtils.checkPermission(getActivity(), new OnPermissionListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onRefused() {
                        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.READ_EXTERNAL_STORAGE);
                break;
            case R.id.location:
                PermissionUtils.checkPermission(getActivity(), new OnPermissionListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onRefused() {
                        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.ACCESS_COARSE_LOCATION);
                break;
            case R.id.sms:
                PermissionUtils.checkPermission(getActivity(), new OnPermissionListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onRefused() {
                        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.READ_SMS);
                break;
            case R.id.group:
                PermissionUtils.checkPermission(getActivity(), new OnPermissionListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onRefused() {
                        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_SMS);
                break;
            default:
                break;
        }

    }
}