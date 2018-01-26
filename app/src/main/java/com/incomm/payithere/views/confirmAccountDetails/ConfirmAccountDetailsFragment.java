package com.incomm.payithere.views.confirmAccountDetails;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.ConfirmAccountListAdapter;
import com.incomm.payithere.models.ConfirmAccountItem;
import com.incomm.payithere.models.services.response.Form;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.repositories.ConfirmAccountDetailsCMSRepository;
import com.incomm.payithere.services.ConfirmAccountService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipFragment;
import com.incomm.payithere.views.enterAmount.EnterAmountFragment;
import com.incomm.payithere.views.enteraccountinformation.EnterAccountInformationFragment;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmAccountDetailsFragment extends ViewFragment implements ConfirmAccountDetailsMVP.View {

    private MainTabInterface mListener;
    private ConfirmAccountDetailsPresenter presenter;
    private ArrayList<ConfirmAccountItem> accountItems;
    private RecyclerView mRecyclerView;
    private String billerName;
    private PaymentPost payment;
    private NumberFormat numberFormat;
    private int backPressCount = 0;
    boolean doubleBackPressedOnce = false;
    private FragmentManager fragmentManager;

    @BindView(R.id.question_tv)
    TextView questionTV;
    @BindView(R.id.button_accept)
    Button confirmButton;
    @BindView(R.id.button_decline)
    Button declineButton;
    @BindView(R.id.fee_value)
    TextView feeTV;
    @BindView(R.id.fee_label)
    TextView feeLabelTV;
    @BindView(R.id.biller_name_tv)
    TextView billerNameTV;


    public ConfirmAccountDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle passedInBundle = getArguments();
        if (passedInBundle != null) {
            payment = passedInBundle.getParcelable("payment");
            int anFormat = passedInBundle.getInt("anFormat");
            accountItems = new ArrayList<>();
            for (Form item : payment.getBiller().getForm()) {
                if (payment.getUserData().get(item.getName()) != null) {
                    if (item.getName().equals("account_number")) {
                        String formatedAccNumber = getFormattedNumber(payment.getUserData().get("account_number"), anFormat);
                        accountItems.add(new ConfirmAccountItem(item.getLabel(), formatedAccNumber));
                    } else {
                        accountItems.add(new ConfirmAccountItem(item.getLabel(), payment.getUserData().get(item.getName())));
                    }
                }
            }
            if(payment.getValidationValue()!=null && !payment.getValidationValue().equals("")){
                accountItems.add(new ConfirmAccountItem(payment.getValidationLabel(), payment.getValidationValue()));
            }

        }
    }

    private String getFormattedNumber(String accountNumber, int anFormat) {
        StringBuilder str = new StringBuilder(accountNumber);
        if(anFormat==10){
            int idx = 4;
            while (idx < str.length())
            {
                str.insert(idx, " ");
                idx = idx + 4;
            }
        }else if(anFormat == 20){
            str.insert(5,"-");
        }
        return str.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO: Create a copy from the below xml, rename appropriately
        View rootView = inflater.inflate(R.layout.fragment_confirm_account_details, container, false);
        ButterKnife.bind(this, rootView);
        numberFormat = NumberFormat.getCurrencyInstance();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.confirm_account_recycler_view);
        presenter = new ConfirmAccountDetailsPresenter(this, new ConfirmAccountDetailsCMSRepository(), new ConfirmAccountService());
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
    public void onResume() {
        super.onResume();
        // Disable the back button for this fragment
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (doubleBackPressedOnce) {
                        fragmentManager = getFragmentManager();
                        while (fragmentManager.getBackStackEntryCount() != 0) {
                            fragmentManager.popBackStackImmediate();
                        }
                        return true;
                    }
                    doubleBackPressedOnce = true;
                    Toast.makeText(getActivity(), "Please click BACK again to start over.", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackPressedOnce=false;
                        }
                    }, 2000);
                    return true;
                }
                return false;
            }
        });

        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.setTitle();
        displayAccountList();
        displayBillerName();
        presenter.setQuestionLabel();
        presenter.setConfirmButtonText();
        presenter.setDeclineButtonText();
        displayFee();
    }

    private void displayFee() {
        feeTV.setText(numberFormat.format(Float.parseFloat(payment.getFee())));
        feeTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @OnClick(R.id.button_accept)
    public void onConfirmClick() {
        presenter.confirmAccount(payment.getBiller().getId(), payment.getToken());
        //presenter.confirmAccount("b889ad54a546069f6f67e742a43e1583","d2e52ea95bfb1106753b76ece577cbd0");
    }

    @OnClick(R.id.button_decline)
    public void onDeclinceClick() {
       /* Bundle bundle = new Bundle();
        bundle.putString("billerId", payment.getBiller().getId());
        Fragment fragment = getFragmentManager().findFragmentByTag("Enter Account Info");
        //EnterAccountInformationFragment fragment = new EnterAccountInformationFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.create_payment_slip_root, fragment);
        transaction.commit();*/
        PayItHereApplication.setIsFromConfirmAcc(true);
       getActivity().onBackPressed();
    }

    private void displayBillerName() {
        billerNameTV.setText(payment.getBiller().getName());
    }

    private void displayAccountList() {
        ConfirmAccountListAdapter adapter = new ConfirmAccountListAdapter(accountItems, Color.parseColor(presenter.getGeneralTextColor()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void displayQuestion(String question) {
        questionTV.setText(question);
    }

    @Override
    public void displayConfirmButton(String confirm) {
        confirmButton.setText(confirm);
        confirmButton.setTextColor(Color.parseColor(presenter.getPositiveBtnTextColor()));
        confirmButton.setBackgroundColor(Color.parseColor(presenter.getPositiveButtonBackground()));
    }

    @Override
    public void displayDeclineButton(String decline) {
        declineButton.setText(decline);
        declineButton.setTextColor(Color.parseColor(presenter.getNegativeBtnTextColor()));
        declineButton.setBackgroundColor(Color.parseColor(presenter.getNegativeButtonBackground()));
    }

    @Override
    public void displayNextPage(PaymentPost payment) {
        if (payment.getAskUserForAmount()) {
            EnterAmountFragment fragment = new EnterAmountFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("payment", payment);
            bundle.putInt("current_fragment", R.id.create_payment_slip_root);
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_payment_slip_root, fragment);
            transaction.addToBackStack("Enter Payment Amount");
            transaction.commit();
        } else {
            BarcodeSlipFragment fragment = new BarcodeSlipFragment();
            Bundle bundle = new Bundle();
            bundle.putString("paymentId",payment.getToken());
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_payment_slip_root, fragment);
            transaction.addToBackStack("Barcode Slip Fragment");
            transaction.commit();
            //Toast.makeText(getActivity(), "View unavailable", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }
}
