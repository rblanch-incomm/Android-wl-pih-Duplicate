package com.incomm.payithere.views.featuresection;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contentful.vault.Asset;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.FeatureSectionAdapter;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.FeatureSection;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.views.MainTabInterface;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureSectionFragment extends Fragment implements FeatureSectionMVP.View {
    private static final String FEATURE_SECTION_KEY = "key";

    @BindView(R.id.feature_section_recycler_view)
    RecyclerView recyclerView;

    private MainTabInterface mListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private int currentFragment;
    private FeatureSectionMVP.Presenter presenter;

    public FeatureSectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FeatureSection mFeatureSection = null;

        if (getArguments() != null) {
            mFeatureSection = getArguments().getParcelable(FEATURE_SECTION_KEY);
            currentFragment = getArguments().getInt("current_fragment");
        }

        presenter = new FeatureSectionPresenter(this,
                                                mFeatureSection,
                                                ColorSchemeManager.getInstance(),
                                                GlobalImagesManager.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature_section, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        presenter.getViewElements();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void setupUi() { }

    @Override
    public void setViewFeatures() { }

    @Override
    public void setTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void createRecyclerView(final List<Feature> features, String textColor, Asset chevron) {
        FeatureSectionAdapter adapter = new FeatureSectionAdapter(features, textColor, ActivityUtils.checkNull(chevron));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
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
        bundle.putInt("current_fragment",currentFragment);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(currentFragment, fragment);
        transaction.addToBackStack("featureSectionClick");
        transaction.commit();
    }
}
