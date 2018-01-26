package com.incomm.payithere.views.changepassword;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.repositories.ChangePasswordCMSRepository;
import com.incomm.payithere.services.ChangePasswordService;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.changepasswordsuccess.ChangePasswordSuccessFragment;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordFragment extends ViewFragment implements ChangePasswordMVP.View {

    @BindView(R.id.change_password_current_et)
    EditText currentPasswordET;
    @BindView(R.id.new_password_et)
    EditText newPasswordET;
    @BindView(R.id.confirm_password_et)
    EditText confirmPasswordET;
    @BindView(R.id.global_big_button)
    LinearLayout submitButton;
    @BindView(R.id.button_textview)
    TextView submitTextView;

    private MainTabInterface mListener;
    private ChangePasswordPresenter presenter;
    private ProgressDialog progressDialog;
    private MarkdownView mMarkdownView;
    private String labelColor, buttonBackground, buttonTextColor;

    public ChangePasswordFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChangePasswordPresenter(this,new ChangePasswordCMSRepository(),new ChangePasswordService());
        progressDialog =new ProgressDialog(getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this,view);
        mMarkdownView = (MarkdownView) view.findViewById(R.id.change_password_text_notifier);
        presenter.getViewElements();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void setupUi() {
        presenter.getButtonColor();
        presenter.getLabelColor();
        presenter.getButtonTitleColor();
        presenter.getTitle();
        presenter.getCurrentPasswordTextFieldPlaceholder();
        presenter.getNewPasswordTextFieldPlaceholder();
        presenter.getConfirmPasswordTextFieldPlaceholder();
        presenter.getBodyText();
        presenter.getSubmitButtonTitle();
    }

    @OnClick(R.id.global_big_button)
    public void OnSubmit(){
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
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void displayCurrentPasswordHint(String currentHint) {
        currentPasswordET.setHint(currentHint);
        currentPasswordET.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displayNewPasswordHint(String newHint) {
        newPasswordET.setHint(newHint);
        newPasswordET.setTextColor(Color.parseColor(labelColor));
    }

    @Override
    public void displayConfirmPasswordHint(String confirm) {
        confirmPasswordET.setHint(confirm);
        confirmPasswordET.setTextColor(Color.parseColor(labelColor));
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
    public void displaySubmitButton(String submit) {
        submitTextView.setTextColor(Color.parseColor(buttonTextColor));
        submitButton.setBackgroundColor(Color.parseColor(buttonBackground));
        submitTextView.setText(submit);
    }

    @Override
    public String getCurrentPassword() {
        return currentPasswordET.getText().toString().trim();
    }

    @Override
    public void showError(String error) {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        displayErrorAlert(error);
    }

    @Override
    public String getNewPassword() {
        return newPasswordET.getText().toString().trim();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPasswordET.getText().toString().trim();
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
    public void setGeneralTextColor(String generalTextColor) {
        labelColor = generalTextColor;
    }

    @Override
    public void displaySuccessFragment() {
        progressDialog.dismiss();
        ChangePasswordSuccessFragment fragment = new ChangePasswordSuccessFragment();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.change_password_root, fragment);
        transaction.addToBackStack("ChangePasswordSuccess");
        transaction.commit();
    }

}