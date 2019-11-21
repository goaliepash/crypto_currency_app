package ru.pavelivanov.develop.cryptocurrency_app.interface_adapters.presenters;

import java.lang.ref.WeakReference;
import retrofit2.Call;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.CryptoApi;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.callbacks.CryptoDataCallback;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.NetworkService;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.pojo.Data;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.business_rules.utils.Constants;

public class CryptoPresenter {

    private WeakReference<ICryptoView> cryptoViewWeakReference;
    private CryptoApi coinApi = NetworkService.getInstance().getJSONApi();

    public CryptoPresenter(ICryptoView cryptoView) {
        this.cryptoViewWeakReference = new WeakReference<>(cryptoView);
    }

    public void loadCryptoData() {
        if (cryptoViewWeakReference.get() != null) {
            cryptoViewWeakReference.get().showProgress();
        }

        Call<Data> call = coinApi.getLatestQuotes(Constants.CMC_PRO_API_KEY);
        call.enqueue(new CryptoDataCallback(cryptoViewWeakReference.get()));
    }
}
