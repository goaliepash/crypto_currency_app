package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.RoundingMode;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;

public class CryptoViewHolder extends BaseHolder {

    private Context context;
    private TextView cryptoCurrencyNameTextView;
    private TextView marketCapTextView;
    private TextView priceTextView;
    private TextView volume24hTextView;
    private TextView circulatingSupplyTextView;

    public CryptoViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        cryptoCurrencyNameTextView = itemView.findViewById(R.id.crypto_currency_name_text_view);
        marketCapTextView = itemView.findViewById(R.id.market_cap_text_view);
        priceTextView = itemView.findViewById(R.id.price_text_view);
        volume24hTextView = itemView.findViewById(R.id.volume_24h_text_view);
        circulatingSupplyTextView = itemView.findViewById(R.id.circulating_supply_text_view);
    }

    public void onBind(Datum datum) {

        String cryptoCurrencyName = context.getString(R.string.crypto_currency_name_string) + datum.name;
        cryptoCurrencyNameTextView.setText(cryptoCurrencyName);

        String marketCap = context.getString(R.string.market_cap_string) + datum.quote.uSD.marketCap.setScale(2, RoundingMode.UP).toString();
        marketCapTextView.setText(marketCap);

        String price = context.getString(R.string.price_string) + datum.quote.uSD.price.setScale(2, RoundingMode.UP).toString();
        priceTextView.setText(price);

        String volume24h = context.getString(R.string.volume_24h_string) + datum.quote.uSD.volume24h.setScale(2, RoundingMode.UP).toString();
        volume24hTextView.setText(volume24h);

        String circulatingSupply = context.getString(R.string.circulating_supply_string) + datum.circulatingSupply.setScale(2, RoundingMode.UP).toString();
        circulatingSupplyTextView.setText(circulatingSupply);
    }

    @Override
    protected void clear() {

    }
}