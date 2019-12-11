package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response;

import com.google.gson.annotations.SerializedName;

/**
 * POJO-класс для получения котировок криптовалюты в долларах.
 *
 * @author Иванов Павел Александрович
 */
public class Quote {

    /**
     * Рыночная котировка
     */
    @SerializedName("USD")
    public USD uSD;
}