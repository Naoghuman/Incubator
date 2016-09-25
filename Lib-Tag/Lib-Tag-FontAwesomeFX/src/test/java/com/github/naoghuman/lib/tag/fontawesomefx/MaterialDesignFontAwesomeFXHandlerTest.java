/*
 * Copyright (C) 2016 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.lib.tag.fontawesomefx;

import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsFactory;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naoghuman
 */
public class MaterialDesignFontAwesomeFXHandlerTest {

    private MaterialDesignFontAwesomeFXHandler handler;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        handler = new MaterialDesignFontAwesomeFXHandler();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGlyphsFactoryIsntNULL() {
        final GlyphsFactory glyphsFactory = handler.getGlyphsFactory();

        assertNotNull("glyphsFactory can't be NULL", glyphsFactory);
    }

    @Test
    public void testGlyphIconsIsFound() {
        final String fontIconName = "ACCOUNT_ALERT";
        final GlyphIcons actualGlyphIcons = handler.resolve(fontIconName);

        assertNotNull("actualGlyphIcons can't be NULL", actualGlyphIcons);

        final GlyphIcons expectedGlyphIcons = MaterialDesignIcon.ACCOUNT_ALERT;
        assertEquals("actualGlyphIcons.ACCOUNT_ALERT must be MaterialDesignIcon.ACCOUNT_ALERT", expectedGlyphIcons, actualGlyphIcons);
    }

    @Test
    public void testGlyphIconsNOTFound() {
        final String fontIconName = "DONT_EXISTS";
        final GlyphIcons actualGlyphIcons = handler.resolve(fontIconName);

        assertNull("GlyphIcons.DONT_EXISTS can't exists", actualGlyphIcons);
    }
    
    /*
     * Throws an java.lang.RuntimeException: Internal graphics not initialized yet
     * because the JavaFX Application thread isn't initialized.
     */
//    @Test
//    public void checkGetIconAsNode() {
//        final GlyphsFactory glyphsFactory = handler.getGlyphsFactory();
//        
//        final String fontIconName = "ACCOUNT_ALERT";
//        final GlyphIcons glyphIcons = handler.resolve(fontIconName);
//        
//        final Node actualNode = handler.getIconAsNode(glyphsFactory, glyphIcons);
//        assertNotNull("actualNode.ACCOUNT_ALERT can't be NULL", actualNode);
//    }

}
