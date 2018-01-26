package com.incomm.payithere.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.models.tabs.NavigationTab;


/**
 * Created by agodambe on 1/19/2017.
 */


public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fm;
    private Context context;
    //private List<NavTab> tabs;
    private NavigationTab[] tabs;


    public SectionsPagerAdapter(FragmentManager fm, Context context, NavigationTab[] tabs) {
        super(fm);
        this.fm = fm;
        this.context = context;
        this.tabs = tabs;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = FragmentFactory.getFragment(tabs[position].getIdentifier());
        return fragment;
    }



    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }



}
