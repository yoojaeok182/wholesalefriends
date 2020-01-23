package com.wholesale.wholesalefriends.main.data;

import android.content.Intent;

import java.util.List;

public class NoticeListResponse {
    private Integer total;
    private Integer current_page ;
    private Intent  last_page ;


    private List<NoticeListData> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Intent getLast_page() {
        return last_page;
    }

    public void setLast_page(Intent last_page) {
        this.last_page = last_page;
    }

    public List<NoticeListData> getData() {
        return data;
    }

    public void setData(List<NoticeListData> data) {
        this.data = data;
    }
}
