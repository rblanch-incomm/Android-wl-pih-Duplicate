package com.incomm.payithere.views.educational;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;

import butterknife.ButterKnife;

public class EducationalActivity extends ViewActivity implements Constants, MainTabInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#e6e6e6"));

        setContentView(R.layout.activity_educational);
        ButterKnife.bind(this);

        EducationalFragment educationalFragment = new EducationalFragment();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                educationalFragment, R.id.educational_walk_through_container);


    }

    @Override
    public void onSetToolbarTitle(String title) {
//        if (title == null || title.equals("")) {
//            toolbarTitle.setText("");
//            toolbarImage.setVisibility(View.VISIBLE);
//            toolbarContainer.setBackgroundResource(0);
//        } else {
//            toolbarImage.setVisibility(View.GONE);
//            //toolbarImage.setVisibility(View.INVISIBLE);
//            toolbarTitle.setText(title);
//            toolbarTitle.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getThemeSecondary()));
//            setActionbarBackground(toolbarContainer);
//
//        }
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
}
