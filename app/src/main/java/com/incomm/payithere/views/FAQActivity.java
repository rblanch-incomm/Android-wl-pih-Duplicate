package com.incomm.payithere.views;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.FAQQuestionItem;
import com.incomm.payithere.repositories.FAQRepository;
import com.incomm.payithere.views.faqs.FaqsFragmentRoot;
import com.incomm.payithere.views.faqs.FaqsMVP;
import com.incomm.payithere.views.faqs.FaqsPresenter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import butterknife.ButterKnife;

public class FAQActivity extends AppCompatActivity implements FaqsMVP.View {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Toolbar toolbar;
    private FaqsPresenter faqsPresenter;
    private TextView toolbarTitle;
    private ImageView toolbarImage;
    private LinearLayout mainLayout;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();

        toolbar = (Toolbar) findViewById((R.id.apptoolbar));
        faqsPresenter = new FaqsPresenter(this,new FAQRepository());
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        FaqsFragmentRoot fragment = new FaqsFragmentRoot();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarImage = (ImageView) findViewById(R.id.toolbar_image);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        faqsPresenter.getViewElements();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                enableBack();
            }
        });
    }

    public void disableBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void enableBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // show back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    @Override
//    public void onSetToolbarTitle(String title) {
//        if (null == title || title.equals("")) {
//            toolbarTitle.setVisibility(View.GONE);
//            toolbarImage.setVisibility(View.VISIBLE);
//        } else {
//            toolbarTitle.setVisibility(View.VISIBLE);
//            toolbarImage.setVisibility(View.GONE);
//
//            //toolbarTitle.setText(title);
//            toolbar.setTitle(title);
//            toolbarTitle.setTextColor(Color.parseColor(faqsPresenter.getGlobalTextColor()));
//        }
//    }


    @Override
    public void setupUi() {
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarImage.setVisibility(View.GONE);
       // imageManager.displayImage(ActivityUtils.checkNull(presenter.globalImages.getTitleLogo()), toolbarImage, imageOptions);
        Bitmap bitmap = imageManager.loadImageSync(faqsPresenter.getNavigationBar(), imageOptions);
        Drawable d = new BitmapDrawable(getResources(), bitmap);

        mainLayout.setBackground(d);
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void displayQuestions(List<FAQQuestionItem> questionsList) {

    }

    @Override
    public void setGeneralTextColor(String generalTextColor) {

    }

    @Override
    public void displayNoQuestions() {

    }
}
