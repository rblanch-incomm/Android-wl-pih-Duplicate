package com.incomm.payithere.views.selectpayment;

import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.Option;
import com.incomm.payithere.views.selectPayment.SelectPaymentMVP;
import com.incomm.payithere.views.selectPayment.SelectPaymentPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rblanch on 11/9/17.
 */

@RunWith(MockitoJUnitRunner.class)

public class SelectPaymentPresenterTest {

    @Mock
    SelectPaymentMVP.View view;
    @Mock
    SelectPaymentMVP.Repository repository;
    SelectPaymentPresenter presenter;
    BillerGroup testBiller;
    @Before
    public void setUp() throws Exception {
        presenter = new SelectPaymentPresenter(view,repository);
        testBiller = new BillerGroup();
    }

    @Test
    public void shouldDisplayPaymentType() throws Exception {
        List<Option> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Option newOption = new Option();
            options.add(newOption);
        }
        testBiller.setOptions(options);
        presenter.setBiller(testBiller);
        presenter.displayPaymentType();
        verify(view).displayPaymentType((ArrayList)options);
    }

    @Test
    public void shouldDisplayEmptyPaymentTypeIfNoOptions() throws Exception {
        List<Option> options = new ArrayList<>();
        testBiller.setOptions(options);
        presenter.setBiller(testBiller);
        presenter.displayPaymentType();
        verify(view).displayPaymentType((ArrayList)options);
    }

    @Test
    public void shouldDisplayEmptyPaymentTypeIfNull() throws Exception {
        testBiller.setOptions(null);
        presenter.setBiller(testBiller);
        presenter.displayPaymentType();
        verify(view).displayPaymentType(new ArrayList<Option>());
    }

    @Test
    public void shouldPassBillerNameForTitle() throws Exception {
        String title = "biller name";
        testBiller.setName(title);
        presenter.setBiller(testBiller);
        presenter.displayTitle();
        verify(view).displayTitle(title);
    }

    @Test
    public void shouldPassEmptyStringIfBillerNameForTitleNull() throws Exception{
        testBiller.setName(null);
        presenter.setBiller(testBiller);
        presenter.displayTitle();
        verify(view).displayTitle("");
    }
}
