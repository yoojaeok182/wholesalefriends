package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class ProductViewOptionData {
    private List<ProductViewOptionColorData>color;
    private List<ProductViewOptionSizeData>size;

    public List<ProductViewOptionColorData> getColor() {
        return color;
    }

    public void setColor(List<ProductViewOptionColorData> color) {
        this.color = color;
    }

    public List<ProductViewOptionSizeData> getSize() {
        return size;
    }

    public void setSize(List<ProductViewOptionSizeData> size) {
        this.size = size;
    }
}
