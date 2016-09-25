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
package com.github.naoghuman.lib.tag.ikonli;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kordamp.ikonli.octicons.Octicons;
import org.kordamp.ikonli.Ikon;

/**
 *
 * @author Naoghuman
 */
public class OcticonsIkonliHandlerTest {

    private OcticonsIkonliHandler handler;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        handler = new OcticonsIkonliHandler();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIkonIsFound() {
        final String fontIconName = "oct-alert";
        final Ikon actualIkon = handler.resolve(fontIconName);

        assertNotNull("actualIkon can't be NULL", actualIkon);

        final Ikon expectedIkon = Octicons.ALERT;
        assertEquals("actualIkon.ALERT must be Octicons.ALERT.getDescription() -> oct-alert", expectedIkon, actualIkon);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIkonNOTFound() {
        final String fontIconName = "DONT_EXISTS";
        final Ikon actualIkon = handler.resolve(fontIconName);

        assertNull("Ikon.DONT_EXISTS can't exists", actualIkon);
    }
    
    /*
     * Throws an java.lang.RuntimeException: Internal graphics not initialized yet
     * because the JavaFX Application thread isn't initialized.
     */
//    @Test
//    public void checkGetIconAsNode() {
//        final String fontIconName = "oct-alert";
//        final Ikon ikon = handler.resolve(fontIconName);
//        
//        final Node actualNode = handler.getIconAsNode(ikon);
//        assertNotNull("actualNode can't be NULL", actualNode);
//    }

}
