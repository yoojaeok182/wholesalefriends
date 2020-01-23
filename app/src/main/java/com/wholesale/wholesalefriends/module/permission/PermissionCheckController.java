package com.wholesale.wholesalefriends.module.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.widget.Toast;

import com.gun0912.tedpermission.TedPermissionResult;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.permission.rx2.TedRx2Permission;
import com.wholesale.wholesalefriends.module.util.LogUtil;

import androidx.core.app.ActivityCompat;

/**
 * Created by Handasoft_dev01 on 2018-04-02.
 */

public class PermissionCheckController {

    private Activity activity;
    private Handler resultHandler;
    public PermissionCheckController(Activity act, Handler handler){
        activity = act;
        resultHandler = handler;
    }

    public Handler getResultHandler() {
        return resultHandler;
    }

    public void setResultHandler(Handler resultHandler) {
        this.resultHandler = resultHandler;
    }

    public boolean isNeedPermission(String strPermission1){
        boolean isResult = false;
        if (ActivityCompat.checkSelfPermission(activity, strPermission1) != PackageManager.PERMISSION_GRANTED ) {

            isResult = true;
        }
        return isResult;
    }
    public boolean isNeedPermission(String strPermission1, String strPermission2){
        boolean isResult = false;
        if (ActivityCompat.checkSelfPermission(activity, strPermission1) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity,strPermission2) != PackageManager.PERMISSION_GRANTED) {

            isResult = true;
        }
        return isResult;
    }

    public boolean isNeedPermission(String... strPermission){
        String[] permissions = strPermission;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED ) {
                LogUtil.v("check permission: " +permission  );
                return true;
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean hasWindowPermission() {
        return Settings.canDrawOverlays(activity.getApplicationContext());
    }
    public void settingPermission(String rationalMsg){

        TedRx2Permission.with(activity)
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage(rationalMsg) // "",, Manifest.permission.WRITE_EXTERNAL_STORAGE
                .setDeniedMessage(activity.getString(R.string.permission_reject_cannot_service_msg))
                .setGotoSettingButton(true)
                .setGotoSettingButtonText(activity.getString(R.string.permission_reject_cannot_service_go_btn))
                .setPermissions(Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .request()
                .subscribe(tedPermissionResult -> {
                    if (tedPermissionResult.isGranted()) {
                        if(resultHandler!=null) resultHandler.sendEmptyMessage(0);
                    } else {
                        LogUtil.d(" check permission 1: " + ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE));
                        LogUtil.d(" check permission 2: " + ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE));

                        if(isNeedPermission(Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)){
                            Message msg = new Message();
                            if(!tedPermissionResult.isFinalDenied()){
                                Toast.makeText(activity,activity.getResources().getString(R.string.rationale_denied_message_close), Toast.LENGTH_LONG).show();
                                msg.what = 1;
                            }else{
                                msg.what = 2;
                            }


                            msg.obj = tedPermissionResult.getDeniedPermissions().toString();
                            if(resultHandler!=null) resultHandler.sendMessage(msg);
                        }else{
                            if(resultHandler!=null) resultHandler.sendEmptyMessage(0);
                        }

                    }
                }, throwable -> {
                }, () -> {
                });


    }

    public void settingPermission(String rationalMsg, String... strPermission1){

        TedRx2Permission.with(activity)
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage(rationalMsg) // "we need permission for read contact and find your location"
                .setDeniedMessage(activity.getString(R.string.permission_reject_cannot_service_msg))
                .setGotoSettingButton(true)
                .setGotoSettingButtonText(activity.getString(R.string.permission_reject_cannot_service_go_btn))
                .setPermissions(strPermission1)
                .request()
                .subscribe(tedPermissionResult -> {
                    if (tedPermissionResult.isGranted()) {
                        if(resultHandler!=null) resultHandler.sendEmptyMessage(0);
                    } else {
                        LogUtil.d(" check permission 1: " + ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE));
                        LogUtil.d(" check permission 2: " + ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE));

                        if(isNeedPermission(Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)){
                            Toast.makeText(activity,activity.getResources().getString(R.string.rationale_denied_message_close), Toast.LENGTH_LONG).show();
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = tedPermissionResult.getDeniedPermissions().toString();
                            if(resultHandler!=null) resultHandler.sendMessage(msg);
                        }else{
                            if(resultHandler!=null) resultHandler.sendEmptyMessage(0);
                        }

                    }
                }, throwable -> {
                }, () -> {
                });



    }

    private void showDialog(final TedPermissionResult tedPermissionResult, String errorMsg){
        final CommonAlertDialog dg = new CommonAlertDialog(activity,false,false);
        dg.setMessage(errorMsg);
        dg.setCancelable(false);
        dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = tedPermissionResult.getDeniedPermissions().toString();
                if(resultHandler!=null) resultHandler.sendMessage(msg);
            }
        });
        dg.show();
    }

    public void settingRx2Permission(String strPermission1){

        TedRx2Permission.with(activity)
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage("") // "we need permission for read contact and find your location"
                .setDeniedMessage(activity.getString(R.string.permission_reject_cannot_service_msg))
                .setGotoSettingButton(true)
                .setGotoSettingButtonText(activity.getString(R.string.permission_reject_cannot_service_go_btn))
                .setPermissions(strPermission1)
                .request()
                .subscribe(tedPermissionResult -> {
                    if (tedPermissionResult.isGranted()) {
                        if(resultHandler!=null) resultHandler.sendEmptyMessage(0);
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = tedPermissionResult.getDeniedPermissions().toString();
                        if(resultHandler!=null) resultHandler.sendMessage(msg);
                    }
                }, throwable -> {
                }, () -> {
                });


    }

    public void settingRx2Permission(String strPermission1, String strPermission2, String page){
        TedRx2Permission.with(activity)
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage("") // "we need permission for read contact and find your location"
                .setDeniedMessage(activity.getString(R.string.permission_reject_cannot_service_msg))
                .setGotoSettingButton(true)
                .setGotoSettingButtonText(activity.getString(R.string.permission_reject_cannot_service_go_btn))
                .setPermissions(strPermission1, strPermission2)
                .request()
                .subscribe(tedPermissionResult -> {
                    if (tedPermissionResult.isGranted()) {
                        resultHandler.sendEmptyMessage(0);
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = tedPermissionResult.getDeniedPermissions().toString();
                        if(resultHandler!=null) resultHandler.sendMessage(msg);

                    }
                }, throwable -> {
                }, () -> {
                });


    }
}
