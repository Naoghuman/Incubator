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
package com.github.naoghuman.sbs.view.backgroundimages;

import com.github.naoghuman.lib.properties.api.PropertiesFacade;
import com.github.naoghuman.sbs.configuration.IBackgroundConfiguration;
import com.github.naoghuman.sbs.configuration.IOverlayConfiguration;
import com.github.naoghuman.sbs.debug.DebugConsole;
import com.github.naoghuman.sbs.resources.ResourcesFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Naoghuman
 */
public class BackgroundImagesPresenter implements Initializable, IBackgroundConfiguration, IOverlayConfiguration {
    
    @FXML private AnchorPane apTileImage;
    @FXML private Circle cClippedBackgroundImage;
    @FXML private ImageView ivBackgroundImage;
    @FXML private ImageView ivClippedBackgroundImage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().info(this.getClass(), "Initialize BackgroundImagesPresenter"); // NOI18N
        
        this.initializeBackgroundImage();
        this.initializeClippedBackgroundImage();
        this.initializeTileImage();
    }
    
    private void initializeBackgroundImage() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize BackgroundImage"); // NOI18N
        
        // Big BackgroundImage
        ivBackgroundImage.setFitHeight(1080.0d);
        ivBackgroundImage.setFitWidth(1920.0d);
        
        final String imageName = this.getPropertyBackground(KEY__BACKGROUND__1920x1080_IMAGE);
        final String widthAsString = this.getPropertyBackground(KEY__BACKGROUND__1920x1080_WIDTH);
        final String heigthAsString = this.getPropertyBackground(KEY__BACKGROUND__1920x1080_HEIGHT);
        final Image iBackgroundImage = ResourcesFacade.getDefault().getImageLoader().loadBackground(
                imageName, widthAsString, heigthAsString);
        ivBackgroundImage.setImage(iBackgroundImage);
    }
    
    private void initializeClippedBackgroundImage() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize ClippedBackgroundImage"); // NOI18N
        
        // The ClippedBackgroundImage
        ivClippedBackgroundImage.setFitHeight(768.0d);
        ivClippedBackgroundImage.setFitWidth(1366.0d);
        
        final String imageName = this.getPropertyBackground(KEY__BACKGROUND__1366x768_IMAGE);
        final String widthAsString = this.getPropertyBackground(KEY__BACKGROUND__1366x768_WIDTH);
        final String heigthAsString = this.getPropertyBackground(KEY__BACKGROUND__1366x768_HEIGHT);
        final Image iClippedBackgroundImage = ResourcesFacade.getDefault().getImageLoader().loadBackground(
                imageName, widthAsString, heigthAsString);
        ivClippedBackgroundImage.setImage(iClippedBackgroundImage);
        
        // clip image by circle
        final Circle clipCircle = new Circle(300.0d);
        clipCircle.setLayoutX(1366.0d / 2);
        clipCircle.setLayoutY(768.0d / 2);
        ivClippedBackgroundImage.setClip(clipCircle);
        
        // The border for the ClippedBackgroundImage
        final DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.CORNFLOWERBLUE);
        dropShadow.setSpread(0.15);
 
        cClippedBackgroundImage.setEffect(dropShadow);
    }
    
    private void initializeTileImage() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize TileImage"); // NOI18N
        
        final String imageName = this.getPropertyOverlay(KEY__OVERLAY__3PX_TILE_IMAGE);
        final String widthAsString = this.getPropertyOverlay(KEY__OVERLAY__3PX_TILE_WIDTH);
        final String heigthAsString = this.getPropertyOverlay(KEY__OVERLAY__3PX_TILE_HEIGHT);
        final Image iOverlayImage = ResourcesFacade.getDefault().getImageLoader().loadOverlay(
                imageName, widthAsString, heigthAsString);
        final BackgroundSize backgroundSize = new BackgroundSize(100.0d, 100.0d, false, false, false, false);
        final BackgroundImage backgroundImage = new BackgroundImage(iOverlayImage, BackgroundRepeat.REPEAT, 
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        final Background background = new Background(backgroundImage);
        apTileImage.setBackground(background);
    }
    
    private String getPropertyBackground(String propertyKey) {
        return PropertiesFacade.INSTANCE.getProperty(KEY__BACKGROUND__RESOURCE_BUNDLE, propertyKey);
    }
    
    private String getPropertyOverlay(String propertyKey) {
        return PropertiesFacade.INSTANCE.getProperty(KEY__OVERLAY__RESOURCE_BUNDLE, propertyKey);
    }
    
}
