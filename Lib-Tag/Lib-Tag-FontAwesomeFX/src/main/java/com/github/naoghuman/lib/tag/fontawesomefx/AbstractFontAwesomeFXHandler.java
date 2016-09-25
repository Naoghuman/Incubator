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
import javafx.scene.Node;

/**
 *
 * @author Naoghuman
 */
public abstract class AbstractFontAwesomeFXHandler {
    
    /**
     * 
     * @param glyphsFactory
     * @param fontIcon
     * @return 
     */
    public Node getIconAsNode(GlyphsFactory glyphsFactory, GlyphIcons fontIcon) {
        if (fontIcon == null) {
            throw new IllegalArgumentException("'fontIcon' can't be NULL."); // NOI18N
        }
        
        // TODO add more data (size, color...)
        final Node node = glyphsFactory.createIcon(fontIcon, "16px");// 1em
        
        return node;
    }
    
    /**
     * 
     * @return 
     */
    public abstract GlyphsFactory getGlyphsFactory();
    
    /**
     * 
     * @param fontIconName
     * @return
     * @throws IllegalArgumentException if the 'fontIconName' can't be resolved to an 'Ikon'.
     */
    public abstract GlyphIcons resolve(String fontIconName);
    
}
