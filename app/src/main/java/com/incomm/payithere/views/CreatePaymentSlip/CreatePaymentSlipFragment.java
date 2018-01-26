package com.incomm.payithere.views.CreatePaymentSlip;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.adapters.BillerListAdapter;
import com.incomm.payithere.adapters.CreatePaymentBillerListAdapter;
import com.incomm.payithere.adapters.TopBillerListAdapter;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.models.cms.TopBiller;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.Option;
import com.incomm.payithere.repositories.CreatePaymentSlipCMSRepository;
import com.incomm.payithere.services.BillerByNameService;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.SimpleDividerItemDecoration;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.billerCategory.BillerCategoryFragment;
import com.incomm.payithere.views.enteraccountinformation.EnterAccountInformationFragment;
import com.incomm.payithere.views.selectPayment.SelectPaymentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePaymentSlipFragment extends ViewFragment implements SearchView.OnQueryTextListener,CreatePaymentSlipMVP.View{

    @BindView(R.id.categories_text_view)
    TextView categoryTV;
    @BindView(R.id.billers_not_found_textview)
    TextView billersNotFoundTV;

    private MainTabInterface mListener;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private CreatePaymentSlipPresenter presenter;
    private String rightChevron,themePrimary;
    private ImageView clearButtonSearchIV,searchButtonIV;
    private List<TopBiller> topBillerList;
    private EditText searchET;
    private RecyclerView.ItemDecoration recyclerViewDivider;

    private List<BillerGroup> billerList;

    public CreatePaymentSlipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_payment_slip, container, false);
        ButterKnife.bind(this,rootView);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.biller_recycler_view);
        recyclerViewDivider = new SimpleDividerItemDecoration(PayItHereApplication.getContext());
        // mRecyclerView.addItemDecoration(recyclerViewDivider);
        mSearchView = (SearchView) rootView.findViewById(R.id.biller_searchView);
        clearButtonSearchIV = (ImageView) mSearchView.findViewById(R.id.search_close_btn);
        searchButtonIV = (ImageView) mSearchView.findViewById(R.id.search_mag_icon);
        searchET = (EditText) mSearchView.findViewById(R.id.search_src_text);
        clearButtonSearchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(getActivity());
                clearButtonSearchIV.setVisibility(View.INVISIBLE);
                searchET.setText("");
                mSearchView.setQuery("", false);
                /*searchButtonIV.setImageResource(R.drawable.ic_search);
                searchButtonIV.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);*/
                if(mRecyclerView.getVisibility() == View.GONE){
                    displayTopBillers(topBillerList);
                }
            }
        });
        searchButtonIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(getActivity());
                displayTopBillers(topBillerList);
                clearButtonSearchIV.setVisibility(View.GONE);
                searchButtonIV.setImageResource(R.drawable.ic_search);
                searchButtonIV.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                searchET.setText("");
                mSearchView.setQuery("", false);
            }
        });
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setIconifiedByDefault(false);
        presenter = new CreatePaymentSlipPresenter(this,new CreatePaymentSlipCMSRepository(),new BillerByNameService());
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

    @OnClick(R.id.categories_text_view)
    public void categoryClicked(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        BillerCategoryFragment fragment = new BillerCategoryFragment();
        transaction.replace(R.id.create_payment_slip_root, fragment);
        transaction.addToBackStack("Biller Category");
        transaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.getTitle();
        presenter.getTopBillers();
        presenter.getThemePrimary();
        presenter.getRightChevron();
        presenter.getCategoriesButtonText();
        presenter.getSearchBarPlaceholder();
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
        displayErrorAlert(error);
    }

    @Override
    public void displayBillerList(List<BillerGroup> billerList) {
        Log.d("CreatePaymentSlip",billerList.toString());

        this.billerList = billerList;
        mRecyclerView.setVisibility(View.VISIBLE);
        billersNotFoundTV.setVisibility(View.GONE);
        mRecyclerView.removeItemDecoration(recyclerViewDivider);
        CreatePaymentBillerListAdapter adapter = new CreatePaymentBillerListAdapter(billerList, Color.parseColor(presenter.getGlobalTextColor()),rightChevron);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.removeOnItemTouchListener(topBillerListListener);
        mRecyclerView.addOnItemTouchListener(billerSearchListListener);
//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                displaySelectPaymentView(position);
//            }
//        }));

    }

    private void displaySelectPaymentView(int position) {
        SelectPaymentFragment fragment = new SelectPaymentFragment();
        BillerGroup biller = billerList.get(position);

        Bundle bundle = new Bundle();
        bundle.putParcelable("biller",biller);

        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.create_payment_slip_root, fragment);
        transaction.addToBackStack("Select Payment");
        transaction.commit();
    }

    @Override
    public void showNoBillersFound() {
        mRecyclerView.setVisibility(View.GONE);
        billersNotFoundTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayTopBillers(final List<TopBiller> topBillers) {
        topBillerList = topBillers;
        mRecyclerView.setVisibility(View.VISIBLE);
        billersNotFoundTV.setVisibility(View.GONE);
        mRecyclerView.addItemDecoration(recyclerViewDivider);
        TopBillerListAdapter adapter = new TopBillerListAdapter(topBillers, Color.parseColor(presenter.getGlobalTextColor()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.removeOnItemTouchListener(billerSearchListListener);
        mRecyclerView.addOnItemTouchListener(topBillerListListener);
    }

    public void displayEnterAccountInformationView(String billerTokenId) {
        EnterAccountInformationFragment fragment = new EnterAccountInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("billerId", billerTokenId);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.create_payment_slip_root, fragment);
        transaction.addToBackStack("Select Payment");
        transaction.commit();
    }

    RecyclerTouchListener billerSearchListListener = new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
        @Override
        public void onClick(View view, int position) {
            displaySelectPaymentView(position);
        }
    });

    RecyclerTouchListener topBillerListListener = new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
        @Override
        public void onClick(View view, int position) {
           displayEnterAccountInformationView(topBillerList.get(position).getToken());
        }
    });

    @Override
    public void setSearchBarPlaceHolder(String searchBarPlaceholder) {
        mSearchView.setQueryHint(searchBarPlaceholder);
    }

    @Override
    public void setCategoryButtonText(String categoriesButtonText) {
        categoryTV.setText(categoriesButtonText);
        categoryTV.setTextColor(Color.parseColor(themePrimary));
    }

    @Override
    public void setRightChevron(String rightChevron) {
        this.rightChevron = rightChevron;
    }

    @Override
    public void setThemePrimary(String color) {
        themePrimary = color;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("CreatePaymentSlip",query);
        presenter.getBillerByName(query);
        hideSoftKeyboard(getActivity());
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length()>0) {
            searchButtonIV.setImageResource(R.drawable.ic_arrow_back_black_24dp);
            searchButtonIV.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        }
        return false;
    }

}
