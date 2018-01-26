package com.incomm.payithere.views.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.incomm.payithere.views.locations.LocationsFragment.REQUEST_LOCATION;

public class LoginActivity extends ViewActivity implements Constants, MainTabInterface {

    @BindView(R.id.appbar)
    AppBarLayout toolBarConatainer;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_image)
    ImageView toolbarImage;
    @BindView(R.id.apptoolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    RelativeLayout toolbarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffffff"));
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        window.setBackgroundDrawableResource(R.drawable.splash_background);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        LoginFragment loginFragment = LoginFragment.newInstance(this);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                loginFragment, R.id.login_container);

        PreferencesManager.getInstance().setSharedPreferences(Constants.IS_FIRST_LAUNCH, false);
    }

    @Override
    public void onSetToolbarTitle(String title) {
        if (title == null || title.equals("")) {
            toolbarTitle.setText("");
            toolbarImage.setVisibility(View.VISIBLE);
            toolbarContainer.setBackgroundResource(0);
        } else {
            toolbarImage.setVisibility(View.GONE);
            //toolbarImage.setVisibility(View.INVISIBLE);
            toolbarTitle.setText(title);
            toolbarTitle.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getThemeSecondary()));
            setActionbarBackground(toolbarContainer);

        }
    }

    @Override
    public void onSetToolbarTitle(String title, String subtitle) {

    }

    @Override
    public void showContextHelper(Context context, FeatureContextualHelp contextualHelpView) {
        showContextualHelp(this, contextualHelpView);
    }

    @Override
    public void setTab(int tabNumber) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // The handles the enable GPS dialog click from location presenter and passes it on the result to location fragment.
        if (requestCode == REQUEST_LOCATION) {
            Fragment locationsFragment = getSupportFragmentManager().findFragmentByTag("locations");
            locationsFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
