package com.incomm.payithere.views.splash;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

/**
 * Created by Jerome Davis on 8/25/17.
 */

public interface SplashMVP {

    interface View extends BaseView<Presenter> {
        void launchLoginVIew();
        void launchWalkthroughs();
    }

    interface Presenter extends BasePresenter {
        void getSplashBackgroundImage();
        void getSplashLogo();
    }
}
