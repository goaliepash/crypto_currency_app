package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders.BaseHolder;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders.CryptoViewHolder;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders.ProgressHolder;

public class CryptoRecyclerAdapter extends Adapter<BaseHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private Context context;
    private List<Datum> data;

    public CryptoRecyclerAdapter(Context context, List<Datum> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new CryptoViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_crypto_currency, parent, false), context);
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        Datum datum = data.get(position);
        holder.onBind(datum);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == data.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void addItems(List<Datum> dataList) {
        data.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        data.add(new Datum());
        notifyItemInserted(data.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = data.size() - 1;
        Datum item = getItem(position);
        if (item != null) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    private Datum getItem(int position) {
        return data.get(position);
    }
}
