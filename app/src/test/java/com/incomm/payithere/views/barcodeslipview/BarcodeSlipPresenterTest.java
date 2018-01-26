package com.incomm.payithere.views.barcodeslipview;

import com.incomm.payithere.views.barcodeSlip.BarcodeSlipMVP;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipPresenter;
import com.incomm.payithere.views.billerCategory.BillerCategoryMVP;
import com.incomm.payithere.views.billerCategory.BillerCategoryPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by agodambe on 10/26/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BarcodeSlipPresenterTest {

    @Mock
    BarcodeSlipMVP.View view;
    @Mock
    BarcodeSlipMVP.Repository repository;
    @Mock
    BarcodeSlipMVP.Service service;
    BarcodeSlipPresenter presenter;
    

    @Before
    public void setUp() throws Exception {
        presenter = new BarcodeSlipPresenter(view,repository, service);
    }

    @Test
    public void passTitleToView() throws Exception {
        String accountLabel = "label";
        String accountName = "Comcasting Cables and Satellites";
        String accountNumber = "12345";
        presenter.setAccountNumber(accountNumber);
        presenter.setAccountName(accountName);
        Mockito.when(repository.getAccountLabel()).thenReturn(accountLabel);
        presenter.setTitle();
        Mockito.verify(view).displayTitle(accountName, accountLabel + " " + accountNumber);
    }

    @Test
    public void passSaveLaterBtnToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getSaveLaterBtn()).thenReturn(text);
        presenter.getSaveLaterBtn();
        Mockito.verify(view).displaySaveLaterBtn(text);
    }

    @Test
    public void passPaymentAmountLabelToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getPaymentAmountLabel()).thenReturn(text);
        presenter.getPaymentAmountLabel();
        Mockito.verify(view).displayPaymentAmountLabel(text);
    }

    @Test
    public void passFeeLabelToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getFeeLabel()).thenReturn(text);
        presenter.getFeeLabel();
        Mockito.verify(view).displayFeeLabel(text);
    }

    @Test
    public void passTotalLabelToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getTotalLabel()).thenReturn(text);
        presenter.getTotalLabel();
        Mockito.verify(view).displayTotalLabel(text);
    }

    @Test
    public void passBillDueLabelToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getBillDuelLabel()).thenReturn(text);
        presenter.getBillDuelLabel();
        Mockito.verify(view).displayBillDuelLabel(text);
    }

    @Test
    public void passSlipExpiresLabelToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getSlipExipresLabel()).thenReturn(text);
        presenter.getSlipExipresLabel();
        Mockito.verify(view).displaySlipExipresLabel(text);
    }

    @Test
    public void passScanBarCodeTextToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getScanBarCodeText()).thenReturn(text);
        presenter.getScanBarCodeText();
        Mockito.verify(view).displayScanBarCodeText(text);
    }

    @Test
    public void passTermsAndConditionsToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getTermsAndConditions()).thenReturn(text);
        presenter.getTermsAndConditions();
        Mockito.verify(view).displayTermsAndConditions(text);
    }

    @Test
    public void passPaymentLocationsBtnToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getPaymentLocationsBtn()).thenReturn(text);
        presenter.getPaymentLocationsBtn();
        Mockito.verify(view).displayPaymentLocationsBtn(text);
    }

    @Test
    public void passEmailPaymentSlipBtnToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getEmailPaymentSlipBtn()).thenReturn(text);
        presenter.getEmailPaymentSlipBtn();
        Mockito.verify(view).displayEmailPaymentSlipBtn(text);
    }

    @Test
    public void passVanillaLogoToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getVanillaLogo()).thenReturn(text);
        presenter.getVanillaLogo();
        Mockito.verify(view).displayVanillaLogo(text);
    }

    @Test
    public void passInstructionsToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getInstructionSteps()).thenReturn(text);
        presenter.getInstructions();
        Mockito.verify(view).displayInstructions(text);
    }

    @Test
    public void passInstructionsFooterToView() throws Exception {
        String text = "test text";
        Mockito.when(repository.getInstructionFooterInformation()).thenReturn(text);
        presenter.getInstructionFooter();
        Mockito.verify(view).displayInstructionFooterInformation(text);
    }

}