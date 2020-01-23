package com.wholesale.wholesalefriends.module;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.main.base.MyApplication;
import com.wholesale.wholesalefriends.main.data.CategoryListResponse;

public class AppData {

    private static AppData instance;
    public static AppData getInstance(){
        if(instance ==null){
            instance = new AppData();
        }
        return instance;
    }

    private AppData(){}

    private CategoryListResponse categoryListResponse;

    public CategoryListResponse getCategoryListResponse() {
        if(categoryListResponse == null){
            categoryListResponse = new Gson().fromJson(SharedPreference.getSharedPreference(MyApplication.getApplication(),"categoryListResponse"),CategoryListResponse.class);
        }
        return categoryListResponse;
    }

    public void setCategoryListResponse(CategoryListResponse categoryListResponse) {
        this.categoryListResponse = categoryListResponse;
        try{
            SharedPreference.putSharedPreference(MyApplication.getApplication(),"categoryListResponse",new Gson().toJson(categoryListResponse));
        }catch (Throwable e){}
    }
}
