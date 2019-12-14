package ru.pavelivanov.develop.cryptocurrency_app.presentation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response.Crypto;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.fragments.ConversionFragment;
import ru.pavelivanov.develop.cryptocurrency_app.presentation.ui.fragments.InfoFragment;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private String[] tabTitles = new String[] { "Info", "Conversion" };
    private Crypto crypto;

    public SampleFragmentPagerAdapter(FragmentManager fm, Crypto crypto) {
        super(fm);
        this.crypto = crypto;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return InfoFragment.newInstance(crypto);
        } else {
            return ConversionFragment.newInstance(crypto);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
