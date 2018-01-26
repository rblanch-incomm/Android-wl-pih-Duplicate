package com.incomm.payithere.views.about;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.BuildConfig;
import com.incomm.payithere.R;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements AboutMVP.View {

    AboutPresenter presenter;

    @BindView(R.id.about_body)
    TextView aboutBody;
    @BindView(R.id.about_version_info) TextView aboutVersionInfo;
    @BindView(R.id.about_logo)
    ImageView aboutLogo;
    /*@BindView(R.id.context_help_btn)
    Button contextHelpButton;*/

    private MainTabInterface mListener;
    private Unbinder unbinder;

    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    public AboutFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#007e58"));

        if (getArguments() != null) { }

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();

        presenter = new AboutPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter.getViewElements();
        return view;
    }

    @Override
    public void setupUi() {
        mListener.onSetToolbarTitle(presenter.getTitle());
        aboutBody.setText(presenter.getBody());
        aboutBody.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getGeneralText()));

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        aboutVersionInfo.setText( "v " + versionName + " (" + versionCode + ")");
        aboutVersionInfo.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getGeneralText()));

        Bitmap bitmap = imageManager.loadImageSync(presenter.getLogoUrl(), imageOptions);
        Drawable d = new BitmapDrawable(getResources(),bitmap);
        aboutLogo.setImageDrawable(d);

        /*// TODO: this is just for testing
        contextHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContextHelp();
            }
        });*/
    }

    /*private void showContextHelp() {
        mListener.showContextHelper(getActivity(), presenter.getFeatureContextualHelp());
    }*/

    @Override
    public void setViewFeatures() { }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}
