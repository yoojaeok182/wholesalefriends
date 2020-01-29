package com.wholesale.wholesalefriends.main.retail_market;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.NoticeAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.NoticeListData;
import com.wholesale.wholesalefriends.main.data.NoticeListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class NoticeActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private RecyclerView recyclerView;

    private boolean isSwipeRefresh;
    private boolean isExistMore;
    private ArrayList<NoticeListData> list = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.recyclerView);

        noticeAdapter = new NoticeAdapter(this, list);
        tvTitle.setText("공지사항");

        init();

        clearData();
        loadList(1);
    }

    private void init(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        recyclerView.setAdapter(noticeAdapter);

    }

    private void clearData(){
        isSwipeRefresh = true;
        isExistMore =false;
        noticeAdapter.clear();
    }
    private void loadList(int page){
        noticeAdapter.setnCurrentPage(page);
        API.noticeList(NoticeActivity.this,page+"",resultListHandler,errHandler);
    }

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    NoticeListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), NoticeListResponse.class);
                    if(productListResponse!=null && productListResponse.getData().size()>0){

                        if(isSwipeRefresh){
                            noticeAdapter.clear();
                            noticeAdapter.addAll(productListResponse.getData());
                            isSwipeRefresh = false;
                        }else{
                            if(noticeAdapter.getItemCount()>0){
                                int nowSize = noticeAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getData().size();i++){
                                    noticeAdapter.add(productListResponse.getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getData().size();i++){
                                    noticeAdapter.add(productListResponse.getData().get(i),i);
                                }
                            }
                        }

                        if(productListResponse.getData().size() >= Constant.PAGE_GO){
                            isExistMore = true;
                        }else{
                            isExistMore = false;
                        }
                    }else{
                        isExistMore = false;
                    }
                }else{
                    isExistMore = false;
                }
            }catch (Throwable e){
                isExistMore = false;
                e.printStackTrace();
            }
        }
    };

    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(NoticeActivity.this, false, true);
                        dg.setTitle("공지사항");
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };
}
