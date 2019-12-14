package ru.pavelivanov.develop.cryptocurrency_app.domain.listeners;

import android.content.Context;
import android.view.View;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.activities.CryptoActivity;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.fragments.TabFragment;

/**
 * Класс-слушатель для клика по элементу списка котировок криптовалют.
 *
 * @author Иванов Павел Александрович
 */
public class CryptoItemClickListener implements View.OnClickListener {

    private Context context;
    private Crypto crypto;

    /**
     * Конструктор с двумя параметрами
     *
     * @param context Контекст
     * @param crypto Объект выбранной котировки
     */
    public CryptoItemClickListener(Context context, Crypto crypto) {
        this.context = context;
        this.crypto = crypto;
    }

    @Override
    public void onClick(View view) {
        ((CryptoActivity) context)
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_crypto_layout, TabFragment.newInstance(crypto))
                .commit();
    }
}
