package com.incomm.payithere.views.dashboard;



import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.TabNavigation;
import com.incomm.payithere.models.tabs.NavigationTab;

import java.io.IOException;

/**
 * Created by Jerome Davis on 4/3/17.
 */

public interface DashboardMVP {
    interface View extends BaseView<Presenter> {
        void setViewConfig(com.incomm.payithere.models.cms.TabNavigation tabs);
        void completeLogout(boolean displayInactivityWarning, boolean displaySessionTimeoutWarning);
    }

    interface Presenter extends BasePresenter {
        void createTabs(TabNavigation tabs) throws IOException;
        NavigationTab[] getTabs();
    }
}
