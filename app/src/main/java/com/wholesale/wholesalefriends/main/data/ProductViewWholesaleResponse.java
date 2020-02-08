package com.wholesale.wholesalefriends.main.data;

import java.util.List;

public class ProductViewWholesaleResponse {

    private ProductWholesaleViewInfoData info;
    private  List<ProductViewImageData> image;

    public ProductWholesaleViewInfoData getInfo() {
        return info;
    }

    public void setInfo(ProductWholesaleViewInfoData info) {
        this.info = info;
    }

    public List<ProductViewImageData> getImage() {
        return image;
    }

    public void setImage(List<ProductViewImageData> image) {
        this.image = image;
    }
}
