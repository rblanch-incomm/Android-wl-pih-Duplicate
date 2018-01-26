package com.incomm.payithere.models.cms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by agodambe on 9/14/2017.
 */
public class ColorSchemeTest {

    ColorScheme colorScheme;

    @Before
    public void setUp() throws Exception {
        colorScheme = new ColorScheme();
    }

    @Test public void passName() throws Exception {
        String input = "name";
        colorScheme.setName(input);
        assertEquals(input,colorScheme.getName());
    }

    @Test public void passEmptyStringIfNameNull() throws Exception {
        String input = null;
        colorScheme.setName(input);
        assertEquals("",colorScheme.getName());
    }

    @Test public void passThemePrimary() throws Exception{
        String input = "#111111";
        colorScheme.setThemePrimary(input);
        assertEquals(input,colorScheme.getThemePrimary());
    }

    @Test public void passBlackIfThemePrimaryNull() throws Exception{
        String input = null;
        colorScheme.setThemePrimary(input);
        assertEquals("#000000",colorScheme.getThemePrimary());
    }

    @Test public void passThemeSecondary() throws Exception {
        String input = "#111111";
        colorScheme.setThemeSecondary(input);
        assertEquals(input,colorScheme.getThemeSecondary());
    }

    @Test public void passBlackIfThemeSecondaryNull() throws Exception {
        String input = null;
        colorScheme.setThemeSecondary(input);
        assertEquals("#000000",colorScheme.getThemeSecondary());
    }

    @Test public void passGeneralText() throws Exception {
        String input = "#111111";
        colorScheme.setGeneralText(input);
        assertEquals(input,colorScheme.getGeneralText());
    }

    @Test public void passBlackIfGeneralTextNull() throws Exception {
        String input = null;
        colorScheme.setGeneralText(input);
        assertEquals("#000000",colorScheme.getGeneralText());
    }

    @Test public void passPositiveButton() throws Exception {
        String input = "#222222";
        colorScheme.setPositiveButton(input);
        assertEquals(input,colorScheme.getPositiveButton());
    }

    @Test public void passBlackIfPositiveButtonNull() throws Exception {
        String input = null;
        colorScheme.setPositiveButton(input);
        assertEquals("#000000",colorScheme.getPositiveButton());
    }

    @Test public void passPositiveButtonTitle() throws Exception {
        String input = "#222222";
        colorScheme.setPositiveButtonTitle(input);
        assertEquals(input,colorScheme.getPositiveButtonTitle());
    }

    @Test public void passBlackIfPositiveButtonTitleNull() throws Exception {
        String input = null;
        colorScheme.setPositiveButtonTitle(input);
        assertEquals("#000000",colorScheme.getPositiveButtonTitle());
    }

    @Test
    public void passNegativeButton() throws Exception {
        String input = "#222222";
        colorScheme.setNegativeButton(input);
        assertEquals(input,colorScheme.getNegativeButton());
    }

    @Test
    public void passBlackIfNegativeButtonNull() throws Exception {
        String input = null;
        colorScheme.setNegativeButton(input);
        assertEquals("#000000",colorScheme.getNegativeButton());
    }

    @Test
    public void passNegativeButtonTitle() throws Exception {
        String input = "#222222";
        colorScheme.setNegativeButtonTitle(input);
        assertEquals(input,colorScheme.getNegativeButtonTitle());
    }

    @Test
    public void passBlackIfNegativeButtonTitleNull() throws Exception {
        String input = null;
        colorScheme.setNegativeButtonTitle(input);
        assertEquals("#000000",colorScheme.getNegativeButtonTitle());
    }





}