package com.incomm.payithere.views;

import android.content.Context;

import com.incomm.payithere.models.cms.FeatureContextualHelp;

/**
 * Created by jayma on 4/18/2017.
 */

public interface MainTabInterface {
    void onSetToolbarTitle(String title);
    void onSetToolbarTitle(String title, String subtitle);
    void showContextHelper(Context context, FeatureContextualHelp contextualHelpView);
    void setTab(int tabNumber);
}
