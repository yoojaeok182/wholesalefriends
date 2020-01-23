package com.wholesale.wholesalefriends.main.data;

public class NoticeListData {

    private String title;
    private String created_at;
    private String content;
    private boolean isCheck;
    private boolean isNew;
    private Integer is_new;

    public Integer getnNew() {
        return is_new;
    }

    public void setnNew(Integer nNew) {
        this.is_new = nNew;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
