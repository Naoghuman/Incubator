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

import javafx.scene.Node;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Naoghuman
 */
public abstract class AbstractIkonliHandler {

    /**
     * 
     * @param fontIcon
     * @return 
     */
    public Node getIconAsNode(Ikon fontIcon) {
        if (fontIcon == null) {
            throw new IllegalArgumentException("'fontIcon' can't be NULL."); // NOI18N
        }
        
        // TODO add more data (size, color...)
        final Node node = new FontIcon(fontIcon);
        
        return node;
    }
    
    /**
     * 
     * @param fontIconName
     * @return
     * @throws IllegalArgumentException if the 'fontIconName' can't be resolved to an 'Ikon'.
     */
    public abstract Ikon resolve(String fontIconName);

}
