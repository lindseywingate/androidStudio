package com.example.myfirstapp;

public class ProductData {
    private String itemId;
    private String productTitle;
    private int productImage;
    private String productShip;
    private String productCondition;
    private String productPrice;

    public ProductData(String itemId, String productTitle, String productShip, String productCondition,String productPrice) {
        this.itemId = itemId;
        this.productTitle = productTitle;
        this.productImage = productImage;
        this.productShip = productShip;
        this.productCondition = productCondition;
        this.productPrice = productPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public String getProductTitle() {
        return productTitle;
    }

   public int getProductImage() {
        return productImage;
    }

    public String getProductShip() {
        return productShip;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
