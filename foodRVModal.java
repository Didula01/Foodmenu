package com.example.foodin;

import android.os.Parcel;
import android.os.Parcelable;

public class foodRVModal implements Parcelable {
    private  String foodName;
    private String foodDetails;
    private String foodPriceR;
    private String foodPriceL;
    private String foodImgUrl;
    private String foodID;

    public foodRVModal(){

    }

    protected foodRVModal(Parcel in) {
        foodName = in.readString();
        foodDetails = in.readString();
        foodPriceR = in.readString();
        foodPriceL = in.readString();
        foodImgUrl = in.readString();
        foodID = in.readString();
    }

    public static final Creator<foodRVModal> CREATOR = new Creator<foodRVModal>() {
        @Override
        public foodRVModal createFromParcel(Parcel in) {
            return new foodRVModal(in);
        }

        @Override
        public foodRVModal[] newArray(int size) {
            return new foodRVModal[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(String foodDetails) {
        this.foodDetails = foodDetails;
    }

    public String getFoodPriceR() {
        return foodPriceR;
    }

    public void setFoodPriceR(String foodPriceR) {
        this.foodPriceR = foodPriceR;
    }

    public String getFoodPriceL() {
        return foodPriceL;
    }

    public void setFoodPriceL(String foodPriceL) {
        this.foodPriceL = foodPriceL;
    }

    public String getFoodImgUrl() {
        return foodImgUrl;
    }

    public void setFoodImgUrl(String foodImgUrl) {
        this.foodImgUrl = foodImgUrl;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public foodRVModal(String foodName, String foodDetails, String foodPriceR, String foodPriceL, String foodImgUrl, String foodID) {
        this.foodName = foodName;
        this.foodDetails = foodDetails;
        this.foodPriceR = foodPriceR;
        this.foodPriceL = foodPriceL;
        this.foodImgUrl = foodImgUrl;
        this.foodID = foodID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodName);
        parcel.writeString(foodDetails);
        parcel.writeString(foodPriceR);
        parcel.writeString(foodPriceL);
        parcel.writeString(foodImgUrl);
        parcel.writeString(foodID);
    }
}
