package ru.pavelivanov.develop.cryptocurrency_app.domain.remote.callbacks;

import java.lang.ref.WeakReference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.ListingLatestResponse;

public class CryptoDataCallback implements Callback<ListingLatestResponse> {

    private WeakReference<ICryptoView> cryptoViewWeakReference;

    public CryptoDataCallback(ICryptoView cryptoView) {
        this.cryptoViewWeakReference = new WeakReference<>(cryptoView);
    }

    @Override
    public void onResponse(Call<ListingLatestResponse> call, Response<ListingLatestResponse> response) {
        if (response.body() != null) {
            cryptoViewWeakReference.get().setCryptoCurrency(response.body().data);
            cryptoViewWeakReference.get().hideProgress();
        }
    }

    @Override
    public void onFailure(Call<ListingLatestResponse> call, Throwable t) {

    }
}
