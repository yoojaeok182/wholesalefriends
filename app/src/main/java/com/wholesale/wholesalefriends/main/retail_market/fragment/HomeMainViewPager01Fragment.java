package com.wholesale.wholesalefriends.main.retail_market.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.BannerPageAdapter;
import com.wholesale.wholesalefriends.main.adapter.HomeMain01ListAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.BannerLIstData;
import com.wholesale.wholesalefriends.main.data.BannerList2Response;
import com.wholesale.wholesalefriends.main.data.BannerListResponse;
import com.wholesale.wholesalefriends.main.data.CategoryListResponse;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.retail_market.DetailProductActivity;
import com.wholesale.wholesalefriends.main.retail_market.ProductListActivity;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.AppData;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeMainViewPager01Fragment extends Fragment {

    private static Context ctx;

    private HomeMain01ListAdapter homeMain01ListAdapter = null;
    private ArrayList<ProductListData> listDatas = new ArrayList<>();
    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;


    private ViewPager viewPager;
    private TextView tvBannerName;
    private TextView tvBannerIndex;
    private TextView tvBannerTotalIndex;
    private LinearLayout btnMenu01;
    private LinearLayout btnMenu02;
    private LinearLayout btnMenu03;
    private LinearLayout btnMenu04;
    private LinearLayout btnMenu05;
    private LinearLayout btnMenu06;
    private ImageView ivPhoto01;
    private TextView tvPhotoName01;
    private TextView tvPhotoInfo01;
    private ImageView ivPhoto02;
    private TextView tvPhotoName02;
    private TextView tvPhotoInfo02;

    private ImageView ivPhoto03;
    private TextView tvPhotoName03;
    private TextView tvPhotoInfo03;

    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private ImageButton btnUp;
    private BannerPageAdapter bannerPageAdapter;
    private ArrayList<BannerLIstData> listBanner = new ArrayList<>();
    private LinearLayout btnStoreBanner01;
    private LinearLayout btnStoreBanner02;
    private LinearLayout btnStoreBanner03;

    private ImageView ivLineStoreBanner1;
    private ImageView ivLineStoreBanner2;



    private RequestManager requestManager;
    private boolean isShowVisible;

    public static HomeMainViewPager01Fragment newInstance(Context context) {
        ctx = context;
        HomeMainViewPager01Fragment fragment = new HomeMainViewPager01Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager_home_01, container, false);

        isSwipeRefresh = true;
        btnUp = view.findViewById(R.id.btnUp);

        btnStoreBanner01 = view.findViewById(R.id.btnStoreBanner01);
        btnStoreBanner02 = view.findViewById(R.id.btnStoreBanner02);
        btnStoreBanner03 = view.findViewById(R.id.btnStoreBanner03);
        viewPager = view.findViewById(R.id.viewPager);
        tvBannerName = view.findViewById(R.id.tvBannerName);
        tvBannerIndex = view.findViewById(R.id.tvBannerIndex);
        tvBannerTotalIndex = view.findViewById(R.id.tvBannerTotalIndex);
        btnMenu01 = view.findViewById(R.id.btnMenu01);
        btnMenu02 = view.findViewById(R.id.btnMenu02);
        btnMenu03 = view.findViewById(R.id.btnMenu03);
        btnMenu04 = view.findViewById(R.id.btnMenu04);
        btnMenu05 = view.findViewById(R.id.btnMenu05);
        btnMenu06 = view.findViewById(R.id.btnMenu06);
        ivPhoto01 = view.findViewById(R.id.ivPhoto01);
        tvPhotoName01 = view.findViewById(R.id.tvPhotoName01);
        tvPhotoInfo01 = view.findViewById(R.id.tvPhotoInfo01);
        ivPhoto02 = view.findViewById(R.id.ivPhoto02);
        tvPhotoName02 = view.findViewById(R.id.tvPhotoName02);
        tvPhotoInfo02 = view.findViewById(R.id.tvPhotoInfo02);

        ivPhoto03 = view.findViewById(R.id.ivPhoto03);
        tvPhotoName03 = view.findViewById(R.id.tvPhotoName03);
        tvPhotoInfo03 = view.findViewById(R.id.tvPhotoInfo03);

        ivLineStoreBanner1 = view.findViewById(R.id.ivLineStoreBanner1);
        ivLineStoreBanner2 = view.findViewById(R.id.ivLineStoreBanner2);


        appBarLayout = view.findViewById(R.id.appBarLayout);
        recyclerView = view.findViewById(R.id.recyclerView);

        requestManager = Glide.with(getActivity());
        homeMain01ListAdapter = new HomeMain01ListAdapter(getActivity(), listDatas);
        homeMain01ListAdapter.setAdapterListener(new HomeMain01ListAdapter.AdapterListener() {
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
                Intent intent = new Intent(ctx, DetailProductActivity.class);
                intent.putExtra(Constant.CommonKey.product_id,data.getId());
                intent.putExtra(Constant.CommonKey.product_name,data.getName());
                startActivity(intent);
            }
        });
        homeMain01ListAdapter.setnCurrentPage(1);

       /* final WrapContentGridLayoutManager manager = (WrapContentGridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addItemDecoration(new MarginDecoration(getActivity(),ctx.getResources().getDimensionPixelSize(R.dimen.item_margin_half2)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);*/
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(homeMain01ListAdapter);

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

        btnMenu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goProductList(0,Constant.CommonCategoryMenuName.arrHomeMenu[0]);

            }
        });

        btnMenu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(1,Constant.CommonCategoryMenuName.arrHomeMenu[1]);
            }
        });

        btnMenu03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(2,Constant.CommonCategoryMenuName.arrHomeMenu[2]);
            }
        });

        btnMenu04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(3,Constant.CommonCategoryMenuName.arrHomeMenu[3]);
            }
        });

        btnMenu05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(4,Constant.CommonCategoryMenuName.arrHomeMenu[4]);
            }
        });

        btnMenu06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(5,Constant.CommonCategoryMenuName.arrHomeMenu[5]);
            }
        });

        bannerList();
        bannerList2();
        loadList(1,"","","");
        return view;
    }

    private void goProductList(int position, String menuName){
        if(menuName.equals("전체")){
            Intent intent = new Intent(ctx, ProductListActivity.class);
            startActivity(intent);
        }else{
            CategoryListResponse response = AppData.getInstance().getCategoryListResponse();
            if(response!=null &&response.getList().size()>0){
                for(int i=0; i<response.getList().size();i++){
                    if(response.getList().get(i).getName().indexOf(menuName)>-1){
                        Intent intent = new Intent(ctx, ProductListActivity.class);
                        intent.putExtra(Constant.CommonKey.category_code,response.getList().get(i).getCode()+"");
                        intent.putExtra(Constant.CommonKey.category_name,response.getList().get(i).getName()+"");
                        startActivity(intent);
                    }
                }
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    private void bannerList() {
        API.banerList(ctx, 2 + "", resultHandler, errHandler);
    }

    private void bannerList2() {
        API.banerList(ctx, 1 + "", resultStoreHandler, errHandler);
    }

    private void loadList(int page,String category,String is_sale,String store_id) {
        homeMain01ListAdapter.setnCurrentPage(page);
        API.productList(ctx,page+"",category,is_sale,store_id,null,resultListHandler,errHandler);
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
                            homeMain01ListAdapter.clear();
                            homeMain01ListAdapter.addAll(productListResponse.getList().getData());

                            isSwipeRefresh = false;
                        }else{
                            if(homeMain01ListAdapter.getItemCount()>0){
                                int nowSize = homeMain01ListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getList().getData().size();i++){
                                    homeMain01ListAdapter.add(productListResponse.getList().getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getList().getData().size();i++){
                                    homeMain01ListAdapter.add(productListResponse.getList().getData().get(i),i);
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
    private Handler resultStoreHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                ivLineStoreBanner1.setVisibility(View.GONE);
                ivLineStoreBanner2.setVisibility(View.GONE);
                btnStoreBanner01.setVisibility(View.GONE);
                btnStoreBanner02.setVisibility(View.GONE);
                btnStoreBanner03.setVisibility(View.GONE);
                if (jsonObject.getBoolean("result")) {
                    BannerList2Response bannerListResponse = new Gson().fromJson(jsonObject.toString(), BannerList2Response.class);
                    if (bannerListResponse != null && bannerListResponse.getList().size() > 0) {
                        switch (bannerListResponse.getList().size()) {
                            case 1:
                                btnStoreBanner01.setVisibility(View.VISIBLE);

                                BannerLIstData bannerLIstData = bannerListResponse.getList().get(0);
                                Glide.with(ctx).load(bannerLIstData.getImage()).into(ivPhoto01);
                                tvPhotoName01.setText(bannerLIstData.getName());
                                tvPhotoInfo01.setText(bannerLIstData.getStore_addr());
                                btnStoreBanner01.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //TODO
                                    }
                                });
                                break;
                            case 2:
                                btnStoreBanner01.setVisibility(View.VISIBLE);
                                btnStoreBanner02.setVisibility(View.VISIBLE);
                                ivLineStoreBanner1.setVisibility(View.VISIBLE);
                                BannerLIstData bannerLIstData1 = bannerListResponse.getList().get(0);
                                Glide.with(ctx).load(bannerLIstData1.getImage()).into(ivPhoto01);
                                tvPhotoName01.setText(bannerLIstData1.getName());
                                tvPhotoInfo01.setText(bannerLIstData1.getStore_addr());
                                btnStoreBanner01.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        //TODO
                                    }
                                });

                                BannerLIstData bannerLIstData2 = bannerListResponse.getList().get(1);
                                Glide.with(ctx).load(bannerLIstData2.getImage()).into(ivPhoto02);
                                tvPhotoName02.setText(bannerLIstData2.getName());
                                tvPhotoInfo02.setText(bannerLIstData2.getStore_addr());
                                btnStoreBanner02.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //TODO
                                    }
                                });
                                break;

                            case 3:

                            {
                                btnStoreBanner01.setVisibility(View.VISIBLE);
                                btnStoreBanner02.setVisibility(View.VISIBLE);
                                btnStoreBanner03.setVisibility(View.VISIBLE);
                                ivLineStoreBanner1.setVisibility(View.VISIBLE);
                                ivLineStoreBanner2.setVisibility(View.VISIBLE);
                                BannerLIstData data1 = bannerListResponse.getList().get(0);
                                Glide.with(ctx).load(data1.getImage()).into(ivPhoto01);
                                tvPhotoName01.setText(data1.getName());
                                tvPhotoInfo01.setText(data1.getStore_addr());
                                btnStoreBanner01.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        //TODO
                                    }
                                });

                                BannerLIstData data2 = bannerListResponse.getList().get(1);
                                Glide.with(ctx).load(data2.getImage()).into(ivPhoto02);
                                tvPhotoName02.setText(data2.getName());
                                tvPhotoInfo02.setText(data2.getStore_addr());
                                btnStoreBanner02.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //TODO
                                    }
                                });

                                BannerLIstData data3 = bannerListResponse.getList().get(2);
                                Glide.with(ctx).load(data3.getImage()).into(ivPhoto03);
                                tvPhotoName03.setText(data3.getName());
                                tvPhotoInfo03.setText(data3.getStore_addr());
                                btnStoreBanner03.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //TODO
                                    }
                                });
                            }
                                break;
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    BannerListResponse bannerListResponse = new Gson().fromJson(jsonObject.toString(), BannerListResponse.class);

                    if (bannerListResponse != null && bannerListResponse.getList().size() > 0) {
                        tvBannerIndex.setText((1) + "");
                        tvBannerTotalIndex.setText("/"+(bannerListResponse.getList().size()) + "");
                        listBanner.clear();
                        listBanner.addAll(bannerListResponse.getList());



                        BannerPageAdapter bannerPageAdapter = new BannerPageAdapter(ctx, listBanner);
                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                BannerLIstData data = listBanner.get(position);
                                if (data != null) {
                                    tvBannerName.setText(data.getStore_addr());
                                    tvBannerIndex.setText((position + 1) + "/");
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        BannerLIstData data = listBanner.get(0);
                        if (data != null) {
                            tvBannerName.setText(data.getStore_addr());
                        }
                        viewPager.setAdapter(bannerPageAdapter);
                        viewPager.setCurrentItem(0);
                        bannerPageAdapter.notifyDataSetChanged();
                    } else {
                        tvBannerIndex.setText((0) + "");
                        tvBannerTotalIndex.setText("/"+(0) + "");
                    }

                } else {
                    tvBannerIndex.setText((0) + "");
                    tvBannerTotalIndex.setText("/"+(0) + "");
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
                        final CommonAlertDialog dg = new CommonAlertDialog(ctx, false, true);
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
}
