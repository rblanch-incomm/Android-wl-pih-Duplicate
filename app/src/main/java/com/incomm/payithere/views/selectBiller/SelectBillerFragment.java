package com.incomm.payithere.views.selectBiller;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.BillerListAdapter;
import com.incomm.payithere.adapters.BillerListAdapterV2;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.models.ConfirmAccountItem;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.Option;
import com.incomm.payithere.repositories.SelectBillerCMSRepository;
import com.incomm.payithere.services.SingleCategoryService;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.SimpleDividerItemDecoration;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.confirmAccountDetails.ConfirmAccountDetailsFragment;
import com.incomm.payithere.views.enterAmount.EnterAmountFragment;
import com.incomm.payithere.views.selectPayment.SelectPaymentFragment;
import com.incomm.payithere.views.template.TemplateCMSRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectBillerFragment extends ViewFragment implements SelectBillerMVP.View,SearchView.OnQueryTextListener,MenuItem.OnActionExpandListener  {

    @BindView(R.id.billers_not_found_textview)
    TextView mBillerNotFoundTV;
    private MainTabInterface mListener;
    private SelectBillerPresenter presenter;
    private RecyclerView mRecyclerView;
    private String category,rightChevron,categorytitle;
    private BillerListAdapterV2 mAdapter;


    public SelectBillerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
            categorytitle = getArguments().getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_biller, container, false);
        ButterKnife.bind(this,rootView);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.billers_recycler_view);
        setHasOptionsMenu(true);
        //mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(PayItHereApplication.getContext()));
        presenter = new SelectBillerPresenter(this,new SelectBillerCMSRepository(),new SingleCategoryService());
        presenter.getViewElements();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setPadding(0,0,16,0);
        searchView.setQueryHint("Search");
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

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.setTitle();
        presenter.setBillersList(category);
        presenter.getRightChevron();
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(categorytitle);
    }

    @Override
    public void showBillersList(final List<BillerGroup> billerList) {
        mAdapter = new BillerListAdapterV2(this,billerList, Color.parseColor(presenter.getGlobalTextColor()),rightChevron);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        mBillerNotFoundTV.setVisibility(View.GONE);
        /*mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SelectPaymentFragment fragment = new SelectPaymentFragment();
                Bundle bundle = new Bundle();
//                List<Option> paymentOptions = billerList.get(position).getOptions();
//                bundle.putParcelableArrayList("biller",(ArrayList)paymentOptions);
                BillerGroup biller = billerList.get(position);
                bundle.putParcelable("biller",biller);

                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.create_payment_slip_root, fragment);
                transaction.addToBackStack("Select Payment");
                transaction.commit();
            }
        }));*/
    }

    @Override
    public void setRightChevron(String rightChevron) {
        this.rightChevron = rightChevron;
    }

    @Override
    public void displayQueryData(List<BillerGroup> filteredList) {
        if(filteredList.isEmpty()){
            mRecyclerView.setVisibility(View.GONE);
            mBillerNotFoundTV.setVisibility(View.VISIBLE);
        }else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mBillerNotFoundTV.setVisibility(View.GONE);
        }
    }

    @Override
    public void showWhenListEmpty() {
        mRecyclerView.setVisibility(View.GONE);
        mBillerNotFoundTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayPaymentFragment(BillerGroup biller) {
        SelectPaymentFragment fragment = new SelectPaymentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("biller",biller);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.create_payment_slip_root, fragment);
        transaction.addToBackStack("Select Payment");
        transaction.commit();
    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }
}
