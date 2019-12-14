package ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters;

import java.lang.ref.WeakReference;
import java.util.Objects;

import retrofit2.Call;

import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.Repository;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.callbacks.CryptoDataCallback;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.NetworkService;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.ListingLatestResponse;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.data.utils.Constants;

public class CryptoPresenter {

    private WeakReference<ICryptoView> cryptoViewWeakReference;
    private Repository repository = new Repository(NetworkService.getInstance().getJSONApi());

    /**
     * Конструктор с одним параметром.
     *
     * @param cryptoView Ссылка на View
     */
    public CryptoPresenter(ICryptoView cryptoView) {
        this.cryptoViewWeakReference = new WeakReference<>(cryptoView);
    }

    /**
     * Загрузить данные по криптовалютам из интернета.
     *
     * @param start Стартовая позиция списка
     * @param limit Сколько элементов данных получить
     * @param sort Параметр сортировки
     */
    public void loadCryptoData(int start, int limit, String sort, String sortDir) {
        ICryptoView cryptoView = cryptoViewWeakReference.get();

        if (cryptoView != null) {
            cryptoViewWeakReference.get().showProgress();
        }

        Call<ListingLatestResponse> call = repository.getListingLatest(Constants.CMC_PRO_API_KEY, start, limit, sort, sortDir);
        call.enqueue(new CryptoDataCallback(Objects.requireNonNull(cryptoView)));
    }
}
