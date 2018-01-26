package com.incomm.payithere.views.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.SignInRequest;
import com.incomm.payithere.models.services.request.User;
import com.incomm.payithere.models.services.response.SignInResponse;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.TemporaryDataManager;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.dashboard.DashboardActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends ViewFragment implements LoginMVP.View, Constants {

    private LoginPresenter presenter;
    private MainTabInterface mListener;
    private ProgressDialog progressDialog;

    @BindView(R.id.login_username_et)
    EditText emailEditText;
    @BindView(R.id.login_password_et)
    EditText passwordEditText;

    @BindView(R.id.login_remember_me_label)
    TextView rememberMeLabel;
    @BindView(R.id.login_forgot_password_tv)
    TextView forgotLoginLabel;
    @BindView(R.id.login_sign_up_tv)
    TextView signUpLabel;

    @BindView(R.id.login_sign_in_bt)
    Button signInButton;

    @BindView(R.id.btn_footer_1)
    LinearLayout footerButton1;
    @BindView(R.id.feature_button_1_label)
    TextView footerButtonLabel1;

    @BindView(R.id.btn_footer_2)
    LinearLayout footerButton2;
    @BindView(R.id.feature_button_2_label)
    TextView footerButtonLabel2;

    @BindView(R.id.btn_footer_3)
    LinearLayout footerButton3;
    @BindView(R.id.feature_button_3_label)
    TextView footerButtonLabel3;


    @BindView(R.id.mini_logo_imageView)
    ImageView miniLogoImageview;
    @BindView(R.id.footer_copyright_tv)
    TextView copyRightTextview;

    @BindView(R.id.remember_me_switch)
    SwitchCompat rememberMeSwitch;

    private String textColor, buttonTextColor, secondaryColor;

    public static LoginFragment newInstance(MainTabInterface mListener) {
        LoginFragment fragment = new LoginFragment();
        fragment.setLoginInterface(mListener);

        return fragment;
    }

    private void setLoginInterface(MainTabInterface mListener) {
        this.mListener = mListener;
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#e6e6e6"));

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });
        forgotLoginLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotCredentialsClicked();
            }
        });
        signUpLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });
        presenter = new LoginPresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getViewElements();
        mListener.onSetToolbarTitle(presenter.getTitle());
        presenter.setIsFromTab(false);
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());

        setupUi();
    }

    private void signUpClicked(){
        presenter.signUpClicked();

        Fragment fragment = FragmentFactory.getFragment(Constants.SIGN_UP_IDENTIFIER);
        Bundle bundle = new Bundle();
        //bundle.putParcelable("key", presenter.getShortcutButtons().get(position));
        bundle.putInt("current_fragment", R.id.login_container);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit);
        transaction.replace(R.id.login_container, fragment);
        transaction.addToBackStack("signUpFeature");
        transaction.commit();
    }

    private void requestLogin() {
        String userName = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (isConnected()) {
           showProgressbar(progressDialog);

            passwordEditText.setText("");

            if (userName.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
                hideProgessbar(progressDialog);
                displayErrorAlert("20308");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
                hideProgessbar(progressDialog);
                displayErrorAlert("20304");
            } else {
                authenticate(userName, password);
            }
        } else {
            // Network offline
            displayErrorAlert("896");
        }
    }


    private void authenticate(String userName, String password) {

        final SignInRequest request = new SignInRequest();

        final User userRequest = new User();
        userRequest.setEmail(userName);
        userRequest.setPassword(password);
        request.setUser(userRequest);

        RetrofitManager retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
        retrofitManager.getPayItHereInstance().postLogin(request).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                Log.i("Sign In", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                if (response.isSuccessful() && response.code() == 200) {
                    hideProgessbar(progressDialog);
                    PayItHereApplication.setToken(response.body().getUser().getToken());
                    if (presenter.isRememberLogin()) {
                        presenter.setRememberMe(true);
                        presenter.setRememberUserName(userRequest.getEmail());
                    } else {
                        presenter.setRememberMe(false);
                        presenter.setRememberUserName("");
                    }
                    TemporaryDataManager.getInstance().setEmail(userRequest.getEmail());
                    TemporaryDataManager.getInstance().setFirstName(response.body().getUser().getFirst_name());
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else if (response.code() == 401) {
                    hideProgessbar(progressDialog);
                    displayErrorAlert("40002");
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                progressDialog.dismiss();
                displayErrorAlert("100");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        textColor = presenter.getColorSchemeManager().getGeneralText();
        buttonTextColor = presenter.getColorSchemeManager().getPositiveButtonTitle();
        secondaryColor = presenter.getColorSchemeManager().getThemeSecondary();

        emailEditText.setHint(presenter.getUsernamePlaceholderText());
        emailEditText.setTextColor(Color.parseColor(textColor));
        emailEditText.setText(presenter.getRememberedUserName());
        emailEditText.setText("public-test-user@eteam.io");

        passwordEditText.setHint(presenter.getPasswordPlaceholderText());
        passwordEditText.setTextColor(Color.parseColor(textColor));
        passwordEditText.setText("12345678");

        setRememberMeButton();
        rememberMeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    presenter.setRememberMe(true);
                } else {
                    presenter.setRememberMe(false);
                }
            }
        });

        rememberMeLabel.setText(presenter.getRememberMeLabelText());
        rememberMeLabel.setTextColor(Color.parseColor(textColor));

        signInButton.setText(presenter.getLoginButtonTitle());
        signInButton.setTextColor(Color.parseColor(buttonTextColor));

        signUpLabel.setText(presenter.getRegisterButtonTitle());
        signUpLabel.setTextColor(Color.parseColor(secondaryColor));

        forgotLoginLabel.setText(presenter.getForgotCredentialsButtonTitle());
        forgotLoginLabel.setTextColor(Color.parseColor(secondaryColor));

        copyRightTextview.setText(presenter.getFooterText());
        copyRightTextview.setTextColor(Color.parseColor(secondaryColor));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        
        setViewFeatures();

        Picasso.with(PayItHereApplication.getContext()).load(presenter.getFooterLogoImage().url()).into(miniLogoImageview);
    }

    private void setRememberMeButton() {
        if (presenter.isRememberLogin()) {
            rememberMeSwitch.toggle();
        }
    }

    @Override
    public void setViewFeatures() {
        // Must be 3 and only 3 features - no more no less
        footerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeature(v);
            }
        });

        footerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeature(v);
            }
        });

        footerButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeature(v);
            }
        });

        footerButton1.setTag("0");
        footerButtonLabel1.setText(presenter.getShortcutButtons().get(0).getTitle());
        footerButtonLabel1.setTextColor(Color.parseColor(secondaryColor));

        footerButton2.setTag("1");
        footerButtonLabel2.setText(presenter.getShortcutButtons().get(1).getTitle());
        footerButtonLabel2.setTextColor(Color.parseColor(secondaryColor));

        footerButton3.setTag("2");
        footerButtonLabel3.setText(presenter.getShortcutButtons().get(2).getTitle());
        footerButtonLabel3.setTextColor(Color.parseColor(secondaryColor));

    }

    private void openFeature(View v) {

        int position = Integer.parseInt(v.getTag().toString());

        Fragment fragment = FragmentFactory.getFragment(presenter.getShortcutButtons().get(position).getKey());

        Bundle bundle = new Bundle();
        bundle.putParcelable("key", presenter.getShortcutButtons().get(position));
        bundle.putInt("current_fragment", R.id.login_container);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit);
        transaction.replace(R.id.login_container, fragment);
        transaction.addToBackStack("loginfeatures");
        transaction.commit();
    }

    private void forgotCredentialsClicked() {
        // TODO: Redo this once the view is created in CMS

        Fragment fragment = FragmentFactory.getFragment("Forgot Password");
        Bundle bundle = new Bundle();
        //bundle.putParcelable("key", presenter.getShortcutButtons().get(position));
        bundle.putInt("current_fragment", R.id.login_container);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit);
        transaction.replace(R.id.login_container, fragment);
        transaction.addToBackStack("loginfeatures");
        transaction.commit();
    }
}
