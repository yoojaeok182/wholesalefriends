package com.wholesale.wholesalefriends.main.wholesale_market.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.ProductList2Adapter;
import com.wholesale.wholesalefriends.main.adapter.ProductListListAdapter;
import com.wholesale.wholesalefriends.main.adapter.ProductTop30ListAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.data.TopStoreListData;
import com.wholesale.wholesalefriends.main.data.TopStoreListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.join.JoinStep3Activity;
import com.wholesale.wholesalefriends.main.wholesale_market.DetailProduct2Activity;
import com.wholesale.wholesalefriends.main.wholesale_market.Main2Activity;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.widget.MarginDecoration;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private EditText tvCategorySearch;
    private ImageButton btnManagerment;
    private RecyclerView horRecyclerView;
    private LinearLayout btnAutoRecommAdd;
    private TextView btnHidden;
    private TextView tvTobMenu01;
    private LinearLayout llayoutTopMenu01;
    private TextView tvTobMenu02;
    private LinearLayout btnSort;
    private LinearLayout llayoutTopMenu02;
    private LinearLayout llayoutTopLine01;
    private LinearLayout llayoutTopLine02;
    private TextView tvSearchItem;
    private LinearLayout btnProcuctManage;
    private LinearLayout btnFilter;
    private RecyclerView recyclerView;
    private AppBarLayout appBarLayout;
    private ImageButton btnUp;
    private ImageView ivSearch;
    private String search;
    private ArrayList<ProductListData> listDatas = new ArrayList<>();
    private ArrayList<TopStoreListData> listTop30 = new ArrayList<>();


    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;

    private RelativeLayout btnPMClose;
    private TextView tvSelectItemCount;
    private RelativeLayout btnAllCheck;
    private RelativeLayout btnRemove;
    private ImageView ivAllCheck;

    private LinearLayout btnReWareHousing;
    private LinearLayout btnSoldOut;
    private LinearLayout btnTop30;

    private ProductList2Adapter productList2Adapter;
    private ProductTop30ListAdapter productTop30ListAdapter;

    private String[] sortName = {"","sale","soldout"};
    private String sort_type="";
    private int open_type = 0;
    private String strKeyword="";

    private String p_id="";

    private boolean isAllCheck;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnSort= view.findViewById(R.id.btnSort);
        tvCategorySearch = view.findViewById(R.id.tvCategorySearch);
        btnManagerment = view.findViewById(R.id.btnManagerment);
        horRecyclerView = view.findViewById(R.id.horRecyclerView);
        btnAutoRecommAdd = view.findViewById(R.id.btnAutoRecommAdd);
        btnHidden = view.findViewById(R.id.btnHidden);
        tvTobMenu01 = view.findViewById(R.id.tvTobMenu01);
        llayoutTopMenu01 = view.findViewById(R.id.llayoutTopMenu01);
        tvTobMenu02 = view.findViewById(R.id.tvTobMenu02);
        llayoutTopMenu02 = view.findViewById(R.id.llayoutTopMenu02);
        llayoutTopLine01 = view.findViewById(R.id.llayoutTopLine01);
        llayoutTopLine02 = view.findViewById(R.id.llayoutTopLine02);
        tvSearchItem = view.findViewById(R.id.tvSearchItem);
        btnProcuctManage = view.findViewById(R.id.btnProcuctManage);
        btnFilter = view.findViewById(R.id.btnFilter);
        recyclerView = view.findViewById(R.id.recyclerView);
        appBarLayout =  view.findViewById(R.id.appBarLayout);
        ivSearch = view.findViewById(R.id.ivSearch);
        btnUp = view.findViewById(R.id.btnUp);

        init();

        clearData();
        sort_type = "";
        open_type = 0;
        strKeyword = "";

        loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                "","","","");

        clearTop30Data();
        loadTop30List();
        return view;
    }

    private void init(){
        String[] items01 =getResources().getStringArray(R.array.order_sort_type);
        tvSearchItem.setText(items01[0]);


        productList2Adapter = new ProductList2Adapter(getActivity(),listDatas);
        productList2Adapter.setAdapterListener(new ProductList2Adapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {

                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadList(page+1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                                    strKeyword,sort_type,open_type+"","");
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void onCheckItem(ProductListData data, int pos,boolean isCheck) {
                productList2Adapter.getItem(pos).setCheck(isCheck);
                productList2Adapter.notifyDataSetChanged();
            }

            @Override
            public void onClickItem(ProductListData data, int pos) {
                Intent intent = new Intent(getActivity(), DetailProduct2Activity.class);
                intent.putExtra(Constant.CommonKey.product_id,data.getId());
                intent.putExtra(Constant.CommonKey.product_name,data.getName());
                startActivity(intent);
            }
        });
        productList2Adapter.setnCurrentPage(1);

        final WrapContentGridLayoutManager manager = (WrapContentGridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addItemDecoration(new MarginDecoration(getActivity(),getResources().getDimensionPixelSize(R.dimen.item_margin_half2)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(productList2Adapter);


        productTop30ListAdapter = new ProductTop30ListAdapter(getActivity(),listTop30);
        productTop30ListAdapter.setAdapterListener(new ProductTop30ListAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {

            }

            @Override
            public void onClickItem(TopStoreListData data, int pos) {

            }
        });
        horRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        horRecyclerView.setAdapter(productTop30ListAdapter);





        btnProcuctManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Main2Activity.getInstance()!=null){
                    Main2Activity.getInstance().setVisibilityProductManager(true);
                }

                productList2Adapter.setProductManager(true);
                productList2Adapter.notifyDataSetChanged();
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO
            }
        });
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      String[] items01 =getResources().getStringArray(R.array.order_sort_type);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("정렬");
                builder.setSingleChoiceItems(items01, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sort_type =sortName[which];
                        tvCategorySearch.setText(items01[which]);
                        loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                                strKeyword,sort_type,open_type+"","");
                        dialog.dismiss();
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
        });
        llayoutTopMenu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMenu(0);
                clearData();
                loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                        strKeyword,sort_type,open_type+"","");
            }
        });

        llayoutTopMenu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMenu(1);
                clearData();
                loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                        strKeyword,sort_type,open_type+"","");
            }
        });


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appBarLayout.setExpanded(true);
                recyclerView.smoothScrollToPosition(0);
            }
        });


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (appBarLayout.getTotalScrollRange() == 0 || verticalOffset == 0) {
                    btnUp.setVisibility(View.GONE);
                    return;
                }

                btnUp.setVisibility(View.VISIBLE);

            }
        });

        btnHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                final CommonAlertDialog dg = new CommonAlertDialog(getActivity(),true,true);
                dg.setTitle("우리매장 TOP30자동등록");
                dg.setMessage("숨기기를 하면 이미 등록된 우리매장 TOP30 상품이 비워집니다.\n\n\n\n" +
                        "* 우리매장 TOP 30을 다시 사용하시려면 아래 매장TOP 버튼을 이용해 등록해주시면 됩니다.\n\n\n\n" +
                        "* 추천상품 자동등록 버튼으로 우리매장 TOP30을 편하게 관리해보세요.");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(dg.isOk()){
                            API.topDel(getActivity(),SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.user_no)+"",resultOkListHandler,errHandler);
                        }
                    }
                });
                dg.show();
            }
        });
        btnAutoRecommAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                final CommonAlertDialog dg = new CommonAlertDialog(getActivity(),true,true);
                dg.setTitle("우리매장 TOP30 자동등록");
                dg.setMessage("자동등록은 우리매장의 인기상품을 일괄 등록하는 기능입니다.\n\n\n\n*기존 등록된 우리매장 TOP30이 모두 삭제되니 주의해주세요!\n\n\n" +
                        "\n" +
                        "*자동등록된 우리매장 TOP30은 절대적인 순위가 아닙니다. 따라서 매장에서 밀고 싶은 상품을 30개까지 추가 등록하여 사용하는 것을 권장합니다.");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(dg.isOk()){

                            API.topAuto(getActivity(),SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.user_no)+"",resultOkListHandler,errHandler);
                        }
                    }
                });
                dg.show();
            }
        });
        btnManagerment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardFrom(getActivity(),tvCategorySearch);
                strKeyword = tvCategorySearch.getText().toString();
                loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                        strKeyword,sort_type,open_type+"","");
            }
        });

        tvCategorySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    hideKeyboardFrom(getActivity(),tvCategorySearch);
                    strKeyword = tvCategorySearch.getText().toString();
                    loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                            strKeyword,sort_type,open_type+"","");
                }
                return false;
            }
        });
        tvCategorySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void getRefresh(){
        clearData();
        loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                strKeyword,sort_type,open_type+"","");
    }
    public void requestProcuctManager(RelativeLayout btnAllCheck,ImageView ivAllCheck,TextView tvSelectItemCount,LinearLayout btnReWareHousing,LinearLayout btnSoldOut,LinearLayout btnTop30 ){
        isAllCheck = false;
        int count = 0;
        for(int i=0; i<productList2Adapter.getItemCount();i++){
            if(productList2Adapter.getItem(i).isCheck()){
                count ++;
            }
        }

        tvSelectItemCount.setText(count+"");
        btnAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAllCheck){
                    ivAllCheck.setBackgroundResource(R.drawable.check_default);
                    isAllCheck = false;
                }else{
                    ivAllCheck.setBackgroundResource(R.drawable.check_on);
                    isAllCheck = true;
                }

                for(int i=0; i<productList2Adapter.getItemCount();i++){
                    productList2Adapter.getItem(i).setCheck(isAllCheck);
                }

                productList2Adapter.notifyDataSetChanged();
            }
        });

        // 재입고
        btnReWareHousing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_id = "";
                for(int i=0; i<productList2Adapter.getItemCount();i++){
                    if(productList2Adapter.getItem(i).getId()>0 &&productList2Adapter.getItem(i).isCheck()){
                        p_id = p_id+productList2Adapter.getItem(i).getId()+"||";
                    }
                }
                API.restock(getActivity(),SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.user_no)+"",p_id,resultOkListHandler,errHandler);
            }
        });

        //품절
        btnSoldOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_id = "";
                for(int i=0; i<productList2Adapter.getItemCount();i++){
                    if(productList2Adapter.getItem(i).getId()>0 &&productList2Adapter.getItem(i).isCheck()){
                        p_id = p_id+productList2Adapter.getItem(i).getId()+"||";
                    }
                }
                API.soldOut(getActivity(),SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.user_no)+"",p_id,resultOkListHandler,errHandler);
            }
        });

        btnTop30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_id = "";
                for(int i=0; i<productList2Adapter.getItemCount();i++){
                    if(productList2Adapter.getItem(i).getId()>0 &&productList2Adapter.getItem(i).isCheck()){
                        p_id = p_id+productList2Adapter.getItem(i).getId()+"||";
                    }
                }
                API.topAdd(getActivity(),SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.user_no)+"",p_id,resultOkListHandler,errHandler);
            }
        });
    }

    public void setHiddenAdapterCheck(){
        productList2Adapter.setProductManager(false);
        productList2Adapter.notifyDataSetChanged();

    }
    public  void setManagerMode(RelativeLayout close,TextView tvItemCount,RelativeLayout allCheckBtn,ImageView allCheckIv,RelativeLayout remove,LinearLayout reWareHousing,LinearLayout soldOut,LinearLayout top30Btn){
        btnPMClose = close;
        tvSelectItemCount = tvItemCount;
        btnAllCheck = allCheckBtn;
        ivAllCheck = allCheckIv;
        btnRemove = remove;
        btnReWareHousing = reWareHousing;
        btnSoldOut = soldOut;
        btnTop30 = top30Btn;

        btnPMClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Main2Activity.getInstance()!=null){
                    Main2Activity.getInstance().setVisibilityProductManager(false);
                }
            }
        });

        btnAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnReWareHousing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSoldOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTop30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });


    }
    private void selectMenu(int pos){
        llayoutTopLine01.setVisibility(View.INVISIBLE);
        llayoutTopLine02.setVisibility(View.INVISIBLE);
        tvTobMenu01.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));
        tvTobMenu02.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));

        switch (pos){
            case 0:

                open_type = 0;
                llayoutTopLine01.setVisibility(View.VISIBLE);
                tvTobMenu01.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 1:
                open_type = 1;
                llayoutTopLine02.setVisibility(View.VISIBLE);
                tvTobMenu02.setTextColor(getResources().getColor(R.color.color_text_07));
                break;

        }
    }

    private void clearTop30Data(){
        productTop30ListAdapter.clear();
    }
    private void  clearData(){

        productList2Adapter.clear();
        isSwipeRefresh = true;
        isExistMore = false;
    }
    private void loadTop30List() {
        int user_no = SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.user_no);
        API.topList(getActivity(),user_no+"",resultListTop30Handler,errHandler);
    }

    private void loadList(int page,String category,String is_sale,String store_id,
                          String keyword,String orderBy,String open_type,String is_soldout) {
        productList2Adapter.setnCurrentPage(page);
        API.productList2(getActivity(),page+"",category,is_sale,store_id,keyword,orderBy,open_type,is_soldout,resultListHandler,errHandler);
    }

    private Handler resultListTop30Handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    TopStoreListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), TopStoreListResponse.class);
                    if(productListResponse!=null && productListResponse.getList().size()>0){

                        clearTop30Data();
                        productTop30ListAdapter.addAll(productListResponse.getList());
                    }else{
                    }
                }else{
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private Handler resultOkListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    clearData();
                    loadList(1,"","",SharedPreference.getIntSharedPreference(getActivity(), Constant.CommonKey.store_id)+"",
                            strKeyword,sort_type,open_type+"","");
                }else{
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    ProductListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), ProductListResponse.class);
                    if(productListResponse!=null && productListResponse.getList().getData().size()>0){

                        if(isSwipeRefresh){
                            productList2Adapter.clear();
                            productList2Adapter.addAll(productListResponse.getList().getData());

                            isSwipeRefresh = false;
                        }else{
                            if(productList2Adapter.getItemCount()>0){
                                int nowSize = productList2Adapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getList().getData().size();i++){
                                    productList2Adapter.add(productListResponse.getList().getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getList().getData().size();i++){
                                    productList2Adapter.add(productListResponse.getList().getData().get(i),i);
                                }
                            }
                        }

                        if(productListResponse.getList().getData().size() >= Constant.PAGE_GO){
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
                        final CommonAlertDialog dg = new CommonAlertDialog(getActivity(), false, false);
                        dg.setTitle("계정 정보 확인");
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
