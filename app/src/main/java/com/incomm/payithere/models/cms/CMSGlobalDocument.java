package com.incomm.payithere.models.cms;

import com.contentful.vault.Space;
import com.incomm.payithere.BuildConfig;

/**
 * Created by Jerome Davis on 3/3/17.
 */
// prod: bzduxajdjbwr  qa: ojkazz4eeyp1  qa1: 7nmsq9sgwg2k
@Space(
        dbVersion = 6,
        value = BuildConfig.CONTENFUL_API,
        models = {
                AboutView.class,
//                AccountDashboardView.class,
//                AlertsItem.class,
//                AlertsItemDetails.class,
//                AlertsView.class,
//                AppUpdate.class,
//                AppLinkOutView.class,
                BarcodeSlipView.class,
                BillerCategoryIcon.class,
                BillerCategoryView.class,
                BillsHistoryView.class,
                BillHistoryDetailView.class,
                ChangePasswordSuccessView.class,
                ChangePasswordView.class,
                ColorScheme.class,
                ConfirmAccountView.class,
                CreatePaymentSlipView.class,
                HelpView.class,
                Error.class,
                EducationalWalkthroughView.class,
                EducationalSlide.class,
                EnterAmountView.class,
                EnterAccountInformationView.class,
                FAQCategoryItem.class,
                FAQQuestionItem.class,
                FAQView.class,
                Feature.class,
                FeatureLegalDocument.class,
                FeatureLinkOut.class,
                FeatureSection.class,
                FeatureContextualHelp.class,
//                FeeItem.class,
//                FeesLimitsView.class,
//                FeesCategoryItem.class,
                ForgotCredentialsView.class,
                ForgotPasswordChallengeView.class,
                ForgotPasswordResetView.class,
                ForgotPasswordView.class,
                ForgotPasswordSuccessView.class,
                ForgotUsernameView.class,
                GettingStartedView.class,
                GlobalImages.class,
                ContextualHelpView.class,
                ImportantInformationView.class,
//                LegalAcceptanceView.class,
                LegalDocumentViewCopy.class,
                LegalDocumentViewImage.class,
                LegalDocumentViewLink.class,
//                LimitItem.class,
//                LimitsCategoryItem.class,
//                LinkOutView.class,
//                LoadChecksView.class,
                LoginView.class,
//                WelcomeView.class,
                MoreView.class,
                SelectBillerView.class,
                SelectPaymentFeeView.class,
                SignUpView.class,
                SignUpSuccessView.class,
                ReloadLocationsView.class,
//                SpendingHistoryView.class,
                TabNavigation.class,
                TopBiller.class,
                BillDashboardNoHistoryView.class
//                SMSLegalAcceptanceView.class
        },
        locales = {"en-US"}
)
public class CMSGlobalDocument {
}
