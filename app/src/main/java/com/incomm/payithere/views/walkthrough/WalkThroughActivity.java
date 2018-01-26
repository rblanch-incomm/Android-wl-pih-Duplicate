package com.incomm.payithere.views.walkthrough;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalkThroughActivity extends ViewActivity implements Constants, MainTabInterface {

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
        window.setStatusBarColor(Color.parseColor("#288cb9"));

        setContentView(R.layout.activity_walk_through);

        ButterKnife.bind(this);

        WalkThroughFragment walkThroughFragment =  WalkThroughFragment.newInstance(true);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                walkThroughFragment, R.id.walk_through_container);
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
}
