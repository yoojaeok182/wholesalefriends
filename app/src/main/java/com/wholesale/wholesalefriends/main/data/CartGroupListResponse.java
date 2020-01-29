package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class CartGroupListResponse {


    private CartListStoreData store;

    private List<CartListProductData> product;

    public CartListStoreData getStore() {
        return store;
    }

    public void setStore(CartListStoreData store) {
        this.store = store;
    }

    public List<CartListProductData> getProduct() {
        return product;
    }

    public void setProduct(List<CartListProductData> product) {
        this.product = product;
    }
}
