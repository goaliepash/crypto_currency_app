package ru.pavelivanov.develop.cryptocurrency_app.crypto_mvp;

import java.lang.ref.WeakReference;
import retrofit2.Call;
import ru.pavelivanov.develop.cryptocurrency_app.remote.CryptoApi;
import ru.pavelivanov.develop.cryptocurrency_app.remote.callbacks.CryptoDataCallback;
import ru.pavelivanov.develop.cryptocurrency_app.remote.NetworkService;
import ru.pavelivanov.develop.cryptocurrency_app.remote.pojo.Data;
import ru.pavelivanov.develop.cryptocurrency_app.utils.Constants;

class CryptoPresenter {

    private WeakReference<ICryptoView> cryptoViewWeakReference;
    private CryptoApi coinApi = NetworkService.getInstance().getJSONApi();

    CryptoPresenter(ICryptoView cryptoView) {
        this.cryptoViewWeakReference = new WeakReference<>(cryptoView);
    }

    void loadCryptoData() {
        if (cryptoViewWeakReference.get() != null) {
            cryptoViewWeakReference.get().showProgress();
        }

        Call<Data> call = coinApi.getLatestQuotes(Constants.CMC_PRO_API_KEY);
        call.enqueue(new CryptoDataCallback(cryptoViewWeakReference.get()));
    }
}
