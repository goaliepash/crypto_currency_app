package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations;

import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;

public interface ICryptoView {

    void showProgress();

    void hideProgress();

    void setCryptoCurrency(List<Datum> data);
}
