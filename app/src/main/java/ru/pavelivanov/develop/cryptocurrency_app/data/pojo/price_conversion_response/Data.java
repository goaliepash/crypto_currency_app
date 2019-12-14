package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    public float id;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("name")
    public String name;

    @SerializedName("amount")
    public double amount;

    @SerializedName("last_updated")
    public String lastUpdated;

    @SerializedName("quote")
    public Quote quote;
}
