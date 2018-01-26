package com.incomm.payithere.views.billsNoHistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;

public class NoBillerHistoryFragmentRoot extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_no_biller_history_fragment_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        NoBillerHistoryFragment fragment = new NoBillerHistoryFragment();
        transaction.replace(R.id.no_biller_history_root, fragment);
        transaction.commit();

        return view;
    }

}
