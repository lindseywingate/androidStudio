package com.example.myfirstapp;

public class ProductData {
    private String itemId;
    private String productTitle;
    private int productImage;
    private String productShipping;
    private String productCondition;
    private String productPrice;

    public ProductData( String productTitle, String productShip, String productCondition,String productPrice) {
        //this.itemId = itemId;
        this.productTitle = productTitle;
        this.productImage = productImage;
        this.productShipping = productShip;
        this.productCondition = productCondition;
        this.productPrice = productPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public String getProductTitle() {
        return productTitle;
    }
    public void setProductTitle(String newTitle) {this.productTitle = newTitle;}

    public int getProductImage() {
        return productImage;
    }

    public String getProductShipping() {
        return productShipping;
    }
    public void setProductShipping(String newShipping) {this.productShipping = newShipping;}

    public String getProductCondition() {
        return productCondition;
    }
    public void setProductCondition(String newCondition) {this.productShipping = newCondition;}

    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String newPrice) {this.productShipping = newPrice;}

}
