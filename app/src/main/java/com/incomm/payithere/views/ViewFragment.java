package com.incomm.payithere.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.ErrorDisplayManager;
import com.incomm.payithere.models.cms.Error;

/**
 * Created by Jerome Davis on 9/4/17.
 */

public abstract class ViewFragment extends Fragment{

    public boolean isConnected() {

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void showProgressbar(ProgressDialog progressDialog) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Accessing");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void showFragmentWithBundle(Fragment fragment, Bundle bundle,int root,String tag,FragmentManager fragmentManager){
        fragment.setArguments(bundle);
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(root, fragment,tag);
        transaction.addToBackStack(tag);
        transaction.commit();

    }

    public void showFragmentWithOutBundle(Fragment fragment,int root,String tag,FragmentManager fragmentManager){
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(root, fragment,tag);
        transaction.addToBackStack(tag);
        transaction.commit();

    }

    public void hideSoftKeyboard(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void hideProgessbar(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    public void displayErrorAlert(String  errorCode) {

        Error error = ErrorDisplayManager.getInstance().getErrorMessage(errorCode);
        String errorMessage = error.getMessage();
        String dismissButtonTitle = error.getDismissButtonTitle();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(errorMessage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, dismissButtonTitle,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void displayErrorAlert(String  errorCode, String replacementFieldText, String condition) {

        Error error = ErrorDisplayManager.getInstance().getErrorMessage(errorCode);
        String errorMessage = error.getMessage();
        if (errorMessage.contains("$")) {
            errorMessage = errorMessage.replace("$", replacementFieldText);
        }
        String dismissButtonTitle = error.getDismissButtonTitle();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        if (!condition.equals("")) {
            errorMessage = errorMessage + " (" + condition + ")";
        }
        alertDialog.setMessage(errorMessage);


        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, dismissButtonTitle,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void displayAlert(String  message) {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void disableBack(){
        // Disable the back button for this fragment
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // Do nothing on back press
                    return true;
                }
                return false;
            }
        });
    }




}
