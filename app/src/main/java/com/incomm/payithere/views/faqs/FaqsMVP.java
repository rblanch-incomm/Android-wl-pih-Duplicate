package com.incomm.payithere.views.faqs;


import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.FAQQuestionItem;

import java.util.List;

/**
 * Created by jayma on 4/20/2017.
 */

public interface FaqsMVP {
    interface View extends BaseView<Presenter> {

        void setTitle(String title);
        void displayQuestions(List<FAQQuestionItem> questionsList);
        void setGeneralTextColor(String generalTextColor);
        void displayNoQuestions();
    }

    interface Presenter extends BasePresenter {

    }
    interface Repository {
        String getTitle();
        String getRightChevron();
        List<FAQQuestionItem> getQuestions();
        String getGeneralTextColor();
        String getAnalyticsId();
    }
}
