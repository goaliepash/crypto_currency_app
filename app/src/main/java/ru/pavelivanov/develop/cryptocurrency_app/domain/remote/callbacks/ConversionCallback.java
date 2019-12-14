package ru.pavelivanov.develop.cryptocurrency_app.domain.remote.callbacks;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response.PriceConversionResponse;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.IConversionView;

public class ConversionCallback implements Callback<PriceConversionResponse> {

    private WeakReference<IConversionView> conversionViewWeakReference;

    public ConversionCallback(IConversionView conversionView) {
        this.conversionViewWeakReference = new WeakReference<>(conversionView);
    }

    @Override
    public void onResponse(Call<PriceConversionResponse> call, Response<PriceConversionResponse> response) {
        if (response.body() != null) {
            conversionViewWeakReference.get().setCost(response.body().data);
            conversionViewWeakReference.get().hideProgress();
        }
    }

    @Override
    public void onFailure(Call<PriceConversionResponse> call, Throwable t) {

    }
}
