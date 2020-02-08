package com.wholesale.wholesalefriends.main.data;

public class ProductQnaReplyData {
    Integer is_notice;
    Integer id;
    Integer q_id;
    String name;
    String created_at;
    String content;

    public Integer getIs_notice() {
        return is_notice;
    }

    public void setIs_notice(Integer is_notice) {
        this.is_notice = is_notice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
