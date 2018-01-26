package com.incomm.payithere.views.enterAmount;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.ErrorDisplayManager;
import com.incomm.payithere.models.cms.Error;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.repositories.EnterAmountCMSRepository;
import com.incomm.payithere.services.EnterAmountService;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipFragment;

import java.text.NumberFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterAmountFragment extends ViewFragment implements EnterAmountMVP.View,EditText.OnFocusChangeListener {

    @BindView(R.id.biller_name_tv)
    TextView billerNameTV;
    @BindView(R.id.account_number_label_tv)
    TextView accountNumberLabelTV;
    @BindView(R.id.account_number_tv)
    TextView accountNumberTV;
    @BindView(R.id.enter_amount_et)
    EditText enterAmountEV;
    @BindView(R.id.max_pay_amount_label_tv)
    TextView maxPaymentLabelTV;
    @BindView(R.id.max_pay_amount_tv)
    TextView maxPaymentTV;
    @BindView(R.id.payment_amount_label_tv)
    TextView paymentAmountLabelTV;
    @BindView(R.id.payment_amount_tv)
    TextView paymentAmountTV;
    @BindView(R.id.payment_fee_label_tv)
    TextView paymentFeeLabelTV;
    @BindView(R.id.payment_fee_tv)
    TextView paymentFeeTV;
    @BindView(R.id.payment_total_label_tv)
    TextView paymentTotalLabelTV;
    @BindView(R.id.payment_total_tv)
    TextView paymentTotalTV;
    @BindView(R.id.due_date_et)
    EditText dueDateEV;
    @BindView(R.id.notification_tv)
    TextView notificationTV;
    @BindView(R.id.notification_switch)
    Switch notificationSwitch;
    @BindView(R.id.create_payment_button)
    Button createPaymentButton;
    @BindView(R.id.ctx_help_tv)
    TextView contextualHelpLabel;


    private MainTabInterface mListener;
    private EnterAmountPresenter presenter;
    private NumberFormat numberFormat;
    private PaymentPost payment;
    boolean doubleBackPressedOnce = false;
    private FragmentManager fragmentManager;
    private int currentFragment;

    public EnterAmountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle passedInBundle = getArguments();
        if (passedInBundle != null) {
            payment = passedInBundle.getParcelable("payment");
            currentFragment = getArguments().getInt("current_fragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO: Create a copy from the below xml, rename appropriately
        View rootView = inflater.inflate(R.layout.fragment_enter_amount, container, false);
        ButterKnife.bind(this, rootView);
        presenter = new EnterAmountPresenter(this, new EnterAmountCMSRepository(),new EnterAmountService());
        dueDateEV.setOnFocusChangeListener(this);
        numberFormat = NumberFormat.getCurrencyInstance();
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
        presenter.setTitle();
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
        presenter.getAccountIdLabel();
        presenter.getEnterAmountPlaceHolder();
        presenter.getMaxPaymentLabelText();
        presenter.getPaymentAmountLabelText();
        presenter.getFeeLabelText();
        presenter.getTotalAmountLabelText();
        presenter.getDueDatePlaceholderText();
        presenter.getNotificationLabelText();
        presenter.getCreatePaymentButtonTitle();
        displayFee();
        displayAccountNumber();
        displayBillerName();
        displayMaxAmountLimit();
        displayContextualHelpLabel();
        setLabelColors();
    }

    private void displayMaxAmountLimit() {
        maxPaymentTV.setText(numberFormat.format(Float.parseFloat(payment.getMaxTransactionAmount())));
        maxPaymentTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @OnClick(R.id.create_payment_button)
    public void onCreatePaymentClick(){
        String amount = paymentAmountTV.getText().toString().replace("$","");
        if (paymentAmountTV.getText().toString().equals("$0.00")) {
            showError("20308");
        } else if(Float.valueOf(amount) > Float.valueOf(payment.getMaxTransactionAmount())) {
            Error error = ErrorDisplayManager.getInstance().getErrorMessage("20317");
            String errorMessage = error.getMessage();
            if (errorMessage.contains("$")) {
                errorMessage = errorMessage.replace("$", numberFormat.format(Float.parseFloat(payment.getMaxTransactionAmount())));
            }
            String dismissButtonTitle = error.getDismissButtonTitle();

            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(errorMessage);


            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, dismissButtonTitle,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else if (Float.valueOf(amount) < Float.valueOf(payment.getMinTransactionAmount())) {
            Error error = ErrorDisplayManager.getInstance().getErrorMessage("20316");
            String errorMessage = error.getMessage();
            if (errorMessage.contains("$")) {
                errorMessage = errorMessage.replace("$", numberFormat.format(Float.parseFloat(payment.getMinTransactionAmount())));
            }
            String dismissButtonTitle = error.getDismissButtonTitle();

            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(errorMessage);


            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, dismissButtonTitle,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }else {
            presenter.createPaymentSlip(amount, payment.getBiller().getId(), payment.getToken());
        }
    }

    @Override
    public void displayContextualHelpLabel() {
        contextualHelpLabel.setText(presenter.getContextHelpText().getTitle());
    }

    @OnClick(R.id.ctx_help_container)
    public void ctxBtnClick(){
        mListener.showContextHelper(getActivity(), presenter.getContextHelpText());
    }

    private void displayBillerName() {
        billerNameTV.setText(payment.getBiller().getName());
        billerNameTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    private void displayAccountNumber() {
        accountNumberTV.setText(payment.getUserData().get("account_number"));
        accountNumberTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    private void displayFee() {
        paymentFeeTV.setText(numberFormat.format(Float.parseFloat(payment.getFee())));
        paymentFeeTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void setViewFeatures() {}

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void displayAccountIdLabel(String text) {
        accountNumberLabelTV.setText(text);
        accountNumberLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayMaxPaymentLabel(String label) {
        maxPaymentLabelTV.setText(label);
        maxPaymentLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayPaymentAmountLabel(String label) {
        paymentAmountLabelTV.setText(label);
        paymentAmountLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));

    }

    @Override
    public void displayFeeLabel(String label) {
        paymentFeeLabelTV.setText(label);
        paymentFeeLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayTotalAmountLabel(String label) {
        paymentTotalLabelTV.setText(label);
        paymentTotalLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayDueDatePlaceholder(String placeHolder) {
        dueDateEV.setHint(placeHolder);
        dueDateEV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayNotificationText(String text) {
        notificationTV.setText(text);
        notificationTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayCreatePaymentButton(String title) {
        createPaymentButton.setText(title);
    }

    @Override
    public void displayEnterAmountPlaceHolder(final String text) {
        enterAmountEV.setHint(text);
        enterAmountEV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
        enterAmountEV.setPadding(20,25,0,40);
        enterAmountEV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$")) {
                    String userInput = "" + s.toString().replaceAll("[^\\d]", "");
                    StringBuilder cashAmountBuilder = new StringBuilder(userInput);

                    while (cashAmountBuilder.length() > 3 && cashAmountBuilder.charAt(0) == '0') {
                        cashAmountBuilder.deleteCharAt(0);
                    }
                    while (cashAmountBuilder.length() < 3) {
                        cashAmountBuilder.insert(0, '0');
                    }
                    cashAmountBuilder.insert(cashAmountBuilder.length() - 2, '.');
                    cashAmountBuilder.insert(0, '$');

                    enterAmountEV.setText(cashAmountBuilder.toString());
                    String amt = enterAmountEV.getText().toString().replace("$","");
                    Double amount = Double.parseDouble(amt);
                    Double fee = Double.parseDouble(paymentFeeTV.getText().toString().replace("$",""));
                    paymentAmountTV.setText(cashAmountBuilder.toString());
                    Double total = amount+fee;
                    paymentTotalTV.setText(numberFormat.format(Float.parseFloat(total.toString())));
                    // keeps the cursor always to the right
                    Selection.setSelection(enterAmountEV.getText(), cashAmountBuilder.toString().length());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setLabelColors() {
        paymentTotalTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
        paymentAmountTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayPaymentSlip(PaymentPost payment) {
        BarcodeSlipFragment fragment = new BarcodeSlipFragment();
        Bundle bundle = new Bundle();
        bundle.putString("paymentId",payment.getToken());
        bundle.putInt("current_fragment",currentFragment);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(currentFragment, fragment);
        transaction.addToBackStack("Barcode Slip Fragment");
        transaction.commit();
    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dueDateEV.setText(month + "/" + dayOfMonth + "/" + year);
            }
        };
        if(hasFocus){
            hideSoftKeyboard(getActivity());
            new DatePickerDialog(getContext(),dateListener,calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
}
