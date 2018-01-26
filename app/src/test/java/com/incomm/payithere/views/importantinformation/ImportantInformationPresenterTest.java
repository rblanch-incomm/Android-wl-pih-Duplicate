package com.incomm.payithere.views.importantinformation;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.ImportantInformationView;
import com.incomm.payithere.models.legaldocs.LegalDocument;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by bjennings on 10/23/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImportantInformationPresenterTest {
    @Mock
    ImportantInformationView cmsView;
    @Mock
    ImportantInformationFragment view;
    @Mock
    ColorSchemeManager color;
    @Mock
    GlobalImagesManager images;
    @Mock
    LegalDocument l1;
    @Mock
    LegalDocument l2;

    LegalDocument[] docs = {l1, l2};
    ImportantInformationPresenter presenter;

    @Before
    public void setUp() throws Exception {
        when(cmsView.getLegalDocuments()).thenReturn(docs);
        when(color.getGeneralText()).thenReturn("#ffffff");
        presenter = new ImportantInformationPresenter(color, cmsView, view, images);
    }

    @Test
    public void setsUiAndFeatures() throws Exception {
        presenter.getViewElements();

        verify(view).setupUi();
        verify(view).setViewFeatures();
    }
}