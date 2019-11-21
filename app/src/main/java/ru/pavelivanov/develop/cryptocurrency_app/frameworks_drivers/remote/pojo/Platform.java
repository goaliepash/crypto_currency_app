package ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.pojo;

import com.google.gson.annotations.SerializedName;

public class Platform {

    /**
     * Уникальный идентификатор CoinMarketCap для родительской платформы криптовалюты.
     */
    @SerializedName("id")
    public Integer id;

    /**
     * Название родительской платформы криптовалюты.
     */
    @SerializedName("name")
    public String name;

    /**
     * Символ тикера для родительской платформы криптовалюты.
     */
    @SerializedName("symbol")
    public String symbol;

    /**
     * URL-адрес дружественной сокращенной версии имени родительской платформы криптовалюты.
     */
    @SerializedName("slug")
    public String slug;

    /**
     * Адрес токена на родительской платформе криптовалюты.
     */
    @SerializedName("token_address")
    public String tokenAddress;
}
