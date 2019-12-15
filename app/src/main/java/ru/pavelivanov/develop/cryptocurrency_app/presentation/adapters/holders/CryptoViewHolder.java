package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.data.utils.CryptoConverter;

public class CryptoViewHolder extends BaseHolder {

    private TextView rankTextView;
    private TextView cryptoCurrencyNameTextView;
    private TextView marketCapTextView;
    private TextView priceTextView;
    private TextView volume24hTextView;
    private TextView circulatingSupplyTextView;
    private TextView percentageChange24hTextView;
    private ImageView raiseImageView;
    private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public CryptoViewHolder(@NonNull View itemView) {
        super(itemView);
        rankTextView = itemView.findViewById(R.id.rank_text_view);
        cryptoCurrencyNameTextView = itemView.findViewById(R.id.crypto_currency_name_text_view);
        marketCapTextView = itemView.findViewById(R.id.market_cap_text_view);
        priceTextView = itemView.findViewById(R.id.price_text_view);
        volume24hTextView = itemView.findViewById(R.id.volume_24h_text_view);
        circulatingSupplyTextView = itemView.findViewById(R.id.circulating_supply_text_view);
        percentageChange24hTextView = itemView.findViewById(R.id.percentage_change_24h_text_view);
        raiseImageView = itemView.findViewById(R.id.raise_image_view);
    }

    /**
     * Инициализировать view-элементы
     *
     * @param crypto данные элемента криптовалюты
     * @param position позиция криптовалюты в списке
     */
    public void onBind(Crypto crypto, int position) {
        String cryptoCurrencyName = crypto.name;
        cryptoCurrencyNameTextView.setText(cryptoCurrencyName);

        String rank = String.valueOf(position);
        rankTextView.setText(rank);


        String marketCap = (crypto.quote.uSD.marketCap != null)
                ? CryptoConverter.getStringValue(crypto.quote.uSD.marketCap, moneyFormat)
                : "";
        marketCapTextView.setText(marketCap);

        String price = (crypto.quote.uSD.price != null)
                ? CryptoConverter.getStringValue(crypto.quote.uSD.price, moneyFormat)
                : "";
        priceTextView.setText(price);

        String volume24h = (crypto.quote.uSD.volume24h != null)
                ? CryptoConverter.getStringValue(crypto.quote.uSD.volume24h, moneyFormat)
                : "";
        volume24hTextView.setText(volume24h);

        String circulatingSupply = (crypto.circulatingSupply != null)
                ? CryptoConverter.getStringValue(crypto.circulatingSupply, moneyFormat)
                : "";
        circulatingSupplyTextView.setText(circulatingSupply);

        String percentageChange24h = (crypto.quote.uSD.percentChange24h != null)
                ? crypto.quote.uSD.percentChange24h.toString()
                : "";
        percentageChange24hTextView.setText(percentageChange24h);

        if (crypto.quote.uSD.percentChange24h != null) {
            if (crypto.quote.uSD.percentChange24h.compareTo(new BigDecimal(0)) < 0) {
                raiseImageView.setImageResource(R.drawable.ic_arrow_downward_red_24dp);
            } else {
                raiseImageView.setImageResource(R.drawable.ic_arrow_upward_green_24dp);
            }
        }
    }
}