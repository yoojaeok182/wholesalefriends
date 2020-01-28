package com.wholesale.wholesalefriends.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.dialog.AlertDialog;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.join.JoinStep1Activity;
import com.wholesale.wholesalefriends.main.join.JoinStep4Activity;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;

import org.json.JSONObject;

public class LoginActivity extends GroupActivity {

    private EditText mEdtId;
    private EditText mEdtPwd;
    private Button mBtnOk;
    private Button mBtnJoin;
    private Button mBtnKindPwd;

    private static LoginActivity instance;
    public static LoginActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instance = this;

        mEdtId = findViewById(R.id.edtId);
        mEdtPwd = findViewById(R.id.edtPwd);
        mBtnOk = findViewById(R.id.btnOk);
        mBtnJoin = findViewById(R.id.btnJoin);


        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mEdtId.getText().toString().length() ==0){
                    final CommonAlertDialog dg = new CommonAlertDialog(LoginActivity.this,false,true);
                    dg.setTitle("계정 정보 확인");
                    dg.setMessage("아이디를 입력해주세요.");
                    dg.show();
                    return;
                }
                if(mEdtPwd.getText().toString().length() ==0){
                    final CommonAlertDialog dg = new CommonAlertDialog(LoginActivity.this,false,true);
                    dg.setTitle("계정 정보 확인");
                    dg.setMessage("비밀번호를 입력해주세요.");
                    dg.show();
                    return;
                }

                API.login(LoginActivity.this,mEdtId.getText().toString(), mEdtPwd.getText().toString(),
                        resultHandler, errHandler);
            }
        });

        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinStep1Activity.class);
                startActivity(intent);
            }
        });

    }

    private Handler resultHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           try{
               JSONObject jsonObject = (JSONObject)msg.obj;

               if(jsonObject.getBoolean("result")){
                   SharedPreference.putSharedPreference(LoginActivity.this, Constant.CommonKey.user_no,jsonObject.getInt("user_id"));
                   SharedPreference.putSharedPreference(LoginActivity.this, Constant.CommonKey.user_id,mEdtId.getText().toString());
                   SharedPreference.putSharedPreference(LoginActivity.this, Constant.CommonKey.user_pwd,mEdtPwd.getText().toString());
                   Integer store_type = null;
                   Integer level = null;
                   if(!jsonObject.isNull("store_type")){
                        store_type = jsonObject.getInt("store_type");
                   }
                   if(!jsonObject.isNull("level")){
                       level = jsonObject.getInt("level");
                   }

                   if(!jsonObject.isNull("store_id")){
                     int  store_id = jsonObject.getInt("store_id");
                       SharedPreference.putSharedPreference(LoginActivity.this, Constant.CommonKey.store_id,store_id);
                   }

                   Intent intent = null;
                   if(store_type!=null &&store_type ==2){
                       intent = new Intent(LoginActivity.this,MainActivity.class);
                   }else{
                       intent = new Intent(LoginActivity.this,Main2Activity.class);
                   }
                   startActivity(intent);
                   finish();
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
                        final CommonAlertDialog dg = new CommonAlertDialog(LoginActivity.this,false,true);
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
}
