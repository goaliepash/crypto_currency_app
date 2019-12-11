package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response;

import com.google.gson.annotations.SerializedName;

/**
 * POJO-класс для получения метаданных о родительской платформе криптовалюты.
 *
 * @author Иванов Павел Александрович
 */
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
