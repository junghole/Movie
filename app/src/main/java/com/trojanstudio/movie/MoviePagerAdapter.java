package com.trojanstudio.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by smjs2000 on 10/3/16.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {

    List<MovieListFragment> movieListFragments;

    public MoviePagerAdapter(FragmentManager fm, List<MovieListFragment> fragments) {
        super(fm);
        movieListFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return movieListFragments.get(position);
    }

    @Override
    public int getCount() {
        return movieListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return movieListFragments.get(position).getArguments().getString(MovieListFragment.KEY_LIST_TYPE);
    }
}
