package ru.pavelivanov.develop.cryptocurrency_app.domain.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.pavelivanov.develop.cryptocurrency_app.data.api.CryptoApi;

import static ru.pavelivanov.develop.cryptocurrency_app.data.utils.Constants.BASE_URL;

public class NetworkService {

    private static NetworkService instance;
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    public CryptoApi getJSONApi() {
        return retrofit.create(CryptoApi.class);
    }
}
