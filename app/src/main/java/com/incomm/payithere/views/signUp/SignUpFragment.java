package com.incomm.payithere.views.signUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.repositories.SignUpCMSRepository;
import com.incomm.payithere.services.SignUpService;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.signUpSuccess.SignUpSuccessFragment;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends ViewFragment implements SignUpMVP.View {

    @BindView(R.id.first_name_et)
    EditText firstNameET;
    @BindView(R.id.last_name_et)
    EditText lastNameET;
    @BindView(R.id.email_et)
    EditText emailET;
    @BindView(R.id.confirm_email_et)
    EditText confirmEmailET;
    @BindView(R.id.phone_et)
    EditText phoneET;
    //    @BindView(R.id.zipcode_et)
//    EditText zipcodeET;
    @BindView(R.id.password_et)
    EditText passwordET;
    @BindView(R.id.confirm_password_et)
    EditText confirmPasswordET;
    @BindView(R.id.button_textview)
    TextView signUpTV;
    @BindView(R.id.global_big_button)
    LinearLayout signUpButton;

    private MainTabInterface mListener;
    private SignUpPresenter presenter;
    private String labelColor, buttonBackground, buttonTextColor;
    private MarkdownView mMarkdownView;
    private ProgressDialog progressDialog;

    public SignUpFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#089b77"));
        presenter = new SignUpPresenter(this, new SignUpCMSRepository(),new SignUpService());
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        mMarkdownView = (MarkdownView) view.findViewById(R.id.change_password_text_notifier);
        presenter.getViewElements();
        return view;
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
    public void setupUi() {
        presenter.getButtonColor();
        presenter.getButtonTextColor();
        presenter.getGeneralTextColor();
        presenter.getTitle();
        presenter.getFirstNameHint();
        presenter.getLastNameHint();
        presenter.getEmailHint();
        presenter.getConfirmEmailHint();
        presenter.getPhoneHint();
        //presenter.getZipcodeHint();
        presenter.getPasswordHint();
        presenter.getConfirmPasswordHint();
        presenter.getBodyText();
        presenter.getSignUpButton();
    }

    @Override
    public void setViewFeatures() {
    }

    @OnClick(R.id.global_big_button)
    public void signUpClicked() {
        if (isConnected()) {
            showProgressbar(progressDialog);
            presenter.signUpClicked();
        }else {
            displayErrorAlert("896");
        }
    }


    @Override
    public void displayFirstNameHint(String firstName) {
        firstNameET.setHint(firstName);
        firstNameET.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displayLastNameHint(String lastName) {
        lastNameET.setHint(lastName);
        lastNameET.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displayEmailHint(String email) {
        emailET.setHint(email);
        emailET.setTextColor(Color.parseColor(labelColor));
    }
    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

//    @Override
//    public void onSetToolbarTitle(String title) {
//
//    }

    @Override
    public void displayConfirmEmail(String email) {
        confirmEmailET.setHint(email);
        confirmEmailET.setTextColor(Color.parseColor(labelColor));

    }

    @Override
    public void displayZipcode(String zipcode) {
        // zipcodeET.setHint(zipcode);
        // zipcodeET.setTextColor(Color.parseColor(labelColor));

    }

    @Override
    public void displayBodyText(String bodyText) {
        InternalStyleSheet css = new InternalStyleSheet();
        css.addRule("p","color:"+labelColor+"");
        css.addRule("p","size:"+"1");
        css.addRule("font-family: sans-serif-light");
        mMarkdownView.addStyleSheet(css);
        mMarkdownView.loadMarkdown(bodyText);
    }

    @Override
    public String getConfirmEmail() {
        return confirmEmailET.getText().toString();
    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void displayPhoneHint(String phone) {
        phoneET.setHint(phone);
        phoneET.setTextColor(Color.parseColor(labelColor));
        phoneET.addTextChangedListener(new PhoneNumberFormattingTextWatcher("US"));
    }

    @Override
    public void displayPasswordHint(String password) {
        passwordET.setHint(password);
        passwordET.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displayConfirmPasswordHint(String confirmPass) {
        confirmPasswordET.setHint(confirmPass);
        confirmPasswordET.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displaySignUpButton(String signUp) {
        signUpButton.setBackgroundColor(Color.parseColor(buttonBackground));
        signUpTV.setText(signUp);
        signUpTV.setTextColor(Color.parseColor(buttonTextColor));
    }

    @Override
    public void setButtonColor(String color) {
        buttonBackground = color;
    }

    @Override
    public void setButtonTextColor(String color) {
        buttonTextColor = color;
    }

    @Override
    public void setGeneralTextColor(String color) {
        labelColor = color;
    }

    @Override
    public String getFirstName() {
        return firstNameET.getText().toString();
    }

    @Override
    public void showError(String error) {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        displayErrorAlert(error);
    }

    @Override
    public String getLastName() {
        return lastNameET.getText().toString();
    }

    @Override
    public String getEmail() {
        return emailET.getText().toString();
    }

    @Override
    public String getPhone() {
        return phoneET.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordET.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPasswordET.getText().toString();
    }

    @Override
    public void displaySuccessFragment() {
        progressDialog.dismiss();
        SignUpSuccessFragment fragment = new SignUpSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("current_fragment", R.id.login_container);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.login_container, fragment);
        transaction.addToBackStack("SignUpSuccess");
        transaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}