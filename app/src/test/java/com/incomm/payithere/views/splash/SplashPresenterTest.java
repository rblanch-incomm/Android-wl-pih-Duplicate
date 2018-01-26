/*
package com.incomm.payithere.views.splash;

import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


 // Created by Brian Jennings on 10/9/2017.


@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {
    @Mock
    SplashMVP.View view;
    @Mock
    PreferencesManager prefManager;
    @Mock
    DocumentsManager docManager;

    private SplashPresenter presenter;

    @Test
    public void showWalkthroughsOnFirstRun() throws Exception {
        when(prefManager.getIsFirstLaunch()).thenReturn(true);
        this.presenter = new SplashPresenter(view);
        this.presenter.onSynchCompleted(true);

        verify(view).launchWalkthroughs();
    }

    @Test
    public void showWalkthroughsOnFirstRunIfSyncNotComplete() throws Exception {
        when(prefManager.getIsFirstLaunch()).thenReturn(true);
        presenter = new SplashPresenter(view);
        presenter.onSynchCompleted(false);

        verify(view).launchWalkthroughs();
    }

    @Test
    public void showLoginViewOnNotFirstRun() throws Exception {
        when(prefManager.getIsFirstLaunch()).thenReturn(false);
        presenter = new SplashPresenter(view);
        presenter.onSynchCompleted(true);

        verify(view).launchLoginVIew();
    }

    @Test
    public void showLoginViewOnNotFirstRunIfSyncNotComplete() throws Exception {
        when(prefManager.getIsFirstLaunch()).thenReturn(false);
        presenter = new SplashPresenter(view);
        presenter.onSynchCompleted(false);

        verify(view).launchLoginVIew();
    }
}
*/
