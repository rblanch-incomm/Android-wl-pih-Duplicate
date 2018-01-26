package com.incomm.payithere.views.changepasswordsuccess;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;

import br.tiagohm.markdownview.MarkdownView;

import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.styles.Github;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChangePasswordSuccessFragment extends Fragment implements ChangePasswordSuccessMVP.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /* @BindView(R.id.text_message_success)
     TextView changePasswordSuccessBody;*/
    @BindView(R.id.global_big_button)
    LinearLayout continueButton;
    @BindView(R.id.button_textview)
            TextView successTextView;
    FragmentManager fragmentManager;
    private MarkdownView mMarkdownView;
    private ChangePasswordSuccessPresenter presenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainTabInterface mListener;

    public ChangePasswordSuccessFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChangePasswordSuccessFragment newInstance(String param1, String param2) {
        ChangePasswordSuccessFragment fragment = new ChangePasswordSuccessFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        presenter = new ChangePasswordSuccessPresenter(this);
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
        ((ViewActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password_success, container, false);
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
