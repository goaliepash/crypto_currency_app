package ru.pavelivanov.develop.cryptocurrency_app.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class USD {

    /**
     * Цена в указанной валюте.
     */
    @SerializedName("price")
    public BigDecimal price;

    @SerializedName("volume_24h")
    public BigDecimal volume24h;

    @SerializedName("volume_24h_reported")
    public BigDecimal volume24hReported;

    @SerializedName("volume_7d")
    public BigDecimal volume7d;

    @SerializedName("volume_7d_reported")
    public Double volume7dReported;

    @SerializedName("volume_30d")
    public BigDecimal volume30d;

    @SerializedName("volume_30d_reported")
    public BigDecimal volume30dReported;

    @SerializedName("market_cap")
    public BigDecimal marketCap;

    @SerializedName("percent_change_1h")
    public BigDecimal percentChange1h;

    @SerializedName("percent_change_24h")
    public BigDecimal percentChange24h;

    @SerializedName("percent_change_7d")
    public BigDecimal percentChange7d;

    /**
     * Временная метка (ISO 8601), когда использовалась текущая стоимость валюты конвертации.
     */
    @SerializedName("last_updated")
    public String lastUpdated;
}
