package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations;

import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response.Data;

public interface IConversionView {

    /**
     * Показать ProgressBar, пока идёт загрузка данных.
     */
    void showProgress();

    /**
     * Скрыть ProgressBar, когда загрузка данных закончилась.
     */
    void hideProgress();

    /**
     * Получить стоимость.
     *
     * @param data данные
     */
    void setCost(Data data);
}
