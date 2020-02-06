package com.wholesale.wholesalefriends.main.data;

public class ProductListData {

    private Integer id;
    private String image;
    private Integer image_count;
    private String name;
    private String price;
    private String store_name;
    private Integer store_id;
    private Integer like;
    private String created_at;
    private String is_soldout;
    private String is_top;

    private boolean is_check;


    public boolean isCheck() {
        return is_check;
    }

    public void setCheck(boolean check) {
        is_check = check;
    }

    public String getIs_soldout() {
        return is_soldout;
    }

    public void setIs_soldout(String is_soldout) {
        this.is_soldout = is_soldout;
    }

    public String getIs_top() {
        return is_top;
    }

    public void setIs_top(String is_top) {
        this.is_top = is_top;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getImage_count() {
        return image_count;
    }

    public void setImage_count(Integer image_count) {
        this.image_count = image_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
