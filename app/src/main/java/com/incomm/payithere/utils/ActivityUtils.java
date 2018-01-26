package com.incomm.payithere.utils;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.contentful.vault.Asset;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Jerome Davis on 3/22/17.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

    public static boolean validatePassword(String newPassword, String newPassword2) {

        Pattern pattern;
        Matcher newPassmatcher;


        //String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}$";
        String passwordPattern = "^{8,50}$";
        pattern = Pattern.compile(passwordPattern);
        newPassmatcher = pattern.matcher(newPassword);

        if ( newPassword.equals(newPassword2) && newPassmatcher.matches()) {
            return true;
        }else {
            return false;
        }

    }

//    public static void showOKDialog(Context context, int message){
//        AlertDialog dialog = DialogFactory.createConfirmAlertDialog(context,context.getString(message),
//                "OK",null,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                    }});
//
//        dialog.show();
//    }
//
//    public static void showOKDialog(Context context, String message){
//        AlertDialog dialog = DialogFactory.createConfirmAlertDialog(context,message,
//                "OK",null,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                    }});
//
//        dialog.show();
//    }

//    public static Fragment getFragment(String identifier){
//        Fragment fragment;
//        switch(identifier){
//            case "Important Information":
//                fragment = new ImportantInformationFragment();
//                break;
//            case "Linkout Feature":
//                fragment = new LinkOutFragment();
//                break;
//            case "Fees & Limits":
//                fragment = new Fragment();
//                break;
//            case "Load Checks":
//                fragment = new Fragment();
//                break;
//            case "Account Dashboard":
//                fragment = new AccountsFragmentRoot();
//                break;
//            case "transfers":
//                fragment = new TransferFragmentRoot();
//                break;
//            case "Reload Locations":
//                fragment = new LocationsFragment();
//                break;
//            case "More":
//                fragment = new MoreFragmentRoot();
//                break;
//            case "About":
//                fragment = new Fragment();
//                break;
//            case "Contact":
//                fragment = new Fragment();
//                break;
//            case "Spending History":
//                fragment = new TransactionsFragment();
//                break;
//            case "Gettings Started":
//                fragment = new Fragment();
//                break;
//            case "FAQ":
//                fragment = new Fragment();
//                break;
//            case "Change Password":
//                fragment = new ChangePasswordUpdateFragment();
//                //fragment = new ChangePasswordSuccessFragment();
//                break;
//            case "Alerts":
//                fragment = new AlertSettingsFragment();
//                break;
//            case "Section":
//                fragment = new FeatureSectionFragment();
//                break;
//            default:
//                fragment = new Fragment();
//        }
//
//        return fragment;
//    }

    /**
        /**
     * Method to recognize numerical data in a string and convert it into a new string, formatted
     * for MONEY, formatted with a comma (if <= 999,999), and enforces two-digit cents,
     * with NO-Round-off:  OUTPUT: "$[-]#,###.##"
     *
     * @param sInput
     * @return String containing the CLEAN'ed version of this string input
     * and it is formatted correctly. Examples are:
     * "0" ==> "$0.00"
     * "1.0" ==> "$1.00"
     * "-4." ==> "$-4.00"
     * "1.01 " ===> "$1.01"
     */
    public static String strCleanMoneyValueString(String sInput) {
        BigDecimal money = new BigDecimal(sInput.replaceAll(",", ""));
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setCurrency(Currency.getInstance(Locale.US));
        return format.format(money);
    }

    private static boolean isNumber(char integerChar) {
        return integerChar >= '0' && integerChar <= '9';
    }

    private static int skipBlanks(String cleanString, int i) {
        while (i < cleanString.length() && isIgnorable(cleanString.charAt(i))) ++i;

        return i;
    }

    private static boolean isIgnorable(char c) {
        return c <= 32;
    }

    public static CharSequence convertToMMDDYYYY(String date) {
        String[] splitDate = date.split("-");
        if (splitDate.length == 3) {
            return splitDate[1] + "/" + splitDate[2] + "/" + splitDate[0];
        } else {
            return date;
        }
    }

    public static String convertToMMDDYYYY1(String dateString) {
        String[] splitDate = dateString.split("T");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        String convertedDate= "";
        try {
            date = df.parse(splitDate[0]);
            convertedDate = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "00-00-0000";
        }
        return convertedDate;
    }

    public static Integer checkNull(Integer checkedValue) {
        Integer value = 0;
        if (checkedValue != null) {
            value = checkedValue;
        }

        return value;
    }

    public static String checkNull(String checkedValue) {
        String value = "";
        if (checkedValue != null) {
            value = checkedValue;
        }

        return value;
    }

    public static String checkColorNull(String checkedValue) {
        String value = "#000000";
        if (checkedValue != null) {
            value = checkedValue;
        }

        return value;
    }

    public static List checkNull(List list) {
        List assetList = null;
        if (list != null) {
            assetList = list;
        } else {
            assetList = new ArrayList<>();
        }
        return assetList;

    }

//    public static List<String> checkNull(List<String> list) {
//        List<String> assetList = null;
//        if (list != null) {
//            assetList = list;
//        } else {
//            assetList = new ArrayList<>();
//        }
//        return assetList;
//
//    }

    public static String checkNull(Asset checkedAsset) {
        String url = "";
        if (checkedAsset != null) {
            url = checkedAsset.url();
        }

        return url;
    }

    public static Date determineExpirationDateBillerHistory(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    public static String determineExpirationDateBarcodeSlip(String date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy hh:mm a z");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 30);
        SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yy");
        String output = originalFormat.format(calendar.getTime());
        return checkNull(output);
    }

}
