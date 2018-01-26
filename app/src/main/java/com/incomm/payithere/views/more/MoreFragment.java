package com.incomm.payithere.views.more;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.MoreViewAdapter;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.TemporaryDataManager;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.login.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements MoreMVP.View {

    @BindView(R.id.more_recycler_view)
    RecyclerView recyclerView;
    private LinearLayout logOutButton;
    @BindView(R.id.customer_first_name_tv)
    TextView customerFirstName;
    @BindView(R.id.button_textview)
    TextView logouttext;

    private MorePresenter presenter;

    private MainTabInterface mListener;
    private RecyclerView.LayoutManager mLayoutManager;

    public MoreFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MorePresenter(this);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        logOutButton = (LinearLayout) view.findViewById(R.id.global_big_button);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
//        params.setMarginStart(40);
//        params.setMarginEnd(40);
        params.setMargins(40, 20, 40, 0);
        logOutButton.setLayoutParams(params);

        presenter.getViewElements();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onSetToolbarTitle(presenter.getViewTitle());
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void setupUi() {

        customerFirstName.setText("Account: " + TemporaryDataManager.getInstance().getFirstName());

        logouttext.setText(presenter.getButtonLabel());
        logouttext.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButtonTitle()));
        logOutButton.setBackgroundColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButton()));

    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mListener.onSetToolbarTitle(presenter.getViewTitle());

    }*/

    @Override
    public void setViewFeatures() {
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        final List<Feature> features = presenter.getFeatures();

        MoreViewAdapter adapter = new MoreViewAdapter(presenter.getFeatures(),presenter.getGlobalTextColor(),presenter.getnextChevron());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Fragment fragment = FragmentFactory.getFragment(features.get(position).getKey());

                Bundle bundle = new Bundle();
                bundle.putParcelable("key", features.get(position));
                bundle.putInt("current_fragment",R.id.more_root);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.more_root, fragment);
                transaction.addToBackStack(features.get(position).getKey());
                transaction.commit();
            }
        }));
    }

    @OnClick(R.id.global_big_button)
    public void onLogoutClick(){
        PayItHereApplication.setToken("");
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
//        mListener.requestLogout(false);
    }

//    @Override
//    public void onSetToolbarTitle(String title) {
//        mListener.onSetToolbarTitle(title);
//    }
}
