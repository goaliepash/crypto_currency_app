package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;

public abstract class BaseHolder extends RecyclerView.ViewHolder {

    BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(Crypto crypto, int position) {
        clear();
    }

    protected abstract void clear();
}
