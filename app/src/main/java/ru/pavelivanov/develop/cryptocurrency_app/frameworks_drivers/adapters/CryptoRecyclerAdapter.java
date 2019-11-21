package ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.math.RoundingMode;
import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.pojo.Datum;

public class CryptoRecyclerAdapter extends Adapter<CryptoRecyclerAdapter.CryptoViewHolder> {

    private List<Datum> data;

    public CryptoRecyclerAdapter(List<Datum> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CryptoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CryptoViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_crypto_currency, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoViewHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CryptoViewHolder extends RecyclerView.ViewHolder {

        private TextView cryptoCurrencyNameTextView;
        private TextView marketCapTextView;
        private TextView priceTextView;
        private TextView volume24hTextView;
        private TextView circulatingSupplyTextView;

        CryptoViewHolder(@NonNull View itemView) {
            super(itemView);
            cryptoCurrencyNameTextView = itemView.findViewById(R.id.crypto_currency_name_text_view);
            marketCapTextView = itemView.findViewById(R.id.market_cap_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            volume24hTextView = itemView.findViewById(R.id.volume_24h_text_view);
            circulatingSupplyTextView = itemView.findViewById(R.id.circulating_supply_text_view);
        }

        void bindView(@NonNull Datum datum) {
            String cryptoCurrencyName = cryptoCurrencyNameTextView.getText() + datum.name;
            cryptoCurrencyNameTextView.setText(cryptoCurrencyName);

            String marketCap = marketCapTextView.getText() + datum.quote.uSD.marketCap.setScale(2, RoundingMode.UP).toString();
            marketCapTextView.setText(marketCap);

            String price = priceTextView.getText() + datum.quote.uSD.price.setScale(2, RoundingMode.UP).toString();
            priceTextView.setText(price);

            String volume24h = volume24hTextView.getText() + datum.quote.uSD.volume24h.setScale(2, RoundingMode.UP).toString();
            volume24hTextView.setText(volume24h);

            String circulatingSupply = circulatingSupplyTextView.getText() + datum.circulatingSupply.setScale(2, RoundingMode.UP).toString();
            circulatingSupplyTextView.setText(circulatingSupply);
        }
    }
}
