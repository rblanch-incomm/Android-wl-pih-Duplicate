package com.incomm.payithere.views.billsNoHistory;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.adapters.NoBillsHistoryStepsAdapter;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.repositories.NoBillerHistoryCMSRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoBillerHistoryFragment extends ViewFragment implements NoBillerHistoryMVP.View {

    @BindView(R.id.body_text_tv)
    TextView bodyTextTextView;
    @BindView(R.id.instruction_header_tv)
    TextView instructionHeaderTextView;
    @BindView(R.id.create_slip_bt)
    Button createSlipButton;
    @BindView(R.id.steps_ll)
    LinearLayout linearLayout;

    private MainTabInterface mListener;
    private NoBillerHistoryPresenter presenter;

    public NoBillerHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_biller_history, container, false);

        ButterKnife.bind(this, view);

        presenter = new NoBillerHistoryPresenter(this, new NoBillerHistoryCMSRepository());
        presenter.getViewElements();

        linearLayout = (LinearLayout) view.findViewById(R.id.steps_ll);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.displayTitle();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void setupUi() {
        presenter.displayTitle();
        presenter.displayBodyText();
        presenter.displayCreateSlipButtonTitle();
        presenter.displayInstructionHeader();
        presenter.displaySteps();
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void displayBodyText(String bodyText) {
        bodyTextTextView.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getGeneralText()));
        bodyTextTextView.setText(bodyText);
    }

    @Override
    public void displayInstructionHeader(String headerText) {
        instructionHeaderTextView.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getGeneralText()));
        instructionHeaderTextView.setText(headerText);
    }

    @Override
    public void displayCreateSlipButtonTitle(String buttonTitle) {
        createSlipButton.setText(buttonTitle);
        createSlipButton.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButtonTitle()));
        createSlipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.createSlipButtonClicked();
            }
        });
    }

    @Override
    public void displaySteps(List<String> listOfSteps) {
        for (int i = 0; i < listOfSteps.size(); i++) {
            View step = getLayoutInflater().inflate(R.layout.incl_instructions_step_row, null);
            TextView text = step.findViewById(R.id.step_text_tv);
            text.setText(listOfSteps.get(i));
            TextView number = step.findViewById(R.id.step_number_tv);
            String iterator = String.valueOf(i + 1);
            number.setText(iterator);
            step.setPadding(0, 10, 0, 10);
            linearLayout.addView(step);
        }
//        NoBillsHistoryStepsAdapter adapter = new NoBillsHistoryStepsAdapter(listOfSteps);
//        linearLayout.setLayoutManager(new LinearLayoutManager(getContext()));
//        linearLayout.setAdapter(adapter);
    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void showCreateNewPaymentSlip() {
        mListener.setTab(1);
    }
}
