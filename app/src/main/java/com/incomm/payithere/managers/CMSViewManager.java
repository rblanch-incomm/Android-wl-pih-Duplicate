package com.incomm.payithere.managers;

import android.icu.util.ValueIterator;

import com.incomm.payithere.factories.TitleFactory;
import com.incomm.payithere.models.cms.AboutView;
import com.incomm.payithere.models.cms.BarcodeSlipView;
import com.incomm.payithere.models.cms.BillDashboardNoHistoryView;
import com.incomm.payithere.models.cms.BillHistoryDetailView;
import com.incomm.payithere.models.cms.BillerCategoryView;
import com.incomm.payithere.models.cms.BillsHistoryView;
import com.incomm.payithere.models.cms.ChangePasswordView;
import com.incomm.payithere.models.cms.ConfirmAccountView;
import com.incomm.payithere.models.cms.EducationalWalkthroughView;
import com.incomm.payithere.models.cms.CreatePaymentSlipView;
import com.incomm.payithere.models.cms.EnterAmountView;
import com.incomm.payithere.models.cms.EnterAccountInformationView;
import com.incomm.payithere.models.cms.FAQView;
import com.incomm.payithere.models.cms.FAQView$Fields;
import com.incomm.payithere.models.cms.ForgotPasswordView;
import com.incomm.payithere.models.cms.GettingStartedView;
import com.incomm.payithere.models.cms.GettingStartedView$Fields;
import com.incomm.payithere.models.cms.HelpView;
import com.incomm.payithere.models.cms.ImportantInformationView;
import com.incomm.payithere.models.cms.ImportantInformationView$Fields;
import com.incomm.payithere.models.cms.MoreView;
import com.incomm.payithere.models.cms.ReloadLocationsView;
import com.incomm.payithere.models.cms.SelectBillerView;
import com.incomm.payithere.models.cms.SelectPaymentFeeView;
import com.incomm.payithere.models.cms.SignUpSuccessView;
import com.incomm.payithere.models.cms.SignUpView;
import com.incomm.payithere.utils.Constants;


/**
 * Created by jayma on 4/6/2017.
 */

public class CMSViewManager implements BaseDocumentsManager,Constants {

    private static CMSViewManager instance;

    private AboutView aboutView;
    private FAQView faqView;
    private MoreView moreView;
    private SignUpView signUpView;
    private SignUpSuccessView signUpSuccessView;
    private HelpView helpView;
    private BillDashboardNoHistoryView billDashboardNoHistoryView;

    public static CMSViewManager getInstance() {
        if(null == instance) {
            instance = new CMSViewManager();
        }

        return instance;
    }
    public CMSViewManager(){
    }

    @Override
    public void setup() { }


    //all of the Getters reference the View object
    public FAQView getFaqView(){
        String channel = FAQView$Fields.CHANNEL;
        faqView = (FAQView) DocumentsManager.getInstance().getCmsResourceWhereChannel(FAQView.class, channel);
        TitleFactory.getInstance().putTitle(Constants.FAQ_IDENTIFIER,faqView.getTitle());
        return faqView;
    }

    public MoreView getMoreView(){
        moreView = (MoreView)DocumentsManager.getInstance().getCmsResource(MoreView.class);
        TitleFactory.getInstance().putTitle(Constants.MORE_IDENTIFIER,moreView.getTitle());
        return moreView;
    }

    public HelpView getHelpView() {
        helpView = (HelpView) DocumentsManager.getInstance().getCmsResource(HelpView.class);
        TitleFactory.getInstance().putTitle(Constants.CONTACT_IDENTIFIER, helpView.getTitle());
        return helpView;
    }

    public SignUpView getSignUpView(){
        signUpView = (SignUpView) DocumentsManager.getInstance().getCmsResource(SignUpView.class);
        TitleFactory.getInstance().putTitle(Constants.SIGN_UP_IDENTIFIER,signUpView.getTitle());
        return signUpView;
    }

    public SignUpSuccessView getSignUpSuccessView(){
        signUpSuccessView = (SignUpSuccessView) DocumentsManager.getInstance().getCmsResource(SignUpSuccessView.class);
        TitleFactory.getInstance().putTitle(Constants.SIGN_UP_SUCCESS_IDENTIFIER,signUpSuccessView.getTitle());
        return signUpSuccessView;
    }

    public ChangePasswordView getChangePasswordView(){
        ChangePasswordView changePasswordView;
        changePasswordView = (ChangePasswordView) DocumentsManager.getInstance().getCmsResource(ChangePasswordView.class);
        TitleFactory.getInstance().putTitle(Constants.CHANGE_PASSWORD_IDENTIFIER,changePasswordView.getTitle());
        return changePasswordView;
    }

    public BillsHistoryView getBillsHistoryView(){
        BillsHistoryView billsHistoryView;
        billsHistoryView = (BillsHistoryView) DocumentsManager.getInstance().getCmsResource(BillsHistoryView.class);
        TitleFactory.getInstance().putTitle(Constants.BILLER_HISTORY_IDENTIFIER,billsHistoryView.getTitle());
        return billsHistoryView;
    }

    public BillHistoryDetailView getBillHistoryDetailView() {
        BillHistoryDetailView view;
        view = (BillHistoryDetailView) DocumentsManager.getInstance().getCmsResource(BillHistoryDetailView.class);
        TitleFactory.getInstance().putTitle(Constants.BILLER_HISTORY_DETAIL_IDENTIFIER, view.getTitle());
        return view;
    }

