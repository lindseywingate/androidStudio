package com.example.myfirstapp;

public class ProductData {
    private String productName;
    private String productDescription;
    private int productImage;

    public ProductData(String productName, String productDescription, int productImage) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductImage() {
        return productImage;
    }
}
