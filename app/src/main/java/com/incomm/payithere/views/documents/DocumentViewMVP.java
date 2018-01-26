package com.incomm.payithere.views.documents;

import android.graphics.Matrix;
import android.net.Uri;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.contentful.vault.Asset;
import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.LegalDocumentView;

/**
 * Created by Jerome Davis on 5/2/17.
 */

public interface DocumentViewMVP {

    interface View extends BaseView<Presenter> {
        void setImageMatrix(ImageView view);
        void displayTextDocument();
        void displayImageDocument();
        void launchLink(String url);
        void imageZoom(MotionEvent event);
        void imageDrag(MotionEvent event);
        void firstFingerDown(MotionEvent event);
        double secondFingerDown(MotionEvent event);
    }

    interface Presenter extends BasePresenter {
        String getTitle();
        String getAbbreviated();
        String getCopy();
        Asset getImage();
        String getUrl();
        String getName();
        LegalDocumentView getLegalDocumentType();
        void onImageViewTouched(ImageView view, MotionEvent event);
        void onDocumentLinkClicked(String href, String text);
    }
}
