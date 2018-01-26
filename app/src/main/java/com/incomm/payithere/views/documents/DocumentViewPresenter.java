package com.incomm.payithere.views.documents;

import android.view.MotionEvent;
import android.widget.ImageView;

import com.contentful.vault.Asset;
import com.incomm.payithere.models.cms.FeatureLegalDocument;
import com.incomm.payithere.models.cms.LegalDocumentView;
import com.incomm.payithere.models.cms.LegalDocumentViewCopy;
import com.incomm.payithere.models.cms.LegalDocumentViewImage;

/**
 * Created by Jerome Davis on 5/2/17.
 */

public class DocumentViewPresenter implements DocumentViewMVP.Presenter {
    private DocumentViewMVP.View view;

    private FeatureLegalDocument featureLegalDocument;
    private LegalDocumentView legalDocumentView;

    // The 3 states (events) which the user is trying to perform
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    public DocumentViewPresenter(DocumentViewMVP.View view,
                                 FeatureLegalDocument doc) {
        this.view = view;
        this.featureLegalDocument = doc;
    }

    @Override
    public void getViewElements() {
        this.legalDocumentView = featureLegalDocument.getLegalDocumentViews();

        if (legalDocumentView.getClass().equals(LegalDocumentViewCopy.class)) {
            view.displayTextDocument();
        } else if (legalDocumentView.getClass().equals(LegalDocumentViewImage.class)) {
            view.displayImageDocument();
        }

        view.setupUi();
    }

    @Override
    public String getTitle() {
       return featureLegalDocument.getTitle();
    }
    @Override
    public String getAbbreviated() {
        return legalDocumentView.getAbbreviated();
    }
    @Override
    public String getCopy() {
        return legalDocumentView.getCopy();
    }

    @Override
    public Asset getImage() {
        return legalDocumentView.getImage();
    }

    @Override
    public String getUrl() {
        return legalDocumentView.getUrl();
    }

    @Override
    public String getName() {
        return featureLegalDocument.getName();
    }

    @Override
    public LegalDocumentView getLegalDocumentType() {
        return legalDocumentView;
    }

    @Override
    public void onImageViewTouched(ImageView image, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                view.firstFingerDown(event);
                mode = DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // second finger down
                double distance = view.secondFingerDown(event);
                if (distance > 5f) {
                    mode = ZOOM;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    view.imageDrag(event);
                } else if (mode == ZOOM) {
                    view.imageZoom(event);
                }
                break;

            case MotionEvent.ACTION_UP: // first finger lifted
            case MotionEvent.ACTION_POINTER_UP: // second finger lifted
                if (mode == DRAG) {
                    mode = NONE;
                } else if (mode == ZOOM) {
                    mode = DRAG;
                    view.firstFingerDown(event); //keeps the view from jumping when you start dragging
                }
                break;
        }

        view.setImageMatrix(image);
    }

    @Override
    public void onDocumentLinkClicked(String href, String text) {
        String url = text;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        view.launchLink(url);
    }
}
