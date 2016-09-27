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
package com.github.naoghuman.stepbystep.background;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.properties.api.PropertiesFacade;
import com.github.naoghuman.stepbystep.configuration.IBackgroundConfiguration;
import com.github.naoghuman.stepbystep.resources.ResourcesFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;

/**
 *
 * @author Naoghuman
 */
public class BackgroundPresenter implements Initializable, IBackgroundConfiguration {
    
    @FXML private AnchorPane apBackgroundTile;
    @FXML private Circle cBackgroundClipBorder;
    @FXML private ImageView ivBackgroundImage;
    @FXML private ImageView ivBackgroundClip;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize BackgroundPresenter"); // NOI18N
        
        this.initializeBackgroundImage();
    }
    
    private void initializeBackgroundImage() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize BackgroundImage"); // NOI18N
        
        // Big BackgroundImage
        ivBackgroundImage.setFitHeight(1080.0d);
        ivBackgroundImage.setFitWidth(1920.0d);
        
        final String imageName = this.getPropertyBackground(KEY__BACKGROUND_IMAGE__1920x1080_IMAGE);
        final String widthAsString = this.getPropertyBackground(KEY__BACKGROUND_IMAGE__1920x1080_WIDTH);
        final String heigthAsString = this.getPropertyBackground(KEY__BACKGROUND_IMAGE__1920x1080_HEIGHT);
        final Image iBackgroundImage = ResourcesFacade.getDefault().getBackgroundLoader().loadBackground(
                imageName, widthAsString, heigthAsString);
        ivBackgroundImage.setImage(iBackgroundImage);
        
        // Tile
        final Background background = ResourcesFacade.getDefault().getRandomTile();
        apBackgroundTile.setBackground(background);
    }
    
    private String getPropertyBackground(String propertyKey) {
        return PropertiesFacade.getDefault().getProperty(KEY__BACKGROUND_IMAGE__RESOURCE_BUNDLE, propertyKey);
    }
    
}
