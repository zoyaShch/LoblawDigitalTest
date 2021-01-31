package com.example.loblawdigital_test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductModel {

    private String id;	//string($uuid)
                        //  example: 062600300751
    //The Unique ID of a product. Each product will have their own ID and they are guaranteed to not collide. You can use this ID to add or remove products from the cart.

     private String name;
    //example: RAPID CLEAR® Spot Gel
    //The user readable name of the product. This is what the user will see.

     private String  image; //string($url
    // example: https://assets.beauty.shoppersdrugmart.ca/bb-prod-product-image/062600300751/enfr/01/front/400/white.jpg
    //The URL for the image of the product.

      private String price; //	string($price)
    //example: $11.99
    //The user readable price of the product. This is what the user will see.

    private String type;
    //example: BeautyFace
    //The Unique ID of of product type. This helps group products into different categories.

    public String getID(){
        return id;
    }
    public void setID(String id){
        this.id= id;
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image= image;
    }

    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price= price;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type= type;
    }

}
