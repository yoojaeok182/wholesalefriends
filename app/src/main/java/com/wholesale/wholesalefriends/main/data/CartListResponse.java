package com.wholesale.wholesalefriends.main.data;

import android.content.Intent;

import java.util.List;

public class CartListResponse {



    private String total;

    private List<CartListProductData> list;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<CartListProductData> getProduct() {
        return list;
    }

    public void setProduct(List<CartListProductData> product) {
        this.list = product;
    }
}
