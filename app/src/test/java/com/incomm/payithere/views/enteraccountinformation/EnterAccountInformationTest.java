package com.incomm.payithere.views.enteraccountinformation;

import com.incomm.payithere.models.services.response.Form;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rblanch on 11/13/17.
 */

@RunWith(MockitoJUnitRunner.class)

public class EnterAccountInformationTest {
    @Mock
    private EnterAccountInformationMVP.Repository repository;
    @Mock
    private EnterAccountInformationMVP.View view;
    @Mock
    private EnterAccountInformationMVP.Service service;
    private EnterAccountInformationPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new EnterAccountInformationPresenter(view, repository, service);
    }

    @Test
    public void shouldPassFormFields() throws Exception {
        SpecificBillerInformation billerInformation = new SpecificBillerInformation();
        List<Form> formList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Form newField = new Form();
            newField.setLabel("test" + i);
            formList.add(newField);
        }
        billerInformation.setAnFormat(1);
        billerInformation.setForm(formList);
        presenter.OnEnterAccountInformationCallSuccess(billerInformation);
        verify(view).displayForm(formList);
    }

    @Test
    public void shouldPassNullIfNoFormFields() throws Exception {
        SpecificBillerInformation billerInformation = new SpecificBillerInformation();
        billerInformation.setForm(null);
        billerInformation.setAnFormat(1);
        presenter.OnEnterAccountInformationCallSuccess(billerInformation);
        verify(view).displayForm(null);
    }

    @Test
    public void shouldPassStringToDisplayTitle() throws Exception {
        String title = "Title";
        when(repository.getTitle()).thenReturn(title);
        presenter.displayTitle();
        verify(view).displayTitle(title);
    }

    @Test
    public void shouldPassEmptyStringToTitleIfNull() throws Exception {
        when(repository.getTitle()).thenReturn(null);
        presenter.displayTitle();
        verify(view).displayTitle("");
    }

    @Test
    public void shouldDisplayBillerNameForTitle() throws Exception {
        String title = "Title";
        when(repository.getTitle()).thenReturn(title);
        presenter.displayTitle();
        verify(view).displayTitle(title);
    }

    @Test
    public void shouldPassEmptyStringDisplayBillerNameForTitle() throws Exception {
        presenter.setBillerName(null);
        presenter.displayTitle();
        verify(view).displayTitle("");
    }

    @Test
    public void shouldDisplayInstructionsText() throws Exception {
        String text = "instructions";
        when(repository.getInstructionsText()).thenReturn(text);
        presenter.displayInstructionsText();
        verify(view).displayInstructions(text);
    }

    @Test
    public void shouldPassEmptyStringToInstructionsText() throws Exception {
        when(repository.getInstructionsText()).thenReturn(null);
        presenter.displayInstructionsText();
        verify(view).displayInstructions("");
    }

    @Test
    public void shouldDisplaySubmitButton() throws Exception {
        String text = "submit";
        when(repository.getSubmitButtonTitle()).thenReturn(text);
        presenter.displaySubmitButton();
        verify(view).displaySubmitButton(text);
    }

    @Test
    public void shouldPassEmptyStringToSubmitButton() throws Exception {
        when(repository.getSubmitButtonTitle()).thenReturn(null);
        presenter.displaySubmitButton();
        verify(view).displaySubmitButton("");
    }
}
