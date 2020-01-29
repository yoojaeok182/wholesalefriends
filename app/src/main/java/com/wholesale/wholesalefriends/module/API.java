package com.wholesale.wholesalefriends.module;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.wholesale.wholesalefriends.main.data.BannerLIstData;
import com.wholesale.wholesalefriends.main.data.BestProductListData;
import com.wholesale.wholesalefriends.main.data.BestProductResponse;
import com.wholesale.wholesalefriends.main.data.BuildSearchData;
import com.wholesale.wholesalefriends.main.data.BuildingListData;
import com.wholesale.wholesalefriends.main.data.CartGroupListResponse;
import com.wholesale.wholesalefriends.main.data.CartListProductData;
import com.wholesale.wholesalefriends.main.data.CartListResponse;
import com.wholesale.wholesalefriends.main.data.CartListStoreData;
import com.wholesale.wholesalefriends.main.data.CategoryLIstData;
import com.wholesale.wholesalefriends.main.data.CodeListData;
import com.wholesale.wholesalefriends.main.data.NoticeListData;
import com.wholesale.wholesalefriends.main.data.NoticeListResponse;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductResponse;
import com.wholesale.wholesalefriends.main.data.ProductViewImageData;
import com.wholesale.wholesalefriends.main.data.ProductViewInfoData;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionColorData;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionData;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionSizeData;
import com.wholesale.wholesalefriends.main.data.ProductViewResponse;
import com.wholesale.wholesalefriends.main.data.RecomWordData;
import com.wholesale.wholesalefriends.main.data.StoreListData;
import com.wholesale.wholesalefriends.main.data.StoreListResponse;
import com.wholesale.wholesalefriends.main.data.StoreSearchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class API {

    public static void cartAdd(Context context, String p_id, String user_id,String store_id,String p_option_1,String p_option_2,String amount, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadCartAdd("cart/add",user_id,store_id,p_id,p_option_1,p_option_2,amount,true);
            hp.setOnCartAdd(new Retrofit.OnCartAdd() {
                @Override
                public void onResponse(Retrofit.ContributorCartAdd c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("user_id",c.user_id);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"장바구니 담기 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void qnaWrite(Context context, String p_id, String user_id,String content, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadQnaWrite("product/qnaWrite",p_id,user_id,content,true);
            hp.setOnQnaWrite(new Retrofit.OnQnaWrite() {
                @Override
                public void onResponse(Retrofit.ContributorQnaWrite c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("qna_id",c.qna_id);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"상품 문의 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }
    public static void login(Context context, String id, String pwd, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadLogin("login",id,pwd,resultHandler,errorHandler,true);
            hp.setOnLoginListener(new Retrofit.OnLoginListener() {

                @Override
                public void onResponse(Retrofit.ContributorLogin c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("user_id",c.user_id);
                        jsonObject.put("store_type",c.store_type);
                        jsonObject.put("level",c.level);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"로그인 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    /**
     * * store_type : 도매 : 1, 소매 : 2
     * * level : 회원 구분 1:대표, 2: 직원
     * * name : 이름
     * * id : 아이디
     * * password : 비번호
     * * mobile : 핸드폰번호 // 도매 대표
     * * store_photo : 상가 이미지
     * * store_name : 상가명
     * * store_number : 상가 사업자번호
     * * store_building_id : 빌딩 ID (빌딩 검색 참조)
     * * store_addr : 상가 주소 // 소매 대표
     * * store_photo : 상가 이미지
     * * store_name : 상가명
     * * store_onoﬀ : 온라인 : 1, 오프라인 : 2 (소매 전용, 0은 도매)
     * * store_site : 사이트명
     * * store_site_url : 사이트 주소
     * * store_addr : 상가 주소 // 직원
     * * store_id : 상가 ID (상가 검색 참조)
     * @param id
     * @param resultHandler
     * @param errorHandler
     */
    public static void join(Context context,int store_type, int level, String name, String id, String password,
                            String mobile, String store_name, String store_number, String store_building_id,
                            String store_addr, Bitmap store_photo, String store_onoﬀ,
                            String store_site,String store_site_url, String store_id, Handler resultHandler, Handler errorHandler){

        try{
            Retrofit hp = new Retrofit( context);

            if(store_type ==1){
                if(level ==1){

                    hp.join1("register",store_type+"",level+"",name,id,password,mobile,store_name,store_number,store_building_id,store_addr,store_photo);
                }else{
                    hp.join3("register",store_type+"",level+"",name,id,password,mobile,store_id);
                }
            }else if(store_type ==2){
                if(level ==1){
                    hp.join2("register",store_type+"",level+"",name,id,password,mobile,store_name,store_onoﬀ+"",store_site,store_site_url,store_addr,store_photo);
                }else{
                    hp.join3("register",store_type+"",level+"",name,id,password,mobile,store_id);
                }
            }

            hp.setOnJoinListener(new Retrofit.OnJoinListener() {
                @Override
                public void onResponse(Retrofit.ContributorJoin c) {
                    try {
                        Message msg = new Message();
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("user_id",c.user_id);

                       /* try{
                            jsonObject.put("store_type",c.store_type);
                            jsonObject.put("level",c.level);
                        }catch (Throwable e){
                            e.printStackTrace();
                        }*/

                        msg.obj = jsonObject;

                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"회원가입 실패",Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Throwable e){e.printStackTrace();}

       /* RetrofitModel hp = new RetrofitModel(MyApplication.get_instance().getApplicationContext());
        hp.addParam("store_type", store_type+"");
        hp.addParam("level", level+"");
        hp.addParam("name", name);
        hp.addParam("id", id);
        hp.addParam("password", password);
        hp.addParam("mobile", mobile);
        hp.addParam("store_name", store_name);
        hp.addParam("store_number", store_number);
        hp.addParam("store_building_id", store_building_id);
        hp.addParam("store_addr", store_addr);
        hp.addParam("store_photo", id);
        hp.addParam("store_onoﬀ", store_onoﬀ);
        hp.addParam("store_site", store_site);
        hp.addParam("store_id", store_id);
        hp.addParam("store_photo", store_photo);


        hp.setResultHandler(resultHandler);
        hp.setErrorHandler(errorHandler);
        hp.callBackHttp("join");*/

    }

    public static void buildingSearch(Context context, String depth, String value, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadBuildSearch("buildingSearch",depth,value);
            hp.setOnBuildingSearch(new Retrofit.OnBuildingSearch() {


                @Override
                public void onResponse(Retrofit.ContributorBuildSearch c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<BuildSearchData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                           for(int i=0; i<list.size();i++){
                               BuildSearchData data = list.get(i);
                               JSONObject object = new JSONObject();
                               object.put("id",data.getId());
                               object.put("depth",data.getDepth());
                               object.put("parent",data.getParent());
                               object.put("order",data.getOrder());
                               object.put("building_name", data.getBuilding_name());

                               jsonArray.put(object);
                           }
                            jsonObject.put("list",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"로그인 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void storeSearch(Context context, String depth, String value, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadStoreSearch("buildingSearch",depth,value);
            hp.setOnStoreSearch(new Retrofit.OnStoreSearch() {


                @Override
                public void onResponse(Retrofit.ContributorStoreSearch c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<StoreSearchData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                StoreSearchData data = list.get(i);
                                JSONObject object = new JSONObject();
                                object.put("id",data.getId());
                                object.put("store_name",data.getStore());

                                jsonArray.put(object);
                            }
                            jsonObject.put("list",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"로그인 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void banerList(Context context, String type, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadBannerList("bannerList",type);
            hp.setOnBannerList(new Retrofit.OnBannerList() {
                @Override
                public void onResponse(Retrofit.ContributorBannerList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<BannerLIstData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                BannerLIstData data = list.get(i);
                                JSONObject object = new JSONObject();
                                object.put("banner_target_id",data.getBanner_target_id());
                                object.put("image",data.getImage());
                                object.put("store_addr",data.getStore_addr());
                                object.put("name",data.getName());
                                jsonArray.put(object);
                            }
                            jsonObject.put("list",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void categoryList(Context context, Handler resultHandler, Handler errorHandler,boolean isLoading){
        try{
            Retrofit hp = new Retrofit( context,isLoading);
            hp.uploadCategoryList("product/categoryList");
            hp.setOnCategoryList(new Retrofit.OnCategoryList() {
                @Override
                public void onResponse(Retrofit.ContributorCategoryList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<CategoryLIstData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                CategoryLIstData data = list.get(i);
                                JSONObject object = new JSONObject();
                                object.put("code",data.getCode());
                                object.put("name",data.getName());
                                jsonArray.put(object);
                            }
                            jsonObject.put("list",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void codeList(Context context, String code, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadCodeList("product/codeList",code);
            hp.setOnCodeList(new Retrofit.OnCodeList() {
                @Override
                public void onResponse(Retrofit.ContributorCodeList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<CodeListData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                CodeListData data = list.get(i);
                                JSONObject object = new JSONObject();
                                object.put("code_value",data.getCode_name());
                                object.put("code_name",data.getCode_name());
                                jsonArray.put(object);
                            }
                            jsonObject.put("list",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void productList(Context context, String page, String category,String is_sale,String store_id, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadProductList("product/list",page,category,is_sale,store_id);
            hp.setOnProductList(new Retrofit.OnProductList() {
                @Override
                public void onResponse(Retrofit.ContributorProductList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        ProductResponse productResponse = c.list;
                        JSONObject object = new JSONObject();
                        object.put("total",productResponse.getTotal());
                        object.put("curent_page",productResponse.getCurent_page());
                        object.put("last_page",productResponse.getLast_page());
                        object.put("from",productResponse.getFrom());
                        object.put("to",productResponse.getTo());
                        object.put("next_page_url",productResponse.getNext_page_url());
                        object.put("prev_page_url",productResponse.getPrev_page_url());
                        object.put("per_page",productResponse.getPer_page());

                        List<ProductListData> list = productResponse.getData();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                ProductListData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("id",data.getId());
                                object1.put("image",data.getImage());
                                object1.put("image_count",data.getImage_count());
                                object1.put("name",data.getName());
                                object1.put("price", data.getPrice());
                                object1.put("store_name", data.getStore_name());
                                object1.put("store_id", data.getStore_id());
                                object1.put("like", data.getLike());
                                object1.put("created_at", data.getCreated_at());

                                jsonArray.put(object1);
                            }

                        }
                        object.put("data",jsonArray);
                        jsonObject.put("list",object);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }


    public static void productList(Context context, String page, String category,String is_sale,String store_id,
                                   String keyword,Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadProductListSearch("product/list",page,category,is_sale,store_id,keyword);
            hp.setOnProductList(new Retrofit.OnProductList() {
                @Override
                public void onResponse(Retrofit.ContributorProductList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        ProductResponse productResponse = c.list;
                        JSONObject object = new JSONObject();
                        object.put("total",productResponse.getTotal());
                        object.put("curent_page",productResponse.getCurent_page());
                        object.put("last_page",productResponse.getLast_page());
                        object.put("from",productResponse.getFrom());
                        object.put("to",productResponse.getTo());
                        object.put("next_page_url",productResponse.getNext_page_url());
                        object.put("prev_page_url",productResponse.getPrev_page_url());
                        object.put("per_page",productResponse.getPer_page());

                        List<ProductListData> list = productResponse.getData();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                ProductListData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("id",data.getId());
                                object1.put("image",data.getImage());
                                object1.put("image_count",data.getImage_count());
                                object1.put("name",data.getName());
                                object1.put("price", data.getPrice());
                                object1.put("store_name", data.getStore_name());
                                object1.put("store_id", data.getStore_id());
                                object1.put("like", data.getLike());
                                object1.put("created_at", data.getCreated_at());

                                jsonArray.put(object1);
                            }
                        }else{

                        }
                        object.put("data",jsonArray);
                        jsonObject.put("list",object);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void bestProductList(Context context,int page, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadBestProductList("product/best/list",page+"");
            hp.setOnBestProductList(new Retrofit.OnBestProductList() {
                @Override
                public void onResponse(Retrofit.ContributorBestProductList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        BestProductResponse productResponse = c.list;
                        JSONObject object = new JSONObject();
                        object.put("total",productResponse.getTotal());
                        object.put("curent_page",productResponse.getCurent_page());
                        object.put("last_page",productResponse.getLast_page());
                        object.put("from",productResponse.getFrom());
                        object.put("to",productResponse.getTo());
                        object.put("next_page_url",productResponse.getNext_page_url());
                        object.put("prev_page_url",productResponse.getPrev_page_url());
                        object.put("per_page",productResponse.getPer_page());

                        List<BestProductListData> list = productResponse.getData();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                BestProductListData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("rank",data.getRank());
                                object1.put("id",data.getId());
                                object1.put("name",data.getName());
                                object1.put("image",data.getImage());
                                object1.put("imge_count",data.getImage_count());
                                object1.put("price", data.getPrice());
                                object1.put("store_name", data.getStore_name());
                                object1.put("store_id", data.getStore_id());
                                object1.put("like", data.getLike());
                                object1.put("created_at", data.getCreated_at());
                                jsonArray.put(object1);
                            }

                        }
                        object.put("data",jsonArray);
                        jsonObject.put("list",object);

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void productVIew(Context context, String id, String user_id, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadProductView("product/view",id,user_id);
            hp.setOnProductView(new Retrofit.OnProductView() {
                @Override
                public void onResponse(Retrofit.ContributorProductView c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        ProductViewResponse response = c.data;
                        JSONObject object = new JSONObject();
                        List<ProductViewInfoData> list = response.getInfo();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                ProductViewInfoData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("id",data.getId());
                                object1.put("name",data.getName());
                                object1.put("price",data.getPrice());
                                object1.put("store_name",data.getStore_name());
                                object1.put("store_id", data.getStore_id());
                                object1.put("detail", data.getDetail());
                                object1.put("keyword", data.getKeyword());
                                object1.put("favorites", data.getFavorites());
                                jsonArray.put(object1);
                            }
                            jsonObject.put("info",jsonArray);
                        }
                       ProductViewOptionData option = response.getOption();
                        List<ProductViewOptionColorData> color = option.getColor();
                        JSONArray jsonArray1 = new JSONArray();

                        if(color!=null && color.size()>0){
                            for(int i=0; i<color.size();i++){
                                ProductViewOptionColorData data = color.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("code_value",data.getCode_value());
                                object1.put("code_name",data.getCode_name());
                                jsonArray1.put(object1);
                            }
                            object.put("color",jsonArray1);
                        }

                        List<ProductViewOptionSizeData> size = option.getSize();
                        JSONArray jsonArray2 = new JSONArray();

                        if(size!=null && size.size()>0){
                            for(int i=0; i<size.size();i++){
                                ProductViewOptionSizeData data = size.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("code_value",data.getCode_value());
                                object1.put("code_name",data.getCode_name());
                                jsonArray2.put(object1);
                            }
                            object.put("size",jsonArray2);
                        }
                        jsonObject.put("option",object);

                        List<ProductViewImageData> list1 = response.getImage();
                        JSONArray jsonArray3 = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                ProductViewImageData data = list1.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("url",data.getUrl());
                                jsonArray3.put(object1);
                            }
                            jsonObject.put("image",jsonArray3);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void buildingList(Context context, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadBuildingList("product/buildingList");
            hp.setOnBuildingList(new Retrofit.OnBuildingList() {
                @Override
                public void onResponse(Retrofit.ContributorBuildingList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<BuildingListData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                BuildingListData data = list.get(i);
                                JSONObject object = new JSONObject();
                                object.put("building_id",data.getBuilding_id());
                                object.put("name",data.getName());
                                jsonArray.put(object);
                            }
                            jsonObject.put("list",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void storeLList(Context context, String page, String building_id,String user_id, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadStoreList("product/storeList",page,building_id,user_id);
            hp.setOnStoreList(new Retrofit.OnStoreList() {
                @Override
                public void onResponse(Retrofit.ContributorStoreViewList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        StoreListResponse response = c.list;
                        jsonObject.put("total",response.getTotal());
                        jsonObject.put("curent_page",response.getCurent_page());
                        jsonObject.put("last_page",response.getLast_page());
                        jsonObject.put("from",response.getFrom());
                        jsonObject.put("to",response.getTo());
                        jsonObject.put("next_page_url",response.getNext_page_url());
                        jsonObject.put("prev_page_url",response.getPrev_page_url());
                        jsonObject.put("per_page",response.getPer_page());
                        List<StoreListData> list = response.getData();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                StoreListData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("id",data.getId());
                                object1.put("image",data.getImage());
                                object1.put("store_name",data.getStore_name());
                                object1.put("favorites",data.getFavorites());
                                object1.put("created_at", data.getCreated_at());
                                object1.put("store_name", data.getStore_name());
                                object1.put("is_new", data.getIs_new());
                                jsonArray.put(object1);
                            }
                            jsonObject.put("data",jsonArray);
                        }

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void storeLList(Context context, String page, String building_id,String user_id,String keyword, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadStoreList("product/storeList",page,building_id,user_id);
            hp.setOnStoreList(new Retrofit.OnStoreList() {
                @Override
                public void onResponse(Retrofit.ContributorStoreViewList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        StoreListResponse response = c.list;
                        JSONObject object = new JSONObject();
                        object.put("total",response.getTotal());
                        object.put("curent_page",response.getCurent_page());
                        object.put("last_page",response.getLast_page());
                        object.put("from",response.getFrom());
                        object.put("to",response.getTo());
                        object.put("next_page_url",response.getNext_page_url());
                        object.put("prev_page_url",response.getPrev_page_url());
                        object.put("per_page",response.getPer_page());

                        List<StoreListData> list = response.getData();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                StoreListData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("id",data.getId());
                                object1.put("image",data.getImage());
                                object1.put("store_name",data.getStore_name());
                                object1.put("favorites",data.getFavorites());
                                object1.put("created_at", data.getCreated_at());
                                object1.put("store_name", data.getStore_name());
                                object1.put("is_new", data.getIs_new());
                                jsonArray.put(object1);
                            }
                            object.put("data",jsonArray);
                        }
                        jsonObject.put("list",object);

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void productAdd(Context context,String name, String price,String category,String material_info,
                                  String cloth_info_1,String cloth_info_2,String cloth_info_3,String cloth_info_4,
                                  String washing_info,String style_info,String option_1, String option_2,
                                  String detail,String origin_info,String store_id,String open_type,String open_day,
                                  String kakao_open,String global_open,Bitmap[] bms, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadProductAdd("product/add",name,price,category,material_info,cloth_info_1,cloth_info_2,cloth_info_3,cloth_info_4,
                    washing_info,style_info,option_1,option_2,detail,origin_info,store_id,open_type,open_day,kakao_open,global_open,bms);
            hp.setOnProductAdd(new Retrofit.OnProductAdd() {
                @Override
                public void onResponse(Retrofit.ContributorProductAdd c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("p_id",c.p_id);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }



    public static void favorites(Context context,String user_id,String type,String target_id, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadFavorites("user/favorites",user_id,type,target_id);
            hp.setOnFavorites(new Retrofit.OnFavorites() {
                @Override
                public void onResponse(Retrofit.ContributorFavorites c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("user_id",c.user_id);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void noticeList(Context context, String page, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadNotice("notice/list",page);
            hp.setOnNoticeList(new Retrofit.OnNoticeList() {
                @Override
                public void onResponse(Retrofit.ContributorNoticeList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        NoticeListResponse response = c.list;
                        jsonObject.put("total",response.getTotal());
                        jsonObject.put("curent_page",response.getCurrent_page());
                        jsonObject.put("last_page",response.getLast_page());
                        jsonObject.put("per_page",response.getPer_page());
                        jsonObject.put("next_page_url",response.getNext_page_url());
                        jsonObject.put("prev_page_url",response.getPrev_page_url());
                        jsonObject.put("from",response.getFrom());
                        jsonObject.put("to",response.getTo());

                        List<NoticeListData> list = response.getData();
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                NoticeListData data = list.get(i);
                                JSONObject object1 = new JSONObject();
                                object1.put("title",data.getTitle());
                                object1.put("content",data.getContent());
                                object1.put("created_at", data.getCreated_at());
                                object1.put("isNew", data.getnNew()==1?true:false);
                                object1.put("is_new", data.getnNew());
                                jsonArray.put(object1);
                            }

                        }
                        jsonObject.put("data",jsonArray);

                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    t.printStackTrace();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }

    public static void recomWord(Context context, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadRecomWord("recomWord/list");
            hp.setOnRecomWord(new Retrofit.OnRecomWord() {
                @Override
                public void onResponse(Retrofit.ContributorRecomWord c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);

                        List<RecomWordData> list = c.list;
                        JSONArray jsonArray = new JSONArray();

                        if(list!=null && list.size()>0){
                            for(int i=0; i<list.size();i++){
                                RecomWordData data = list.get(i);
                                JSONObject object = new JSONObject();
                                object.put("recomm_order",data.getRecom_order());
                                object.put("word",data.getWord());
                                jsonArray.put(object);
                            }

                        }
                        jsonObject.put("list",jsonArray);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }


    public static void cartList(Context context, String user_id, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadCartList("cart/list",user_id);
            hp.setOnCartList(new Retrofit.OnCartList() {
                @Override
                public void onResponse(Retrofit.ContributorCartList c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("total",c.total);

                        List<CartGroupListResponse> cartListResponse = c.list;

                        JSONArray jsonArray = new JSONArray();
                        if(cartListResponse!=null &&cartListResponse.size()>0){
                            for(int i=0; i<cartListResponse.size();i++){
                                JSONObject object = new JSONObject();
                                CartListStoreData data = cartListResponse.get(i).getStore();
                                List<CartListProductData> product = cartListResponse.get(i).getProduct();
                                JSONArray jsonArray1 = new JSONArray();

                                if(product!=null&& product.size()>0){
                                    for(int j=0; j<product.size();j++){
                                        CartListProductData data1 = product.get(i);
                                        JSONObject object2 = new JSONObject();
                                        if(data!=null){
                                            object2.put("store_id",data.getStore_id());
                                            object2.put("store_name",data.getStore_name());
                                        }
                                        object2.put("c_id",data1.getC_id());
                                        object2.put("p_id",data1.getP_id());
                                        object2.put("name",data1.getName());
                                        object2.put("price",data1.getPrice());
                                        object2.put("amount",data1.getAmount());
                                        object2.put("image",data1.getImage());
                                        object2.put("option_1",data1.getOption_1());
                                        object2.put("option_2",data1.getOption_2());
                                        object2.put("total",data1.getTotal());
                                        jsonArray1.put(object2);
                                    }
                                }
                                object.put("product",jsonArray1);

                                jsonArray.put(object);
                            }
                        }

                        jsonObject.put("list",jsonArray);



                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    t.printStackTrace();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }
    public static void cartAmountMod(Context context, String user_id,String store_id, String c_id,String amount, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadCartAmountMod("cart/mod",user_id,store_id,c_id,amount,true);
            hp.setonCartAmountMod(new Retrofit.onCartAmountMod() {
                @Override
                public void onResponse(Retrofit.ContributorCartAmountModify c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("user_id",c.user_id);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"장바구니 담기 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }


    public static void cartDelete(Context context, String user_id, String c_id,String amount, Handler resultHandler, Handler errorHandler){
        try{
            Retrofit hp = new Retrofit( context);
            hp.uploadCartListDelete("cart/del",user_id,c_id,amount,true);

            hp.setOnCartListDelete(new Retrofit.OnCartListDelete() {
                @Override
                public void onResponse(Retrofit.ContributorCartListDelete c) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        if(c.result!=null && c.result.equals("success")){

                            jsonObject.put("result",true);

                        }else{
                            jsonObject.put("result",false);
                        }

                        jsonObject.put("error",c.error);
                        jsonObject.put("user_id",c.user_id);
                        Message msg = new Message();
                        msg.obj = jsonObject;
                        if(jsonObject.getBoolean("result")){
                            if(resultHandler!=null)  resultHandler.sendMessage(msg);
                        }else{
                            if(errorHandler!=null) errorHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context,"장바구니 담기 실패",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Throwable e){e.printStackTrace();}

    }
}
