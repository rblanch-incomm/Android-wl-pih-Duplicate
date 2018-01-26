package com.incomm.payithere.views.faqs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.FAQQuestionItem;
import com.incomm.payithere.views.MainTabInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainTabInterface} interface
 * to handle interaction events.
 */
public class FaqQuestionAnswerFragment extends Fragment implements FaqQuestionAnswerMVP.View {

    @BindView(R.id.title_section__text)
    TextView faqQuestionItemInnerTitle;
    @BindView(R.id.faq_question_answer)
    TextView faqQuestionAnswer;

    //private TextView faqQuestionAnswer,faqQuestionItemInnerTitle;
    private MainTabInterface mListener;
    private FAQQuestionItem questionItem;
    private Unbinder unbinder;
    private String parentTitle;
    private int globalTextColor;

    public FaqQuestionAnswerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle passedInBundle = getArguments();
        if (passedInBundle != null) {
            questionItem = passedInBundle.getParcelable("FaqQuestionItem");
            parentTitle = passedInBundle.getString("ParentTitle");
            globalTextColor = passedInBundle.getInt("global_text_color");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_category_question_answer, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupUi();
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
    public void setupUi() {
        faqQuestionItemInnerTitle.setText(questionItem.getQuestion());
        faqQuestionItemInnerTitle.setTextColor(globalTextColor);
        faqQuestionAnswer.setText(questionItem.getAnswer());
        faqQuestionAnswer.setTextColor(globalTextColor);
    }

    @Override
    public void setViewFeatures() { }

    @Override
    public void onResume() {
        super.onResume();

        String title = parentTitle != null ? parentTitle : "";
        mListener.onSetToolbarTitle(title);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
