package com.wholesale.wholesalefriends.main.data;

public class SideCountData {
     String image; //: 상가 이미지 -
    String store_name; //: 상가명 -
    String  level; //: 대표, 직원 -
    Integer views; //: 방문 카운터 수 -
    Integer  users; //: 거래처 수 -
    Integer  products; //: 상품 수
     String addr; //: 상가 주소
    Integer  store_building_id; //: 상품 수

    public Integer getStore_building_id() {
        return store_building_id;
    }

    public void setStore_building_id(Integer store_building_id) {
        this.store_building_id = store_building_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }

    public Integer getProducts() {
        return products;
    }

    public void setProducts(Integer products) {
        this.products = products;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
