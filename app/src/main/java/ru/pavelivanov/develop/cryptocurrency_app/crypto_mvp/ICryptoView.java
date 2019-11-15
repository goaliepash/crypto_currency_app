package ru.pavelivanov.develop.cryptocurrency_app.crypto_mvp;

import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.remote.pojo.Datum;

public interface ICryptoView {

    void showProgress();

    void hideProgress();

    void setCryptoCurrency(List<Datum> data);
}
