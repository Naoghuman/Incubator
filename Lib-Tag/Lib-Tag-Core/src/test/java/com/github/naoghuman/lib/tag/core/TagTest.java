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
package com.github.naoghuman.lib.tag.core;

import javafx.scene.paint.Color;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naoghuman
 */
public class TagTest {
    
    public TagTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testConvertColorToString() {
        final Color color = Color.ANTIQUEWHITE;
        
        final StringBuilder expResult = new StringBuilder();
        expResult.append(color.getRed());
        expResult.append(";"); // NOI18N
        expResult.append(color.getGreen());
        expResult.append(";"); // NOI18N
        expResult.append(color.getBlue());
        expResult.append(";"); // NOI18N
        expResult.append(color.getOpacity());
        
        final Tag instance = new Tag();
        final String result = instance.convertColorToString(color);
        assertEquals(expResult.toString(), result);
    }

    @Test
    public void testConvertStringToColor() {
        final Color expResult = Color.ANTIQUEWHITE;
        
        final StringBuilder strColor = new StringBuilder();
        strColor.append(expResult.getRed());
        strColor.append(";"); // NOI18N
        strColor.append(expResult.getGreen());
        strColor.append(";"); // NOI18N
        strColor.append(expResult.getBlue());
        strColor.append(";"); // NOI18N
        strColor.append(expResult.getOpacity());
        
        final Tag instance = new Tag();
        final Color result = instance.convertStringToColor(strColor.toString());
        assertEquals(expResult, result);
    }
    
}
