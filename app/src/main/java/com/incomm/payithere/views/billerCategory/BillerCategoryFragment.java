package com.incomm.payithere.views.billerCategory;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.BillerCategoryListAdapter;
import com.incomm.payithere.adapters.BillerListAdapter;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.models.BillerCategoryItem;
import com.incomm.payithere.models.cms.BillerCategoryIcon;
import com.incomm.payithere.models.services.response.BillerCategory;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.repositories.BillerCategoryCMSRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.SimpleDividerItemDecoration;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.billHistoryDetail.BillHistoryDetailsFragment;
import com.incomm.payithere.views.selectBiller.SelectBillerFragment;

import java.util.List;

public class BillerCategoryFragment extends ViewFragment implements BillerCategoryMVP.View {

    private MainTabInterface mListener;
    private BillerCategoryPresenter presenter;
    private RecyclerView mRecyclerView;

    public BillerCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_biller_category, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.category_recycler_view);
        //mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(PayItHereApplication.getContext()));
        presenter = new BillerCategoryPresenter(this, new BillerCategoryCMSRepository());
        presenter.getViewElements();


        return rootView;
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
    public void setupUi() {
        presenter.getTitle();
        presenter.getBillerCategories();
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
    public void displayCategoryList(final List<BillerCategoryItem> categoryList) {
        Log.d("BillerCategoryFragment",categoryList.toString());
        BillerCategoryListAdapter adapter = new BillerCategoryListAdapter(categoryList,Color.parseColor(presenter.getGlobalTextColor()),
                presenter.getRightChevron());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SelectBillerFragment fragment = new SelectBillerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", categoryList.get(position).getId());
                bundle.putString("name",categoryList.get(position).getName());
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.create_payment_slip_root, fragment);
                transaction.addToBackStack("Bills History Details");
                transaction.commit();
            }
        }));

    }
}
