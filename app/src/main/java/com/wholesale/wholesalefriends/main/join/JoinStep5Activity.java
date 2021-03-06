package com.wholesale.wholesalefriends.main.join;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.StoreSearchListAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.data.BuildingSearchResponse;
import com.wholesale.wholesalefriends.main.data.StoreSearchListData;
import com.wholesale.wholesalefriends.main.data.StoreSearchListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.AutofitRecyclerView;
import com.wholesale.wholesalefriends.widget.Margin2Decoration;
import com.wholesale.wholesalefriends.widget.MarginDecoration;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class JoinStep5Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private EditText edtSearch;
    private LinearLayout btnSearch;
    private LinearLayout btnAgain;
    private AutofitRecyclerView recyclerView;
    private Button btnOk;

    private StoreSearchListAdapter storeSearchListAdapter;
    private ArrayList<StoreSearchListData> listDatas = new ArrayList<>();

    private int level = 0;
    private int store_type = -1;

    private String store_name;
    private int store_id;
    private int nWholesaleMode = -1;
    private TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_step_05);
        Intent intent = getIntent();
        if (intent.hasExtra("store_id")) {
            store_id = intent.getExtras().getInt("store_id");
        }

        if (intent.hasExtra("level")) {
            level = intent.getExtras().getInt("level");
        }

        if (intent.hasExtra("store_type")) {
            store_type = intent.getExtras().getInt("store_type");
        }


        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnAgain = findViewById(R.id.btnAgain);
        recyclerView = findViewById(R.id.recyclerView);
        btnOk = findViewById(R.id.btnOk);
        tvCount = findViewById(R.id.tvCount);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSearch.getText().toString().trim().length()>0){
                    hideKeyboardFrom(JoinStep5Activity.this,edtSearch);
                    loadList(edtSearch.getText().toString().trim());
                }

            }
        });
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    hideKeyboardFrom(JoinStep5Activity.this,edtSearch);
                    loadList(edtSearch.getText().toString().trim());
                }
                return false;
            }
        });

        storeSearchListAdapter = new StoreSearchListAdapter(this, listDatas);
        storeSearchListAdapter.setnCurrentPage(1);
        storeSearchListAdapter.setMeetingPlaceListener(new StoreSearchListAdapter.ListMeetingPlaceListener() {
            @Override
            public void moreLoading(int page) {

            }

            @Override
            public void itemClick(int pos, StoreSearchListData data) {
                btnOk.setBackgroundResource(R.drawable.btn_02);
                if(data.isCheck())
                    btnOk.setBackgroundResource(R.drawable.btn_02_on);

                store_id = data.getId();
                store_name = data.getStore_name();
                storeSearchListAdapter.notifyDataSetChanged();
            }
        });
        final WrapContentGridLayoutManager manager = (WrapContentGridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addItemDecoration(new Margin2Decoration(JoinStep5Activity.this,getResources().getDimensionPixelSize(R.dimen.item_margin_half2)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(storeSearchListAdapter);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(store_id ==-1){
                    return;
                }

                Intent intent = new Intent(JoinStep5Activity.this,JoinStep6Activity.class);
                intent.putExtra("store_type", store_type);
                intent.putExtra("store_id", store_id);
                intent.putExtra("level", level);
                intent.putExtra("store_name", store_name);
                startActivity(intent);
            }
        });

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store_id = -1;
                storeSearchListAdapter.clear();
                edtSearch.setText("");
                edtSearch.setHint("상호명을 검색해 주세요.");
            }
        });

    }

    private void loadList(String search) {

        API.storeSearch(this, store_type + "", search, resultHandler, errHandler);
    }

    private Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    StoreSearchListResponse storeSearchListResponse = new Gson().fromJson(msg.obj.toString(), StoreSearchListResponse.class);
                    if(storeSearchListResponse!=null && storeSearchListResponse.getList().size()>0){
                        storeSearchListAdapter.clear();
                        storeSearchListAdapter.addAll(storeSearchListResponse.getList());

                        tvCount.setText("" + storeSearchListAdapter.getItemCount());
                    }

                }
            } catch (Throwable e) {
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
                        final CommonAlertDialog dg = new CommonAlertDialog(JoinStep5Activity.this, false, true);
                        dg.setTitle("회원가입");
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
