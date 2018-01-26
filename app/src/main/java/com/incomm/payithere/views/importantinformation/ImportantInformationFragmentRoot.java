package com.incomm.payithere.views.importantinformation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;

import static com.incomm.payithere.R.id.important_information_root;

public class ImportantInformationFragmentRoot extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_important_information_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        ImportantInformationFragment fragment = new ImportantInformationFragment();

        transaction.replace(important_information_root, fragment);

        transaction.commit();

        return view;
    }
}
