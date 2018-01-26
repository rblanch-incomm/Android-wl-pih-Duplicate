package com.incomm.payithere.views.forgotPasswordSuccess;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;
import com.incomm.payithere.views.changepasswordsuccess.ChangePasswordSuccessMVP;
import com.incomm.payithere.views.changepasswordsuccess.ChangePasswordSuccessPresenter;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.styles.Github;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgotPasswordSuccessFragment extends Fragment implements ForgotPasswordSuccessMVP.View {

    @BindView(R.id.global_big_button)
    LinearLayout continueButton;
    @BindView(R.id.button_textview)
            TextView successTextView;
    FragmentManager fragmentManager;
    private MarkdownView mMarkdownView;
    private ForgotPasswordSuccessPresenter presenter;

    private MainTabInterface mListener;

    public ForgotPasswordSuccessFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ForgotPasswordSuccessFragment newInstance(String param1, String param2) {
        ForgotPasswordSuccessFragment fragment = new ForgotPasswordSuccessFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#089b77"));

        presenter = new ForgotPasswordSuccessPresenter(this);
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
                    // Do nothing on back press
                    return true;
                }
                return false;
            }
        });

        mListener.onSetToolbarTitle(presenter.getTitle());

        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password_success, container, false);
        ButterKnife.bind(this, view);
        mMarkdownView = (MarkdownView) view.findViewById(R.id.text_message_success);
        presenter.getViewElements();

        return view;
    }

    @Override
    public void setupUi() {

    }

    @Override
    public void setViewFeatures() {
        InternalStyleSheet css = new Github();
        css.addRule("p","color:"+presenter.getLabelColor()+"");
        css.addFontFace("MyFont", "condensed", "italic", "bold", "url('sans-serif-light')");
        css.addRule("font-family: sans-serif-light");
        mMarkdownView.addStyleSheet(css);
        mMarkdownView.loadMarkdown(presenter.getBodyText());
        //changePasswordSuccessBody.setText(presenter.getBodyText());
        successTextView.setText(presenter.getContinueButtontitle());
        continueButton.setBackgroundColor(Color.parseColor(presenter.getButtonColor()));
        successTextView.setTextColor(Color.parseColor(presenter.getButtonTitleColor()));


    }

    @OnClick(R.id.global_big_button)
    public void onClick() {
        fragmentManager = getFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
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

}
