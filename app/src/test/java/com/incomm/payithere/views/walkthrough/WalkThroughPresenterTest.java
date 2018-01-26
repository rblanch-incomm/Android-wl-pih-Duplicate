package com.incomm.payithere.views.walkthrough;

import com.contentful.vault.Asset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Jerome Davis on 9/26/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class WalkThroughPresenterTest {

    @Mock
    private WalkThroughMVP.View view;
    @Mock
    private WalkThroughMVP.WalkThroughRepository repository;
    private WalkThroughPresenter presenter;

    List<Asset> mockedImageList = mock(List.class);

    List<Asset> mockedAltImageList = mock(List.class);

    @Before
    public void setUp() throws Exception {
        presenter = new WalkThroughPresenter(view, repository);
        presenter.getViewElements();
    }

    @Test
    public void getViewElements() throws Exception {
        when(repository.getTitle()).thenReturn("Success");
        when(repository.getChannel()).thenReturn("Success");
        when(repository.getContinueButtonTitle()).thenReturn("Success");
        when(repository.getName()).thenReturn("Success");
        when(repository.getImages()).thenReturn(mockedImageList);
        when(repository.getImagesAlternateResolution()).thenReturn(mockedAltImageList);


    }

    @Test
    public void getNameShouldAlwaysBeString() throws Exception {
        // should always return a String value - if null we should return "" (empty string)
        when(repository.getName()).thenReturn(null);
        presenter.getViewElements();
        assertTrue("presenter.getName = null", presenter.getName() != null
                && presenter.getName().getClass().equals(String.class));
    }

    @Test
    public void getTitleShouldAlwaysBeString() throws Exception {
        when(repository.getTitle()).thenReturn(null);
        presenter.getViewElements();
        assertTrue("presenter.setTitle = null", presenter.getTitle() != null
                && presenter.getTitle().getClass().equals(String.class));
    }

    @Test
    public void getChannelShouldAlwaysBeString() throws Exception {
        when(repository.getChannel()).thenReturn(null);
        presenter.getViewElements();
        assertTrue("presenter.getChannel = null", presenter.getChannel() != null
                && presenter.getChannel().getClass().equals(String.class));
    }

    @Test
    public void getContinueButtonTitleShouldAlwaysBeString() throws Exception {
        when(repository.getContinueButtonTitle()).thenReturn(null);
        presenter.getViewElements();
        assertTrue("presenter.getContinueButtonTitle = null", presenter.getContinueButtonTitle() != null
                && presenter.getContinueButtonTitle().getClass().equals(String.class));
    }

    @Test
    public void getImagesShouldAlwaysBeListOfAssets() throws Exception {
        when(repository.getImages()).thenReturn(null);
        presenter.getViewElements();
        assertTrue("presenter.getImages = null", presenter.getImages() != null
            && presenter.getImages() instanceof List); // need to find a way to test for Assets
    }

    @Test
    public void getImagesAlternateResolutionShouldAlwaysBeListOfAssets() throws Exception {
        when(repository.getImagesAlternateResolution()).thenReturn(null);
        presenter.getViewElements();
        assertTrue("presenter.getImagesAlternateResolution = null", presenter.getImagesAlternateResolution() != null
                && presenter.getImages() instanceof List); // need to find a way to test for Assets);
    }

    @Test
    public void onButtonClicked() throws Exception {
        presenter.onButtonClicked();
        Mockito.verify(view).showLoginView();
    }

    @Test
    public void displayWalkThrough() throws Exception {
        when(repository.getImages()).thenReturn(mockedImageList);
        presenter.getViewElements();
        presenter.displayWalkThrough();
        Mockito.verify(view).displayWalkthrough(mockedImageList);
    }

    @Test
    public void setupBottomButton() throws Exception {
        when(repository.getContinueButtonTitle()).thenReturn("Success");
        presenter.getViewElements();
        presenter.setupBottomButton();
        Mockito.verify(view).setupBottomButton("Success");
    }

    @Test
    public void displayBottomButton() throws Exception {
        boolean showBottom;

        showBottom = true;

        presenter.displayBottomButton(showBottom);
        Mockito.verify(view).showBottomButton();

        showBottom = false;
        presenter.displayBottomButton(showBottom);
        Mockito.verify(view).hideBottomButton();
    }

    @Test
    public void displayTitle() throws Exception {
        when(repository.getTitle()).thenReturn("Title");
        presenter.getViewElements();
        presenter.displayTitle();
        Mockito.verify(view).displayTitle(presenter.getTitle());
    }

    @Test
    public void setPageIndicator() throws Exception {
        int position = 2;
        presenter.setPageIndicator(position);
        Mockito.verify(view).setPageIndicator(position);
    }

}