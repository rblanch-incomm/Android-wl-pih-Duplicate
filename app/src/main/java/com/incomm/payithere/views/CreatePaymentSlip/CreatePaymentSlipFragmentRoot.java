package com.incomm.payithere.views.CreatePaymentSlip;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;
import com.incomm.payithere.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePaymentSlipFragmentRoot extends Fragment {

    private static final String TAG = "BillerHistoryRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_create_payment_slip_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        CreatePaymentSlipFragment fragment = new CreatePaymentSlipFragment();
        transaction.replace(R.id.create_payment_slip_root, fragment, Constants.CREATE_PAYMENT_SLIP_FRAGMENT);
        transaction.commit();

        return view;
    }

}
