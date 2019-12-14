package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Objects;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.data.utils.CryptoConverter;

public class InfoFragment extends Fragment {

    private static final String ARG_CRYPTO = "CRYPTO";
    private Crypto crypto;
    private TextView nameTextView;
    private TextView dateAddedTextView;
    private TextView maxSupplyTextView;
    private TextView totalSupplyTextView;
    private TextView priceTextView;
    private TextView volume24hTextView;
    private TextView percentageChange1hTextView;
    private ImageView percentageChange1hImageView;
    private TextView percentageChange24hTextView;
    private ImageView percentageChange24hImageView;
    private TextView percentageChange7dTextView;
    private ImageView percentageChange7dImageView;
    private TextView marketCapTextView;

    public static InfoFragment newInstance(Crypto crypto) {
        Bundle args = new Bundle();

        args.putParcelable(ARG_CRYPTO, crypto);

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crypto = Objects.requireNonNull(getArguments()).getParcelable(ARG_CRYPTO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(CryptoConverter.getCryptoName(crypto));

        dateAddedTextView = view.findViewById(R.id.date_added_text_view);
        dateAddedTextView.setText(CryptoConverter.getDateAdded(crypto.dateAdded));

        maxSupplyTextView = view.findViewById(R.id.max_supply_text_view);
        String maxSupplyText = (crypto.maxSupply != null)
                ? CryptoConverter.getStringValue(crypto.maxSupply)
                : "";
        maxSupplyTextView.setText(maxSupplyText);

        totalSupplyTextView = view.findViewById(R.id.total_supply_text_view);
        String totalSupplyText = (crypto.totalSupply != null)
                ? CryptoConverter.getStringValue(crypto.totalSupply)
                : "";
        totalSupplyTextView.setText(totalSupplyText);

        priceTextView = view.findViewById(R.id.price_text_view);
        String priceText = (crypto.quote.uSD.price != null)
                ? CryptoConverter.getStringValue(crypto.quote.uSD.price)
                : "";
        priceTextView.setText(priceText);

        volume24hTextView = view.findViewById(R.id.volume_24h_text_view);
        String volume24hText = (crypto.quote.uSD.volume24h != null)
                ? CryptoConverter.getStringValue(crypto.quote.uSD.volume24h)
                : "";
        volume24hTextView.setText(volume24hText);

        percentageChange1hTextView = view.findViewById(R.id.percentage_change_1h_text_view);
        String percentageChange1hText = (crypto.quote.uSD.percentChange1h != null)
                ? crypto.quote.uSD.percentChange1h.toString()
                : "";
        percentageChange1hTextView.setText(percentageChange1hText);

        percentageChange1hImageView = view.findViewById(R.id.percentage_change_1h_image_view);
        if (crypto.quote.uSD.percentChange1h.compareTo(new BigDecimal(0)) < 0) {
            percentageChange1hImageView.setImageResource(R.drawable.ic_arrow_downward_red_24dp);
        } else {
            percentageChange1hImageView.setImageResource(R.drawable.ic_arrow_upward_green_24dp);
        }

        percentageChange24hTextView = view.findViewById(R.id.percentage_change_24h_text_view);
        String percentageChange24hText = (crypto.quote.uSD.percentChange24h != null)
                ? crypto.quote.uSD.percentChange24h.toString()
                : "";
        percentageChange24hTextView.setText(percentageChange24hText);

        percentageChange24hImageView = view.findViewById(R.id.percentage_change_24h_image_view);
        if (crypto.quote.uSD.percentChange24h.compareTo(new BigDecimal(0)) < 0) {
            percentageChange24hImageView.setImageResource(R.drawable.ic_arrow_downward_red_24dp);
        } else {
            percentageChange24hImageView.setImageResource(R.drawable.ic_arrow_upward_green_24dp);
        }

        percentageChange7dTextView = view.findViewById(R.id.percentage_change_7d_text_view);
        String percentageChange7dText = (crypto.quote.uSD.percentChange7d != null)
                ? crypto.quote.uSD.percentChange7d.toString()
                : "";
        percentageChange7dTextView.setText(percentageChange7dText);

        percentageChange7dImageView = view.findViewById(R.id.percentage_change_7d_image_view);
        if (crypto.quote.uSD.percentChange7d.compareTo(new BigDecimal(0)) < 0) {
            percentageChange7dImageView.setImageResource(R.drawable.ic_arrow_downward_red_24dp);
        } else {
            percentageChange7dImageView.setImageResource(R.drawable.ic_arrow_upward_green_24dp);
        }

        marketCapTextView = view.findViewById(R.id.market_cap_text_view);
        String marketCapText = (crypto.quote.uSD.marketCap != null)
                ? CryptoConverter.getStringValue(crypto.quote.uSD.marketCap)
                : "";
        marketCapTextView.setText(marketCapText);
    }
}
