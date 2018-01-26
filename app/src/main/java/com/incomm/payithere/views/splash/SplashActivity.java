package com.incomm.payithere.views.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewActivity;
import com.incomm.payithere.views.educational.EducationalActivity;
import com.incomm.payithere.views.login.LoginActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends ViewActivity implements SplashMVP.View {
    private FirebaseAnalytics mFirebaseAnalytics;
    private SplashPresenter presenter;
    private ImageView splashImageView, logoImageView;

    @BindView(R.id.splash_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Picasso.with(PayItHereApplication.getContext()).setIndicatorsEnabled(true);
        Picasso.with(PayItHereApplication.getContext()).setLoggingEnabled(true);

        presenter = new SplashPresenter(this);

        ButterKnife.bind(this);

        if (isConnected()) {
            setupUi();
        } else {
            displayErrorAlert("896");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(this, "Splash View");
    }


    @Override
    public void setupUi() {
        presenter.getViewElements();
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void launchLoginVIew() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void launchWalkthroughs() {
        Intent intent = new Intent(this, EducationalActivity.class);
        startActivity(intent);
        finish();
    }

}
