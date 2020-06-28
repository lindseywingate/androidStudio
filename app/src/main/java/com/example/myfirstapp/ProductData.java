package com.example.myfirstapp;

public class ProductData {
    private String itemId;
    private String productTitle;
    //private int productImage;

    public ProductData(String itemId, String productTitle) {
        this.itemId = itemId;
        this.productTitle = productTitle;
        //this.productImage = productImage;
    }

    public String getItemId() {
        return itemId;
    }

    public String getProductTitle() {
        return productTitle;
    }

   /* public int getProductImage() {
        return productImage;
    }*/
}
