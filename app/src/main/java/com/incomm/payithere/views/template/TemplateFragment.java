package com.incomm.payithere.views.template;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;

import butterknife.ButterKnife;

public class TemplateFragment extends ViewFragment implements TemplateMVP.View  {

    private MainTabInterface mListener;
    private TemplatePresenter presenter;


    public TemplateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO: Create a copy from the below xml, rename appropriately
        View rootView = inflater.inflate(R.layout.fragment_template, container, false);
        ButterKnife.bind(this,rootView);
        presenter = new TemplatePresenter(this,new TemplateCMSRepository());
        presenter.sendGoogleAnalytics(this.getActivity());
        presenter.getViewElements();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.setTitle();
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }
}
