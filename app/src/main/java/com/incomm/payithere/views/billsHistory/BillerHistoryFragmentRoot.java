package com.incomm.payithere.views.billsHistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.incomm.payithere.R;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.changepassword.ChangePasswordFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillerHistoryFragmentRoot extends Fragment {

    private static final String TAG = "BillerHistoryRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_biller_history_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        BillerHistoryFragment fragment = new BillerHistoryFragment();
        transaction.replace(R.id.biller_history_root, fragment, Constants.BILLER_HISTORY_IDENTIFIER);
        transaction.commit();

        return view;
    }

}
