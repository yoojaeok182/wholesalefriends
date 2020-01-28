package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class ProductViewResponse {

    private List<ProductViewInfoData> info;
    private  List<ProductViewImageData> image;
    private  ProductViewOptionData option;

    public ProductViewOptionData getOption() {
        return option;
    }

    public void setOption(ProductViewOptionData option) {
        this.option = option;
    }

    public List<ProductViewInfoData> getInfo() {
        return info;
    }

    public void setInfo(List<ProductViewInfoData> info) {
        this.info = info;
    }

    public List<ProductViewImageData> getImage() {
        return image;
    }

    public void setImage(List<ProductViewImageData> image) {
        this.image = image;
    }
}
