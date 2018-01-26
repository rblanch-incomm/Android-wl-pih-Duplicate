package com.incomm.payithere.views.walkthrough;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.contentful.vault.Asset;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.SwipeAdapter;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.repositories.WalkThroughCMSRepository;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.login.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalkThroughFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalkThroughFragment extends ViewFragment implements WalkThroughMVP.View {

    private MainTabInterface mainTabInterface;
    private WalkThroughPresenter presenter;
    private boolean showButton;
    private SwipeAdapter swipeAdapter;
    private String[] gettingStartedImages;
    private RadioButton[] radioButtons;
    private int numberOfImages;

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.page_indicator_group)
    RadioGroup radioGroup;
    @BindView(R.id.global_big_button)
    LinearLayout globalBigButton;
    @BindView(R.id.button_textview)
    TextView globalBigButtonTextView;
    @BindView(R.id.bottom_button_container)
    LinearLayout bottomButtonContainer;


    public WalkThroughFragment() {
        // Required empty public constructor
    }

    public static WalkThroughFragment newInstance(boolean showButton) {
        WalkThroughFragment fragment = new WalkThroughFragment();
        fragment.setShowButton(showButton);

        return fragment;
    }

    private void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WalkThroughPresenter(this, new WalkThroughCMSRepository());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk_through, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mainTabInterface = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.getViewElements();
        setupUi();

        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticId());
    }

    @Override
    public void setupUi() {
        presenter.displayTitle();
        presenter.displayBottomButton(showButton);
        presenter.setupBottomButton();
        presenter.displayWalkThrough();

    }

    @Override
    public void setPageIndicator(int position) {
        radioButtons[position].setChecked(true);
    }

    @Override
    public void displayTitle(String title) {
        mainTabInterface.onSetToolbarTitle(title);
    }

    @Override
    public void displayWalkthrough(List<Asset> images) {

        numberOfImages = images.size();
        gettingStartedImages = new String[numberOfImages];

        int i = 0;

        for (Asset asset : images) {
            gettingStartedImages[i] = ActivityUtils.checkNull(asset);
            i++;
        }

        radioButtons = new RadioButton[numberOfImages];

        for (int x = 0; x < numberOfImages; x++) {
            radioButtons[x] = (RadioButton) getActivity().getLayoutInflater().inflate(R.layout.radiobutton_template, null);
            if (x == 0) {
                radioButtons[x].setChecked(true);
            }
            radioButtons[x].setId(radioGroup.getChildCount());
            radioGroup.addView(radioButtons[x]);
        }

        swipeAdapter = new SwipeAdapter(getContext(), gettingStartedImages);
        viewPager.setAdapter(swipeAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                presenter.setPageIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showBottomButton() {
        bottomButtonContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBottomButton() {
        bottomButtonContainer.setVisibility(View.GONE);
    }

    @Override
    public void setupBottomButton(String buttonLabel) {
        globalBigButtonTextView.setText(buttonLabel);
        globalBigButtonTextView.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButtonTitle()));

        globalBigButton.setBackgroundColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButton()));
        globalBigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonClicked();
            }
        });

    }

    @Override
    public void showLoginView() {
        presenter.setFirstLaunch();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    @Override
    public void setViewFeatures() {

    }

}
