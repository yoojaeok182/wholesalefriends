package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class PaymentGroupListResponse {


    private PaymentListStoreData store;

    private List<PaymentListProductData> product;
    private PaymentListPaymnetData payment;

    public PaymentListStoreData getStore() {
        return store;
    }

    public void setStore(PaymentListStoreData store) {
        this.store = store;
    }

    public List<PaymentListProductData> getProduct() {
        return product;
    }

    public void setProduct(List<PaymentListProductData> product) {
        this.product = product;
    }

    public PaymentListPaymnetData getPayment() {
        return payment;
    }

    public void setPayment(PaymentListPaymnetData payment) {
        this.payment = payment;
    }
}
