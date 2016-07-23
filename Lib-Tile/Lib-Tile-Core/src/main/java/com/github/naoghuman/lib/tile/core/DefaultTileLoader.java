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
import java.net.URISyntaxException;
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

    private static DefaultTileLoader instance = null;

    public static final DefaultTileLoader getDefault() {
        if (instance == null) {
            instance = new DefaultTileLoader();
        }

        return instance;
    }

    private DefaultTileLoader() {

    }

    public void checkParameters(final String name, final String header, final double width, final double height) {
        if (name == null) {
            throw new NullPointerException("name can't be NULL");
        }
        
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("name can't be EMPTY");
        }

        if (header == null) {
            throw new NullPointerException("header can't be NULL");
        }

        if (header.trim().isEmpty()) {
            throw new IllegalArgumentException("header can't be EMPTY");
        }

        if (width <= 0.0d) {
            throw new IllegalArgumentException("width can't <= 0.0d");
        }

        if (height <= 0.0d) {
            throw new UnsupportedOperationException("height can't <= 0.0d");
        }
    }

    public Background loadAsBackground(final TileLoader loader, final Tile tile) {
        final Image overlay = DefaultTileLoader.getDefault().loadAsImage(loader, tile);

        final BackgroundSize backgroundSize = new BackgroundSize(tile.getWidth(), tile.getHeight(), 
                false, false, false, false);
        final BackgroundImage backgroundImage = new BackgroundImage(overlay, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        final Background background = new Background(backgroundImage);

        return background;
    }

    public Image loadAsImage(final TileLoader loader, final Tile tile) {
        // DebugConsole.getDefault().debug(this.getClass(), "Load image: " + image); // NOI18N

        if (!loader.isSupported(tile)) {
            throw new UnsupportedOperationException("The tile-loader " + loader.getClass().getSimpleName()
                    + " doesn't support the Tile " + tile.getName());
        }

        Image img = null;
        try {
            final URI uri = loader.getClass().getResource(tile.getName()).toURI();
            img = new Image(uri.toString(), tile.getWidth(), tile.getHeight(), true, true);
        } catch (final URISyntaxException ex) {
            // LoggerFacade.INSTANCE.error(this.getClass(), "Can't load image: " + image, ex); // NOI18N
        }

        return img;
    }
    
}
