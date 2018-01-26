package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Created by agodambe on 9/15/2017.
 */
public class GlobalImagesTest {

    GlobalImages globalImages;
    @Mock
    Asset input;

    @Before
    public void setUp() throws Exception {
        globalImages = new GlobalImages();
    }

    @Test
    public void getBackground() throws Exception {
        globalImages.setBackground(input);
        assertEquals(input,globalImages.getBackground());
    }

}