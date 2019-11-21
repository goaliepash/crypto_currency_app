package ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.ui.implementations;

import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.pojo.Datum;

public interface ICryptoView {

    void showProgress();

    void hideProgress();

    void setCryptoCurrency(List<Datum> data);
}
