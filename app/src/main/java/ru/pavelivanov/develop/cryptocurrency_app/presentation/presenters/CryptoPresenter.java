package ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters;

import java.lang.ref.WeakReference;

import retrofit2.Call;

import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.CryptoApi;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.callbacks.CryptoDataCallback;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.NetworkService;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Data;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.models.utils.Constants;

public class CryptoPresenter {

    private WeakReference<ICryptoView> cryptoViewWeakReference;
    private CryptoApi coinApi = NetworkService.getInstance().getJSONApi();

    public CryptoPresenter(ICryptoView cryptoView) {
        this.cryptoViewWeakReference = new WeakReference<>(cryptoView);
    }

    public void loadCryptoData(int start, int limit) {
        if (cryptoViewWeakReference.get() != null) {
            cryptoViewWeakReference.get().showProgress();
        }

        Call<Data> call = coinApi.getLatestQuotes(Constants.CMC_PRO_API_KEY, start, limit);
        call.enqueue(new CryptoDataCallback(cryptoViewWeakReference.get()));
    }
}
