package com.incomm.payithere.views.educational;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.adapters.EducationalWalkthroughAdapter;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.EducationalSlide;
import com.incomm.payithere.repositories.EducationalWalkThroughCMSRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.login.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EducationalFragment extends Fragment implements EducationalMVP.View {

    private EducationalPresenter presenter;
    private EducationalWalkthroughAdapter adapter;
    private RadioButton[] radioButtons;

    @BindView(R.id.educational_view_pager)
    ViewPager viewPager;
    @BindView(R.id.page_indicator_group)
    RadioGroup radioGroup;
    @BindView(R.id.global_big_button)
    LinearLayout globalBigButton;
    @BindView(R.id.button_textview)
    TextView globalBigButtonTextView;
    @BindView(R.id.bottom_button_container)
    LinearLayout bottomButtonContainer;
    @BindView(R.id.skip_btn)
    Button skipButton;

    public EducationalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EducationalPresenter(this, new EducationalWalkThroughCMSRepository());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_educational_view, container, false);
        ButterKnife.bind(this, view);
        presenter.getViewElements();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }
    @Override
    public void setupUi() {
        presenter.displaySlides();
        if (presenter.getFirstLaunch()) {
            presenter.displaySkipButton();
            presenter.showSkipButton();
        }
        presenter.displayBottomButton();
    }

    @Override
    public void setViewFeatures() {

    }


    @Override
    public void displaySkipButton(String buttonLabel) {
        skipButton.setText(buttonLabel);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonClicked();
            }
        });

    }

    @Override
    public void showSkipButton() {
        skipButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSkipButton() {
        skipButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayHelpMedia(final List<EducationalSlide> educationalSlideList) {
        final int numberOfSlide = educationalSlideList.size();
        radioButtons = new RadioButton[numberOfSlide];

        for (int x = 0; x < educationalSlideList.size(); x++) {
            radioButtons[x] = (RadioButton) getActivity().getLayoutInflater().inflate(R.layout.radiobutton_template, null);
            if (x == 0) {
                radioButtons[x].setChecked(true);
            }
            radioButtons[x].setId(radioGroup.getChildCount());
            radioGroup.addView(radioButtons[x]);
        }

        adapter = new EducationalWalkthroughAdapter(getContext(), educationalSlideList);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

                presenter.setPageIndicator(position);
                presenter.checkIfButtonShown(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void displayBottomButton(String buttonLabel) {
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
    public void showBottomButton() {
        bottomButtonContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBottomButton() {
        bottomButtonContainer.setVisibility(View.GONE);
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
    public void setPageIndicator(int position) {
        radioButtons[position].setChecked(true);
    }

}
