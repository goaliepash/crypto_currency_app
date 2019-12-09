package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.List;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.domain.listeners.PaginationListener;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.CryptoRecyclerAdapter;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters.CryptoPresenter;

import static ru.pavelivanov.develop.cryptocurrency_app.domain.listeners.PaginationListener.PAGE_START;

public class CryptoActivity extends AppCompatActivity implements ICryptoView, SwipeRefreshLayout.OnRefreshListener{

    private Spinner sortSpinner;
    private RelativeLayout progressRelativeLayout;
    private SwipeRefreshLayout cryptoSwipeRefreshLayout;
    private CryptoPresenter cryptoPresenter;
    private RecyclerView recyclerView;
    private CryptoRecyclerAdapter cryptoAdapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private static final int INCREMENT = 10;
    private static final int TOTAL_PAGE = 5000;
    private boolean isLoading = false;
    private String sortMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);
        initSpinner();
        initRecyclerView();
        initCryptoSwipeRefreshLayout();
        initProgressRelativeLayout();
        initPresenter();
    }

    /**
     * Показать ProgressBar, пока идёт загрузка данных.
     */
    @Override
    public void showProgress() {
        progressRelativeLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Скрыть ProgressBar, когда загрузка данных закончилась.
     */
    @Override
    public void hideProgress() {
        progressRelativeLayout.setVisibility(View.GONE);
    }

    /**
     * Получить данные по криптовалютам.
     *
     * @param data Данные по криптовалютам
     */
    @Override
    public void setCryptoCurrency(List<Datum> data) {

        if (currentPage != PAGE_START) {
            cryptoAdapter.removeLoading();
        }
        cryptoAdapter.addItems(data);
        cryptoSwipeRefreshLayout.setRefreshing(false);

        if (currentPage < TOTAL_PAGE) {
            cryptoAdapter.addLoading();
        } else {
            isLastPage = true;
        }
        isLoading = false;
    }

    /**
     * Обновить список.
     */
    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        cryptoAdapter.clear();
        cryptoPresenter.loadCryptoData(currentPage, INCREMENT, sortMode);
    }

    private void initSpinner() {
        String[] data = getResources().getStringArray(R.array.sort_by_array);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner = findViewById(R.id.sort_spinner);
        sortSpinner.setAdapter(adapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getSelectedItem().toString().equals(getString(R.string.market_cap))) {
                    sortMode = getString(R.string.market_cap);
                } else if (adapterView.getSelectedItem().toString().equals(getString(R.string.name))) {
                    sortMode = getString(R.string.name);
                } else if (adapterView.getSelectedItem().toString().equals(getString(R.string.price))) {
                    sortMode = getString(R.string.price);
                } else if (adapterView.getSelectedItem().toString().equals(getString(R.string.volume_24h))) {
                    sortMode = getString(R.string.volume_24h);
                } else if (adapterView.getSelectedItem().toString().equals(getString(R.string.circulating_supply))) {
                    sortMode = getString(R.string.circulating_supply);
                }
                onRefresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initRecyclerView() {
        cryptoAdapter = new CryptoRecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.crypto_recycler_view);
        recyclerView.setAdapter(cryptoAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += INCREMENT;
                cryptoPresenter.loadCryptoData(currentPage, INCREMENT, sortMode);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void initCryptoSwipeRefreshLayout() {
        cryptoSwipeRefreshLayout = findViewById(R.id.crypto_swipe_refresh_layout);
        cryptoSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initProgressRelativeLayout() {
        progressRelativeLayout = findViewById(R.id.progress_relative_layout);
    }

    private void initPresenter() {
        cryptoPresenter = new CryptoPresenter(this);
    }
}
