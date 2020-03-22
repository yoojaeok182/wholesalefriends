package com.wholesale.wholesalefriends.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;

import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.BannerLIstData;
import com.wholesale.wholesalefriends.main.data.BestProductResponse;
import com.wholesale.wholesalefriends.main.data.BuildSearchData;
import com.wholesale.wholesalefriends.main.data.BuildingListData;
import com.wholesale.wholesalefriends.main.data.CartGroupListResponse;
import com.wholesale.wholesalefriends.main.data.CartListResponse;
import com.wholesale.wholesalefriends.main.data.CategoryLIstData;
import com.wholesale.wholesalefriends.main.data.CodeListData;
import com.wholesale.wholesalefriends.main.data.NoticeListResponse;
import com.wholesale.wholesalefriends.main.data.PaymentGroupListResponse;
import com.wholesale.wholesalefriends.main.data.ProductQnaListResponse;
import com.wholesale.wholesalefriends.main.data.ProductResponse;
import com.wholesale.wholesalefriends.main.data.ProductViewResponse;
import com.wholesale.wholesalefriends.main.data.ProductViewWholesaleResponse;
import com.wholesale.wholesalefriends.main.data.RecomWordData;
import com.wholesale.wholesalefriends.main.data.SideCountData;
import com.wholesale.wholesalefriends.main.data.StoreListResponse;
import com.wholesale.wholesalefriends.main.data.StoreSearchData;
import com.wholesale.wholesalefriends.main.data.TopStoreListData;
import com.wholesale.wholesalefriends.main.dialog.LoadingDialog;
import com.wholesale.wholesalefriends.module.util.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by kst on 2017-04-25.
 */

public class Retrofit {

    private Context mContext;
    private retrofit2.Retrofit mRetrofit;
    private LoadingDialog dg = null;
    private boolean isShowLoading;
    public Retrofit(Context context,boolean isLoading){
        mContext = context;

        isShowLoading = isLoading;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(dg != null&& dg.isShowing())dg.dismiss();
        dg = new LoadingDialog(mContext);
        if(dg!=null && isShowLoading)dg.show();
        String url = "";
        url = Constant.DOMAIN.url;

        mRetrofit = new retrofit2.Retrofit.Builder()
                .baseUrl( url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }
    public Retrofit(Context context){
        mContext = context;

        isShowLoading = true;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(dg != null&& dg.isShowing())dg.dismiss();
        dg = new LoadingDialog(mContext);
        if(dg!=null && isShowLoading)dg.show();
        String url = "";
        url = Constant.DOMAIN.url;

        mRetrofit = new retrofit2.Retrofit.Builder()
                .baseUrl( url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    private interface UploadLoginInterface{
        @Multipart
        @POST
        Call<ContributorLogin> upload(
                /**
                 * REST API 등록
                 */
                @Part("id") RequestBody id,
                @Part("pw") RequestBody pwd, @Url String apiUrl
        );
    }

    private interface UploadJoin1Interface{
        @Multipart
        @POST
        Call<ContributorJoin> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_type") RequestBody store_type,
                @Part("level") RequestBody level,
                @Part("name") RequestBody name,
                @Part("id") RequestBody id,
                @Part("password") RequestBody password,
                @Part("mobile") RequestBody mobile,

                @Part("store_name") RequestBody store_name,
                @Part("store_number") RequestBody store_number,
                @Part("store_building_id") RequestBody store_building_id,
                @Part("store_addr") RequestBody store_addr,

                @Part MultipartBody.Part file, @Url String apiUrl);
    }

    private interface UploadJoin1_noBitmapInterface{
        @Multipart
        @POST
        Call<ContributorJoin> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_type") RequestBody store_type,
                @Part("level") RequestBody level,
                @Part("name") RequestBody name,
                @Part("id") RequestBody id,
                @Part("password") RequestBody password,
                @Part("mobile") RequestBody mobile,

                @Part("store_name") RequestBody store_name,
                @Part("store_number") RequestBody store_number,
                @Part("store_building_id") RequestBody store_building_id,
                @Part("store_addr") RequestBody store_addr,

                @Url String apiUrl);
    }

    private interface UploadJoin2Interface{
        @Multipart
        @POST
        Call<ContributorJoin> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_type") RequestBody store_type,
                @Part("level") RequestBody level,
                @Part("name") RequestBody name,
                @Part("id") RequestBody id,
                @Part("password") RequestBody password,
                @Part("mobile") RequestBody mobile,

                @Part("store_name") RequestBody store_name,
                @Part("store_number") RequestBody store_number,
                @Part("store_addr") RequestBody store_addr,
                @Part("store_onoﬀ") RequestBody store_onoﬀ,
                @Part("store_site") RequestBody store_site,
                @Part("store_site_url") RequestBody store_site_url,
                @Part MultipartBody.Part file, @Url String apiUrl
        );
    }

    private interface UploadJoin2_noBitmapInterface{
        @Multipart
        @POST
        Call<ContributorJoin> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_type") RequestBody store_type,
                @Part("level") RequestBody level,
                @Part("name") RequestBody name,
                @Part("id") RequestBody id,
                @Part("password") RequestBody password,
                @Part("mobile") RequestBody mobile,

