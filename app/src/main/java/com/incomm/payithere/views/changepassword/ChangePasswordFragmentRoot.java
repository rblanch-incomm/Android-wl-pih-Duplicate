package com.incomm.payithere.views.changepassword;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.changepasswordsuccess.ChangePasswordSuccessFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragmentRoot extends Fragment {

    private static final String TAG = "ChangePasswordRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_change_password_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        ChangePasswordFragment changePasswordUpdateFragment = new ChangePasswordFragment();
        transaction.replace(R.id.change_password_root, changePasswordUpdateFragment, Constants.CHANGE_PASSWORD_IDENTIFIER);
        /*ChangePasswordSuccessFragment fragment = new ChangePasswordSuccessFragment();
        transaction.replace(R.id.change_password_root, fragment);*/

        transaction.commit();

        return view;
    }

}
