package com.incomm.payithere.views.selectPayment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.adapters.SelectPaymentTypeAdapter;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.Option;
import com.incomm.payithere.repositories.SelectPaymentCMSRepository;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.enteraccountinformation.EnterAccountInformationFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPaymentFragment extends ViewFragment implements SelectPaymentMVP.View  {

    @BindView(R.id.select_payment_rv)
    RecyclerView recyclerView;

    @BindView(R.id.contextual_button_tv)
    TextView contextualHelpLabel;

    private MainTabInterface listener;
    private SelectPaymentPresenter presenter;
    private RecyclerView.LayoutManager layoutManager;


    public SelectPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle passedInBundle = getArguments();
        presenter = new SelectPaymentPresenter(this,new SelectPaymentCMSRepository());
        if (passedInBundle != null) {
            BillerGroup biller = passedInBundle.getParcelable("biller");
            presenter.setBiller(biller);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO: Create a copy from the below xml, rename appropriately
        View rootView = inflater.inflate(R.layout.fragment_select_payment, container, false);
        ButterKnife.bind(this,rootView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter.getViewElements();
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            listener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.displayTitle();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void setupUi() {
        presenter.displayTitle();
        presenter.retrieveFeeLabel();
        presenter.retrievePostingTimeLabel();
        presenter.displayPaymentType();
        presenter.retrieveContextualHelpLabel();
    }

    @Override
    public void setViewFeatures() {
        if (presenter.getContextualHelp() != null) {
            contextualHelpLabel.setVisibility(View.VISIBLE);
            contextualHelpLabel.setTextColor(Color.parseColor(presenter.getContextualHelpLabelColor()));
            contextualHelpLabel.setText(presenter.getContextualHelp().getTitle());
            contextualHelpLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showContextHelp();
                }
            });
        } else {
            contextualHelpLabel.setVisibility(View.GONE);
        }
    }

    private void showContextHelp() {
        listener.showContextHelper(getActivity(), presenter.getContextualHelp());
    }

    @Override
    public void displayTitle(String title) {
        listener.onSetToolbarTitle(ActivityUtils.checkNull(title));
    }

    @Override
    public void displayPaymentType(final ArrayList<Option> paymentType) {
        SelectPaymentTypeAdapter adapter = new SelectPaymentTypeAdapter(paymentType, getContext(),
                presenter.getFeeLabel(), presenter.getPostingTimeLabel());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                EnterAccountInformationFragment fragment = new EnterAccountInformationFragment();
                Bundle bundle = new Bundle();
                bundle.putString("billerId", paymentType.get(position).getToken());
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.create_payment_slip_root, fragment,"Enter Account Info");
                transaction.addToBackStack("Enter Account Info");
                transaction.commit();
            }
        }));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }
}
