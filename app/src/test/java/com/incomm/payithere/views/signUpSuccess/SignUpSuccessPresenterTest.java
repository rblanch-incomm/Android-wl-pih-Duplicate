package com.incomm.payithere.views.signUpSuccess;

import com.incomm.payithere.repositories.SignUpSuccessCMSRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by agodambe on 9/14/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpSuccessPresenterTest {
    @Mock
    SignUpSucessMVP.Repository signUpSuccessRepository;
    @Mock
    SignUpSucessMVP.View view;
    SignUpSuccessPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SignUpSuccessPresenter(view, signUpSuccessRepository);
    }

    @Test public void shouldPassBodyTextToView(){
        Mockito.when(signUpSuccessRepository.getBodyText()).thenReturn("Success");
        presenter.getBodyText();
        Mockito.verify(view).displayBodyText("Success");
    }

    @Test public void shouldPassEmptyStringIfNullBodyTextToView(){
        Mockito.when(signUpSuccessRepository.getBodyText()).thenReturn(null);
        presenter.getBodyText();
        Mockito.verify(view).displayBodyText("");
    }

    @Test public void shouldPassTitleToView(){
        Mockito.when(signUpSuccessRepository.getTitle()).thenReturn("Title");
        presenter.getTitle();
        Mockito.verify(view).displayTitle("Title");
    }

    @Test public void shouldPassEmptyStringIfNullTitleToView(){
        Mockito.when(signUpSuccessRepository.getBodyText()).thenReturn(null);
        presenter.getBodyText();
        Mockito.verify(view).displayBodyText("");
    }

    @Test public void shouldPassContinueTitleToView(){
        Mockito.when(signUpSuccessRepository.getContinueButton()).thenReturn("Button");
        presenter.getContinueButton();
        Mockito.verify(view).displayContinueButton("Button");
    }

    @Test public void shouldPassEmptyStingIfNullContinueTitleToView(){
        Mockito.when(signUpSuccessRepository.getContinueButton()).thenReturn(null);
        presenter.getContinueButton();
        Mockito.verify(view).displayContinueButton("");
    }


}