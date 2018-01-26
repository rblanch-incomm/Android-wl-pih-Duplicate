package com.incomm.payithere.views.about;


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
public class AboutFragmentRoot extends Fragment {


    public AboutFragmentRoot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_about_fragment_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        AboutFragment fragment = new AboutFragment();

        transaction.replace(R.id.about_root, fragment);

        transaction.commit();

        return view;
    }

}
