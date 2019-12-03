package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;

public abstract class BaseHolder extends RecyclerView.ViewHolder {

    BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(Datum datum) {
        clear();
    }

    protected abstract void clear();
}
