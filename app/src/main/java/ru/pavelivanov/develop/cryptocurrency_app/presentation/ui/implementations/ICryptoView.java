package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations;

import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;

public interface ICryptoView {

    /**
     * Показать ProgressBar, пока идёт загрузка данных.
     */
    void showProgress();

    /**
     * Скрыть ProgressBar, когда загрузка данных закончилась.
     */
    void hideProgress();

    /**
     * Получить данные по криптовалютам.
     *
     * @param data Данные по криптовалютам
     */
    void setCryptoCurrency(List<Datum> data);
}
