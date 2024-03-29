package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.List;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.domain.listeners.PaginationListener;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.CryptoRecyclerAdapter;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.ICryptoView;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters.CryptoPresenter;

import static ru.pavelivanov.develop.cryptocurrency_app.domain.listeners.PaginationListener.PAGE_START;

public class CryptoActivity extends AppCompatActivity implements ICryptoView, SwipeRefreshLayout.OnRefreshListener{

    private Spinner sortSpinner;
    private ImageButton sortDirImageButton;
    private RelativeLayout progressRelativeLayout;
    private SwipeRefreshLayout cryptoSwipeRefreshLayout;
    private CryptoPresenter cryptoPresenter;
    private RecyclerView recyclerView;
    private CryptoRecyclerAdapter cryptoAdapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private static final int INCREMENT = 20;
    private static final int TOTAL_PAGE = 5000;
    private boolean isLoading = false;
    private String sortMode;
    private String sortDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cryptoPresenter.detachView();
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
    public void setCryptoCurrency(List<Crypto> data) {

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
        cryptoPresenter.loadCryptoData(currentPage, INCREMENT, sortMode, sortDir);
    }

    private void initView() {
        initSpinner();
        initSortDirButton();
        initRecyclerView();
        initCryptoSwipeRefreshLayout();
        initProgressRelativeLayout();
        initPresenter();
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
                String selectedItem = adapterView.getSelectedItem().toString();
                if (selectedItem.equals(getString(R.string.market_cap_title))) {
                    sortMode = getString(R.string.market_cap);
                } else if (selectedItem.equals(getString(R.string.name_title))) {
                    sortMode = getString(R.string.name);
                } else if (selectedItem.equals(getString(R.string.price_title))) {
                    sortMode = getString(R.string.price);
                } else if (selectedItem.equals(getString(R.string.volume_24h_title))) {
                    sortMode = getString(R.string.volume_24h);
                } else if (selectedItem.equals(getString(R.string.circulating_supply_title))) {
                    sortMode = getString(R.string.circulating_supply);
                } else if (selectedItem.equals(getString(R.string.percent_change_24h_title))) {
                    sortMode = getString(R.string.percent_change_24h);
                }
                onRefresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSortDirButton() {
        sortDirImageButton = findViewById(R.id.sort_dir_image_button);
        sortDir = sortDirImageButton.getTag().toString();
        sortDirImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sortDirImageButton.getTag().equals(getString(R.string.desc_string))) {
                    sortDirImageButton.setImageResource(R.drawable.ic_arrow_upward_24dp);
                    sortDirImageButton.setTag(getString(R.string.asc_string));
                } else {
                    sortDirImageButton.setImageResource(R.drawable.ic_arrow_downward_24dp);
                    sortDirImageButton.setTag(getString(R.string.desc_string));
                }
                sortDir = sortDirImageButton.getTag().toString();
                onRefresh();
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
                cryptoPresenter.loadCryptoData(currentPage, INCREMENT, sortMode, sortDir);
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
