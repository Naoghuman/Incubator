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
package com.github.naoghuman.step.by.step.view.backgroundimages;

import com.github.naoghuman.step.by.step.debug.DebugConsole;
import com.github.naoghuman.step.by.step.resources.IResources;
import com.github.naoghuman.step.by.step.resources.ResourcesFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Naoghuman
 */
public class BackgroundImagesPresenter implements Initializable {
    
    @FXML private Circle cClippedBackgroundImage;
    @FXML private ImageView ivBackgroundImage;
    @FXML private ImageView ivClippedBackgroundImage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().info(this.getClass(), "Initialize BackgroundImagesPresenter"); // NOI18N
        
        this.initializeBackgroundImage();
        this.initializeClippedBackgroundImage();
    }
    
    private void initializeBackgroundImage() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize BackgroundImage"); // NOI18N
        
        // Big BackgroundImage
        ivBackgroundImage.setFitHeight(1080.0d);
        ivBackgroundImage.setFitWidth(1920.0d);
        
        final Image img = ResourcesFacade.getDefault().getImageLoader().loadImage(IResources.IMG_146664_1920x1080);
        ivBackgroundImage.setImage(img);
    }
    
    private void initializeClippedBackgroundImage() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize ClippedBackgroundImage"); // NOI18N
        
        // The ClippedBackgroundImage
        ivClippedBackgroundImage.setFitHeight(768.0d);
        ivClippedBackgroundImage.setFitWidth(1366.0d);
        
        final Image img = ResourcesFacade.getDefault().getImageLoader().loadImage(IResources.IMG_146664_1366x768);
        ivClippedBackgroundImage.setImage(img);
        
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
    
}
