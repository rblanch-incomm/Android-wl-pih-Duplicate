package com.incomm.payithere.factories;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.about.AboutFragmentRoot;
import com.incomm.payithere.views.CreatePaymentSlip.CreatePaymentSlipFragmentRoot;
import com.incomm.payithere.views.billsHistory.BillerHistoryFragmentRoot;
import com.incomm.payithere.views.billsNoHistory.NoBillerHistoryFragmentRoot;
import com.incomm.payithere.views.changepassword.ChangePasswordFragmentRoot;
import com.incomm.payithere.views.educational.EducationalFragment;
import com.incomm.payithere.views.help.HelpFragmentRoot;
import com.incomm.payithere.views.documents.DocumentViewFragment;
import com.incomm.payithere.views.dummy.DummyFragment;
import com.incomm.payithere.views.faqs.FaqQuestionAnswerFragment;
import com.incomm.payithere.views.faqs.FaqsFragmentRoot;
import com.incomm.payithere.views.featuresection.FeatureSectionFragment;
import com.incomm.payithere.views.forgotpassword.ForgotPasswordFragmentRoot;
import com.incomm.payithere.views.importantinformation.ImportantInformationFragmentRoot;
import com.incomm.payithere.views.locations.LocationsFragmentRoot;
import com.incomm.payithere.views.more.MoreFragmentRoot;
import com.incomm.payithere.views.signUp.SignUpFragmentRoot;
import com.incomm.payithere.views.walkthrough.WalkThroughFragment;


/**
 * Created by jayma on 4/19/2017.
 */

public final class FragmentFactory implements Constants {

    private FragmentFactory(){}

    public static Fragment getFragment(String identifier){
        Fragment fragment;
        switch(identifier){
            case Constants.FAQ_IDENTIFIER:
                fragment = new FaqsFragmentRoot();
                break;
          /*  case Constants.FAQ_CATEGORY_IDENTIFIER:
                fragment = new FaqCategoryFragment();
                break;*/
            case Constants.FAQ_QUESTION_ANSWER_IDENTIFIER:
                fragment = new FaqQuestionAnswerFragment();
                break;
            case Constants.FORGOT_PASSWORD_IDENTIFIER:
                fragment = new ForgotPasswordFragmentRoot();
                break;
            case Constants.MORE_IDENTIFIER:
               fragment = new MoreFragmentRoot();
                //fragment = new Fragment();
                break;
            case Constants.CHANGE_PASSWORD_IDENTIFIER:
                fragment = new ChangePasswordFragmentRoot();
                break;
            case Constants.SIGN_UP_IDENTIFIER:
                fragment = new SignUpFragmentRoot();
                break;
            case Constants.BILLER_HISTORY_IDENTIFIER:
                fragment = new BillerHistoryFragmentRoot();
                break;
            case Constants.BILLER_NO_HISTORY_FRAGMENT:
                fragment = new NoBillerHistoryFragmentRoot();
                break;
            case Constants.BILLER_DASHBOARD_IDENTIFIER:
                fragment = new CreatePaymentSlipFragmentRoot();
                break;
            case "Important Information":
                fragment = new ImportantInformationFragmentRoot();
                break;
            case Constants.RELOAD_LOCATIONS_IDENTIFIER:
                fragment = new LocationsFragmentRoot();
                break;
            /*case "Linkout Feature":
                fragment = new LinkoutFragmentRoot();
                break;
            case "Fees & Limits":
                fragment = new FeesLimitsFragmentRoot();
                break;
            case "FeesLimitsItem":
                fragment = new FeesLimitsItemFragment();
                break;
            case "Load Checks":
                fragment = new LoadChecksFragmentRoot();
                break;
            case "Account Dashboard":
                fragment = new AccountsFragmentRoot();
                break;
            case "transfers":
                fragment = new TransferFragmentRoot();
                break;
            case "Reload Locations":
                fragment = new LocationsFragmentRoot();
                break;
            case "Spending History":
                fragment = new TransactionsFragment();
                break;
            case "Gettings Started":
                fragment = GettingStartedFragmentWalkThrough.newInstance(false);
                break;
            case "Alerts":
                fragment = new AlertSettingsFragment();
                break;
            */
            case "Educational Walkthroughs":
                fragment = new  EducationalFragment();
                break;
            case "Gettings Started":
                fragment = WalkThroughFragment.newInstance(false);
                break;
            case "Legal Document":
                fragment = new DocumentViewFragment();
                break;
            case "About":
                fragment = new AboutFragmentRoot();
                break;
            case "Contact":
                fragment = new HelpFragmentRoot();
                break;
            case "Section":
                fragment = new FeatureSectionFragment();
                break;
            default:
                fragment = new DummyFragment();
                break;
        }
        return fragment;
    }

   public static Fragment getFragmentWithParcelable(String identifier, String parcelableKey, Parcelable parcelableObject){
       Fragment fragment = getFragment(identifier);
       Bundle bundle = new Bundle();
       bundle.putParcelable(parcelableKey, parcelableObject);
       fragment.setArguments(bundle);
       return fragment;
    }
}
