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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.ProductListListAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.wholesale_market.DetailProduct2Activity;
import com.wholesale.wholesalefriends.main.wholesale_market.Main2Activity;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.MarginDecoration;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

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

    private ProductListListAdapter productListListAdapter;
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
        loadList(1,"","","");
        return view;
    }

    private void init(){

        productListListAdapter = new ProductListListAdapter(getActivity(),listDatas);
        productListListAdapter.setAdapterListener(new ProductListListAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {

                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadList(page+1,"","","");
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void onClickItem(ProductListData data, int pos) {
                Intent intent = new Intent(getActivity(), DetailProduct2Activity.class);
                intent.putExtra(Constant.CommonKey.product_id,data.getId());
                intent.putExtra(Constant.CommonKey.product_name,data.getName());
                startActivity(intent);
            }
        });
        productListListAdapter.setnCurrentPage(1);

        final WrapContentGridLayoutManager manager = (WrapContentGridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addItemDecoration(new MarginDecoration(getActivity(),getResources().getDimensionPixelSize(R.dimen.item_margin_half2)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(productListListAdapter);

        tvCategorySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    hideKeyboardFrom(getActivity(),tvCategorySearch);
//                    loadList(tvCategorySearch.getText().toString().trim());
                }
                return false;
            }
        });

        btnProcuctManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Main2Activity.getInstance()!=null){
                    Main2Activity.getInstance().setVisibilityProductManager(true);
                }

            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llayoutTopMenu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMenu(0);
            }
        });

        llayoutTopMenu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMenu(1);
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
                if (tvCategorySearch.getText() != null && tvCategorySearch.getText().toString() != null && tvCategorySearch.getText().toString().length() > 0) {
                    search = tvCategorySearch.getText().toString();
                } else {
                    search = null;
                }
                hideKeyboardFrom(getActivity(), tvCategorySearch);
            }
        });

        tvCategorySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (tvCategorySearch.getText() != null && tvCategorySearch.getText().toString() != null && tvCategorySearch.getText().toString().length() > 0) {
                        search = tvCategorySearch.getText().toString();
                    } else {
                        search = null;
                    }
                    hideKeyboardFrom(getActivity(), tvCategorySearch);
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
              /*  final CommonAlertDialog dg = new CommonAlertDialog(getActivity(),true,true);
                dg.setTitle("우리매장 TOP30 등록하기");
                dg.setMessage("자동등록은 우리매장의 인기상품을 일괄 등록하는 기능입니다.\n\n\n\n*기존 등록된 우리매장 TOP30이 모두 삭제되니 주의해주세요!\n\n\n" +
                        "\n" +
                        "*자동등록된 우리매장 TOP30은 절대적인 순위가 아닙니다. 따라서 매장에서 밀고 싶은 상품을 30개까지 추가 등록하여 사용하는 것을 권장합니다.");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(dg.isOk()){

                        }
                    }
                });
                dg.show();*/
            }
        });


    }
    private void selectMenu(int pos){
        llayoutTopLine01.setVisibility(View.GONE);
        llayoutTopLine02.setVisibility(View.GONE);
        tvTobMenu01.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));
        tvTobMenu02.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));

        switch (pos){
            case 0:

                llayoutTopLine01.setVisibility(View.VISIBLE);
                tvTobMenu01.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 1:
                llayoutTopLine02.setVisibility(View.VISIBLE);
                tvTobMenu02.setTextColor(getResources().getColor(R.color.color_text_07));
                break;

        }
    }

    private void  clearData(){

        productListListAdapter.clear();
        isSwipeRefresh = true;
        isExistMore = false;
    }
    private void loadList(int page,String category,String is_sale,String store_id) {
        productListListAdapter.setnCurrentPage(page);
        //TODO 바뀔수 있음
        API.productList(getActivity(),page+"",category,is_sale,store_id,resultListHandler,errHandler);
    }

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    ProductListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), ProductListResponse.class);
                    if(productListResponse!=null && productListResponse.getList().getData().size()>0){

                        if(isSwipeRefresh){
                            productListListAdapter.clear();
                            productListListAdapter.addAll(productListResponse.getList().getData());

                            isSwipeRefresh = false;
                        }else{
                            if(productListListAdapter.getItemCount()>0){
                                int nowSize = productListListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getList().getData().size();i++){
                                    productListListAdapter.add(productListResponse.getList().getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getList().getData().size();i++){
                                    productListListAdapter.add(productListResponse.getList().getData().get(i),i);
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
