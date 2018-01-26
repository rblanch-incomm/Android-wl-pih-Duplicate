package com.incomm.payithere.views.faqs;

import com.incomm.payithere.models.cms.FAQQuestionItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by agodambe on 9/21/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FaqsPresenterTest {

    @Mock
    FaqsMVP.View view;
    @Mock
    FaqsMVP.Repository repository;
    FaqsPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new FaqsPresenter(view,repository);
    }

    @Test
    public void passTitleToView() throws Exception {
        when(repository.getTitle()).thenReturn("title");
        presenter.getTitle();
        verify(view).setTitle("title");
    }

    @Test
    public void passEmptyStringIfTitleNullToView() throws Exception {
        when(repository.getTitle()).thenReturn(null);
        presenter.getTitle();
        verify(view).setTitle("");
    }

    @Test
    public void passQuestionsToView() throws Exception {
        when(repository.getQuestions()).thenReturn(Arrays.asList(new FAQQuestionItem(),new FAQQuestionItem()));
        presenter.getQuestions();
        verify(view).displayQuestions(Arrays.asList(new FAQQuestionItem(),new FAQQuestionItem()));
    }

    @Test
    public void passEmptyCollectionIfQuestionsEmptyToView() throws Exception {
        when(repository.getQuestions()).thenReturn(Collections.<FAQQuestionItem>emptyList());
        presenter.getQuestions();
        verify(view).displayNoQuestions();
    }

    @Test
    public void passRightChevronToView() throws Exception {
        when(repository.getRightChevron()).thenReturn("asx.png");
        assertEquals("asx.png",presenter.getRightChevron());
    }

    @Test
    public void passEmptyStringIfRightChevronToViewNull() throws Exception {
        when(repository.getRightChevron()).thenReturn(null);
        assertEquals("",presenter.getRightChevron());
    }

}