    public GettingStartedView getWalkThroughView() {
        String channelField = GettingStartedView$Fields.CHANNEL;

        GettingStartedView gettingStartedView;
        gettingStartedView = (GettingStartedView) DocumentsManager.getInstance()
                .getCmsResourceWhereChannel(GettingStartedView.class, channelField);

        TitleFactory.getInstance().putTitle(Constants.WALKTHROUGH_IDENTIFIER, gettingStartedView.getTitle());

        return gettingStartedView;
    }

    public ForgotPasswordView getForgotPasswordView() {
        ForgotPasswordView forgotPasswordView;
        forgotPasswordView = (ForgotPasswordView) DocumentsManager.getInstance().getCmsResource(ForgotPasswordView.class);
        TitleFactory.getInstance().putTitle(Constants.FORGOT_PASSWORD_IDENTIFIER,forgotPasswordView.getTitle());
        return forgotPasswordView;
    }

    public BillDashboardNoHistoryView getBillDashboardNoHistoryView() {
        billDashboardNoHistoryView = (BillDashboardNoHistoryView) DocumentsManager.getInstance()
                .getCmsResource(BillDashboardNoHistoryView.class);
        TitleFactory.getInstance().putTitle(Constants.BILLER_NO_HISTORY_FRAGMENT, billDashboardNoHistoryView.getTitle());
        return billDashboardNoHistoryView;
    }

    public ImportantInformationView getImportantInformationView() {
        String channel = ImportantInformationView$Fields.CHANNEL;
        ImportantInformationView view;
        view = (ImportantInformationView) DocumentsManager.getInstance()
                .getCmsResourceWhereChannel(ImportantInformationView.class, channel);
        TitleFactory.getInstance().putTitle(Constants.IMPORTANT_INFORMATION, view.getTitle());
        return view;
    }

    public EducationalWalkthroughView getEducationalWalkThroughs() {
        EducationalWalkthroughView educationalWalkthroughView = (EducationalWalkthroughView)
                DocumentsManager.getInstance().getCmsResource(EducationalWalkthroughView.class);
        TitleFactory.getInstance().putTitle(Constants.EDUCATIONAL_WALKTHROUGHS_IDENTIFIER, educationalWalkthroughView.getTitle());
        return educationalWalkthroughView;
    }

    public CreatePaymentSlipView getCreatePaymentSlipView() {
        CreatePaymentSlipView view = (CreatePaymentSlipView) DocumentsManager.getInstance()
                .getCmsResource(CreatePaymentSlipView.class);
        TitleFactory.getInstance().putTitle(Constants.CREATE_PAYMENT_SLIP_FRAGMENT,view.getTitle());
        return view;
    }

    public SelectBillerView getSelectBillerView() {
        SelectBillerView view = (SelectBillerView) DocumentsManager.getInstance()
                .getCmsResource(SelectBillerView.class);
        TitleFactory.getInstance().putTitle(Constants.SELECT_BILLER_FRAGMENT,view.getTitle());
        return view;
    }

    public BillerCategoryView getBillerCategoryView(){
        BillerCategoryView view = (BillerCategoryView) DocumentsManager.getInstance()
                .getCmsResource(BillerCategoryView.class);
        TitleFactory.getInstance().putTitle(Constants.BILLER_CATEGORY_FRAGMENT,view.getTitle());
        return view;
    }

    public ConfirmAccountView getConfirmAccountView(){
        ConfirmAccountView view = (ConfirmAccountView) DocumentsManager.getInstance()
                .getCmsResource(ConfirmAccountView.class);
        TitleFactory.getInstance().putTitle(Constants.CONFIRM_ACCOUNT_IDENTIFIER,view.getTitle());
        return view;
    }

    public EnterAmountView getEnterAmountView(){
        EnterAmountView view = (EnterAmountView) DocumentsManager.getInstance()
                .getCmsResource(EnterAmountView.class);
        TitleFactory.getInstance().putTitle(Constants.ENTER_AMOUNT_IDENTIFIER,view.getTitle());
        return view;
    }

    public SelectPaymentFeeView getSelectPaymentView() {
        SelectPaymentFeeView view = (SelectPaymentFeeView) DocumentsManager.getInstance()
                .getCmsResource(SelectPaymentFeeView.class);
        TitleFactory.getInstance().putTitle(Constants.SELECT_PAYMENT_FEE, view.getTitle());
        return view;
    }

    public EnterAccountInformationView getEnterAccountInformationView() {
        EnterAccountInformationView view = (EnterAccountInformationView) DocumentsManager.getInstance()
                .getCmsResource(EnterAccountInformationView.class);
        TitleFactory.getInstance().putTitle(Constants.ENTER_ACCOUNT_INFORMATION_FRAGMENT, view.getTitle());
        return view;
    }

    public BarcodeSlipView getBarcodeSlipView(){
        BarcodeSlipView view = (BarcodeSlipView) DocumentsManager.getInstance().getCmsResource(BarcodeSlipView.class);
        TitleFactory.getInstance().putTitle(Constants.BARCODE_SLIP_IDENTIFIER, view.getTitle());
        return view;
    }

    public ReloadLocationsView getLocationsView() {
        ReloadLocationsView view = (ReloadLocationsView) DocumentsManager.getInstance().getCmsResource(ReloadLocationsView.class);
        TitleFactory.getInstance().putTitle(Constants.RELOAD_LOCATIONS_IDENTIFIER, view.getTitle());
        return view;
    }
}
