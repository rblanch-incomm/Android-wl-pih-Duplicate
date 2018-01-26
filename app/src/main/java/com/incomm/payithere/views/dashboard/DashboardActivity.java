package com.incomm.payithere.views.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.SectionsPagerAdapter;
import com.incomm.payithere.factories.TitleFactory;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.cms.TabNavigation;
import com.incomm.payithere.models.tabs.NavigationTab;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.utils.TemporaryDataManager;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewActivity;
import com.incomm.payithere.views.billsHistory.BillerHistoryFragment;
import com.incomm.payithere.views.billsHistory.BillerHistoryMVP;
import com.incomm.payithere.views.locations.LocationsFragment;
import com.incomm.payithere.views.locations.LocationsMVP;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.incomm.payithere.R.id.tabs;
import static com.incomm.payithere.views.locations.LocationsFragment.REQUEST_LOCATION;

public class DashboardActivity extends ViewActivity implements DashboardMVP.View, Constants, MainTabInterface {

    @BindView(tabs)
    TabLayout tabLayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_image)
    ImageView toolbarImage;
    @BindView(R.id.apptoolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainLayout;
    @BindView(R.id.toolbar_title_container)
    RelativeLayout toolbarTitleContainer;
    @BindView(R.id.toolbar_back_button)
    ImageView backButton;

    private DashboardPresenter presenter;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#089b77"));
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        window.setBackgroundDrawableResource(R.drawable.splash_background);

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                //.showImageForEmptyUri(R.drawable.global_chevron_right)
                .imageScaleType(ImageScaleType.NONE)
                .build();

        presenter = new DashboardPresenter(this);
        presenter.getViewElements();
        presenter.setIsFromTab(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    private void createTabs(NavigationTab[] tabs) {
        for (int i = 0; i < tabs.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
    }


    public void enableBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // show back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment instanceof BillerHistoryFragment) {
                BillerHistoryMVP.View view = (BillerHistoryMVP.View) fragment;
                if (view != null) {
                    view.onFragmentUnselected();
                }
            }
        }
        hideSoftKeyboard(this);
        super.onBackPressed();
    }

    public void disableBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void completeTabSetup() {
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    String tag = null;
                    tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
                    switch (tag) {
                        case "ChangePasswordSuccess":
                        case "Confirm Account":
                        case "Enter Payment Amount":
                        case "Barcode Slip Fragment":
                            disableBack();
                            break;
                        default:
                            enableBack();
                            break;
                    }
                } else {
                    disableBack();
                }
            }
        });


        // Uncomment
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this, presenter.getAllTabs());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(presenter.getAllTabs().length - 1);
        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor(presenter.getGlobalLabelColor()));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        setTabIcons(tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {

                while (fragmentManager.getBackStackEntryCount() != 0) {
                    fragmentManager.popBackStackImmediate();
                }

                String identifier = presenter.getAllTabs()[tab.getPosition()].getIdentifier();
                String title = TitleFactory.getInstance().getTitle(identifier);
                if(!title.equals(TitleFactory.getInstance().getTitle(Constants.BILLER_HISTORY_IDENTIFIER))){
                    invalidateOptionsMenu();
                }
                if ((presenter.getAllTabs()[tab.getPosition()].getIdentifier().equals(Constants.RELOAD_LOCATIONS_IDENTIFIER)) && presenter.refreshLocations()
                        ) {
                    Fragment locationsFragment = getSupportFragmentManager().findFragmentByTag("locations");
                    if (locationsFragment instanceof LocationsFragment) {
                        presenter.setIsFromTab(true);
                        LocationsMVP.View fragment = (LocationsMVP.View) locationsFragment;
                        if (fragment != null) {
                            fragment.fragmentBecameVisible();
                            presenter.setRefreshLocations(false);
                        }
                    }
                }
                onSetToolbarTitle(title);
                //menuItem.setVisible(false);
                Bitmap bitmap = imageManager.loadImageSync(presenter.getAllTabs()[tab.getPosition()].getImageSec(), imageOptions);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                d.setColorFilter(Color.parseColor(presenter.getGlobalLabelColor()), PorterDuff.Mode.MULTIPLY);
                tabLayout.setSelectedTabIndicatorColor(Color.parseColor(presenter.getGlobalLabelColor()));
                tab.getCustomView().findViewById(R.id.icon).setBackground(d);

                if((presenter.getAllTabs()[tab.getPosition()].getIdentifier().equals(Constants.BILLER_HISTORY_IDENTIFIER))){
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.BILLER_HISTORY_IDENTIFIER);
                    if (fragment instanceof BillerHistoryFragment) {
                        BillerHistoryMVP.View view = (BillerHistoryMVP.View) fragment;
                        if (view != null) {
                            view.onFragmentSelected();
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Bitmap bitmap = imageManager.loadImageSync(presenter.getAllTabs()[tab.getPosition()].getImagePrimary(), imageOptions);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                tab.getCustomView().findViewById(R.id.icon).setBackground(d);
                if((presenter.getAllTabs()[tab.getPosition()].getIdentifier().equals(Constants.BILLER_HISTORY_IDENTIFIER))){
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.BILLER_HISTORY_IDENTIFIER);
                    if (fragment instanceof BillerHistoryFragment) {
                        BillerHistoryMVP.View view = (BillerHistoryMVP.View) fragment;
                        if (view != null) {
                            view.onFragmentUnselected();
                        }
                    }
                }




            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                while (fragmentManager.getBackStackEntryCount() != 0) {
                    fragmentManager.popBackStackImmediate();
                }
            }
        });
    }

    private void setTabIcons(final TabLayout tabLayout) {

        for (int i = 0; i < presenter.getAllTabs().length; i++) {
            Bitmap bitmap;
            Drawable d;
            NavigationTab navigationTab = presenter.getAllTabs()[i];

            if (i == 0) {
                bitmap = imageManager.loadImageSync(navigationTab.getImageSec(), imageOptions);
                d = new BitmapDrawable(getResources(), bitmap);
                d.setColorFilter(Color.parseColor(presenter.getGlobalLabelColor()), PorterDuff.Mode.MULTIPLY);
            } else {
                bitmap = imageManager.loadImageSync(navigationTab.getImagePrimary(), imageOptions);
                d = new BitmapDrawable(getResources(), bitmap);
            }
            View view = getLayoutInflater().inflate(R.layout.incl_custom_tab, null);
            view.findViewById(R.id.icon).setBackground(d);
            tabLayout.getTabAt(i).setCustomView(view);
        }
    }

    private int getInDP(int pixels) {
        float density = getResources().getDisplayMetrics().density;
        int paddingDp = (int) (pixels * density);
        return paddingDp;
    }

    @Override
    public void setupUi() {
        createTabs(presenter.getAllTabs());
        completeTabSetup();
        Bitmap bitmap = imageManager.loadImageSync(ActivityUtils.checkNull(presenter.getNavigationBar()), imageOptions);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        mainLayout.setBackground(d);
        imageManager.displayImage(ActivityUtils.checkNull(presenter.getTitleLogo()), toolbarImage, imageOptions);
    }

    @Override
    public void setViewFeatures() {
    }

    @Override
    public void setViewConfig(TabNavigation tabs) {
    }

    @Override
    public void completeLogout(boolean displayInactivityWarning, boolean displaySessionTimeoutWarning) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        PayItHereApplication.setTitle(toolbar.getTitle().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!PayItHereApplication.getTitle().equals("")) {
            toolbar.setTitle(PayItHereApplication.getTitle());
        }
    }

    @Override
    public void onSetToolbarTitle(String title) {
        int tabPosition = tabLayout.getSelectedTabPosition();
        if (null == title || title.equals("")) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbarImage.setVisibility(View.VISIBLE);
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbarImage.setVisibility(View.GONE);
            toolbar.setTitle(title);
            if(!title.equals(TitleFactory.getInstance().getTitle(Constants.BILLER_HISTORY_IDENTIFIER))){
                invalidateOptionsMenu();
            }
        }

        toolbar.setSubtitle("");

        // This is a super hack. The viewpager loads all the fragments at once and hence you will see the last tab tile in first if this is deleted.
        String lastTabName = presenter.getAllTabs()[tabLayout.getTabCount() - 1].getName();

            if (title.equals(lastTabName) && tabPosition == 0 && fragmentManager.getBackStackEntryCount() == 0) {
                String name = TitleFactory.getInstance().getTitle(presenter.getAllTabs()[0].getIdentifier());
                if (null == name || name.equals("")) {
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    toolbarImage.setVisibility(View.VISIBLE);
                } else {
                    toolbar.setTitle(name);
                }
            }

    }

    @Override
    public void onSetToolbarTitle(String title, String subtitle) {
        int tabPosition = tabLayout.getSelectedTabPosition();
        if (null == title || title.equals("")) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbarImage.setVisibility(View.VISIBLE);
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbarImage.setVisibility(View.GONE);
            toolbar.setTitle(title);
            if(!title.equals(TitleFactory.getInstance().getTitle(Constants.BILLER_HISTORY_IDENTIFIER))){
                invalidateOptionsMenu();
            }
        }

        if (null != subtitle || !subtitle.equals("")) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbarImage.setVisibility(View.GONE);
            toolbar.setSubtitle(subtitle);
            toolbar.setTitleMarginTop(5);
            toolbar.setTitleMarginBottom(5);
        }



        // This is a super hack. The viewpager loads all the fragments at once and hence you will see the last tab tile in first if this is deleted.
        String lastTabName = presenter.getAllTabs()[tabLayout.getTabCount() - 1].getName();

        if (title.equals(lastTabName) && tabPosition == 0 && fragmentManager.getBackStackEntryCount() == 0) {
            String name = TitleFactory.getInstance().getTitle(presenter.getAllTabs()[0].getIdentifier());
            if (null == name || name.equals("")) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                toolbarImage.setVisibility(View.VISIBLE);
            } else {
                toolbar.setTitle(name);
            }
        }




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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TemporaryDataManager.getInstance().setEmail(null);
    }

    @Override
    public void showContextHelper(Context context, FeatureContextualHelp contextualHelpView) {
        showContextualHelp(this, contextualHelpView);
    }

    @Override
    public void setTab(int tabNumber) {
        tabLayout.getTabAt(tabNumber).select();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.setRefreshLocations(true);
        Log.d("MainTabAvtivity", "Refresh locations " + presenter.refreshLocations());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.setRefreshLocations(true);
        Log.d("MainTabAvtivity", "Refresh locations " + presenter.refreshLocations());
    }

}
