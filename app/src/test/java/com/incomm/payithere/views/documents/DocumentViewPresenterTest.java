package com.incomm.payithere.views.documents;

import android.view.MotionEvent;
import android.widget.ImageView;

import com.incomm.payithere.models.cms.FeatureLegalDocument;
import com.incomm.payithere.models.cms.LegalDocumentView;
import com.incomm.payithere.models.cms.LegalDocumentViewCopy;
import com.incomm.payithere.models.cms.LegalDocumentViewImage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by bjennings on 10/23/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentViewPresenterTest {
    @Mock
    DocumentViewMVP.View view;
    @Mock
    FeatureLegalDocument legalDoc;
    @Mock
    LegalDocumentView legalDocView;
    @Mock
    MotionEvent event;
    @Mock
    ImageView image;
    DocumentViewMVP.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        when(legalDoc.getLegalDocumentViews()).thenReturn(legalDocView);
        when(view.secondFingerDown(event)).thenReturn(Double.valueOf(6));

        presenter = new DocumentViewPresenter(view, legalDoc);
    }

    @Test
    public void displayTextLegalDocument() throws Exception {
        when(legalDoc.getLegalDocumentViews()).thenReturn(new LegalDocumentViewCopy());
        presenter.getViewElements();

        verify(view).displayTextDocument();
    }

    @Test
    public void displayImageLegalDocument() throws Exception {
        when(legalDoc.getLegalDocumentViews()).thenReturn(new LegalDocumentViewImage());
        presenter.getViewElements();

        verify(view).displayImageDocument();
    }

    @Test
    public void linkWithProtocalClicked() throws Exception {
        presenter.onDocumentLinkClicked("href", "https://www.google.com");

        verify(view).launchLink("https://www.google.com");
    }

    @Test
    public void linkWithoutProtocolClicked() throws Exception {
        presenter.onDocumentLinkClicked("href", "www.google.com");

        verify(view).launchLink("http://www.google.com");
    }

    @Test
    public void firstFingerDown() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        verify(view).firstFingerDown(event);
    }

    @Test
    public void secondFingerDown() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_POINTER_DOWN);
        presenter.onImageViewTouched(image, event);

        verify(view).secondFingerDown(event);
    }

    @Test
    public void fingerDownMoveShouldDrag() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        presenter.onImageViewTouched(image, event);

        verify(view).imageDrag(event);
    }

    @Test
    public void twoFingersDownMoveShouldZoom() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_POINTER_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        presenter.onImageViewTouched(image, event);

        verify(view).imageZoom(event);
    }

    @Test
    public void twoFingersDownOneUpMoveShouldDrag() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_POINTER_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        presenter.onImageViewTouched(image, event);

        verify(view).imageDrag(event);
    }

    @Test
    public void oneFingerDownOneUpMoveShouldDoNothing() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        presenter.onImageViewTouched(image, event);

        verify(view, never()).imageDrag(any(MotionEvent.class));
        verify(view, never()).imageZoom(any(MotionEvent.class));
    }

    @Test
    public void twoFingersDownTwoUpMoveShouldDoNothing() throws Exception {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_POINTER_DOWN);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        presenter.onImageViewTouched(image, event);

        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        presenter.onImageViewTouched(image, event);

        verify(view, never()).imageDrag(any(MotionEvent.class));
        verify(view, never()).imageZoom(any(MotionEvent.class));
    }
}