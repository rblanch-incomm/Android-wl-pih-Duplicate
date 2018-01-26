package com.incomm.payithere.views.faqs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;

import static com.incomm.payithere.R.id.faqs_root;


public class FaqsFragmentRoot extends Fragment {

    private Bundle passedInBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         passedInBundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_faq_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        FaqsFragment fragment = new FaqsFragment();
       /* passedInBundle.putInt("current_fragment", faqs_root);
        fragment.setArguments(passedInBundle);*/
        transaction.replace(faqs_root, fragment);

        transaction.commit();

        return view;
    }
}
