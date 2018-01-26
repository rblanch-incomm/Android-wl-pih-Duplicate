package com.incomm.payithere.views.billsNoHistory;

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
 * Created by Jerome Davis on 10/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NoBillerHistoryPresenterTest {

    @Mock
    private NoBillerHistoryMVP.View view;
    @Mock
    private NoBillerHistoryMVP.NoBillerHistoryRepository repository;
    private NoBillerHistoryPresenter presenter;

    List<String> mockedStepsList = mock(List.class);

    @Before
    public void setup() {
        presenter = new NoBillerHistoryPresenter(view, repository);
        presenter.getViewElements();
    }

    @Test
    public void getViewElements() throws Exception {
        when(repository.getTitle()).thenReturn("Success");
        when(repository.getChannel()).thenReturn("Success");
        when(repository.getName()).thenReturn("Success");
        when(repository.getBodyText()).thenReturn("Success");
        when(repository.getCreateSlipButtonTitle()).thenReturn("Success");
        when(repository.getInstructionsHeaderText()).thenReturn("Success");
        when(repository.getStepsText()).thenReturn(mockedStepsList);
    }

    @Test
    public void getNameShouldNotBeNull() throws Exception {
        when(repository.getName()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getName().getClass().equals(String.class));
    }

    @Test
    public void getTitle() throws Exception {
        when(repository.getTitle()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getTitle().getClass().equals(String.class));
    }

    @Test
    public void getChannel() throws Exception {
        when(repository.getChannel()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getChannel().getClass().equals(String.class));
    }

    @Test
    public void getBodyText() throws Exception {
        when(repository.getBodyText()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getBodyText().getClass().equals(String.class));
    }

    @Test
    public void getInstructionsHeaderText() throws Exception {
        when(repository.getInstructionsHeaderText()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getInstructionsHeaderText().getClass().equals(String.class));
    }

    @Test
    public void getCreateSlipButtonTitle() throws Exception {
        when(repository.getCreateSlipButtonTitle()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getCreateSlipButtonTitle().getClass().equals(String.class));
    }

    @Test
    public void getStepsText() throws Exception {
        when(repository.getStepsText()).thenReturn(null);
        presenter.getViewElements();
        assertTrue(presenter.getStepsText() instanceof List);
    }

    @Test
    public void displayBodyText() throws Exception {
        when(repository.getBodyText()).thenReturn("Success");
        presenter.getViewElements();
        presenter.displayBodyText();
        Mockito.verify(view).displayBodyText("Success");
    }

    @Test
    public void displayInstructionHeader() throws Exception {
        when(repository.getInstructionsHeaderText()).thenReturn("Success");
        presenter.getViewElements();
        presenter.displayInstructionHeader();
        Mockito.verify(view).displayInstructionHeader("Success");
    }

    @Test
    public void displayCreateSlipButtonTitle() throws Exception {
        when(repository.getCreateSlipButtonTitle()).thenReturn("Success");
        presenter.getViewElements();
        presenter.displayCreateSlipButtonTitle();
        Mockito.verify(view).displayCreateSlipButtonTitle("Success");
    }

    @Test
    public void displaySteps() throws Exception {
        when(repository.getStepsText()).thenReturn(mockedStepsList);
        presenter.getViewElements();
        presenter.displaySteps();
        Mockito.verify(view).displaySteps(mockedStepsList);
    }

    @Test
    public void displayTitle() throws Exception {
        when(repository.getTitle()).thenReturn("Success");
        presenter.getViewElements();
        presenter.displayTitle();
        Mockito.verify(view).displayTitle("Success");
    }

}