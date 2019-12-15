package ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters;

import java.lang.ref.WeakReference;
import java.util.Objects;

import retrofit2.Call;

import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response.PriceConversionResponse;
import ru.pavelivanov.develop.cryptocurrency_app.data.utils.Constants;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.NetworkService;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.Repository;
import ru.pavelivanov.develop.cryptocurrency_app.domain.remote.callbacks.ConversionCallback;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.IConversionView;

public class ConversionPresenter {

    private WeakReference<IConversionView> conversionViewWeakReference;
    private Repository repository = new Repository(NetworkService.getInstance().getJSONApi());

    public ConversionPresenter(IConversionView conversionView) {
        this.conversionViewWeakReference = new WeakReference<>(conversionView);
    }

    /**
     * Получить конвертацию криптовалюты в выбранной валюте.
     *
     * @param id ID криптовалюты
     * @param amount количество
     * @param convert выбранная валюта
     */
    public void convertCryptoCurrency(int id, double amount, String convert) {
        IConversionView conversionView = conversionViewWeakReference.get();

        if (conversionView != null) {
            conversionView.showProgress();
        }

        Call<PriceConversionResponse> call = repository.getPriceConversion(Constants.CMC_PRO_API_KEY, id, amount, convert);
        call.enqueue(new ConversionCallback(Objects.requireNonNull(conversionView)));
    }
}
