package ru.pavelivanov.develop.cryptocurrency_app.data.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import ru.pavelivanov.develop.cryptocurrency_app.R;
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
     * @return полученное строковое представление
     */
    public static String getStringValue(BigDecimal value) {
        return value.setScale(2, RoundingMode.UP).toString();
    }

    /**
     * Получить имя криптовалюты с его символом в скобках.
     * Нужно для фрагмента с подробной информацией о выбранной котировке.
     *
     * @param context Контекст
     * @param value Выбранная котировка
     * @return Строковое представление имение в заданном формате
     */
    public static String getCryptoName(Context context, Crypto value) {
        return context.getString(R.string.crypto_currency_name_string) +
                value.name +
                " (" +
                value.symbol +
                ")";
    }

    /**
     * Получить дату добавления выбранной котировки на CoinMarketCap.
     * Нужно для фрагмента с подробной информацией о выбранной котировке.
     *
     * @param context Контекст
     * @param value Строковое представление даты
     * @return Строковое представление даты в необходимом формате
     */
    public static String getDateAdded(Context context, String value) {
        if (value == null) {
            return context.getString(R.string.date_added_string);
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return context.getString(R.string.date_added_string) + formatter.format(Objects.requireNonNull(date));
    }
}