                @Part("store_name") RequestBody store_name,
                @Part("store_number") RequestBody store_number,
                @Part("store_addr") RequestBody store_addr,
                @Part("store_onoﬀ") RequestBody store_onoﬀ,
                @Part("store_site") RequestBody store_site,
                @Part("store_site_url") RequestBody store_site_url,
                @Url String apiUrl
        );
    }

    private interface UploadJoin3Interface{
        @Multipart
        @POST
        Call<ContributorJoin> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_type") RequestBody store_type,
                @Part("level") RequestBody level,
                @Part("name") RequestBody name,
                @Part("id") RequestBody id,
                @Part("password") RequestBody password,
                @Part("mobile") RequestBody mobile,
                @Part("store_id") RequestBody store_id, @Url String apiUrl
        );
    }

    private interface UploadBuildingSearch1Interface{
        @Multipart
        @POST
        Call<ContributorBuildSearch> upload(
                /**
                 * REST API 등록
                 */
                @Part("depth") RequestBody depth,
                @Part("value") RequestBody value, @Url String apiUrl
        );
    }



    private interface UploadStoreSearch1Interface{
        @Multipart
        @POST
        Call<ContributorStoreSearch> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_type") RequestBody depth,
                @Part("name") RequestBody value, @Url String apiUrl
        );
    }


    private interface UploadBannerListInterface{
        @Multipart
        @POST
        Call<ContributorBannerList> upload(
                /**
                 * REST API 등록
                 */
                @Part("type") RequestBody depth,
                @Url String apiUrl
        );
    }

    private interface UploadCategoryListInterface{
        @POST
        Call<ContributorCategoryList> upload(
                /**
                 * REST API 등록
                 */
                @Url String apiUrl
        );
    }
    private interface UploadCodeListInterface{
        @Multipart
        @POST
        Call<ContributorCodeList> upload(
                /**
                 * REST API 등록
                 */
                @Part("code") RequestBody depth,
                @Url String apiUrl
        );
    }

    private interface UploadProductListInterface{
        @Multipart
        @POST
        Call<ContributorProductList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
                @Part("category") RequestBody category,
                @Part("is_sale") RequestBody is_sale,
                @Part("store_id") RequestBody store_id,
                @Part("is_soldout") RequestBody is_soldout,

                @Url String apiUrl
        );
    }

    private interface UploadProductListSearchInterface{
        @Multipart
        @POST
        Call<ContributorProductList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
                @Part("category") RequestBody category,
                @Part("is_sale") RequestBody is_sale,
                @Part("store_id") RequestBody store_id,
                @Part("keyword") RequestBody keyword,
                @Part("is_soldout") RequestBody is_soldout,

                @Url String apiUrl
        );
    }


    private interface UploadProductList2Interface{
        @Multipart
        @POST
        Call<ContributorProductList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
                @Part("category") RequestBody category,
                @Part("is_sale") RequestBody is_sale,
                @Part("store_id") RequestBody store_id,
                @Part("keyword") RequestBody keyword,
                @Part("orderby") RequestBody orderby,
                @Part("open_type") RequestBody open_type,
                @Part("is_soldout") RequestBody is_soldout,
                @Url String apiUrl
        );
    }

    private interface UploadBestProductListInterface{
        @Multipart
        @POST
        Call<ContributorBestProductList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
               /*
                @Part("category") RequestBody category,
                @Part("is_sale") RequestBody is_sale,
                @Part("store_id") RequestBody store_id,*/
                @Url String apiUrl
        );
    }

    private interface UploadBestProductList2Interface{
        @Multipart
        @POST
        Call<ContributorBestProductList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
               /*
                @Part("category") RequestBody category,
                @Part("is_sale") RequestBody is_sale,
                @Part("store_id") RequestBody store_id,*/
                @Url String apiUrl
        );
    }


    private interface UploadProductViewInterface{
        @Multipart
        @POST
        Call<ContributorProductView> upload(
                /**
                 * REST API 등록
                 */
                @Part("id") RequestBody page,
                @Part("user_id") RequestBody category,
                @Url String apiUrl
        );
    }

    private interface UploadStoreBuildingLIstInterface{
//        @Multipart
        @POST
        Call<ContributorBuildingList> upload(
                /**
                 * REST API 등록
                 */
                @Url String apiUrl
        );
    }

    private interface UploadStoreOfViewLIstInterface{
        @Multipart
        @POST
        Call<ContributorStoreViewList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
                @Part("building_id") RequestBody building_id,
                @Part("user_id") RequestBody user_id,
                @Part("keyword") RequestBody keyword,
                @Url String apiUrl
        );
    }



    private interface UploadProductAddInterface{
        @Multipart
        @POST
        Call<ContributorProductAdd> upload(
                /**
                 * REST API 등록
                 */
                @Part("name") RequestBody name,
                @Part("price") RequestBody price,
                @Part("category") RequestBody category,
                @Part("material_info") RequestBody material_info,
                @Part("cloth_info_1") RequestBody cloth_info1,
                @Part("cloth_info_2") RequestBody cloth_info2,
                @Part("cloth_info_3") RequestBody cloth_info3,
                @Part("cloth_info_4") RequestBody cloth_info4,

                @Part("washing_info") RequestBody washing_info,

                @Part("style_info") RequestBody style_info,
                @Part("option_1") RequestBody option_1,
                @Part("option_2") RequestBody option_2,
                @Part("detail") RequestBody detail,
                @Part("origin_info") RequestBody origin_info,
                @Part("store_id") RequestBody store_id,
                @Part("open_type") RequestBody open_type,
                @Part("open_day") RequestBody open_day,
                @Part("kakao_open") RequestBody kakao_open,
                @Part("global_open") RequestBody global_open,
                @Part List<MultipartBody.Part> file,
                @Url String apiUrl
        );
    }

    private interface UploadFavoritesInterface{
        @Multipart
        @POST
        Call<ContributorFavorites> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("type") RequestBody type,
                @Part("target_id") RequestBody target_id,@Url String apiUrl
        );
    }


    private interface UploadNoticeInterface{
        @Multipart
        @POST
        Call<ContributorNoticeList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
                 @Url String apiUrl
        );
    }

    private interface UploadRecommWordInterface{
//        @Multipart
        @POST
        Call<ContributorRecomWord> upload(
                /**
                 * REST API 등록
                 */
               @Url String apiUrl
        );
    }

    private interface UploadQnaWriteInterface{
        @Multipart
        @POST
        Call<ContributorQnaWrite> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody  p_id,
                @Part("content") RequestBody content ,@Url String apiUrl

        );
    }
    private interface UploadCartAddInterface{
        @Multipart
        @POST
        Call<ContributorCartAdd> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("store_id") RequestBody store_id,
                @Part("p_id") RequestBody  p_id,
                @Part("p_option_1") RequestBody  p_option_1 ,
                @Part("p_option_2") RequestBody  p_option_2  ,
                @Part("amount") RequestBody  amount,
                @Url String apiUrl
        );
    }


    private interface UploadCartListInterface{
        @Multipart
        @POST
        Call<ContributorCartList> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Url String apiUrl
        );
    }

    private interface UploadCartAmountModifyInterface{
        @Multipart
        @POST
        Call<ContributorCartAmountModify> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("store_id") RequestBody store_id,
                @Part("c_id") RequestBody c_id,
                @Part("amount") RequestBody amount,
                @Url String apiUrl
        );
    }

    private interface UploadCartListDeleteInterface{
        @Multipart
        @POST
        Call<ContributorCartListDelete> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("c_id") RequestBody c_id,
//                @Part("amount") RequestBody amount,
                @Url String apiUrl
        );
    }


    private interface UploadPaymentListInterface{
        @Multipart
        @POST
        Call<ContributorPaymentList> upload(
                /**
                 * REST API 등록
                 */
                @Part("order_type") RequestBody order_type,
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody p_id,
                @Part("p_option_1") RequestBody p_option_1,
                @Part("p_option_2") RequestBody p_option_2,
                @Part("amount") RequestBody amount,
                @Url String apiUrl
        );
    }

    private interface UploadPaymentListCartInterface{
        @Multipart
        @POST
        Call<ContributorPaymentList> upload(
                /**
                 * REST API 등록
                 */
                @Part("order_type") RequestBody order_type,
                @Part("user_id") RequestBody user_id,
                @Part("c_id") RequestBody c_id,
                @Url String apiUrl
        );
    }

    private interface UploadOrderFormAddInterface{
        @Multipart
        @POST
        Call<ContributorOrderFormAdd> upload(
                /**
                 * REST API 등록
                 */
                 @Part("user_id") RequestBody user_id,
                 @Part("store_id") RequestBody store_id,
                 @Part("payment_id") RequestBody payment_id,
                 @Part("payment_info") RequestBody  payment_info,
                 @Part("p_id") RequestBody  p_id,
                 @Part("p_option_1") RequestBody p_option_1 ,
                 @Part("p_option_2") RequestBody p_option_2  ,
                 @Part("amount") RequestBody  amount,
                 @Part("price") RequestBody  price,
                 @Part("total") RequestBody  total,


                @Url String apiUrl
        );
    }
    private interface UploadOrderFormAdd2Interface{
        @Multipart
        @POST
        Call<ContributorOrderFormAdd> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("store_id") RequestBody store_id,
                @Part("payment_id") RequestBody payment_id,
                @Part("payment_info") RequestBody  payment_info,
                @Part("p_id") RequestBody  p_id,
                @Part("p_option_1") RequestBody p_option_1 ,
                @Part("p_option_2") RequestBody p_option_2  ,
                @Part("amount") RequestBody  amount,
                @Part("price") RequestBody  price,
                @Part("total") RequestBody  total,
                @Part("message") RequestBody  message,
                @Url String apiUrl
        );
    }

    private interface UploadPaymentOption1Interface{
        @Multipart
        @POST
        Call<ContributorPaymentOption> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_id") RequestBody  store_id,
                @Part("payment") RequestBody  payment ,
                @Url String apiUrl
        );
    }

    private interface UploadPaymentOption2Interface{
        @Multipart
        @POST
        Call<ContributorPaymentOption> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_id") RequestBody  store_id,
                @Part("payment") RequestBody  payment ,
                @Part("tel") RequestBody tel ,
                @Url String apiUrl
        );
    }

    private interface UploadPaymentOption3Interface{
        @Multipart
        @POST
        Call<ContributorPaymentOption> upload(
                /**
                 * REST API 등록
                 */
                @Part("store_id") RequestBody  store_id,
                @Part("payment") RequestBody  payment ,
                @Part("name") RequestBody name ,
                @Url String apiUrl
        );
    }


    private interface UploadTopListInterface{
        @Multipart
        @POST
        Call<ContributorTopList> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
               @Url String apiUrl
        );
    }

    private interface UploadTopAutoAddInterface{
        @Multipart
        @POST
        Call<ContributorTop30AutoAdd> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Url String apiUrl
        );
    }

    private interface UploadTop30DelInterface{
        @Multipart
        @POST
        Call<ContributorTop30Del> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Url String apiUrl
        );
    }

    private interface UploadRestockInterface{
        @Multipart
        @POST
        Call<ContributorRestock> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody p_id,
                @Url String apiUrl
        );
    }

    private interface UploadSoldoutInterface{
        @Multipart
        @POST
        Call<ContributorSoldout> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody p_id,
                @Url String apiUrl
        );
    }


    private interface UploadTop30AddInterface{
        @Multipart
        @POST
        Call<ContributorTopAdd> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody p_id,
                @Url String apiUrl
        );
    }

    private interface UploadProductDelInterface{
        @Multipart
        @POST
        Call<ContributorProductDel> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody p_id,
                @Url String apiUrl
        );
    }
    private interface UploadSideCountInterface{
        @Multipart
        @POST
        Call<ContributorSideCount> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Url String apiUrl
        );
    }

    private interface UploadProductWholesaleViewInterface{
        @Multipart
        @POST
        Call<ContributorProductWholesaleView> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("p_id") RequestBody p_id,

                @Url String apiUrl
        );
    }


    private interface UploadQnaLIstInterface{
        @Multipart
        @POST
        Call<ContributorQnaList> upload(
                /**
                 * REST API 등록
                 */
                @Part("page") RequestBody page,
                @Part("p_id") RequestBody p_id,
                @Url String apiUrl
        );
    }

    private interface UploadProductQnaReplyWriteInterface{
        @Multipart
        @POST
        Call<ContributorProductQnaReplyWrite> upload(
                /**
                 * REST API 등록
                 */
                @Part("user_id") RequestBody user_id,
                @Part("q_id") RequestBody q_id,
                @Part("content") RequestBody content,
                @Url String apiUrl
        );
    }
    public void uploadCartAdd(String method, String user_id,String store_id, String p_id,String p_option_1,String p_option_2,String amount,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), p_id);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), p_option_1);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), p_option_2);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), amount);

        UploadCartAddInterface uploadInterface = mRetrofit.create(UploadCartAddInterface.class);
        Call<ContributorCartAdd> call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorCartAdd>() {
            @Override
            public void onResponse(Call<ContributorCartAdd> call,
                                   Response<ContributorCartAdd> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onCartAdd.onResponse((ContributorCartAdd)response.body());
            }
            @Override
            public void onFailure(Call<ContributorCartAdd> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCartAdd.onFailure(t);
            }
        });
    }

    public void uploadQnaWrite(String method,String p_id, String user_id,String content,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), content);

        UploadQnaWriteInterface uploadInterface = mRetrofit.create(UploadQnaWriteInterface.class);
        Call<ContributorQnaWrite> call = uploadInterface.upload(p1, p2,p3,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorQnaWrite>() {
            @Override
            public void onResponse(Call<ContributorQnaWrite> call,
                                   Response<ContributorQnaWrite> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onQnaWrite.onResponse((ContributorQnaWrite)response.body());
            }
            @Override
            public void onFailure(Call<ContributorQnaWrite> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onQnaWrite.onFailure(t);
            }
        });
    }

    /**
     * API에 맞추어 메소드 작성
     */
    public void uploadLogin(String method,String id, String pwd, final Handler _resultHandler, final Handler errorHandler,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), pwd);

        UploadLoginInterface uploadInterface = mRetrofit.create(UploadLoginInterface.class);
        Call<ContributorLogin> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorLogin>() {
            @Override
            public void onResponse(Call<ContributorLogin> call,
                                   Response<ContributorLogin> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onloginlistener.onResponse((ContributorLogin)response.body());
            }
            @Override
            public void onFailure(Call<ContributorLogin> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onloginlistener.onFailure(t);
            }
        });
    }


    public void join1(String method,String store_type, String level,String name,String id, String password,String mobile,String store_name,String store_number,String store_building_id,String store_addr, Bitmap bm){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_type);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), level);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody p7 = RequestBody.create(MediaType.parse("text/plain"), store_name);
        RequestBody p8 = RequestBody.create(MediaType.parse("text/plain"), store_number);
        RequestBody p9 = RequestBody.create(MediaType.parse("text/plain"), store_building_id);
        RequestBody p10 = RequestBody.create(MediaType.parse("text/plain"), store_addr);

        MultipartBody.Part fileToUpload=null;
        Call<ContributorJoin> call = null;
        if(bm!=null){
            UploadJoin1Interface uploadInterface = mRetrofit.create(UploadJoin1Interface.class);
            File file = createTempFile(mContext, bm);
            RequestBody mFile= RequestBody.create(MediaType.parse("multipart/form-data"), file);
            fileToUpload = MultipartBody.Part.createFormData("store_photo", file.getName(), mFile);
            call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10, fileToUpload,Constant.DOMAIN.api+method+"/");
        }else{
            UploadJoin1_noBitmapInterface uploadInterface = mRetrofit.create(UploadJoin1_noBitmapInterface.class);
            call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10, Constant.DOMAIN.api+method+"/");
        }





        call.enqueue(new Callback<ContributorJoin>() {
            @Override
            public void onResponse(Call<ContributorJoin> call,
                                   Response<ContributorJoin> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onJoinListener.onResponse((ContributorJoin)response.body());
            }
            @Override
            public void onFailure(Call<ContributorJoin> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onJoinListener.onFailure(t);
            }
        });
    }

    public void join2(String method,String store_type, String level,String name,String id, String password,String mobile,
                      String store_name,String store_number,String store_onoff,
                      String store_site,String store_site_url,String store_addr, Bitmap bm){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_type);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), level);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody p7 = RequestBody.create(MediaType.parse("text/plain"), store_name);
        RequestBody p8 = RequestBody.create(MediaType.parse("text/plain"), store_number);
        RequestBody p9 = RequestBody.create(MediaType.parse("text/plain"), store_addr);
        RequestBody p10 = RequestBody.create(MediaType.parse("text/plain"), store_onoff);
        RequestBody p11 = RequestBody.create(MediaType.parse("text/plain"), store_site);
        RequestBody p12 = RequestBody.create(MediaType.parse("text/plain"), store_site_url);



        MultipartBody.Part fileToUpload = null;
        Call<ContributorJoin> call = null;
        if(bm!=null){
            UploadJoin2Interface uploadInterface = mRetrofit.create(UploadJoin2Interface.class);
            File file = createTempFile(mContext, bm);
            RequestBody mFile= RequestBody.create(MediaType.parse("multipart/form-data"), file);
            fileToUpload = MultipartBody.Part.createFormData("store_photo", file.getName(), mFile);
            call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12, fileToUpload, Constant.DOMAIN.api+method+"/");


        }else{
            UploadJoin2_noBitmapInterface uploadInterface = mRetrofit.create(UploadJoin2_noBitmapInterface.class);
            call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,  Constant.DOMAIN.api+method+"/");
        }


        LogUtil.d("url: "+ Constant.DOMAIN.url+Constant.DOMAIN.api+method+"/?store_type="+store_type+"&level="+level
                +"&name="+name+"&id="+id+"&password="+password+"&mobile="+mobile+"&store_name="+store_name+"&store_addr="+store_addr
                +"&store_onoff="+store_onoff+"&store_site="+store_site+"&store_site_url="+store_site_url);


        call.enqueue(new Callback<ContributorJoin>() {
            @Override
            public void onResponse(Call<ContributorJoin> call,
                                   Response<ContributorJoin> response) {

                if(dg!=null&& isShowLoading)dg.dismiss();
                onJoinListener.onResponse((ContributorJoin)response.body());
            }
            @Override
            public void onFailure(Call<ContributorJoin> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onJoinListener.onFailure(t);
            }
        });
    }

    public void join3(String method,String store_type, String level,String name,String id, String password,String mobile,String store_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_type);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), level);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody p7 = RequestBody.create(MediaType.parse("text/plain"), store_id);



        UploadJoin3Interface uploadInterface = mRetrofit.create(UploadJoin3Interface.class);
        Call<ContributorJoin> call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorJoin>() {
            @Override
            public void onResponse(Call<ContributorJoin> call,
                                   Response<ContributorJoin> response) {

                if(dg!=null&& isShowLoading)dg.dismiss();
                onJoinListener.onResponse((ContributorJoin)response.body());
            }
            @Override
            public void onFailure(Call<ContributorJoin> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onJoinListener.onFailure(t);
            }
        });
    }

    public void uploadBuildSearch(String method,String depth, String value){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), depth);
        RequestBody p2 = null;

        if(value!=null && value.length()> 0){
            p2= RequestBody.create(MediaType.parse("text/plain"), value);
        }else{
            p2 =  RequestBody.create(MediaType.parse("text/plain"), "");
        }
        UploadBuildingSearch1Interface uploadInterface = mRetrofit.create(UploadBuildingSearch1Interface.class);
        Call<ContributorBuildSearch> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorBuildSearch>() {
            @Override
            public void onResponse(Call<ContributorBuildSearch> call,
                                   Response<ContributorBuildSearch> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBuildingSearch.onResponse((ContributorBuildSearch)response.body());
            }
            @Override
            public void onFailure(Call<ContributorBuildSearch> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBuildingSearch.onFailure(t);
            }
        });
    }

    public void uploadStoreSearch(String method,String store_type, String name){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_type);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), name);

        UploadStoreSearch1Interface uploadInterface = mRetrofit.create(UploadStoreSearch1Interface.class);
        Call<ContributorStoreSearch> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorStoreSearch>() {
            @Override
            public void onResponse(Call<ContributorStoreSearch> call,
                                   Response<ContributorStoreSearch> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onStoreSearch.onResponse((ContributorStoreSearch)response.body());
            }
            @Override
            public void onFailure(Call<ContributorStoreSearch> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onStoreSearch.onFailure(t);
            }
        });
    }


    /**
     * 5. 배너 리스트
     * @param method
     * @param type
     */
    public void uploadBannerList(String method,String type){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), type);

        UploadBannerListInterface uploadInterface = mRetrofit.create(UploadBannerListInterface.class);
        Call<ContributorBannerList> call = uploadInterface.upload(p1, Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorBannerList>() {
            @Override
            public void onResponse(Call<ContributorBannerList> call,
                                   Response<ContributorBannerList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBannerList.onResponse((ContributorBannerList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorBannerList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBannerList.onFailure(t);
            }
        });
    }

    /**
     * 6.카테고리
     * @param method
     */
    public void uploadCategoryList(String method){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), "");
        UploadCategoryListInterface uploadInterface = mRetrofit.create(UploadCategoryListInterface.class);
        Call<ContributorCategoryList> call = uploadInterface.upload(Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorCategoryList>() {
            @Override
            public void onResponse(Call<ContributorCategoryList> call,
                                   Response<ContributorCategoryList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCategoryList.onResponse((ContributorCategoryList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorCategoryList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCategoryList.onFailure(t);
            }
        });
    }

    /**
     * 7.코드 리스트
     * @param method
     * @param code
     */
    public void uploadCodeList(String method,String code){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), code);

        UploadCodeListInterface uploadInterface = mRetrofit.create(UploadCodeListInterface.class);
        Call<ContributorCodeList> call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorCodeList>() {
            @Override
            public void onResponse(Call<ContributorCodeList> call,
                                   Response<ContributorCodeList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCodeList.onResponse((ContributorCodeList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorCodeList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCodeList.onFailure(t);
            }
        });
    }

    /**
     * 8. 상품목록
     * @param method
     * @param page
     * @param category
     * @param is_sale
     * @param store_id
     */
    public void uploadProductList(String method,String page, String category,String is_sale,String store_id,String is_soldout){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), is_sale);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), is_soldout);

        UploadProductListInterface uploadInterface = mRetrofit.create(UploadProductListInterface.class);
        Call<ContributorProductList> call = uploadInterface.upload(p1, p2,p3,p4,p5,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorProductList>() {
            @Override
            public void onResponse(Call<ContributorProductList> call,
                                   Response<ContributorProductList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductList.onResponse((ContributorProductList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductList.onFailure(t);
            }
        });
    }

    public void uploadProductList2(String method,String page, String category,String is_sale,String store_id,
                                   String keyword,String orderBy,String open_type,String is_soldout){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), is_sale);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), keyword);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), orderBy);
        RequestBody p7 = RequestBody.create(MediaType.parse("text/plain"), open_type);
        RequestBody p8 = RequestBody.create(MediaType.parse("text/plain"), is_soldout);

        UploadProductList2Interface uploadInterface = mRetrofit.create(UploadProductList2Interface.class);
        Call<ContributorProductList> call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,p8,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorProductList>() {
            @Override
            public void onResponse(Call<ContributorProductList> call,
                                   Response<ContributorProductList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductList.onResponse((ContributorProductList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductList.onFailure(t);
            }
        });
    }


    public void uploadProductListSearch(String method,String page, String category,String is_sale,String store_id,String keyword,String is_soldout){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), is_sale);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), keyword);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), is_soldout);

        UploadProductListSearchInterface uploadInterface = mRetrofit.create(UploadProductListSearchInterface.class);
        Call<ContributorProductList> call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorProductList>() {
            @Override
            public void onResponse(Call<ContributorProductList> call,
                                   Response<ContributorProductList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductList.onResponse((ContributorProductList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductList.onFailure(t);
            }
        });
    }

    /**
     * 9. 베스트 상품목록
     * @param method
     */
    public void uploadBestProductList(String method,String page){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);

        UploadBestProductList2Interface uploadInterface = mRetrofit.create(UploadBestProductList2Interface.class);
        Call<ContributorBestProductList> call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorBestProductList>() {
            @Override
            public void onResponse(Call<ContributorBestProductList> call,
                                   Response<ContributorBestProductList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBestProductList.onResponse((ContributorBestProductList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorBestProductList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBestProductList.onFailure(t);
            }
        });
    }

    /**
     * 10. 상품정보
     * @param method
     * @param id
     * @param user_id
     */
    public void uploadProductView(String method,String id, String user_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), user_id);

        UploadProductViewInterface uploadInterface = mRetrofit.create(UploadProductViewInterface.class);
        Call<ContributorProductView> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorProductView>() {
            @Override
            public void onResponse(Call<ContributorProductView> call,
                                   Response<ContributorProductView> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductView.onResponse((ContributorProductView)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductView> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductView.onFailure(t);
            }
        });
    }

    /**
     * 11. 상가 건물 리스트(상가별 보기에 필요한 리스트)
     * @param method
     */
    public void uploadBuildingList(String method){

        UploadStoreBuildingLIstInterface uploadInterface = mRetrofit.create(UploadStoreBuildingLIstInterface.class);
        Call<ContributorBuildingList> call = uploadInterface.upload(Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorBuildingList>() {
            @Override
            public void onResponse(Call<ContributorBuildingList> call,
                                   Response<ContributorBuildingList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBuildingList.onResponse((ContributorBuildingList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorBuildingList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onBuildingList.onFailure(t);
            }
        });
    }

    /**
     * 12.상가별 보기
     * @param method
     * @param page
     * @param building_id
     * @param user_id
     */
    public void uploadStoreList(String method,String page, String building_id,String user_id,String keyword){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), building_id);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), keyword);

        Call<ContributorStoreViewList> call=null;
        UploadStoreOfViewLIstInterface uploadInterface = mRetrofit.create(UploadStoreOfViewLIstInterface.class);
        call = uploadInterface.upload(p1, p2,p3,p4,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorStoreViewList>() {
            @Override
            public void onResponse(Call<ContributorStoreViewList> call,
                                   Response<ContributorStoreViewList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onStoreList.onResponse((ContributorStoreViewList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorStoreViewList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onStoreList.onFailure(t);
            }
        });
    }

    public void uploadProductAdd(String method,String name, String price,String category,String material_info,
                                 String cloth_info_1,String cloth_info_2,String cloth_info_3,String cloth_info_4,
                                 String washing_info,String style_info,String option_1, String option_2,
                                 String detail,String origin_info,String store_id,String open_type,String open_day,
                                 String kakao_open,String global_open,Bitmap[] bms){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), material_info);
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), cloth_info_1);
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), cloth_info_2);
        RequestBody p7 = RequestBody.create(MediaType.parse("text/plain"), cloth_info_3);
        RequestBody p8 = RequestBody.create(MediaType.parse("text/plain"), cloth_info_4);
        RequestBody p9 = RequestBody.create(MediaType.parse("text/plain"), washing_info);
        RequestBody p10 = RequestBody.create(MediaType.parse("text/plain"), style_info);
        RequestBody p11 = RequestBody.create(MediaType.parse("text/plain"), option_1);
        RequestBody p12 = RequestBody.create(MediaType.parse("text/plain"), option_2);
        RequestBody p13 = RequestBody.create(MediaType.parse("text/plain"), detail);
        RequestBody p14 = RequestBody.create(MediaType.parse("text/plain"), origin_info);
        RequestBody p15 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p16 = RequestBody.create(MediaType.parse("text/plain"), open_type);
        RequestBody p17 = RequestBody.create(MediaType.parse("text/plain"), open_day);

        RequestBody p18 = RequestBody.create(MediaType.parse("text/plain"), kakao_open);
        RequestBody p19 = RequestBody.create(MediaType.parse("text/plain"), global_open);


        UploadProductAddInterface uploadInterface = mRetrofit.create(UploadProductAddInterface.class);


        MultipartBody.Part fileToUpload = null;
        Call<ContributorProductAdd> call = null;

        ArrayList<MultipartBody.Part> fileParams = new ArrayList<>();
        if(bms!=null){
            for(int i=0; i<bms.length;i++){
                if(bms[i]!=null){
                    File file = createTempFile(mContext, bms[i]);
                    RequestBody mFile= RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    fileToUpload = MultipartBody.Part.createFormData("product_image[]", file.getName(), mFile);
                    fileParams.add(fileToUpload);
                }
            }

        }
        call = uploadInterface.upload(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19, fileParams, Constant.DOMAIN.api+method+"/");



        call.enqueue(new Callback<ContributorProductAdd>() {
            @Override
            public void onResponse(Call<ContributorProductAdd> call,
                                   Response<ContributorProductAdd> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductAdd.onResponse((ContributorProductAdd)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductAdd> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductAdd.onFailure(t);
            }
        });
    }


    /**
     * 14.즐겨찾기 등록
     * @param method
     * @param user_id
     * @param type
     * @param target_id
     */
    public void uploadFavorites(String method,String user_id,String type, String target_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), type);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), target_id);
        UploadFavoritesInterface uploadInterface = mRetrofit.create(UploadFavoritesInterface.class);
        Call<ContributorFavorites> call = uploadInterface.upload(p1, p2,p3,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorFavorites>() {
            @Override
            public void onResponse(Call<ContributorFavorites> call,
                                   Response<ContributorFavorites> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onFavorites.onResponse((ContributorFavorites)response.body());
            }
            @Override
            public void onFailure(Call<ContributorFavorites> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onFavorites.onFailure(t);
            }
        });
    }

    /**
     * 15.공지사항
     * @param method
     * @param page
     */
    public void uploadNotice(String method,String page){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);

        UploadNoticeInterface uploadInterface = mRetrofit.create(UploadNoticeInterface.class);
        Call<ContributorNoticeList> call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorNoticeList>() {
            @Override
            public void onResponse(Call<ContributorNoticeList> call,
                                   Response<ContributorNoticeList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onNoticeList.onResponse((ContributorNoticeList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorNoticeList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onNoticeList.onFailure(t);
            }
        });
    }

    /**
     * 16.추천검색어
     * @param method
     */
    public void uploadRecomWord(String method){
        UploadRecommWordInterface uploadInterface = mRetrofit.create(UploadRecommWordInterface.class);
        Call<ContributorRecomWord> call = uploadInterface.upload(Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorRecomWord>() {
            @Override
            public void onResponse(Call<ContributorRecomWord> call,
                                   Response<ContributorRecomWord> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onRecomWord.onResponse((ContributorRecomWord)response.body());
            }
            @Override
            public void onFailure(Call<ContributorRecomWord> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onRecomWord.onFailure(t);
            }
        });
    }



    public void uploadCartList(String method, String user_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        UploadCartListInterface uploadInterface = mRetrofit.create(UploadCartListInterface.class);
        Call<ContributorCartList> call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorCartList>() {
            @Override
            public void onResponse(Call<ContributorCartList> call,
                                   Response<ContributorCartList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                if(response.code()==200){
                    onCartList.onResponse((ContributorCartList)response.body());
                }else{
                    onCartList.onServerError(response.message().toString());
                }
            }
            @Override
            public void onFailure(Call<ContributorCartList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCartList.onFailure(t);
            }
        });
    }




    public void uploadCartAmountMod(String method, String user_id,String store_id, String c_id,String amount,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), c_id);
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), amount);

        UploadCartAmountModifyInterface uploadInterface = mRetrofit.create(UploadCartAmountModifyInterface.class);
        Call<ContributorCartAmountModify> call = uploadInterface.upload(p1, p2,p3,p4,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorCartAmountModify>() {
            @Override
            public void onResponse(Call<ContributorCartAmountModify> call,
                                   Response<ContributorCartAmountModify> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onCartAmountMod.onResponse((ContributorCartAmountModify)response.body());
            }
            @Override
            public void onFailure(Call<ContributorCartAmountModify> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCartAmountMod.onFailure(t);
            }
        });
    }


    public void uploadCartListDelete(String method, String user_id,String c_id,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), c_id);

        UploadCartListDeleteInterface uploadInterface = mRetrofit.create(UploadCartListDeleteInterface.class);
        Call<ContributorCartListDelete> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorCartListDelete>() {
            @Override
            public void onResponse(Call<ContributorCartListDelete> call,
                                   Response<ContributorCartListDelete> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onCartListDelete.onResponse((ContributorCartListDelete)response.body());
            }
            @Override
            public void onFailure(Call<ContributorCartListDelete> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onCartListDelete.onFailure(t);
            }
        });
    }



    public void uploadPaymentList(String method, Integer order_type, String user_id, Integer p_id, String p_option_1, String p_option_2,String amount){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), order_type+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), p_id+"");
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), p_option_1+"");
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), p_option_2+"");
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), amount+"");

        UploadPaymentListInterface uploadInterface = mRetrofit.create(UploadPaymentListInterface.class);
        Call<ContributorPaymentList> call = uploadInterface.upload(p1,p2,p3,p4,p5,p6,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorPaymentList>() {
            @Override
            public void onResponse(Call<ContributorPaymentList> call,
                                   Response<ContributorPaymentList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentList.onResponse((ContributorPaymentList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorPaymentList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentList.onFailure(t);
            }
        });
    }

    public void uploadPaymentList(String method, Integer order_type, String user_id, String c_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), order_type+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), c_id);


        UploadPaymentListCartInterface uploadInterface = mRetrofit.create(UploadPaymentListCartInterface.class);
        Call<ContributorPaymentList> call = uploadInterface.upload(p1,p2,p3,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorPaymentList>() {
            @Override
            public void onResponse(Call<ContributorPaymentList> call,
                                   Response<ContributorPaymentList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentList.onResponse((ContributorPaymentList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorPaymentList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentList.onFailure(t);
            }
        });
    }


    public void uploadOrderFormAdd(String method, String user_id, String store_id,String payment_id,String payment_info,
                                   String p_id,String p_option_1,String p_option_2,String amount,
                                   String price,String total,String message){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), store_id+"");
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), payment_id+"");
        RequestBody p4 = RequestBody.create(MediaType.parse("text/plain"), payment_info+"");
        RequestBody p5 = RequestBody.create(MediaType.parse("text/plain"), p_id+"");
        RequestBody p6 = RequestBody.create(MediaType.parse("text/plain"), p_option_1+"");
        RequestBody p7 = RequestBody.create(MediaType.parse("text/plain"), p_option_2+"");
        RequestBody p8 = RequestBody.create(MediaType.parse("text/plain"), amount+"");
        RequestBody p9 = RequestBody.create(MediaType.parse("text/plain"), price+"");
        RequestBody p10 = RequestBody.create(MediaType.parse("text/plain"), total+"");

        Call<ContributorOrderFormAdd> call =null;
        if(message!=null && message.length()>0){
            RequestBody p11 = RequestBody.create(MediaType.parse("text/plain"), message+"");
            UploadOrderFormAdd2Interface uploadInterface = mRetrofit.create(UploadOrderFormAdd2Interface.class);
            call = uploadInterface.upload(p1,p2,p3,p4,p5,p6,p7,
                    p8,p9,p10,p11,Constant.DOMAIN.api+method+"/");
        }else{
            UploadOrderFormAddInterface uploadInterface = mRetrofit.create(UploadOrderFormAddInterface.class);
            call = uploadInterface.upload(p1,p2,p3,p4,p5,p6,p7,
                    p8,p9,p10,Constant.DOMAIN.api+method+"/");
        }



        call.enqueue(new Callback<ContributorOrderFormAdd>() {
            @Override
            public void onResponse(Call<ContributorOrderFormAdd> call,
                                   Response<ContributorOrderFormAdd> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onOrderFormAdd.onResponse((ContributorOrderFormAdd)response.body());
            }
            @Override
            public void onFailure(Call<ContributorOrderFormAdd> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onOrderFormAdd.onFailure(t);
            }
        });
    }


    /**
     * API에 맞추어 메소드 작성
     */
    public void uploadPaymentOption01(String method,String store_id, String payment,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), payment);

        UploadPaymentOption1Interface uploadInterface = mRetrofit.create(UploadPaymentOption1Interface.class);
        Call<ContributorPaymentOption> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorPaymentOption>() {
            @Override
            public void onResponse(Call<ContributorPaymentOption> call,
                                   Response<ContributorPaymentOption> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onPaymentOption.onResponse((ContributorPaymentOption)response.body());
            }
            @Override
            public void onFailure(Call<ContributorPaymentOption> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentOption.onFailure(t);
            }
        });
    }

    /**
     * API에 맞추어 메소드 작성
     */
    public void uploadPaymentOption02(String method,String store_id, String payment,String tel,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), payment);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), tel);

        UploadPaymentOption2Interface uploadInterface = mRetrofit.create(UploadPaymentOption2Interface.class);
        Call<ContributorPaymentOption> call = uploadInterface.upload(p1, p2,p3,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorPaymentOption>() {
            @Override
            public void onResponse(Call<ContributorPaymentOption> call,
                                   Response<ContributorPaymentOption> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onPaymentOption.onResponse((ContributorPaymentOption)response.body());
            }
            @Override
            public void onFailure(Call<ContributorPaymentOption> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentOption.onFailure(t);
            }
        });
    }


    /**
            * API에 맞추어 메소드 작성
     */
    public void uploadPaymentOption03(String method,String store_id, String payment,String name,boolean _isLoading){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), store_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), payment);
        RequestBody p3 = RequestBody.create(MediaType.parse("text/plain"), name);

        UploadPaymentOption3Interface uploadInterface = mRetrofit.create(UploadPaymentOption3Interface.class);
        Call<ContributorPaymentOption> call = uploadInterface.upload(p1, p2,p3,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorPaymentOption>() {
            @Override
            public void onResponse(Call<ContributorPaymentOption> call,
                                   Response<ContributorPaymentOption> response) {
                if(dg!=null && isShowLoading)dg.dismiss();
                onPaymentOption.onResponse((ContributorPaymentOption)response.body());
            }
            @Override
            public void onFailure(Call<ContributorPaymentOption> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onPaymentOption.onFailure(t);
            }
        });
    }


    public void uploadTopList(String method,String user_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);

        UploadTopListInterface uploadInterface = mRetrofit.create(UploadTopListInterface.class);
        Call<ContributorTopList> call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorTopList>() {
            @Override
            public void onResponse(Call<ContributorTopList> call,
                                   Response<ContributorTopList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTopList.onResponse((ContributorTopList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorTopList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTopList.onFailure(t);
            }
        });
    }


    public void uploadTopAutoAdd(String method, String user_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");

        Call<ContributorTop30AutoAdd> call =null;
        UploadTopAutoAddInterface uploadInterface = mRetrofit.create(UploadTopAutoAddInterface.class);
        call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorTop30AutoAdd>() {
            @Override
            public void onResponse(Call<ContributorTop30AutoAdd> call,
                                   Response<ContributorTop30AutoAdd> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTop30AutoAdd.onResponse((ContributorTop30AutoAdd)response.body());
            }
            @Override
            public void onFailure(Call<ContributorTop30AutoAdd> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onOrderFormAdd.onFailure(t);
            }
        });
    }

    public void uploadTop30Del(String method, String user_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");

        Call<ContributorTop30Del> call =null;
        UploadTop30DelInterface uploadInterface = mRetrofit.create(UploadTop30DelInterface.class);
        call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorTop30Del>() {
            @Override
            public void onResponse(Call<ContributorTop30Del> call,
                                   Response<ContributorTop30Del> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTop30Del.onResponse((ContributorTop30Del)response.body());
            }
            @Override
            public void onFailure(Call<ContributorTop30Del> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTop30Del.onFailure(t);
            }
        });
    }

    public void uploadRestock(String method, String user_id,String p_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id+"");

        Call<ContributorRestock> call =null;
        UploadRestockInterface uploadInterface = mRetrofit.create(UploadRestockInterface.class);
        call = uploadInterface.upload(p1,p2,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorRestock>() {
            @Override
            public void onResponse(Call<ContributorRestock> call,
                                   Response<ContributorRestock> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onRestock.onResponse((ContributorRestock)response.body());
            }
            @Override
            public void onFailure(Call<ContributorRestock> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onRestock.onFailure(t);
            }
        });
    }

    public void uploadSoldOut(String method, String user_id,String p_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id+"");

        Call<ContributorSoldout> call =null;
        UploadSoldoutInterface uploadInterface = mRetrofit.create(UploadSoldoutInterface.class);
        call = uploadInterface.upload(p1,p2,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorSoldout>() {
            @Override
            public void onResponse(Call<ContributorSoldout> call,
                                   Response<ContributorSoldout> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onSoldOut.onResponse((ContributorSoldout)response.body());
            }
            @Override
            public void onFailure(Call<ContributorSoldout> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onSoldOut.onFailure(t);
            }
        });
    }


    public void uploadTop30Add(String method, String user_id,String p_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id+"");

        Call<ContributorTopAdd> call =null;
        UploadTop30AddInterface uploadInterface = mRetrofit.create(UploadTop30AddInterface.class);
        call = uploadInterface.upload(p1,p2,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorTopAdd>() {
            @Override
            public void onResponse(Call<ContributorTopAdd> call,
                                   Response<ContributorTopAdd> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTop30Add.onResponse((ContributorTopAdd)response.body());
            }
            @Override
            public void onFailure(Call<ContributorTopAdd> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onTop30Add.onFailure(t);
            }
        });
    }


    public void uploadProductDel(String method, String user_id,String p_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id+"");

        Call<ContributorProductDel> call =null;
        UploadProductDelInterface uploadInterface = mRetrofit.create(UploadProductDelInterface.class);
        call = uploadInterface.upload(p1,p2,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorProductDel>() {
            @Override
            public void onResponse(Call<ContributorProductDel> call,
                                   Response<ContributorProductDel> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductDel.onResponse((ContributorProductDel)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductDel> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onProductDel.onFailure(t);
            }
        });
    }


    public void uploadSideCount(String method, String user_id){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");

        Call<ContributorSideCount> call =null;
        UploadSideCountInterface uploadInterface = mRetrofit.create(UploadSideCountInterface.class);
        call = uploadInterface.upload(p1,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorSideCount>() {
            @Override
            public void onResponse(Call<ContributorSideCount> call,
                                   Response<ContributorSideCount> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onSideCount.onResponse((ContributorSideCount)response.body());
            }
            @Override
            public void onFailure(Call<ContributorSideCount> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onSideCount.onFailure(t);
            }
        });
    }


    public void uploadProductWholesaleView(String method,String user_id, String p_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id);

        UploadProductWholesaleViewInterface uploadInterface = mRetrofit.create(UploadProductWholesaleViewInterface.class);
        Call<ContributorProductWholesaleView> call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");

        call.enqueue(new Callback<ContributorProductWholesaleView>() {
            @Override
            public void onResponse(Call<ContributorProductWholesaleView> call,
                                   Response<ContributorProductWholesaleView> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onWholesaleView.onResponse((ContributorProductWholesaleView)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductWholesaleView> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onWholesaleView.onFailure(t);
            }
        });
    }


    public void uploadQnaList(String method,String page, String p_id){
        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), page);
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), p_id);

        Call<ContributorQnaList> call=null;
        UploadQnaLIstInterface uploadInterface = mRetrofit.create(UploadQnaLIstInterface.class);
        call = uploadInterface.upload(p1, p2,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorQnaList>() {
            @Override
            public void onResponse(Call<ContributorQnaList> call,
                                   Response<ContributorQnaList> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onQnaList.onResponse((ContributorQnaList)response.body());
            }
            @Override
            public void onFailure(Call<ContributorQnaList> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onQnaList.onFailure(t);
            }
        });
    }

    public void uploadQnaReplyWrite(String method, String user_id,String q_id,String content){

        RequestBody p1 = RequestBody.create(MediaType.parse("text/plain"), user_id+"");
        RequestBody p2 = RequestBody.create(MediaType.parse("text/plain"), q_id+"");
        RequestBody p3= RequestBody.create(MediaType.parse("text/plain"), content+"");

        Call<ContributorProductQnaReplyWrite> call =null;
        UploadProductQnaReplyWriteInterface uploadInterface = mRetrofit.create(UploadProductQnaReplyWriteInterface.class);
        call = uploadInterface.upload(p1,p2,p3,Constant.DOMAIN.api+method+"/");


        call.enqueue(new Callback<ContributorProductQnaReplyWrite>() {
            @Override
            public void onResponse(Call<ContributorProductQnaReplyWrite> call,
                                   Response<ContributorProductQnaReplyWrite> response) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onQnaReplyWrite.onResponse((ContributorProductQnaReplyWrite)response.body());
            }
            @Override
            public void onFailure(Call<ContributorProductQnaReplyWrite> call, Throwable t) {
                if(dg!=null&& isShowLoading)dg.dismiss();
                onQnaReplyWrite.onFailure(t);
            }
        });
    }
    /**
     * RESPONSE PARAM 등록
     */

    public static class ContributorLogin{
        public String result ="";
        public String error="";
        public String user_id ="";
        public String store_id  ="";
        public Integer store_type;
        public Integer level;
    }


    public static class ContributorJoin{
        public String result ="";
        public String error="";
        public String user_id ="";
        public String store_id  ="";


    }
    public static class ContributorBuildSearch{
        public String result ="";
        public String error="";
        public List<BuildSearchData> list;

    }

    public static class ContributorStoreSearch{
        public String result ="";
        public String error="";
        public List<StoreSearchData> list;

    }


    public static class ContributorBannerList{
        public String result ="";
        public String error="";
        public List<BannerLIstData> list;

    }


    public static class ContributorCategoryList{
        public String result ="";
        public String error="";
        public List<CategoryLIstData> list;

    }


    public static class ContributorCodeList{
        public String result ="";
        public String error="";
        public List<CodeListData> list;

    }


    public static class ContributorProductList{
        public String result ="";
        public String error="";
        public ProductResponse list;
    }


    public static class ContributorBestProductList{
        public String result ="";
        public String error="";
        public BestProductResponse list;
    }

    public static class ContributorProductView{
        public String result ="";
        public String error="";
        public ProductViewResponse data;
    }
    public static class ContributorBuildingList{
        public String result ="";
        public String error="";
        public List<BuildingListData> list;
    }
    public static class ContributorStoreViewList{
        public String result ="";
        public String error="";
        public StoreListResponse list;
    }


    public static class ContributorProductAdd{
        public String result ="";
        public String error="";
        public Integer p_id;
    }

    public static class ContributorFavorites{
        public String result ="";
        public String error="";
        public Integer user_id;
        public Integer flag;

    }

    public static class ContributorNoticeList{
        public String result ="";
        public String error="";
        public NoticeListResponse list;
    }

    public static class ContributorRecomWord{
        public String result ="";
        public String error="";
        public List<RecomWordData>list;
    }

    public static class ContributorQnaWrite{
        public String result ="";
        public String error="";
        public String qna_id ="";
    }
    public static class ContributorCartAdd{
        public String result ="";
        public String error="";
        public String user_id ="";
    }

    public static class ContributorCartList{
        public String result ="";
        public String error="";
        public Integer total=0;
        public List<CartGroupListResponse> list;
    }

    public static class ContributorCartAmountModify{
        public String result ="";
        public String error="";
        public String user_id ="";
    }

    public static class ContributorCartListDelete{
        public String result ="";
        public String error="";
        public String user_id ="";
    }


    public static class ContributorPaymentList{
        public String result ="";
        public String error="";
        public String total_price="";
        public List<PaymentGroupListResponse> list;
    }

    public static class ContributorOrderFormAdd{
        public String result ="";
        public String error="";
        public String o_id ="";
        public String order_number ="";
        public String account_info ="";
    }


    public static class ContributorPaymentOption{
        public String result ="";
        public String error="";
        public String store_id ="";
    }

    public static class ContributorTopList{
        public String result ="";
        public String error="";
        public List<TopStoreListData> list;
    }

    public static class ContributorTop30AutoAdd{
        public String result ="";
        public String error="";
    }

    public static class ContributorTop30Del{
        public String result ="";
        public String error="";
    }

    public static class ContributorRestock {
        public String result ="";
        public String error="";
    }

    public static class ContributorSoldout {
        public String result ="";
        public String error="";
    }

    public static class ContributorTopAdd {
        public String result ="";
        public String error="";
    }
    public static class ContributorProductDel {
        public String result ="";
        public String error="";
    }

    public static class ContributorSideCount {
        public String result ="";
        public String error="";
        public SideCountData data;
    }

    public static class ContributorProductWholesaleView{
        public String result ="";
        public String error="";
        public ProductViewWholesaleResponse data;
    }



    public static class ContributorQnaList{
        public String result ="";
        public String error="";
        public ProductQnaListResponse list;
    }

    public static class ContributorProductQnaReplyWrite {
        public String result ="";
        public String error="";
    }

    private OnQnaList onQnaList;
    public void setOnQnaList(OnQnaList listener){
        onQnaList = listener;
    }

    public static interface OnQnaList{
        public void onResponse(ContributorQnaList c);
        public void onFailure(Throwable t);
    }

    private OnQnaReplyWrite onQnaReplyWrite;
    public void setOnQnaReplyWrite(OnQnaReplyWrite listener){
        onQnaReplyWrite = listener;
    }

    public static interface OnQnaReplyWrite{
        public void onResponse(ContributorProductQnaReplyWrite c);
        public void onFailure(Throwable t);
    }


    private OnWholesaleView onWholesaleView;
    public void setOnWholesaleView(OnWholesaleView listener){
        onWholesaleView = listener;
    }

    public static interface OnWholesaleView{
        public void onResponse(ContributorProductWholesaleView c);
        public void onFailure(Throwable t);
    }


    private OnSideCount onSideCount;
    public void setOnSideCount(OnSideCount listener){
        onSideCount = listener;
    }

    public static interface OnSideCount{
        public void onResponse(ContributorSideCount c);
        public void onFailure(Throwable t);
    }


    private OnProductDel onProductDel;
    public void setOnProductDel(OnProductDel listener){
        onProductDel = listener;
    }

    public static interface OnProductDel{
        public void onResponse(ContributorProductDel c);
        public void onFailure(Throwable t);
    }


    private OnTop30Add onTop30Add;
    public void setOnTop30Add(OnTop30Add listener){
        onTop30Add = listener;
    }

    public static interface OnTop30Add{
        public void onResponse(ContributorTopAdd c);
        public void onFailure(Throwable t);
    }

    private OnSoldOut onSoldOut;
    public void setOnSoldOut(OnSoldOut listener){
        onSoldOut = listener;
    }

    public static interface OnSoldOut{
        public void onResponse(ContributorSoldout c);
        public void onFailure(Throwable t);
    }


    private OnRestock onRestock;
    public void setOnRestock(OnRestock listener){
        onRestock = listener;
    }

    public static interface OnRestock{
        public void onResponse(ContributorRestock c);
        public void onFailure(Throwable t);
    }


    private OnTop30Del onTop30Del;
    public void setOnTop30Del(OnTop30Del listener){
        onTop30Del = listener;
    }

    public static interface OnTop30Del{
        public void onResponse(ContributorTop30Del c);
        public void onFailure(Throwable t);
    }


    private OnTop30AutoAdd onTop30AutoAdd;
    public void setOnTop30AutoAdd(OnTop30AutoAdd listener){
        onTop30AutoAdd = listener;
    }

    public static interface OnTop30AutoAdd{
        public void onResponse(ContributorTop30AutoAdd c);
        public void onFailure(Throwable t);
    }


    private OnTopList onTopList;
    public void setOnTopList(OnTopList listener){
        onTopList = listener;
    }

    public static interface OnTopList{
        public void onResponse(ContributorTopList c);
        public void onFailure(Throwable t);
    }



    private OnPaymentOption onPaymentOption;
    public void setOnPaymentOption(OnPaymentOption listener){
        onPaymentOption = listener;
    }

    public static interface OnPaymentOption{
        public void onResponse(ContributorPaymentOption c);
        public void onFailure(Throwable t);
    }


    private OnOrderFormAdd onOrderFormAdd;
    public void setOnOrderFormAdd(OnOrderFormAdd listener){
        onOrderFormAdd = listener;
    }

    public static interface OnOrderFormAdd{
        public void onResponse(ContributorOrderFormAdd c);
        public void onFailure(Throwable t);
    }

    private OnPaymentList onPaymentList;
    public void setOnPaymentList(OnPaymentList listener){
        onPaymentList = listener;
    }

    public static interface OnPaymentList{
        public void onResponse(ContributorPaymentList c);
        public void onFailure(Throwable t);
    }

    private OnCartList onCartList;
    public void setOnCartList(OnCartList listener){
        onCartList = listener;
    }

    public static interface OnCartList{
        public void onResponse(ContributorCartList c);
        public void onServerError(String e);
        public void onFailure(Throwable t);
    }

    private onCartAmountMod onCartAmountMod;
    public void setonCartAmountMod(onCartAmountMod listener){
        onCartAmountMod = listener;
    }

    public static interface onCartAmountMod{
        public void onResponse(ContributorCartAmountModify c);
        public void onFailure(Throwable t);
    }

    private OnCartListDelete onCartListDelete;
    public void setOnCartListDelete(OnCartListDelete listener){
        onCartListDelete = listener;
    }

    public static interface OnCartListDelete{
        public void onResponse(ContributorCartListDelete c);
        public void onFailure(Throwable t);
    }

    private OnCartAdd onCartAdd;
    public void setOnCartAdd(OnCartAdd listener){
        onCartAdd = listener;
    }

    public static interface OnCartAdd{
        public void onResponse(ContributorCartAdd c);
        public void onFailure(Throwable t);
    }

    private OnQnaWrite onQnaWrite;
    public void setOnQnaWrite(OnQnaWrite listener){
        onQnaWrite = listener;
    }

    public static interface OnQnaWrite{
        public void onResponse(ContributorQnaWrite c);
        public void onFailure(Throwable t);
    }


    private OnBuildingSearch onBuildingSearch;
    public void setOnBuildingSearch(OnBuildingSearch listener){
        onBuildingSearch = listener;
    }

    public static interface OnBuildingSearch{
        public void onResponse(ContributorBuildSearch c);
        public void onFailure(Throwable t);
    }



    private OnStoreSearch onStoreSearch;
    public void setOnStoreSearch(OnStoreSearch listener){
        onStoreSearch = listener;
    }

    public static interface OnStoreSearch{
        public void onResponse(ContributorStoreSearch c);
        public void onFailure(Throwable t);
    }



    private OnJoinListener onJoinListener;
    public void setOnJoinListener(OnJoinListener listener){
        onJoinListener = listener;
    }

    public static interface OnJoinListener{
        public void onResponse(ContributorJoin c);
        public void onFailure(Throwable t);
    }



    private OnLoginListener onloginlistener;
    public void setOnLoginListener(OnLoginListener listener){
        onloginlistener = listener;
    }

    public static interface OnLoginListener{
        public void onResponse(ContributorLogin c);
        public void onFailure(Throwable t);
    }


    private OnBannerList onBannerList;
    public void setOnBannerList(OnBannerList listener){
        onBannerList = listener;
    }

    public static interface OnBannerList{
        public void onResponse(ContributorBannerList c);
        public void onFailure(Throwable t);
    }


    private OnCategoryList onCategoryList;
    public void setOnCategoryList(OnCategoryList listener){
        onCategoryList = listener;
    }

    public static interface OnCategoryList{
        public void onResponse(ContributorCategoryList c);
        public void onFailure(Throwable t);
    }


    private OnCodeList onCodeList;
    public void setOnCodeList(OnCodeList listener){
        onCodeList = listener;
    }

    public static interface OnCodeList{
        public void onResponse(ContributorCodeList c);
        public void onFailure(Throwable t);
    }

    private OnProductList onProductList;
    public void setOnProductList(OnProductList listener){
        onProductList = listener;
    }

    public static interface OnProductList{
        public void onResponse(ContributorProductList c);
        public void onFailure(Throwable t);
    }

    private OnBestProductList onBestProductList;
    public void setOnBestProductList(OnBestProductList listener){
        onBestProductList = listener;
    }

    public static interface OnBestProductList{
        public void onResponse(ContributorBestProductList c);
        public void onFailure(Throwable t);
    }

    private OnProductView onProductView;
    public void setOnProductView(OnProductView listener){
        onProductView = listener;
    }

    public static interface OnProductView{
        public void onResponse(ContributorProductView c);
        public void onFailure(Throwable t);
    }

    private OnBuildingList onBuildingList;
    public void setOnBuildingList(OnBuildingList listener){
        onBuildingList = listener;
    }

    public static interface OnBuildingList{
        public void onResponse(ContributorBuildingList c);
        public void onFailure(Throwable t);
    }

    private OnStoreList onStoreList;
    public void setOnStoreList(OnStoreList listener){
        onStoreList = listener;
    }

    public static interface OnStoreList{
        public void onResponse(ContributorStoreViewList c);
        public void onFailure(Throwable t);
    }


    private OnProductAdd onProductAdd;
    public void setOnProductAdd(OnProductAdd listener){
        onProductAdd = listener;
    }

    public static interface OnProductAdd{
        public void onResponse(ContributorProductAdd c);
        public void onFailure(Throwable t);
    }

    private OnFavorites onFavorites;
    public void setOnFavorites(OnFavorites listener){
        onFavorites = listener;
    }

    public static interface OnFavorites{
        public void onResponse(ContributorFavorites c);
        public void onFailure(Throwable t);
    }

    private OnNoticeList onNoticeList;
    public void setOnNoticeList(OnNoticeList listener){
        onNoticeList = listener;
    }

    public static interface OnNoticeList{
        public void onResponse(ContributorNoticeList c);
        public void onFailure(Throwable t);
    }

    private OnRecomWord onRecomWord;
    public void setOnRecomWord(OnRecomWord listener){
        onRecomWord = listener;
    }

    public static interface OnRecomWord{
        public void onResponse(ContributorRecomWord c);
        public void onFailure(Throwable t);
    }

    private File createTempFile(Context context, Bitmap bitmap) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "file.jpg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
