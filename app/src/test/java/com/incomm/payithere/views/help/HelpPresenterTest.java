package com.incomm.payithere.views.help;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by bjennings on 10/12/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class HelpPresenterTest {
    @Mock
    HelpMVP.View view;
    @Mock
    HelpMVP.Repository repository;
    @Mock
    GlobalImagesManager images;
    @Mock
    ColorSchemeManager colors;
    @Mock
    Feature f1;
    @Mock
    Feature f2;

    HelpPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new HelpPresenter(view, repository, images, colors);
    }

    @Test
    public void uiShouldBeSetup() {
        Feature[] f = {};
        List<Feature> features = Arrays.asList(f);

        when(repository.getFeatures()).thenReturn(features);
        when(images.getChevronRight()).thenReturn(null);
        when(colors.getGeneralText()).thenReturn("color");

        presenter = new HelpPresenter(view, repository, images, colors);
        presenter.getViewElements();

        verify(view).createRecyclerView(features, "color", null);
        verify(view).setupUi();
    }

    @Test
    public void confirmWithCorrectMessageBeforePlacingCall() {
        when(repository.getCallMessage()).thenReturn("callMessage");
        when(repository.getNegativeButton()).thenReturn("negative");
        when(repository.getPositivePhoneButton()).thenReturn("positivePhone");

        presenter.getViewElements();
        presenter.onPhoneClick();

        verify(view).displayCallConfirmation("callMessage", "positivePhone", "negative");
    }

    @Test
    public void makePhoneCallWithNumber() {
        when(repository.getPhoneNumber()).thenReturn("867-5309");

        presenter.getViewElements();
        presenter.confirmPhoneCall();

        verify(view).makePhoneCall("867-5309");
    }

    @Test
    public void displayMailingAddress() {
        when(repository.getMailingAddress()).thenReturn("mailingAddress");

        presenter.getViewElements();
        presenter.displayMailingAddress();

        verify(view).displayMailingAddress("mailingAddress");
    }

    @Test
    public void displayPhoneNumber() {
        when(repository.getPhoneNumber()).thenReturn("867-5309");

        presenter.getViewElements();
        presenter.displayPhoneNumber();

        verify(view).displayPhoneNumber("867-5309");
    }

    @Test
    public void setsFragmentTitle() {
        when(repository.getPhoneLabel()).thenReturn("phone label");
        when(repository.getAddressLabel()).thenReturn("address label");
        when(repository.getDeviceIdLabel()).thenReturn("device id");
        when(repository.getTitle()).thenReturn("title");

        presenter.setFragmentTitle();

        verify(view).setFragmentTitle("title");
    }

    @Test
    public void displaysPhoneLabel() {
        when(repository.getPhoneLabel()).thenReturn("phone label");
        when(repository.getAddressLabel()).thenReturn("address label");
        when(repository.getDeviceIdLabel()).thenReturn("device id");
        when(repository.getTitle()).thenReturn("title");

        presenter.displayCallLabel();

        verify(view).displayCallLabel("phone label");
    }

    @Test
    public void displaysMailLabel() {
        when(repository.getPhoneLabel()).thenReturn("phone label");
        when(repository.getAddressLabel()).thenReturn("address label");
        when(repository.getDeviceIdLabel()).thenReturn("device id");
        when(repository.getTitle()).thenReturn("title");

        presenter.displayMailLabel();

        verify(view).displayMailLabel("address label");
    }

    @Test
    public void displaysDeviceIdLabel() {
        when(repository.getPhoneLabel()).thenReturn("phone label");
        when(repository.getAddressLabel()).thenReturn("address label");
        when(repository.getDeviceIdLabel()).thenReturn("device id");
        when(repository.getTitle()).thenReturn("title");

        presenter.displayDeviceIdLabel();

        verify(view).displayDeviceIdLabel("device id");
    }

    @Test
    public void launchFragment2OnClick() {
        when(f1.getKey()).thenReturn("key1");
        when(f2.getKey()).thenReturn("key2");

        Feature[] f = {f1, f2};
        List<Feature> features = Arrays.asList(f);

        when(repository.getFeatures()).thenReturn(features);

        presenter = new HelpPresenter(view, repository, images, colors);
        presenter.onItemClicked(1);

        verify(view).launchFragment("key2");
    }

    @Test
    public void launchFragment1OnClick() {
        when(f1.getKey()).thenReturn("key1");
        when(f2.getKey()).thenReturn("key2");

        Feature[] f = {f1, f2};
        List<Feature> features = Arrays.asList(f);

        when(repository.getFeatures()).thenReturn(features);

        presenter = new HelpPresenter(view, repository, images, colors);
        presenter.onItemClicked(0);

        verify(view).launchFragment("key1");
    }
}