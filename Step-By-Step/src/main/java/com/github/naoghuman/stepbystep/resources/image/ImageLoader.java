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
package com.github.naoghuman.stepbystep.resources.image;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.stepbystep.resources.image.background.BackgroundLoader;
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
    
    public ImageLoader() {
        this.init();
    }
    
    private void init() {
        backgroundImages = FXCollections.observableHashMap();
    }
    
    private double convertStringToDouble(String stringToDouble) {
        LoggerFacade.getDefault().debug(this.getClass(), "Convert String to Double"); // NOI18N

        final double convertedStringAsDouble = Double.parseDouble(stringToDouble);
        LoggerFacade.getDefault().debug(this.getClass(), " - Convert '" + stringToDouble + "' to '" + convertedStringAsDouble + "'"); // NOI18N

        return convertedStringAsDouble;
    }
    
    private Image load(Class clazz, String image, double width, double height) {
        LoggerFacade.getDefault().debug(this.getClass(), "Load image: " + image); // NOI18N

        Image img = null;
        try {
            final URI uri = clazz.getResource(image).toURI();
            img = new Image(uri.toString(), width, height, true, true);
        } catch (URISyntaxException ex) {
            LoggerFacade.getDefault().error(this.getClass(), "Can't load image: " + image, ex); // NOI18N
        }
        
        return img;
    }
    
    public Image loadBackground(String imageName, String widthAsString, String heightAsString) {
        LoggerFacade.getDefault().debug(this.getClass(), "Load background image: " + imageName); // NOI18N
        
        final Image background = backgroundImages.computeIfAbsent(
                imageName,
                image -> {
                    final double width = this.convertStringToDouble(widthAsString);
                    final double height = this.convertStringToDouble(heightAsString);
                    
                    return this.load(BackgroundLoader.class, image, width, height);
                });
        
        return background;
    }
    
}
