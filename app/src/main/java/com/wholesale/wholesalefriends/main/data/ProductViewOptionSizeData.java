package com.wholesale.wholesalefriends.main.data;

public class ProductViewOptionSizeData {
    String code_value;
    String code_name;

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
    public String getCode_value() {
        return code_value;
    }

    public void setCode_value(String code_value) {
        this.code_value = code_value;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }
}
