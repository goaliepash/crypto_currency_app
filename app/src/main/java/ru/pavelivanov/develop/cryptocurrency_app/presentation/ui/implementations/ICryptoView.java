package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations;

import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;

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
    void setCryptoCurrency(List<Crypto> data);
}
