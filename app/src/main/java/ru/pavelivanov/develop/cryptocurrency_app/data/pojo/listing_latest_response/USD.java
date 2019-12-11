package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * POJO-класс для рыночной котировки криптовалюты в долларах.
 *
 * @author Иванов Павел Александрович
 */
public class USD {

    /**
     * Цена в указанной валюте.
     */
    @SerializedName("price")
    public BigDecimal price;

    /**
     * 24-часовой оборот криптовалюты.
     */
    @SerializedName("volume_24h")
    public BigDecimal volume24h;

    /**
     * 24-часовой оборот криптовалюты.
     * Возвращается только если указан параметр aux.
     */
    @SerializedName("volume_24h_reported")
    public BigDecimal volume24hReported;

    /**
     * 7-дневный оборот криптовалюты.
     */
    @SerializedName("volume_7d")
    public BigDecimal volume7d;

    /**
     * 7-дневный оборот криптовалюты.
     * Возвращается только если указан параметр aux.
     */
    @SerializedName("volume_7d_reported")
    public Double volume7dReported;

    /**
     * 30-дневный оборот криптовалюты.
     */
    @SerializedName("volume_30d")
    public BigDecimal volume30d;

    /**
     * 30-дневный оборот криптовалюты.
     * Возвращается только если указан параметр aux.
     */
    @SerializedName("volume_30d_reported")
    public BigDecimal volume30dReported;

    /**
     * Общая капитализация криптовалюты.
     */
    @SerializedName("market_cap")
    public BigDecimal marketCap;

    /**
     * Процентное изменение стоимости за 1 час.
     */
    @SerializedName("percent_change_1h")
    public BigDecimal percentChange1h;

    /**
     * Процентное изменение стоимости за 24 часа.
     */
    @SerializedName("percent_change_24h")
    public BigDecimal percentChange24h;

    /**
     * Процентное изменение стоимости за 7 дней.
     */
    @SerializedName("percent_change_7d")
    public BigDecimal percentChange7d;

    /**
     * Временная метка (ISO 8601), когда использовалась текущая стоимость валюты конвертации.
     */
    @SerializedName("last_updated")
    public String lastUpdated;
}
