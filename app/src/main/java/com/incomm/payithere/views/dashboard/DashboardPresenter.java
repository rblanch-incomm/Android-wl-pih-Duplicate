package com.incomm.payithere.views.dashboard;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.cms.TabNavigation;
import com.incomm.payithere.models.tabs.NavigationTab;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.views.ViewPresenter;

import java.io.IOException;

import static com.incomm.payithere.utils.Constants.IS_FROM_TAB;
import static com.incomm.payithere.utils.Constants.REFRESH_LOCATIONS_VIEW;


/**
 * Created by Jerome Davis on 4/3/17.
 */

public class DashboardPresenter extends ViewPresenter implements DashboardMVP.Presenter {

    private DashboardMVP.View view;
    private TabNavigation tabNavigationView;
    private NavigationTab[] allTabs;
    private PreferencesManager preferencesManager;

    public DashboardPresenter(DashboardMVP.View view){
        this.view = view;
        preferencesManager = PreferencesManager.getInstance();
    }

    public void createTabs(TabNavigation tabs) throws IOException {

        for (int i = 0; i < tabs.getTabs().size(); i++){
            allTabs = new NavigationTab[tabs.getTabs().size()];
            allTabs[i].setName(tabs.getTabs().get(i).getName());
            allTabs[i].setImagePrimary(ActivityUtils.checkNull(tabs.getTabs().get(i).getIconPrimary()));
            allTabs[i].setImageSec(ActivityUtils.checkNull(tabs.getTabs().get(i).getIconSecondary()));
            allTabs[i].setIdentifier(tabs.getTabs().get(i).getKey());
            allTabs[i].setPosition(i);
        }

    }

    @Override
    public NavigationTab[] getTabs() {
        return new NavigationTab[0];
    }

    @Override
    public void getViewElements() {
        tabNavigationView = (TabNavigation) DocumentsManager.getInstance().getCmsResource(TabNavigation.class);

        allTabs = new NavigationTab[tabNavigationView.getTabs().size()];

        for (int i = 0; i < tabNavigationView.getTabs().size(); i++){
            String title = tabNavigationView.getTabs().get(i).getTitle();
            String imagePrimary = ActivityUtils.checkNull(tabNavigationView.getTabs().get(i).getIconPrimary());
            String imageSec = "";
            if(null == tabNavigationView.getTabs().get(i).getIconSecondary()) {
                imageSec = ActivityUtils.checkNull(tabNavigationView.getTabs().get(i).getIconPrimary());
            }else {
                imageSec = ActivityUtils.checkNull(tabNavigationView.getTabs().get(i).getIconSecondary());
            }
            String identifier = tabNavigationView.getTabs().get(i).getKey();
            allTabs[i] = new NavigationTab(title,imagePrimary,imageSec,identifier,i);
        }
        view.setupUi();
    }


    public NavigationTab[] getAllTabs(){
        return allTabs;
    }

    public String getGlobalLabelColor(){
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    public String getBackground(){
        return GlobalImagesManager.getInstance().getBackground().url();
    }

    public String getNavigationBar(){
        return GlobalImagesManager.getInstance().getNavigationBar().url();
    }

    public void setRefreshLocations(boolean isClicked) {
        preferencesManager.setSharedPreferences(REFRESH_LOCATIONS_VIEW, isClicked);
    }

    public boolean refreshLocations(){
        return preferencesManager.refreshLocationsView();
    }

    public String getTitleLogo(){
        return GlobalImagesManager.getInstance().getTitleLogo().url();
    }

    public void setIsFromTab(boolean isFromLogin) {
        PreferencesManager.getInstance().setSharedPreferences(IS_FROM_TAB, isFromLogin);
    }

}

