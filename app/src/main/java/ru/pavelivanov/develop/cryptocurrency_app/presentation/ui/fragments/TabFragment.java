package ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import ru.pavelivanov.develop.cryptocurrency_app.R;
import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters.SampleFragmentPagerAdapter;

public class TabFragment extends Fragment {

    private static final String ARG_CRYPTO = "CRYPTO";
    private Crypto crypto;

    public static TabFragment newInstance(Crypto crypto) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_CRYPTO, crypto);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crypto = Objects.requireNonNull(getArguments()).getParcelable(ARG_CRYPTO);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewpager);

        SampleFragmentPagerAdapter adapter = new SampleFragmentPagerAdapter(getChildFragmentManager(), getContext(), crypto);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
