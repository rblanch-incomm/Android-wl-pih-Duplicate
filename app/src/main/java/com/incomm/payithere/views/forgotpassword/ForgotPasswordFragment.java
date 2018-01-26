package com.incomm.payithere.views.forgotpassword;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.repositories.ForgotPasswordCMSRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.forgotPasswordSuccess.ForgotPasswordSuccessFragment;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends ViewFragment implements ForgotPasswordMVP.View {

    private ForgotPasswordPresenter presenter;
    private MainTabInterface mListener;

    @BindView(R.id.reset_password_email_et)
    EditText resetEmailAddress;
    @BindView(R.id.global_big_button)
    LinearLayout resetButton;
    @BindView(R.id.button_textview)
    TextView resetTextView;

    private String labelColor, buttonBackground, buttonTextColor;
    private ProgressDialog progressDialog;
    private MarkdownView mMarkdownView;

    public ForgotPasswordFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        ButterKnife.bind(this, view);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#089b77"));
        progressDialog = new ProgressDialog(getActivity());
        mMarkdownView = (MarkdownView) view.findViewById(R.id.change_password_text_notifier);
        presenter = new ForgotPasswordPresenter(this, new ForgotPasswordCMSRepository());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getViewElements();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        }
    }

    @Override
    public void setupUi() {
        presenter.getButtonColor();
        presenter.getButtonTitleColor();
        presenter.getLabelColor();
        presenter.getTitle();
        presenter.getBodyText();
        presenter.getEmailPlaceholderText();
        presenter.getSubmitButtonTitle();
    }

    @OnClick(R.id.global_big_button)
    public void onSubmitClicked(){
        if (isConnected()) {
            showProgressbar(progressDialog);
            presenter.submitClicked();
        }else {
            displayErrorAlert("896");
        }
    }

    @Override
    public void setViewFeatures() {
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
    public void displayEmailPlaceholder(String email) {
        resetEmailAddress.setHint(email);
        resetEmailAddress.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displaySubmitButton(String button) {
        resetTextView.setText(button);
        resetTextView.setTextColor(Color.parseColor(buttonTextColor));
        resetButton.setBackgroundColor(Color.parseColor(buttonBackground));
    }

    @Override
    public void setTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void setGeneralTextColor(String generalTextColor) {
        labelColor = generalTextColor;
    }

    @Override
    public void setButtonTextColor(String buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    @Override
    public void setButtonBackground(String buttonColor) {
        buttonBackground = buttonColor;
    }

    @Override
    public String getEmail() {
        return resetEmailAddress.getText().toString().trim();
    }

    @Override
    public void showError(String error) {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        displayErrorAlert(error);
    }

    @Override
    public void displaySuccessFragment() {
        progressDialog.dismiss();
        ForgotPasswordSuccessFragment fragment = new ForgotPasswordSuccessFragment();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.login_container, fragment);
        transaction.addToBackStack("ForgotPasswordSuccess");
        transaction.commit();
    }
}
