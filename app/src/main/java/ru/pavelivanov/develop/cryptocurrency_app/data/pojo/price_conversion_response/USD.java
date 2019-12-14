package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class USD {

    @SerializedName("price")
    public BigDecimal price;

    @SerializedName("last_updated")
    public String lastUpdated;
}
