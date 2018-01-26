package com.incomm.payithere.views.locations;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;
import com.incomm.payithere.views.more.MoreFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsFragmentRoot extends Fragment {

    private static final String TAG = "LocationsRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_locations_root, container, false);




        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        LocationsFragment fragment =  new LocationsFragment();
        transaction.replace(R.id.locations_root, fragment,"locations");
        transaction.commit();

        return view;
    }

}
