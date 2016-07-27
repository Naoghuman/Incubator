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
package com.github.naoghuman.lib.tile.core;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 *
 * @author Naoghuman
 */
public final class DefaultTileLoader {
	
    private static final Optional<DefaultTileLoader> instance = Optional.of(new DefaultTileLoader());

    public static final DefaultTileLoader getDefault() {
        return instance.get();
    }

    private DefaultTileLoader() {

    }

    public void checkParameters(final String imageName, final String title, final double width, final double height) {
        if (imageName == null) {
            throw new NullPointerException("The parameter 'imageName' can't be NULL"); // NOI18N
        }
        
        if (imageName.trim().isEmpty()) {
            throw new IllegalArgumentException("The parameter 'imageName' can't be EMPTY"); // NOI18N
        }

        if (title == null) {
            throw new NullPointerException("The parameter 'title' can't be NULL"); // NOI18N
        }

        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("The parameter 'title' can't be EMPTY"); // NOI18N
        }

        if (width <= 0.0d) {
            throw new IllegalArgumentException("The parameter 'width' can't <= 0.0d"); // NOI18N
        }

        if (height <= 0.0d) {
            throw new IllegalArgumentException("The parameter 'height' can't <= 0.0d"); // NOI18N
        }
    }

    public Background loadAsBackground(final AbstractTileLoader loader, final Tile tile) {
        final Image overlay = DefaultTileLoader.getDefault().loadAsImage(loader, tile);

        final BackgroundSize backgroundSize = new BackgroundSize(tile.getWidth(), tile.getHeight(), 
                false, false, false, false);
        final BackgroundImage backgroundImage = new BackgroundImage(overlay, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        final Background background = new Background(backgroundImage);

        return background;
    }

    public Image loadAsImage(final AbstractTileLoader loader, final Tile tile) {
        if (!loader.isSupported(tile)) {
            throw new UnsupportedOperationException(
                    "The tile-loader " + loader.getClass().getSimpleName() // NOI18N
                    + " doesn't support the Tile: " + tile.toString()); // NOI18N
        }

        Image img = null;
        try {
            final URI uri = loader.getClass().getResource(tile.getImageName()).toURI();
            img = new Image(uri.toString(), tile.getWidth(), tile.getHeight(), true, true);
        } catch (Exception ex) {
            Logger.getLogger(DefaultTileLoader.class.getName()).log(Level.SEVERE, 
                    "Can't load Tile: " + tile.toString(), ex);
        }

        return img;
    }
    
}
