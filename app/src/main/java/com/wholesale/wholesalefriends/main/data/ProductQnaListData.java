package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class ProductQnaListData {
    Integer id;
    Integer q_id;
    String created_at;
    String name;
    String content;


    private List<ProductQnaReplyData> with_reply;

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ProductQnaReplyData> getWith_reply() {
        return with_reply;
    }

    public void setWith_reply(List<ProductQnaReplyData> with_reply) {
        this.with_reply = with_reply;
    }
}
