package com.incomm.payithere.views.more;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragmentRoot extends Fragment {

    private static final String TAG = "MoreRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_more_root, container, false);




        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        MoreFragment moreFragment =  new MoreFragment();
        transaction.replace(R.id.more_root, moreFragment);
        transaction.commit();

        return view;
    }

}
