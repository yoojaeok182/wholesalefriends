package com.wholesale.wholesalefriends.main.data;

import android.content.Intent;

import java.util.List;

public class ProductQnaListResponse {

    private Integer total;
    private Integer per_page;
    private Intent curent_page;
    private Integer last_page;

    private String next_page_url;
    private String prev_page_url;
    private Integer from;
    private Integer to;


    private List<ProductQnaListData> data;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Intent getCurent_page() {
        return curent_page;
    }

    public void setCurent_page(Intent curent_page) {
        this.curent_page = curent_page;
    }

    public Integer getLast_page() {
        return last_page;
    }

    public void setLast_page(Integer last_page) {
        this.last_page = last_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public List<ProductQnaListData> getData() {
        return data;
    }

    public void setData(List<ProductQnaListData> data) {
        this.data = data;
    }
}
