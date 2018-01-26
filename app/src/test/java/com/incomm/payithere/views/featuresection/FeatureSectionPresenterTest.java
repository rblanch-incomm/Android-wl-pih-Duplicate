package com.incomm.payithere.views.featuresection;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.FeatureSection;
import com.incomm.payithere.views.help.HelpMVP;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by bjennings on 10/24/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FeatureSectionPresenterTest {
    @Mock
    FeatureSectionMVP.View view;
    @Mock
    ColorSchemeManager colors;
    @Mock
    GlobalImagesManager images;
    @Mock
    Feature f1;
    @Mock
    Feature f2;
    @Mock
    FeatureSection featureSection;

    private List<Feature> featureList = new ArrayList<>();
    private FeatureSectionMVP.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        when(images.getChevronRight()).thenReturn(null);
        when(colors.getGeneralText()).thenReturn("#ffffff");

        when(f1.getKey()).thenReturn("position0");
        when(f2.getKey()).thenReturn("position1");

        featureList.add(f1);
        featureList.add(f2);

        when(featureSection.getTitle()).thenReturn("hi");
        when(featureSection.getFeatures()).thenReturn(featureList);

        presenter = new FeatureSectionPresenter(view, featureSection, colors, images);
    }

    @Test
    public void setTitleTest() throws Exception {
        presenter.setTitle();

        verify(view).setTitle("hi");
    }

    @Test
    public void recyclerViewCreation() throws Exception {
        presenter.getViewElements();

        verify(view).createRecyclerView(featureList, "#ffffff", null);
    }

    @Test
    public void key0Test() throws Exception {
        presenter.getViewElements();
        presenter.onItemClicked(0);

        verify(view).launchFragment("position0");
    }

    @Test
    public void key1Test() throws Exception {
        presenter.getViewElements();
        presenter.onItemClicked(1);

        verify(view).launchFragment("position1");
    }
}