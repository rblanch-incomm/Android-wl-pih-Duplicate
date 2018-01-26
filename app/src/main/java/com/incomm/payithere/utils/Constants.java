package com.incomm.payithere.utils;

/**
 * Created by Jerome Davis on 3/13/17.
 */

public interface Constants {

    //urls
    String BASE_URL = "https://api.sandbox.payithere.com";
    String LOCATION_URL = "https://testapi.cashtie.com";
    // Shared Preference Keys
    String APP_NAME = "com.incomm.myvanilla";
    String IS_FIRST_LAUNCH = "is_first_launch";
    String IS_LEGAL_ACCEPTED = "is_legal_accepted";
    String IS_REMEMBER_LOGIN = "is_remember_login";
    String REMEBERED_USERNAME = "remembered_username";
    String IS_FIRST_TIME_LOGIN = "is_first_time_login";
    String IS_CALL_PHONE_NEVER_ASK_AGAIN = "is_call_phone_never_ask_again";
    String IS_TEXT_MESSAGE_AGREEMENT_ACCEPTED = "is_text_message_agreement_accepted";
    String IS_ACCESS_LOCATION_NEVER_ASK_AGAIN = "is_access_location_never_ask_again";
    String SHOW_LOCATIONS_PERMISSION = "show_locations_permission";
    String REFRESH_LOCATIONS_VIEW = "refresh_locations";
    String IS_FROM_TAB = "is_from_login";
    public static final int TYPE_SAVINGS     = 0;
    public static final int TYPE_SPENDING    = 1;
    public static final int TYPE_ILD_CALLING = 2;
    String FAQ_IDENTIFIER = "FAQ";
    String FAQ_CATEGORY_IDENTIFIER = "FaqCategory";
    String FAQ_QUESTION_ANSWER_IDENTIFIER = "FaqQuestionAnswer";
    String FORGOT_PASSWORD_IDENTIFIER = "Forgot Password";
    String MORE_IDENTIFIER = "More";
    String CHANGE_PASSWORD_IDENTIFIER = "Change Password";
    String SIGN_UP_IDENTIFIER = "Sign Up";
    String SIGN_UP_SUCCESS_IDENTIFIER = "Signup Success";
    String WALKTHROUGH_IDENTIFIER = "WalkTrough";

    String BILLER_HISTORY_IDENTIFIER = "Bill History";
    String CONTACT_IDENTIFIER = "Contact";
    String BILLER_NO_HISTORY_FRAGMENT = "No History fragment";
    String BILLER_HISTORY_DETAIL_IDENTIFIER = "Bill History Detail";
    String IMPORTANT_INFORMATION = "Important Information";
    String EDUCATIONAL_WALKTHROUGHS_IDENTIFIER = "Educational Walkthroughs";
    String BILLER_DASHBOARD_IDENTIFIER = "CreateSlip";
    String CREATE_PAYMENT_SLIP_FRAGMENT = "CreateSlip" ;
    String SELECT_BILLER_FRAGMENT = "billerListView";
    String BILLER_CATEGORY_FRAGMENT = "billerCategoryView" ;
    String CONFIRM_ACCOUNT_IDENTIFIER = "confirmAccountView";
    String ENTER_AMOUNT_IDENTIFIER = "enterAmountView";
    String SELECT_PAYMENT_FEE = "selectBillerFeeView";
    String ENTER_ACCOUNT_INFORMATION_FRAGMENT = "Enter Account Information";
    String BARCODE_SLIP_IDENTIFIER = "billPendingDetailView";
    String RELOAD_LOCATIONS_IDENTIFIER = "Reload Locations";
}
