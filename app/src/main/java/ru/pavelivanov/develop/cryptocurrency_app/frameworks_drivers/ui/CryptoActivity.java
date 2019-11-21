package ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.List;
import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.adapters.CryptoRecyclerAdapter;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.remote.pojo.Datum;
import ru.pavelivanov.develop.cryptocurrency_app.frameworks_drivers.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.interface_adapters.presenters.CryptoPresenter;

public class CryptoActivity extends AppCompatActivity implements ICryptoView {

    private RelativeLayout progressRelativeLayout;
    private CryptoPresenter cryptoPresenter;
    private RecyclerView recyclerView;
    private List<Datum> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);
        initProgressRelativeLayout();
        initPresenter();
    }

    @Override
    public void showProgress() {
        progressRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void setCryptoCurrency(List<Datum> data) {
        this.data = data;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.crypto_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        CryptoRecyclerAdapter adapter = new CryptoRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void initProgressRelativeLayout() {
        progressRelativeLayout = findViewById(R.id.progress_relative_layout);
    }

    private void initPresenter() {
        cryptoPresenter = new CryptoPresenter(this);
        cryptoPresenter.loadCryptoData();
    }
}
