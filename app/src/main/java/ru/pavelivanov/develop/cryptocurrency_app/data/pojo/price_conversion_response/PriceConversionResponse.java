package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response;

import com.google.gson.annotations.SerializedName;

public class PriceConversionResponse {

    @SerializedName("status")
    public Status status;

    @SerializedName("data")
    public Data data;
}
