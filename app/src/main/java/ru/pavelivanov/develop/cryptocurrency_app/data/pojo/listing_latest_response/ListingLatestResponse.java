package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * POJO-класс для получения ответа от CoinMarketCap по запросу последних котировок криптовалют
 *
 * @author Иванов Павел Александрович
 */
public class ListingLatestResponse {

    /**
     * Список криптовалюты.
     */
    @SerializedName("data")
    public List<Crypto> data;

    /**
     * Статус вызова API.
     */
    @SerializedName("status")
    public Status status;
}
