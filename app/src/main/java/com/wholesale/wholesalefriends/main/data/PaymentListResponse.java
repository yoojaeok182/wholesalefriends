package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class PaymentListResponse {



    private String total_price;

    private List<PaymentListData> list;


    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<PaymentListData> getList() {
        return list;
    }

    public void setList(List<PaymentListData> data) {
        this.list = data;
    }
}
