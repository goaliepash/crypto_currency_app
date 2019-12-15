package ru.pavelivanov.develop.cryptocurrency_app.domain.remote;

import retrofit2.Call;

import ru.pavelivanov.develop.cryptocurrency_app.data.api.CryptoApi;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.ListingLatestResponse;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response.PriceConversionResponse;

public class Repository {

    private CryptoApi api;

    public Repository(CryptoApi api) {
        this.api = api;
    }

    /**
     * Получить последние котировки криптовалют.
     *
     * @param apiKey уникальный API-ключ
     * @param start с какой позиции в списке получать данные
     * @param limit сколько всего элементов будет в списке
     * @param sort по какому критерию будут отсортированы данные
     * @return Список котировок криптовалют
     */
    public Call<ListingLatestResponse> getListingLatest(String apiKey, int start, int limit, String sort, String sortDir) {
        return api.getListingLatest(apiKey, start, limit, sort, sortDir);
    }

    /**
     * Получить конвертированную стоимость.
     *
     * @param apiKey API-ключ
     * @param id ID криптовалюты
     * @param amount количество
     * @param convert выбранная валюта
     * @return ответ от web-сервиса
     */
    public Call<PriceConversionResponse> getPriceConversion(String apiKey, int id, double amount, String convert) {
        return api.getPriceConversion(apiKey, id, amount, convert);
    }
}
