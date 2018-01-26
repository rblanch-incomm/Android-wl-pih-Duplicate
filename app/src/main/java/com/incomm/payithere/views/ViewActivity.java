package com.incomm.payithere.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.ErrorDisplayManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.ContextualHelpView;
import com.incomm.payithere.models.cms.Error;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Jerome Davis on 9/4/17.
 */

public abstract class ViewActivity extends AppCompatActivity {

    public void requestLogout(boolean displayWarning) {

    }

    public void setActionbarBackground(final RelativeLayout toolbarTitleContainer) {
        String imageUrl = GlobalImagesManager.getInstance().getNavigationBar().url();
        Picasso.with(this).load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                BitmapDrawable d = new BitmapDrawable(getResources(), bitmap);
                toolbarTitleContainer.setBackground(d);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public void setActionbarBackground(final AppBarLayout toolbarTitleContainer) {
        String imageUrl = GlobalImagesManager.getInstance().getNavigationBar().url();
        Picasso.with(this).load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                BitmapDrawable d = new BitmapDrawable(getResources(), bitmap);
                toolbarTitleContainer.setBackground(d);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public boolean isConnected() {

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void hideSoftKeyboard(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void displayErrorAlert(String  errorCode) {

        final Error error = ErrorDisplayManager.getInstance().getErrorMessage(errorCode);

        String errorMessage = "Network is offline. Unable to complete request.";
        String dismissButtonTitle = "OK";

        if (error != null) {
            errorMessage = error.getMessage();
            dismissButtonTitle = error.getDismissButtonTitle();
        }

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(errorMessage);
        alertDialog.setNeutralButton(dismissButtonTitle,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (error == null) {
                            finish();
                        }
                    }
                });
        alertDialog.show();
    }

    public void showContextualHelp(Activity activity, FeatureContextualHelp contextualHelpView) {
        LayoutInflater inflater = getLayoutInflater();
        ContextualHelpView contextualHelp = contextualHelpView.getHelpViewAndroid();

        View view = inflater.inflate(R.layout.dialog_layout, null);

        TextView titleLabel = (TextView) view.findViewById(R.id.help_title_tv);
        titleLabel.setText(contextualHelp.getTitle());

        TextView bodyText = (TextView) view.findViewById(R.id.help_body_text_tv);
        bodyText.setText(contextualHelp.getBodyText());

        ImageView helperImageView = (ImageView) view.findViewById(R.id.help_image_iv);
        if (contextualHelp.getHelpImage() != null && !(contextualHelp.getHelpImage().url().equals(""))) {
            helperImageView.setVisibility(View.VISIBLE);
            Picasso.with(activity).load(contextualHelp.getHelpImage().url()).into(helperImageView);
        } else {
            helperImageView.setVisibility(View.GONE);
        }

        Button dismissButton = (Button) view.findViewById(R.id.help_dialog_bt);
        dismissButton.setText(contextualHelp.getDismissButtonText());
//        dismissButton.setBackgroundColor(Color.parseColor(ColorSchemeManager.getInstance().getThemePrimary()));
        dismissButton.setBackgroundColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButton()));

        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        //wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.show();
    }
}
