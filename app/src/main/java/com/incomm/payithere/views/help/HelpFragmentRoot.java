package com.incomm.payithere.views.help;


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
public class HelpFragmentRoot extends Fragment {


    public HelpFragmentRoot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_fragment_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        HelpFragment fragment = new HelpFragment();

        transaction.replace(R.id.help_root, fragment);

        transaction.commit();

        // Inflate the layout for this fragment
        return view;
    }

}
