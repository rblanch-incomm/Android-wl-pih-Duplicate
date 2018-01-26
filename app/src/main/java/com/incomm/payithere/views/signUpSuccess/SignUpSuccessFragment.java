package com.incomm.payithere.views.signUpSuccess;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.repositories.SignUpSuccessCMSRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpSuccessFragment extends ViewFragment implements SignUpSucessMVP.View {

    @BindView(R.id.global_big_button)
    LinearLayout continueButton;
    @BindView(R.id.button_textview)
    TextView continueTextView;

    private MarkdownView mMarkdownView;
    private MainTabInterface mListener;
    private FragmentManager fragmentManager;
    private SignUpSuccessPresenter presenter;
    private String labelColor, buttonBackground, buttonTextColor;

    public SignUpSuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignUpSuccessPresenter(this, new SignUpSuccessCMSRepository());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_success, container, false);
        mMarkdownView = (MarkdownView) view.findViewById(R.id.text_message_success);
        ButterKnife.bind(this, view);
        setupUi();
        return view;
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
        disableBack();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.getGeneralTextColor();
        presenter.getButtonTextColor();
        presenter.getButtonColor();
        presenter.getTitle();
        presenter.getBodyText();
        presenter.getContinueButton();
    }

    @Override
    public void setViewFeatures() {
    }

    @OnClick(R.id.global_big_button)
    public void onClick() {
        fragmentManager = getFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }
    @Override
    public void displayBodyText(String bodyText) {
        InternalStyleSheet css = new InternalStyleSheet();
        css.addRule("p", "color:" + labelColor + "");
        css.addRule("p", "size:" + "1");
        css.addRule("font-family: sans-serif-light");
        mMarkdownView.addStyleSheet(css);
        mMarkdownView.loadMarkdown(bodyText);
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
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void displayContinueButton(String button) {
        continueButton.setBackgroundColor(Color.parseColor(buttonBackground));
        continueTextView.setTextColor(Color.parseColor(buttonTextColor));
        continueTextView.setText(button);
    }
}
