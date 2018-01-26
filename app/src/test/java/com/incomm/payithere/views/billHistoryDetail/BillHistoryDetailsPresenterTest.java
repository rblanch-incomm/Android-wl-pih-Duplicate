//package com.incomm.payithere.views.billHistoryDetail;
//
//import com.incomm.payithere.models.BillsHistoryItem;
//import com.incomm.payithere.models.services.response.Ereceipt;
//import com.incomm.payithere.models.services.response.PaymentWithEreceipt;
//import com.incomm.payithere.models.services.response.UserData;
//import com.incomm.payithere.services.PaymentDetailService;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * Created by bjennings on 10/13/2017.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class BillHistoryDetailsPresenterTest {
//    @Mock
//    BillHistoryDetailMVP.View view;
//    @Mock
//    BillsHistoryItem bill;
//    @Mock
//    PaymentDetailService service;
//    @Mock
//    PaymentWithEreceipt payment;
//    @Mock
//    Ereceipt ereceipt;
//    @Mock
//    UserData userData;
//    @Mock
//    BillHistoryDetailMVP.Repository repository;
//
//    BillHistoryDetailMVP.Presenter presenter;
//
//    @Before
//    public void setUp() throws Exception {
//        when(bill.getAmount()).thenReturn("1000000.0");
//        when(bill.getBillerName()).thenReturn("the world");
//        when(bill.getToken()).thenReturn("toke");
//        when(bill.getStatus()).thenReturn("processed");
//        when(bill.getTimestamp()).thenReturn(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2017-10-12T16:15:53Z"));
//
//        when(payment.getEreceipt()).thenReturn(ereceipt);
//        when(ereceipt.getTimestamp()).thenReturn("12/29/93 9:15 AM EDT");
//        when(payment.getUserData()).thenReturn(userData);
//        when(userData.getAccountNumber()).thenReturn("9");
//        when(payment.getFormattedAuthorizedAgentAddress()).thenReturn("1 Imaginary Lane");
//        when(payment.getAuthorizedAgent()).thenReturn("store");
//
//        presenter = new BillHistoryDetailsPresenter(view, bill, service, repository);
//    }
//
//    @Test
//    public void showFormattedNumber() throws Exception {
//        presenter.displayBillAmount();
//
//        verify(view).displayBillAmount("$1,000,000.00");
//    }
//
//    @Test
//    public void showBillerName() throws Exception {
//        presenter.displayBillerName();
//
//        verify(view).displayBillerName("the world");
//    }
//
//    @Test
//    public void showCreatedAt() throws Exception {
//        presenter.displayBillCreated();
//
//        verify(view).displayBillCreated("10/12/2017");
//    }
//
//    @Test
//    public void ereceiptButtonClickedShowsTodo() throws Exception {
//        //TODO update this test when button action is implemented
//        presenter.ereceiptButtonClicked();
//
//        verify(view).showTodoDialog();
//    }
//
//    @Test
//    public void newPaymentButtonClickedShowsTodo() throws Exception {
//        //TODO update this test when button action is implemented
//        presenter.newPaymentButtonClicked();
//
//        verify(view).showTodoDialog();
//    }
//
//    @Test
//    public void shouldDisplayProgressToSetup() throws Exception {
//        presenter.getViewElements();
//
//        verify(view).showProgressDialog();
//        verify(view).setupUi();
//        verify(service).getPaymentDetails("toke", presenter);
//    }
//
//    @Test
//    public void errorDialogOnServiceFail() throws Exception {
//        presenter.paymentDetailFailure("894");
//
//        verify(view).hideProgressDialog();
//        verify(view).showErrorDialog("894");
//    }
//
//    @Test
//    public void displayDataOnServiceSuccess() throws Exception {
//        presenter.paymentDetailSuccess(payment);
//
//        verify(view).hideProgressDialog();
//        verify(view).displayRetailerName("store");
//        verify(view).displayRetailerAddress("1 Imaginary Lane");
//        verify(view).displayUsedTime("12/29/93 at 9:15am");
//        verify(view).displayAccountNumber("9");
//    }
//}