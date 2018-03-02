package com.app.org.activity;

import android.os.Bundle;

import com.app.org.base.BaseActivity;
import com.app.org.util.FileManager;
import com.app.org.utils.ToastUtils;
import com.app.org.utils.BaseUtils;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * 项目启动页
 * 权限提醒-->版本检查-->跳转页面
 * Created by lixingxing on 2018/1/23.
 */

public abstract class BaseSplashActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initPermission();
    }

    protected abstract void setContentView();

    // 设置权限  默认是读写 照相机  定位
    protected void initPermission(){
//        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
//        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        HiPermission.create(getApplicationContext())
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        ToastUtils.showShortToast("用户关闭权限申请");
                    }

                    @Override
                    public void onFinish() {
                        FileManager.initFileDir(BaseUtils.getContext());
                        onPermissionFinish();
                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                        ToastUtils.showShortToast("onDeny" +permisson);
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                        ToastUtils.showShortToast("onGuarantee" + permisson);
                    }
                });
    }

    //权限检查完成
    protected void onPermissionFinish(){
        checkVersion();
    }

    // 检查版本更新
    protected void checkVersion(){
    }



    protected void showProgressBar(){

    }

}
