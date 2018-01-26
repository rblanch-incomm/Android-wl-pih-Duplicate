package com.incomm.payithere.views.help;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.contentful.vault.Asset;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.FeatureSectionAdapter;
import com.incomm.payithere.factories.DialogFactory;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.repositories.HelpRepository;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment implements HelpMVP.View {
    @BindView(R.id.phoneNumber)
    TextView phoneNumberText;
    @BindView(R.id.mailingAddress)
    TextView mailingAddressText;
    @BindView(R.id.deviceId)
    TextView deviceIdText;

    @BindView(R.id.deviceIdLabel)
    TextView deviceIdLabel;
    @BindView(R.id.mailingAddressLabel)
    TextView mailingAddressLabel;
    @BindView(R.id.phoneNumberLabel)
    TextView phoneNumberLabel;

    @BindView(R.id.help_recycler_view)
    RecyclerView helpFeatureRecyclerView;

    private HelpMVP.Presenter presenter;
    private MainTabInterface mListener;
    private Unbinder unbinder;
    private String deviceId;
    private int currentFragment;

    public HelpFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            currentFragment = getArguments().getInt("current_fragment");
        }
        this.deviceId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        presenter = new HelpPresenter(this,
                                      new HelpRepository(),
                                      GlobalImagesManager.getInstance(),
                                      ColorSchemeManager.getInstance());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter.getViewElements();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    public void setViewFeatures() { }

    public void setupUi() {
        presenter.displayPhoneNumber();
        presenter.displayMailingAddress();
        presenter.displayDeviceId();
        presenter.setFragmentTitle();
        presenter.displayCallLabel();
        presenter.displayMailLabel();
        presenter.displayDeviceIdLabel();
    }

    @Override
    public void displayPhoneNumber(String phoneNumber) {
        phoneNumberText.setText(phoneNumber);
        phoneNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPhoneClick();
            }
        });
    }

    @Override
    public void displayMailingAddress(String mailingAddress) {
        mailingAddressText.setText(mailingAddress);
    }

    @Override
    public void displayCallConfirmation(String message, String positiveButton, String negativeButton) {
        DialogFactory.createConfirmAlertDialog(getContext(), message, positiveButton, negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.confirmPhoneCall();
            }
        }).show();
    }

    @Override
    public void makePhoneCall(String phoneNumber) {
        String uri = "tel:" + phoneNumber.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    public void createRecyclerView(List<Feature> features, String textColor, Asset chevron) {
        helpFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FeatureSectionAdapter adapter = new FeatureSectionAdapter(features, textColor, ActivityUtils.checkNull(chevron));
        helpFeatureRecyclerView.setAdapter(adapter);

        helpFeatureRecyclerView.addOnItemTouchListener(
            new RecyclerTouchListener(getActivity(), helpFeatureRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.onItemClicked(position);
            }
        }));
    }

    @Override
    public void launchFragment(String key) {
        Fragment fragment = FragmentFactory.getFragment(key);

        Bundle bundle = new Bundle();
        bundle.putInt("current_fragment", currentFragment);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.help_root, fragment);
        transaction.addToBackStack("featureSectionClick");
        transaction.commit();
    }

    @Override
    public void setFragmentTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void displayCallLabel(String callSupport) {
        phoneNumberLabel.setText(callSupport);
    }

    @Override
    public void displayMailLabel(String mailSupport) {
        mailingAddressLabel.setText(mailSupport);
    }

    @Override
    public void displayDeviceIdLabel(String deviceId) {
        deviceIdLabel.setText(deviceId);
    }

    @Override
    public void displayDeviceId() {
        deviceIdText.setText(this.deviceId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
