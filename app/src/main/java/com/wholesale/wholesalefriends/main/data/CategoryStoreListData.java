package com.wholesale.wholesalefriends.main.data;

public class CategoryStoreListData {

    private String store_id;
    private Integer id;
    private String store_name;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getPhoto() {
        return store_id;
    }

    public void setPhoto(String photo) {
        this.store_id = photo;
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
