package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.domain.listeners.PaginationListener;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.CryptoRecyclerAdapter;
import ru.pavelivanov.develop.cryptocurrency_app.models.pojo.Datum;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters.CryptoPresenter;

import static ru.pavelivanov.develop.cryptocurrency_app.domain.listeners.PaginationListener.PAGE_START;

public class CryptoActivity extends AppCompatActivity implements ICryptoView, SwipeRefreshLayout.OnRefreshListener{

    private RelativeLayout progressRelativeLayout;
    private SwipeRefreshLayout cryptoSwipeRefreshLayout;
    private CryptoPresenter cryptoPresenter;
    private RecyclerView recyclerView;
    private CryptoRecyclerAdapter cryptoAdapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 100;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);
        initRecyclerView();
        initCryptoSwipeRefreshLayout();
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
        cryptoAdapter = new CryptoRecyclerAdapter(this, data);
        recyclerView.setAdapter(cryptoAdapter);

        if (currentPage != PAGE_START) {
            cryptoAdapter.removeLoading();
        }
        cryptoSwipeRefreshLayout.setRefreshing(false);

        if (currentPage < totalPage) {
            cryptoAdapter.addLoading();
        } else {
            isLastPage = true;
        }
        isLoading = false;
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        cryptoAdapter.clear();
        cryptoPresenter.loadCryptoData(currentPage, totalPage);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.crypto_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                cryptoPresenter.loadCryptoData(currentPage, totalPage);
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
        cryptoPresenter.loadCryptoData(currentPage, totalPage);
    }
}
