package com.incomm.payithere.factories;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by jayma on 4/12/2017.
 */

public final class DialogFactory {

    private DialogFactory(){ }

    public static AlertDialog createConfirmAlertDialog(Context context, String appMessage, String positiveButtonTitle, String negativeButtonTitle, DialogInterface.OnClickListener dialogInterfaceOnClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setPositiveButton(positiveButtonTitle, dialogInterfaceOnClickListener );
        builder.setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage(appMessage);

        return builder.create();
    }

    public static ProgressDialog createProgressDialog(Context context, String message){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }

    public static AlertDialog createOkAlertDialog(Context context, String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return alertDialog;
    }

    public static AlertDialog createOkAlertDialog(Context context, String title, String message, String buttonTitle){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, buttonTitle,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return alertDialog;
    }

    public static AlertDialog createOkAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        return alertDialog;
    }
}
