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
import com.github.naoghuman.lib.properties.api.PropertiesFacade;
import com.github.naoghuman.sbs.configuration.IOverlayConfiguration;
import com.github.naoghuman.sbs.debug.DebugConsole;
import com.github.naoghuman.sbs.resources.image.background.BackgroundLoader;
import com.github.naoghuman.sbs.resources.image.overlay.OverlayLoader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
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
public class ImageLoader implements IOverlayConfiguration {
    
    private static final String SUFFIX_HEIGHT = "height"; // NOI18N
    private static final String SUFFIX_IMAGE = "image"; // NOI18N
    private static final String SUFFIX_WIDTH = "width"; // NOI18N
    
    private ObservableMap<String, Image> backgroundImages;
    private ObservableMap<String, Image> overlayImages;
    
    public ImageLoader() {
        this.init();
    }
    
    private void init() {
        backgroundImages = FXCollections.observableHashMap();
        overlayImages = FXCollections.observableHashMap();
    }
    
    private String convertPropertyKeySuffixImageToHeight(String keyToConvert) {
        DebugConsole.getDefault().debug(this.getClass(), "Convert property-key suffix Image to Height"); // NOI18N

        final StringBuilder sb = new StringBuilder();
        sb.append(keyToConvert.substring(0, keyToConvert.length() - SUFFIX_IMAGE.length()));
        sb.append(SUFFIX_HEIGHT);
        
        DebugConsole.getDefault().debug(this.getClass(), " - Convert '" + keyToConvert + "' to '" + sb.toString() + "'"); // NOI18N

        return sb.toString();
    }
    
    private String convertPropertyKeySuffixImageToWidth(String keyToConvert) {
        DebugConsole.getDefault().debug(this.getClass(), "Convert property-key suffix Image to Width"); // NOI18N

        final StringBuilder sb = new StringBuilder();
        sb.append(keyToConvert.substring(0, keyToConvert.length() - SUFFIX_IMAGE.length()));
        sb.append(SUFFIX_WIDTH);
        
        DebugConsole.getDefault().debug(this.getClass(), " - Convert '" + keyToConvert + "' to '" + sb.toString() + "'"); // NOI18N

        return sb.toString();
    }
    
    private double convertStringToDouble(String stringToDouble) {
        DebugConsole.getDefault().debug(this.getClass(), "Convert String to Double"); // NOI18N

        final double convertedStringAsDouble = Double.parseDouble(stringToDouble);
        DebugConsole.getDefault().debug(this.getClass(), " - Convert '" + stringToDouble + "' to '" + convertedStringAsDouble + "'"); // NOI18N

        return convertedStringAsDouble;
    }
    
    private String getPropertyOverlay(String propertyKey) {
        return PropertiesFacade.INSTANCE.getProperty(KEY__OVERLAY__RESOURCE_BUNDLE, propertyKey);
    }
    
    private Image load(Class clazz, String image, double width, double height) {
        DebugConsole.getDefault().debug(this.getClass(), "Load image: " + image); // NOI18N

        Image img = null;
        try {
            final URI uri = clazz.getResource(image).toURI();
            img = new Image(uri.toString(), width, height, true, true);
        } catch (URISyntaxException ex) {
            LoggerFacade.INSTANCE.error(this.getClass(), "Can't load image: " + image, ex); // NOI18N
        }
        
        return img;
    }
    
    public Image loadBackground(String imageName, String widthAsString, String heightAsString) {
        DebugConsole.getDefault().debug(this.getClass(), "Load background image: " + imageName); // NOI18N
        
        final Image background = backgroundImages.computeIfAbsent(
                imageName,
                image -> {
                    final double width = this.convertStringToDouble(widthAsString);
                    final double height = this.convertStringToDouble(heightAsString);
                    
                    return this.load(BackgroundLoader.class, image, width, height);
                });
        
        return background;
    }
    
    public Background loadOverlay(String imageName, String widthAsString, String heightAsString) {
        DebugConsole.getDefault().debug(this.getClass(), "Load Overlay image: " + imageName + " (" + widthAsString + ", " + heightAsString + ")"); // NOI18N
        
        final double width = this.convertStringToDouble(widthAsString);
        final double height = this.convertStringToDouble(heightAsString);
        
        final Image overlay = overlayImages.computeIfAbsent(
                imageName,
                image -> {
                    return this.load(OverlayLoader.class, image, width, height);
                });
        
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        final BackgroundImage backgroundImage = new BackgroundImage(overlay, BackgroundRepeat.REPEAT, 
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        final Background background = new Background(backgroundImage);
        
        return background;
    }
    
    public Background loadRandomOverlay() {
        DebugConsole.getDefault().debug(this.getClass(), "Load RandomOverlay image"); // NOI18N
        
        final Random random = new Random();
        
        final String propertyKeyImage = KEYS__TILE_IMAGES[random.nextInt(KEYS__TILE_IMAGES.length)];
        final String imageName = this.getPropertyOverlay(propertyKeyImage);
        
        final String convertedPropertyKeyWidth = this.convertPropertyKeySuffixImageToWidth(propertyKeyImage);
        final String widthAsString = this.getPropertyOverlay(convertedPropertyKeyWidth);
        
        final String convertedPropertyKeyHeight = this.convertPropertyKeySuffixImageToHeight(propertyKeyImage);
        final String heightAsString = this.getPropertyOverlay(convertedPropertyKeyHeight);
        
        final Background background = this.loadOverlay(imageName, widthAsString, heightAsString);
        
        return background;
    }
    
}
