package ru.pavelivanov.develop.cryptocurrency_app.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.ListingLatestResponse;

/**
 * Интерфейс для работы с CoinMarketCap API.
 *
 * @author Иванов Павел Александрович
 */
public interface CryptoApi {

    /**
     * Получить последние котировки криптовалют.
     *
     * @param apiKey уникальный API-ключ
     * @param start с какой позиции в списке получать данные
     * @param limit сколько всего элементов будет в списке
     * @param sort по какому критерию будут отсортированы данные
     * @return Список котировок криптовалют
     */
    @GET("/v1/cryptocurrency/listings/latest")
    Call<ListingLatestResponse> getListingLatest(
            @Query("CMC_PRO_API_KEY") String apiKey,
            @Query("start") int start,
            @Query("limit") int limit,
            @Query("sort") String sort);
}
