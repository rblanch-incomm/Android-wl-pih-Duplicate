package com.incomm.payithere.views.billHistoryDetail;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.factories.DialogFactory;
import com.incomm.payithere.models.BillsHistoryItem;
import com.incomm.payithere.models.services.response.PaymentPostResponse;
import com.incomm.payithere.repositories.BillHistoryDetailCMSRepository;
import com.incomm.payithere.services.PaymentDetailService;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.enterAmount.EnterAmountFragment;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillHistoryDetailsFragment extends ViewFragment implements BillHistoryDetailMVP.View {
    @BindView(R.id.question_tv)
    TextView billerNameView;
    @BindView(R.id.bill_amount)
    TextView billAmountView;
    @BindView(R.id.bill_created_date)
    TextView billCreatedView;
    @BindView(R.id.retailer_label)
    TextView retailerLabel;
    @BindView(R.id.retailer_name)
    TextView retailerNameView;
    @BindView(R.id.retailer_address)
    TextView retailerAddressView;
    @BindView(R.id.used_on_label)
    TextView usedOnLabel;
    @BindView(R.id.used_on)
    TextView usedOnView;
    @BindView(R.id.account_number_label)
    TextView accountNumberLabel;
    @BindView(R.id.account_number)
    TextView accountNumberView;
    @BindView(R.id.amount_tv)
    TextView amountTV;
    @BindView(R.id.fee_tv)
    TextView feeTV;
    @BindView(R.id.total_tv)
    TextView totalTV;
    @BindView(R.id.amount_label_tv)
    TextView amountLabelTV;
    @BindView(R.id.confirmation_number)
    TextView confirmationNumberTV;
    @BindView(R.id.confirmation_label)
    TextView confirmationLabelTV;
    @BindView(R.id.view_receipt_button)
    Button viewReceiptButton;
    @BindView(R.id.create_new_bill_button)
    Button createNewBillButton;

    private int mCurrentFragment;
    private MainTabInterface mListener;
    private BillHistoryDetailsPresenter presenter;
    private ProgressDialog progressDialog;
    private NumberFormat numberFormat;

    public BillHistoryDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BillsHistoryItem bill = null;
        numberFormat = NumberFormat.getCurrencyInstance();
        if (getArguments() != null) {
            bill = getArguments().getParcelable("key");
            mCurrentFragment = getArguments().getInt("current_fragment");
        }

        presenter = new BillHistoryDetailsPresenter(this, bill, new PaymentDetailService(), new BillHistoryDetailCMSRepository());
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onSetToolbarTitle(presenter.getTitle());
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bill_history_details, container, false);
        ButterKnife.bind(this,rootView);
        progressDialog = DialogFactory.createProgressDialog(getContext(), "Loading details");

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
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.displayLabels();
        presenter.displayBillAmount();
        presenter.displayBillerName();
        presenter.displayBillCreated();

        viewReceiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ereceiptButtonClicked();
            }
        });

        createNewBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newPaymentButtonClicked();
            }
        });
    }

    @Override
    public void setViewFeatures() { }

    @Override
    public void displayLabels(String usedAt, String usedOn, String account, String receiptButton, String newPaymentButton) {
        retailerLabel.setText(usedAt);
        usedOnLabel.setText(usedOn);
        accountNumberLabel.setText(account);
        viewReceiptButton.setText(receiptButton);
        createNewBillButton.setText(newPaymentButton);
    }

    @Override
    public void displayBillerName(String name) {
        billerNameView.setText(name);
    }

    @Override
    public void displayBillAmount(String amount) {
        billAmountView.setText(amount);
    }

    @Override
    public void displayBillCreated(String date) {
        billCreatedView.setText(date);
    }

    @Override
    public void displayRetailerName(String name) {
        retailerNameView.setText(name);
    }

    @Override
    public void displayRetailerAddress(String address) {
        retailerAddressView.setText(address);
    }

    @Override
    public void displayUsedTime(String time) {
        usedOnView.setText(time);
    }

    @Override
    public void displayAccountNumber(String accountNumber) {
        accountNumberView.setText(accountNumber);
    }

    @Override
    public void showTodoDialog() {
        DialogFactory.createOkAlertDialog(getContext(), "TODO", "This action hasn't been implemented yet.").show();
    }

    @Override
    public void showCreateNewSlip(PaymentPostResponse paymentPostResponse) {
        EnterAmountFragment enterAmountFragment = new EnterAmountFragment();
        Bundle b = new Bundle();
        b.putParcelable("payment", paymentPostResponse.getPaymentPost());
        b.putInt("current_fragment", R.id.biller_history_root);
        enterAmountFragment.setArguments(b);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.biller_history_root, enterAmountFragment);
        transaction.addToBackStack("Bill History Details");
        transaction.commit();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void displayAmount(String amount) {
        amountTV.setText(amount);
    }

    @Override
    public void displayFee(String fee) {
        feeTV.setText(fee);
    }

    @Override
    public void displayTotal(String total) {
        totalTV.setText(total);
    }

    @Override
    public void displayAmountDesc(String description) {
        amountLabelTV.setText(description);
    }

    @Override
    public void displayConfirmationNumber(String number) {
        confirmationNumberTV.setText(number);
    }

    @Override
    public void showErrorDialog(String errorCode) {
        displayErrorAlert(errorCode);
    }
}
