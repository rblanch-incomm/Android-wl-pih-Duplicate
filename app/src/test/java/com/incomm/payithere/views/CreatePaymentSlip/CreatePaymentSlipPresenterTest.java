package com.incomm.payithere.views.CreatePaymentSlip;

import com.incomm.payithere.models.cms.TopBiller;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.services.BillerByNameService;
import com.incomm.payithere.views.signUp.SignUpPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by agodambe on 10/26/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreatePaymentSlipPresenterTest {

    @Mock
    CreatePaymentSlipMVP.View view;
    @Mock
    CreatePaymentSlipMVP.Repository repository;
    @Mock
    BillerByNameService service;
    CreatePaymentSlipPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new CreatePaymentSlipPresenter(view,repository,service);
    }

    @Test
    public void showTopBillers() throws Exception {
        List<TopBiller> topBillers = Arrays.asList(new TopBiller(),new TopBiller());
        when(repository.getTopBillers()).thenReturn(topBillers);
        presenter.getTopBillers();
        verify(view).displayTopBillers(topBillers);
    }

    @Test
    public void showBillersNotFoundIfTopBillersEmpty() throws Exception {
        when(repository.getTopBillers()).thenReturn(Collections.<TopBiller>emptyList());
        presenter.getTopBillers();
        verify(view).showNoBillersFound();
    }

    @Test
    public void showBillersWhenCallSuccess() throws Exception {
        List<BillerGroup> billers = Arrays.asList(new BillerGroup(),new BillerGroup());
        presenter.OnBillerByNameCallSuccess(billers);
        verify(view).displayBillerList(billers);
    }

    @Test
    public void showBillersNotFoundWhenEmpty() throws Exception {
        presenter.OnBillerByNameCallSuccess(Collections.<BillerGroup>emptyList());
        verify(view).showNoBillersFound();
    }

    @Test public void shouldPassTitleToView(){
        Mockito.when(repository.getTitle()).thenReturn("Title");
        presenter.getTitle();
        Mockito.verify(view).displayTitle("Title");
    }

    @Test public void shouldPassEmptyStringIfNullTitleToView(){
        Mockito.when(repository.getTitle()).thenReturn(null);
        presenter.getTitle();
        Mockito.verify(view).displayTitle("");
    }

    @Test
    public void passSearchBarPlaceholderToView() throws Exception {
        Mockito.when(repository.getSearchBarPlaceholder()).thenReturn("Search");
        presenter.getSearchBarPlaceholder();
        Mockito.verify(view).setSearchBarPlaceHolder("Search");
    }

    @Test
    public void passEmptyStringIfSearchPlaceholderToView() throws Exception {
        Mockito.when(repository.getSearchBarPlaceholder()).thenReturn(null);
        presenter.getSearchBarPlaceholder();
        Mockito.verify(view).setSearchBarPlaceHolder("");
    }

    @Test
    public void passCategoriesButtonTextToView() throws Exception {
        Mockito.when(repository.getCategoriesButtonText()).thenReturn("Search by billers");
        presenter.getCategoriesButtonText();
        Mockito.verify(view).setCategoryButtonText("Search by billers");
    }

    @Test
    public void passEmptyStringIfCategoriesButtonTextNull() throws Exception {
        Mockito.when(repository.getCategoriesButtonText()).thenReturn(null);
        presenter.getCategoriesButtonText();
        Mockito.verify(view).setCategoryButtonText("");
    }

}