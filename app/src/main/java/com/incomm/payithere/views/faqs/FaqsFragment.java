package com.incomm.payithere.views.faqs;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.incomm.payithere.R;
import com.incomm.payithere.adapters.FaqCategoryAdapter;
import com.incomm.payithere.factories.FragmentFactory;
import com.incomm.payithere.listeners.RecyclerTouchListener;
import com.incomm.payithere.models.cms.FAQQuestionItem;
import com.incomm.payithere.repositories.FAQRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainTabInterface} interface
 * to handle interaction events.
 */
public class FaqsFragment extends Fragment implements FaqsMVP.View {


    private FaqsPresenter presenter;
    private RecyclerView recyclerView;
    private MainTabInterface mListener;
    private Unbinder unbinder;
    private int currentFragment;
    private String generalTextColor,viewTitle;

    public FaqsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#089b77"));
        Bundle passedInBundle = getArguments();
        if (passedInBundle != null) {
            currentFragment = passedInBundle.getInt("current_fragment");
        }
        presenter = new FaqsPresenter(this,new FAQRepository());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faqs, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.bills_recycler_view);
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
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setupUi() {
        presenter.getGlobalTextColor();
        presenter.getTitle();
        presenter.getQuestions();
    }

    @Override
    public void setViewFeatures() {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setTitle(String title) {
        mListener.onSetToolbarTitle(title);
        viewTitle = title;
    }

    @Override
    public void displayQuestions(final List<FAQQuestionItem> questionsList) {
        FaqCategoryAdapter adapter = new FaqCategoryAdapter(questionsList, presenter.getRightChevron(), Color.parseColor(generalTextColor));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Fragment fragment = FragmentFactory.getFragment("FaqQuestionAnswer");
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("FaqQuestionItem", questionsList.get(position));
                        bundle.putString("ParentTitle", viewTitle);
                        bundle.putInt("global_text_color", Color.parseColor(generalTextColor));
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.faqs_root, fragment);
                        transaction.addToBackStack("faqcategorylistclick");
                        transaction.commit();
                    }
                }));
    }

    @Override
    public void setGeneralTextColor(String generalTextColor) {
        this.generalTextColor = generalTextColor;
    }

    @Override
    public void displayNoQuestions() {
        Toast.makeText(getActivity(),"No Questions Found",Toast.LENGTH_SHORT).show();
    }

}