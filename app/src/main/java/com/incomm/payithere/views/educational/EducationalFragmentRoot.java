package com.incomm.payithere.views.educational;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EducationalFragmentRoot extends Fragment {


    public EducationalFragmentRoot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_educational_fragment_root, container, false);
    }

}
