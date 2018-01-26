package com.incomm.payithere.views.documents;


import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.cms.FeatureLegalDocument;
import com.incomm.payithere.models.cms.LegalDocumentViewCopy;
import com.incomm.payithere.models.cms.LegalDocumentViewImage;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import br.tiagohm.markdownview.MarkdownView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentViewFragment extends Fragment implements DocumentViewMVP.View,
                                                                View.OnTouchListener {
    @BindView(R.id.title_section__text)
    TextView titleSubSection;
    @BindView(R.id.copy_document_container)
    ScrollView copyDocumentContainer;
    @BindView(R.id.image_document_container)
    LinearLayout imageDocumentContainer;
    @BindView(R.id.header_container)
    LinearLayout headerContainer;
    @BindView(R.id.document_copy_textview)
    MarkdownView mMarkdownView;
    @BindView(R.id.legal_doc_view_imageView)
    ImageView legalDocumentImageView;

    private View view;
    private DisplayImageOptions imageOptions;

    private boolean showHeader;
    
    private FeatureLegalDocument mFeatureSection;
    private int currentFragment;
    private MainTabInterface mListener;
    private DocumentViewMVP.Presenter presenter;

    // These matrices will be used to scale points of the image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    // these PointF objects are used to record the point(s) the user is touching
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f,MAX_ZOOM = 1f;

    public DocumentViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mFeatureSection = getArguments().getParcelable("key");
            currentFragment = getArguments().getInt("current_fragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_document_view, container, false);

        ButterKnife.bind(this, view);

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();
        legalDocumentImageView.setOnTouchListener(this);

        presenter = new DocumentViewPresenter(this, mFeatureSection);
        presenter.getViewElements();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onSetToolbarTitle(presenter.getTitle());
        setupUi();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getTitle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        }
    }

    @Override
    public void setupUi() {
       titleSubSection.setText(mFeatureSection.getTitle());
        if (showHeader) {
            headerContainer.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setViewFeatures() { }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        ImageView imageView = (ImageView) view;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);

        presenter.onImageViewTouched(imageView, event);
        return true; // indicate event was handled
    }

    @Override
    public void setImageMatrix(ImageView imageView) {
        imageView.setImageMatrix(matrix);
    }

    @Override
    public void imageZoom(MotionEvent event) {
        float newDist = spacing(event);

        if (newDist > 5f) {
            matrix.set(savedMatrix);
            float scale = newDist / oldDist;
            // setting the scaling of the matrix
            // if scale > 1 means zoom in
            // if scale < 1 means zoom out
            matrix.postScale(scale, scale, mid.x, mid.y);
            savedMatrix.set(matrix);
            oldDist = newDist;
        }
    }

    @Override
    public void imageDrag(MotionEvent event) {
        matrix.set(savedMatrix);
        matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
    }

    @Override
    public void firstFingerDown(MotionEvent event) {
        savedMatrix.set(matrix);
        start.set(event.getX(), event.getY());
    }

    @Override
    public double secondFingerDown(MotionEvent event) {
        oldDist = spacing(event);

        if (oldDist > 5f) {
            savedMatrix.set(matrix);
            midPoint(event);
        }

        return oldDist;
    }

    /*
     * --------------------------------------------------------------------------
     * Method: midPoint
     * Parameters: PointF object, MotionEvent
     * Returns: void
     * Description: calculates the midpoint between the two fingers
     * ------------------------------------------------------------
     */

    private void midPoint(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        mid.set(x / 2, y / 2);
    }

    /*
     * --------------------------------------------------------------------------
     * Method: spacing
     * Parameters: MotionEvent
     * Returns: float
     * Description: checks the spacing between the two fingers on touch
     * ----------------------------------------------------
     */

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public void displayTextDocument() {
        copyDocumentContainer.setVisibility(View.VISIBLE);
        imageDocumentContainer.setVisibility(View.GONE);

        // Using: https://github.com/tiagohm/MarkdownView/blob/master/README.md
        mMarkdownView.loadMarkdown(mFeatureSection.getLegalDocumentViews().getCopy());
        mMarkdownView.setOnElementListener(new MarkdownView.OnElementListener() {
            public void onButtonTap(String s) { }
            public void onCodeTap(String s, String s1) { }
            public void onHeadingTap(int i, String s) { }
            public void onImageTap(String s, int i, int i1) { }
            public void onMarkTap(String s) { }
            public void onKeystrokeTap(String s) { }

            @Override
            public void onLinkTap(String s, String s1) {
                presenter.onDocumentLinkClicked(s, s1);
            }
        });
    }

    @Override
    public void displayImageDocument() {
        ImageLoader imageManager = ImageLoader.getInstance();
        imageDocumentContainer.setVisibility(View.VISIBLE);
        copyDocumentContainer.setVisibility(View.GONE);
        imageManager.displayImage(ActivityUtils.checkNull(mFeatureSection.getLegalDocumentViews().getImage()), legalDocumentImageView, imageOptions);
    }

    @Override
    public void launchLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
