package com.wholesale.wholesalefriends.main;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.CategoryListResponse;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.AppData;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.permission.PermissionCheckController;
import com.wholesale.wholesalefriends.module.pref.Pref;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import org.json.JSONObject;

import java.io.File;

public class IntroActivity extends GroupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        initSetting();
        loadCategoryList();
        permissionCheck(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(msg.what == 0){   // Message id 가 0 이면
                    SharedPreference.putSharedPreference(IntroActivity.this, Pref.permission_check_complet,"Y");
                    if(SharedPreference.getIntSharedPreference(IntroActivity.this,Constant.CommonKey.user_no)!=0){
                        String id = SharedPreference.getSharedPreference(IntroActivity.this, Constant.CommonKey.user_id);
                        String pwd = SharedPreference.getSharedPreference(IntroActivity.this, Constant.CommonKey.user_pwd);

                        API.login(IntroActivity.this,id, pwd,
                                resultHandler, errHandler);
                    }else{
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();


                            }
                        },1500);

                    }

                }else if(msg.what ==1){

                    finish();
//
                }else{
                    finish();
                }

            }
        });
    }

    private void loadCategoryList(){
        API.categoryList(IntroActivity.this,resultCategoryHandler,null);
    }

    private Handler resultCategoryHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    CategoryListResponse response = new Gson().fromJson(jsonObject.toString(), CategoryListResponse.class);
                    AppData.getInstance().setCategoryListResponse(response);
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private Handler resultHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    SharedPreference.putSharedPreference(IntroActivity.this, Constant.CommonKey.user_no,jsonObject.getString("user_id"));
                    Integer store_type = null;
                    Integer level = null;
                    if(!jsonObject.isNull("store_type")){
                        store_type = jsonObject.getInt("store_type");
                    }
                    if(!jsonObject.isNull("level")){
                        level = jsonObject.getInt("level");
                    }
                    Intent intent = null;
                    if(store_type!=null &&store_type ==2){
                        intent = new Intent(IntroActivity.this,MainActivity.class);
                    }else{
                        intent = new Intent(IntroActivity.this,Main2Activity.class);
                    }
                    startActivity(intent);
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };
    private Handler errHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){

                    if(jsonObject.getString("error")!=null && jsonObject.getString("error").length()>0){
                        final CommonAlertDialog dg = new CommonAlertDialog(IntroActivity.this,false,true);
                        dg.setTitle("계정 정보 확인");
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

                    }
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    public void permissionCheck(Handler handler){
        final PermissionCheckController permissionCheckController  = new PermissionCheckController(this,handler);
        boolean isPermissionCheckPopup = SharedPreference.getBooleanSharedPreference(this, Pref.permission_check_show_popup);

        if(permissionCheckController.isNeedPermission(Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)){
            permissionCheckController.settingPermission("");
            if(isPermissionCheckPopup){
                SharedPreference.putSharedPreference(this,Pref.permission_check_show_popup,false);
            }else{
                permissionCheckController.settingPermission("",Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }else{
            handler.sendEmptyMessage(0);
        }

    }

    private void initSetting(){
        String folderPath = Constant.PICTURE_DIR;

        try {
            File folder = new File(folderPath);
            if (folder.exists()) {
                if (folder.isDirectory()) {
                    String[] children = folder.list();
                    for (int i = 0; i < children.length; i++) {
                        new File(folder, children[i]).delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
