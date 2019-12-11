package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.data.utils.CryptoConverter;

public class CryptoViewHolder extends BaseHolder {

    private Context context;
    private TextView rankTextView;
    private TextView cryptoCurrencyNameTextView;
    private TextView marketCapTextView;
    private TextView priceTextView;
    private TextView volume24hTextView;
    private TextView circulatingSupplyTextView;
    private TextView percentageChange24hTextView;
    private ImageView raiseImageView;

    public CryptoViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        rankTextView = itemView.findViewById(R.id.rank_text_view);
        cryptoCurrencyNameTextView = itemView.findViewById(R.id.crypto_currency_name_text_view);
        marketCapTextView = itemView.findViewById(R.id.market_cap_text_view);
        priceTextView = itemView.findViewById(R.id.price_text_view);
        volume24hTextView = itemView.findViewById(R.id.volume_24h_text_view);
        circulatingSupplyTextView = itemView.findViewById(R.id.circulating_supply_text_view);
        percentageChange24hTextView = itemView.findViewById(R.id.percentage_change_24h_text_view);
        raiseImageView = itemView.findViewById(R.id.raise_image_view);
    }

    public void onBind(Crypto crypto, int position) {
        String cryptoCurrencyName = context.getString(R.string.crypto_currency_name_string) + crypto.name;
        cryptoCurrencyNameTextView.setText(cryptoCurrencyName);

        String rank = context.getString(R.string.rank_string) + position;
        rankTextView.setText(rank);

        String marketCap = (crypto.quote.uSD.marketCap != null)
                ? context.getString(R.string.market_cap_string) + CryptoConverter.getStringValue(crypto.quote.uSD.marketCap)
                : context.getString(R.string.market_cap_string);
        marketCapTextView.setText(marketCap);

        String price = (crypto.quote.uSD.price != null)
                ? context.getString(R.string.price_string) + CryptoConverter.getStringValue(crypto.quote.uSD.price)
                : context.getString(R.string.price_string);
        priceTextView.setText(price);

        String volume24h = (crypto.quote.uSD.volume24h != null)
                ? context.getString(R.string.volume_24h_string) + CryptoConverter.getStringValue(crypto.quote.uSD.volume24h)
                : context.getString(R.string.volume_24h_string);
        volume24hTextView.setText(volume24h);

        String circulatingSupply = (crypto.circulatingSupply != null)
                ? context.getString(R.string.circulating_supply_string) + CryptoConverter.getStringValue(crypto.circulatingSupply.setScale(2, RoundingMode.UP))
                : context.getString(R.string.circulating_supply_string);
        circulatingSupplyTextView.setText(circulatingSupply);

        String percentageChange24h = (crypto.quote.uSD.percentChange24h != null)
                ? context.getString(R.string.percentage_change_24h_string) + crypto.quote.uSD.percentChange24h
                : context.getString(R.string.percentage_change_24h_string);
        percentageChange24hTextView.setText(percentageChange24h);

        if (crypto.quote.uSD.percentChange24h.compareTo(new BigDecimal(0)) < 0) {
            raiseImageView.setImageResource(R.drawable.ic_arrow_downward_red_24dp);
        } else {
            raiseImageView.setImageResource(R.drawable.ic_arrow_upward_green_24dp);
        }
    }

    @Override
    protected void clear() {

    }
}