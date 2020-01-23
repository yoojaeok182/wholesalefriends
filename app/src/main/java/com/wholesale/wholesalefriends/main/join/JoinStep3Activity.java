package com.wholesale.wholesalefriends.main.join;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.data.BuildSearchData;
import com.wholesale.wholesalefriends.main.data.BuildingSearchResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;

import org.json.JSONObject;

import java.util.ArrayList;

public class JoinStep3Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivType01;
    private TextView tvStore01;
    private LinearLayout btnJoin01;
    private ImageView ivType02;
    private TextView tvStore02;
    private LinearLayout btnJoin02;
    private ImageView ivType03;
    private TextView tvStore03;
    private LinearLayout btnJoin03;
    private Button btnOk;
    private int value =-1;
    private String strStore;
    private  String strFloor;
    private String strRoomNo;
    private int nWholesaleMode=-1;
    private int level = 0;

    private BuildingSearchResponse buildingSearchResponse1;
    private BuildingSearchResponse buildingSearchResponse2;
    private BuildingSearchResponse buildingSearchResponse3;


    private int store_type =0;
    private static JoinStep3Activity instance;
    public static JoinStep3Activity getInstance(){
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_step_03);
        instance = this;

        Intent intent = getIntent();
        if (intent.hasExtra("level")) {
            level = intent.getExtras().getInt("level");
        }
        if(intent.hasExtra("store_type")){
            store_type = intent.getExtras().getInt("store_type");
        }
        if(intent.hasExtra("wholesale")){
            nWholesaleMode = intent.getExtras().getInt("wholesale");
        }
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivType01 = findViewById(R.id.ivType01);
        tvStore01 = findViewById(R.id.tvStore01);
        btnJoin01 = findViewById(R.id.btnJoin01);
        ivType02 = findViewById(R.id.ivType02);
        tvStore02 = findViewById(R.id.tvStore02);
        btnJoin02 = findViewById(R.id.btnJoin02);
        ivType03 = findViewById(R.id.ivType03);
        tvStore03 = findViewById(R.id.tvStore03);
        btnJoin03 = findViewById(R.id.btnJoin03);
        btnOk = findViewById(R.id.btnOk);

        tvTitle.setText("도매 회원가입");

        btnJoin01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                API.buildingSearch(JoinStep3Activity.this,"1","", resultHandler,errHandler);


            }
        });

        btnJoin02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strStore == null || (strStore!=null &&strStore.length() ==0)){
                   final CommonAlertDialog dg = new CommonAlertDialog(JoinStep3Activity.this,false,false);
                   dg.setMessage("상가를 선택해주세요.");
                   return;
                }
                API.buildingSearch(JoinStep3Activity.this,"2",""+(value), resultHandler2,errHandler);
            }
        });
        btnJoin03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strStore == null || (strStore!=null &&strStore.length() ==0)){
                    final CommonAlertDialog dg = new CommonAlertDialog(JoinStep3Activity.this,false,false);
                    dg.setMessage("상가를 선택해주세요.");
                    return;
                }

                if(strFloor == null || (strFloor!=null &&strFloor.length() ==0)){
                    final CommonAlertDialog dg = new CommonAlertDialog(JoinStep3Activity.this,false,false);
                    dg.setMessage("층을 선택해주세요.");
                    return;
                }

                API.buildingSearch(JoinStep3Activity.this,"3",""+(value), resultHandler3,errHandler);


            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelectItem()){
                    if(level ==2){


                    }else{
                        Intent intent = new Intent(JoinStep3Activity.this,JoinStepAddrInputActivity.class);
                        intent.putExtra("store_type", store_type);
                        intent.putExtra("store_id", value);
                        intent.putExtra("store", strStore);
                        intent.putExtra("floor", strFloor);
                        intent.putExtra("roomNo", strRoomNo);
                        intent.putExtra("wholesale",nWholesaleMode);
                        intent.putExtra("level", level);

                        startActivity(intent);
                    }
                }




            }
        });
    }

    private boolean checkSelectItem(){

        if(strFloor ==null || strRoomNo ==null || strStore == null){
            btnOk.setEnabled(false);
            btnOk.setBackgroundResource(R.drawable.btn_02);
            return  false;
        }
        btnOk.setBackgroundResource(R.drawable.btn_02_on);
        btnOk.setEnabled(true);
        return  true;
    }

    private Handler resultHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){

                    buildingSearchResponse1 = new Gson().fromJson(msg.obj.toString(), BuildingSearchResponse.class);

                    if(buildingSearchResponse1!=null && buildingSearchResponse1.getList().size()>0){
//                      String[] items01 = buildingSearchResponse1.getList().toArray(new String[buildingSearchResponse1.getList().size()]);

                        ArrayList<BuildSearchData> arrayList = new ArrayList<>();
                        arrayList.addAll(buildingSearchResponse1.getList());

                        String[] items = new String[arrayList.size()];
                        for(int i=0; i<arrayList.size();i++){
                            items[i]= arrayList.get(i).getBuilding_name();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinStep3Activity.this);
                        builder.setCancelable(false);
                        builder.setTitle("상가 선택");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on options[which]
                                tvStore01.setText(buildingSearchResponse1.getList().get(which).getBuilding_name());
                                value  = buildingSearchResponse1.getList().get(which).getId();
                                strStore = buildingSearchResponse1.getList().get(which).getBuilding_name();
                                checkSelectItem();
                                ivType01.setBackgroundResource(R.drawable.icon_stor_on);
                                btnJoin01.setBackgroundResource(R.drawable.box_on_01);
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //the user clicked on Cancel
                            }
                        });
                        builder.show();

                    }


                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private Handler resultHandler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){

                    buildingSearchResponse2 = new Gson().fromJson(msg.obj.toString(), BuildingSearchResponse.class);

                    if(buildingSearchResponse2!=null && buildingSearchResponse2.getList().size()>0){
                        ArrayList<BuildSearchData> arrayList = new ArrayList<>();
                        arrayList.addAll(buildingSearchResponse2.getList());
                        String[] items = new String[arrayList.size()];
                        for(int i=0; i<arrayList.size();i++){
                            items[i]= arrayList.get(i).getBuilding_name();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinStep3Activity.this);
                        builder.setCancelable(false);
                        builder.setTitle("층 선택");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on options[which]
                                tvStore02.setText(buildingSearchResponse2.getList().get(which).getBuilding_name());
                                value  = buildingSearchResponse2.getList().get(which).getId();
                                strFloor = buildingSearchResponse2.getList().get(which).getBuilding_name();
                                checkSelectItem();
                                ivType02.setBackgroundResource(R.drawable.icon_floor_on);
                                btnJoin02.setBackgroundResource(R.drawable.box_on_01);
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //the user clicked on Cancel
                            }
                        });
                        builder.show();
                    }


                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private Handler resultHandler3 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){

                    buildingSearchResponse3 = new Gson().fromJson(msg.obj.toString(), BuildingSearchResponse.class);

                    if(buildingSearchResponse3!=null && buildingSearchResponse3.getList().size()>0){
                        ArrayList<BuildSearchData> arrayList = new ArrayList<>();
                        arrayList.addAll(buildingSearchResponse3.getList());
                        String[] items = new String[arrayList.size()];
                        for(int i=0; i<arrayList.size();i++){
                            items[i]= arrayList.get(i).getBuilding_name();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinStep3Activity.this);
                        builder.setCancelable(false);
                        builder.setTitle("호수 선택");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on options[which]
                                tvStore03.setText(buildingSearchResponse3.getList().get(which).getBuilding_name());
                                value  = buildingSearchResponse3.getList().get(which).getId();
                                strRoomNo = buildingSearchResponse3.getList().get(which).getBuilding_name();
                                checkSelectItem();
                                ivType03.setBackgroundResource(R.drawable.icon_numbaer_on);
                                btnJoin03.setBackgroundResource(R.drawable.box_on_01);
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //the user clicked on Cancel
                            }
                        });
                        builder.show();

                    }


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
                        final CommonAlertDialog dg = new CommonAlertDialog(JoinStep3Activity.this,false,true);
//                        dg.setTitle("계정 정보 확인");
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
