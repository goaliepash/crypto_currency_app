package ru.pavelivanov.develop.cryptocurrency_app.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.pavelivanov.develop.cryptocurrency_app.remote.pojo.Data;

public interface CryptoApi {

    @GET("/v1/cryptocurrency/listings/latest")
    Call<Data> getLatestQuotes(@Query("CMC_PRO_API_KEY") String apiKey);
}
