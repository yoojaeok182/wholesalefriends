package com.wholesale.wholesalefriends.main.data;

public class StoreSearchListData {

    private String store_id;
    private Integer id;
    private String store_name;
    private String store_photo;
    private boolean isCheck;


    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_photo() {
        return store_photo;
    }

    public void setStore_photo(String store_photo) {
        this.store_photo = store_photo;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}
