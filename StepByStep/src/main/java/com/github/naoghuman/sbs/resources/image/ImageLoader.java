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
package com.github.naoghuman.sbs.resources.image;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.sbs.resources.image.background.BackgroundLoader;
import com.github.naoghuman.sbs.resources.image.overlay.OverlayLoader;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;

/**
 *
 * @author Naoghuman
 */
public class ImageLoader {
    
    private ObservableMap<String, Image> backgroundImages;
    private ObservableMap<String, Image> overlayImages;
    
    public ImageLoader() {
        this.init();
    }
    
    private void init() {
        backgroundImages = FXCollections.observableHashMap();
        overlayImages = FXCollections.observableHashMap();
    }
    
    private double getStringAsDouble(String doubleAsString) {
        return Double.parseDouble(doubleAsString);
    }
    
    public Image loadBackground(String imageName, String widthAsString, String heightAsString) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Load background image: " + imageName); // NOI18N
        
        final Image background = backgroundImages.computeIfAbsent(
                imageName,
                image -> {
                    final double width = this.getStringAsDouble(widthAsString);
                    final double height = this.getStringAsDouble(heightAsString);
                    
                    return this.load(BackgroundLoader.class, image, width, height);
                });
        
        return background;
    }
    
    public Image loadOverlay(String imageName, String widthAsString, String heightAsString) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Load Overlay image: " + imageName); // NOI18N
        
        final Image overlay = overlayImages.computeIfAbsent(
                imageName,
                image -> {
                    final double width = this.getStringAsDouble(widthAsString);
                    final double height = this.getStringAsDouble(heightAsString);
                    
                    return this.load(OverlayLoader.class, image, width, height);
                });
        
        return overlay;
    }
    
    private Image load(Class clazz, String image, double width, double height) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Load image: " + image); // NOI18N

        Image img = null;
        try {
            final URI uri = clazz.getResource(image).toURI();
            img = new Image(uri.toString(), width, height, true, true);
        } catch (URISyntaxException ex) {
            LoggerFacade.INSTANCE.error(this.getClass(), "Can't load image: " + image, ex); // NOI18N
        }
        
        return img;
    }
    
}
