package com.incomm.payithere.views.signUp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;
import com.incomm.payithere.views.forgotpassword.ForgotPasswordFragment;

import static com.incomm.payithere.R.id.forgot_password_root;
import static com.incomm.payithere.R.id.sign_up_root;

/**
 * Created by Jerome Davis on 9/4/17.
 */

public class SignUpFragmentRoot extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_sign_up_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        SignUpFragment fragment = new SignUpFragment();
        /*passedInBundle.putInt("current_fragment", faqs_root);
        fragment.setArguments(passedInBundle);*/
        transaction.replace(sign_up_root, fragment);

        transaction.commit();

        return view;
    }
}
