package com.incomm.payithere.views.billsHistory;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.BillsHistoryAdapter;
import com.incomm.payithere.adapters.BillsHistoryAdapterV2;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.BillsHistoryItem;
import com.incomm.payithere.repositories.BillerHistoryCMSRepository;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.SimpleDividerItemDecoration;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipFragment;
import com.incomm.payithere.views.billHistoryDetail.BillHistoryDetailsFragment;

import java.util.ArrayList;

public class BillerHistoryFragment extends ViewFragment implements BillerHistoryMVP.View,SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
        SwipeRefreshLayout.OnRefreshListener {

    private MainTabInterface mListener;
    private BillerHistoryPresenter presenter;
    private String generalTextColor,primarYThemeColor;
    private RecyclerView recyclerView;
    //private BillsHistoryAdapter adapter;
    private BillsHistoryAdapterV2 adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<BillsHistoryItem> mBillsList;

    public BillerHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biller_history, container, false);
        presenter = new BillerHistoryPresenter(this,new BillerHistoryCMSRepository());
        presenter.getViewElements();
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.bills_recycler_view);
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(PayItHereApplication.getContext()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.bills_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor(primarYThemeColor));

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
    public void setupUi() {
        presenter.getGlobalTextColor();
        presenter.getThemePrimary();
        presenter.getTitle();
        presenter.getBillsList();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTitle();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setViewFeatures() {
    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);

    }

    @Override
    public void showError(String error) {

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
    public void showBillsHistory(final ArrayList<BillsHistoryItem> billsList) {
        mBillsList = billsList;
        adapter = new BillsHistoryAdapterV2(this,billsList, ActivityUtils.checkNull(GlobalImagesManager.getInstance().getChevronRight()), Color.parseColor(generalTextColor));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onRefresh() {
        presenter.getBillsList();
    }

    @Override
    public void showNoBillsHistory() {
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        Fragment fragment = FragmentFactory.getFragment(Constants.BILLER_NO_HISTORY_FRAGMENT);
        Bundle bundle = new Bundle();
        bundle.putInt("current_fragment", R.id.biller_history_root);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.biller_history_root, fragment);
        transaction.commit();
    }

    @Override
    public void setGeneralTextColor(String generalTextColor) {
        this.generalTextColor = generalTextColor;
    }

    @Override
    public void onFragmentSelected() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onFragmentUnselected() {
    setHasOptionsMenu(false);
    }

    @Override
    public void setThemePrimary(String themePrimary) {
        primarYThemeColor = themePrimary;
    }

    @Override
    public void displayDetailFragment(BillsHistoryItem item) {
        String status = item.getStatus();
        Bundle bundle = new Bundle();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (status){
            case "pending":
                BarcodeSlipFragment pendingFragment = new BarcodeSlipFragment();
                bundle.putString("paymentId",item.getToken());
                bundle.putInt("current_fragment",R.id.biller_history_root);
                pendingFragment.setArguments(bundle);
                transaction.replace(R.id.biller_history_root, pendingFragment);
                transaction.addToBackStack("Barcode Slip Details");
                transaction.commit();
                break;
            case "processed":
                BillHistoryDetailsFragment confirmedFragment = new BillHistoryDetailsFragment();
                bundle.putParcelable("key", item);
                bundle.putInt("current_fragment",R.id.biller_history_root);
                confirmedFragment.setArguments(bundle);
                transaction.replace(R.id.biller_history_root, confirmedFragment);
                transaction.addToBackStack("Bills History Details");
                transaction.commit();
                break;
            default:
                return;
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }


}
