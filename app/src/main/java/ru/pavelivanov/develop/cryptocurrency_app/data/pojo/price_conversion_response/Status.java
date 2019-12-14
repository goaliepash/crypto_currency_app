package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response;

import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("error_code")
    public float errorCode;

    @SerializedName("error_message")
    public String error_message;

    @SerializedName("elapsed")
    public float elapsed;

    @SerializedName("credit_count")
    public float creditCount;

    @SerializedName("notice")
    public String notice;
}
