package com.incomm.payithere.views.importantinformation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.R;
import com.incomm.payithere.adapters.ImportantInformationAdapter;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.legaldocs.LegalDocument;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainTabInterface} interface
 * to handle interaction events.
 */
public class ImportantInformationFragment extends Fragment implements ImportantInformationMVP.View {
    @BindView(R.id.important_information_recycler_view)
    RecyclerView mRecyclerView;

    private ImportantInformationMVP.Presenter presenter;

    private MainTabInterface mListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private GlobalImagesManager imagesManager;
    private int currentFragment;

    public ImportantInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentFragment = getArguments().getInt("current_fragment");
        }
        imagesManager = GlobalImagesManager.getInstance();

        presenter = new ImportantInformationPresenter(ColorSchemeManager.getInstance(),
                                                      CMSViewManager.getInstance().getImportantInformationView(),
                                                      this,
                                                      imagesManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_important_information, container, false);
        ButterKnife.bind(this, view);

        presenter.getViewElements();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onSetToolbarTitle(presenter.getViewTitle());
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void setupUi() {
        mListener.onSetToolbarTitle(presenter.getViewTitle());
    }

    @Override
    public void setViewFeatures() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(presenter.getAdapter());
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Fragment fragment = FragmentFactory.getFragment(presenter.getFeatureLegalDocuments().get(position).getKey());

                Bundle bundle = new Bundle();
                bundle.putParcelable("key", presenter.getFeatureLegalDocuments().get(position));
                bundle.putInt("current_key", R.id.more_root);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.important_information_root, fragment);
                transaction.addToBackStack("morelistclick");
                transaction.commit();
            }
        }));
    }
}