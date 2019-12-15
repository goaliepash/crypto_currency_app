package ru.pavelivanov.develop.cryptocurrency_app.data.utils;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;

/**
 * Класс с функциями для конвертации.
 *
 * @author Иванов Павел Александрович
 */
public class CryptoConverter {

    /**
     * Получить строковое представление числа BigDecimal c 2-мя знаками после запятой.
     *
     * @param value входное значение
     * @param moneyFormat денеженый формат
     * @return полученное строковое представление
     */
    public static String getStringValue(BigDecimal value, NumberFormat moneyFormat) {
        return moneyFormat.format(value);
    }

    /**
     * Получить имя криптовалюты с его символом в скобках.
     * Нужно для фрагмента с подробной информацией о выбранной котировке.
     *
     * @param value Выбранная котировка
     * @return Строковое представление имение в заданном формате
     */
    public static String getCryptoName(Crypto value) {
        return value.name +
                " (" +
                value.symbol +
                ")";
    }

    /**
     * Получить дату добавления выбранной котировки на CoinMarketCap.
     * Нужно для фрагмента с подробной информацией о выбранной котировке.
     *
     * @param value Строковое представление даты
     * @return Строковое представление даты в необходимом формате
     */
    public static String getDateAdded(String value) {
        if (value == null) {
            return "";
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat newFormatter = new SimpleDateFormat("dd.MM.yyyy");
        return newFormatter.format(Objects.requireNonNull(date));
    }
}
