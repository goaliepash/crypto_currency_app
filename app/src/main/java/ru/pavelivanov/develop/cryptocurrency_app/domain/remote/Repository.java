package ru.pavelivanov.develop.cryptocurrency_app.domain.remote;

import retrofit2.Call;
import ru.pavelivanov.develop.cryptocurrency_app.data.api.CryptoApi;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.ListingLatestResponse;

public class Repository {

    private CryptoApi api;

    public Repository(CryptoApi api) {
        this.api = api;
    }

    public Call<ListingLatestResponse> getListingLatest(String apiKey, int start, int limit, String sort) {
        return api.getListingLatest(apiKey, start, limit, sort);
    }
}
