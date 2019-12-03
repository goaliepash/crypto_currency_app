package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.holders;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import ru.pavelivanov.develop.cryptocurrency_app.R;

public class ProgressHolder extends BaseHolder {

    private ProgressBar progressBar;

    public ProgressHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }

    @Override
    protected void clear() {

    }
}
