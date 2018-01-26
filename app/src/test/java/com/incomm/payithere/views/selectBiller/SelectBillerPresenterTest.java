package com.incomm.payithere.views.selectBiller;

import com.incomm.payithere.models.services.response.BillerGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by agodambe on 10/27/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectBillerPresenterTest {

    @Mock
    SelectBillerMVP.View view;
    @Mock
    SelectBillerMVP.Repository repository;
    @Mock
    SelectBillerMVP.Service service;
    SelectBillerPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new SelectBillerPresenter(view,repository,service);
    }

    @Test public void shouldPassTitleToView(){
        Mockito.when(repository.getTitle()).thenReturn("Title");
        presenter.setTitle();
        Mockito.verify(view).displayTitle("Title");
    }

    @Test public void shouldPassEmptyStringIfNullTitleToView(){
        Mockito.when(repository.getTitle()).thenReturn(null);
        presenter.setTitle();
        Mockito.verify(view).displayTitle("");
    }


    @Test
    public void showBillerListWhenNotEmpty() throws Exception {
        List<BillerGroup> billers = Arrays.asList(new BillerGroup(),new BillerGroup());
        presenter.OnSingleCategoryCallSuccess(billers);
        Mockito.verify(view).showBillersList(billers);

    }

    @Test
    public void showNotFoundWhenListEmpty() throws Exception {
        presenter.OnSingleCategoryCallSuccess(Collections.EMPTY_LIST);
        Mockito.verify(view).showWhenListEmpty();
    }

    @Test
    public void onSingleCategoryCallError() throws Exception {
        presenter.OnSingleCategoryCallError("30303");
        Mockito.verify(view).showError("30303");
    }

}