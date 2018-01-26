package com.incomm.payithere.views.more;


import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

/**
 * Created by Jerome Davis on 4/13/17.
 */

public interface MoreMVP {
    interface View extends BaseView<Presenter> {
        //void onSetToolbarTitle(String title);
    }

    interface Presenter extends BasePresenter {
    }
}
