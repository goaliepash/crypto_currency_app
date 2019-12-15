package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.price_conversion_response.Data;
import ru.pavelivanov.develop.cryptocurrency_app.data.utils.CryptoConverter;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.presenters.ConversionPresenter;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.implementations.IConversionView;

public class ConversionFragment extends Fragment implements IConversionView {

    private static final String ARG_CRYPTO = "CRYPTO";
    private static final String ARG_COST = "COST";
    private Crypto crypto;
    private TextView nameTextView;
    private EditText amountEditText;
    private Spinner currencySpinner;
    private Button convertButton;
    private TextView costTextView;
    private ConversionPresenter presenter;
    private RelativeLayout progressRelativeLayout;

    public static ConversionFragment newInstance(Crypto crypto) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_CRYPTO, crypto);
        ConversionFragment fragment = new ConversionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crypto = Objects.requireNonNull(getArguments()).getParcelable(ARG_CRYPTO);
        initPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversion, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_COST, costTextView.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            String cost = savedInstanceState.getString(ARG_COST);
            costTextView.setText(cost);
        }
    }

    /**
     * Показать загрузку.
     */
    @Override
    public void showProgress() {
        progressRelativeLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Скрыть загрузку.
     */
    @Override
    public void hideProgress() {
        progressRelativeLayout.setVisibility(View.GONE);
    }

    /**
     * Получить конвертированную стоимость.
     *
     * @param data данные
     */
    @Override
    public void setCost(Data data) {
        String selectedItem = currencySpinner.getSelectedItem().toString();
        String cost = "";
        if (selectedItem.equals(getString(R.string.usd))) {
            cost = CryptoConverter.getStringValue(data.quote.USD.price);
        } else if (selectedItem.equals(getString(R.string.eur))) {
            cost = CryptoConverter.getStringValue(data.quote.EUR.price);
        } else if (selectedItem.equals(getString(R.string.gbp))) {
            cost = CryptoConverter.getStringValue(data.quote.GBP.price);
        } else if (selectedItem.equals(getString(R.string.rub))) {
            cost = CryptoConverter.getStringValue(data.quote.RUB.price);
        }
        costTextView.setText(cost);
    }

    private void initPresenter() {
        presenter = new ConversionPresenter(this);
    }

    private void initView(View view) {
        nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(CryptoConverter.getCryptoName(crypto));

        initEditText(view);
        initSpinner(view);

        convertButton = view.findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = crypto.id;
                double amount = Double.valueOf(amountEditText.getText().toString());
                String convert = currencySpinner.getSelectedItem().toString();
                presenter.convertCryptoCurrency(id, amount, convert);
            }
        });

        costTextView = view.findViewById(R.id.cost_text_view);

        progressRelativeLayout = view.findViewById(R.id.progress_relative_layout);
    }

    private void initEditText(View view) {
        amountEditText = view.findViewById(R.id.amount_edit_text);
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    convertButton.setEnabled(true);
                } else {
                    convertButton.setEnabled(false);
                }
            }
        });
    }

    private void initSpinner(View view) {
        String[] data = getResources().getStringArray(R.array.currency_array);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner = view.findViewById(R.id.currency_spinner);
        currencySpinner.setAdapter(adapter);
    }
}
