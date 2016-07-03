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
package com.github.naoghuman.step.by.step.application;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.step.by.step.engine.EGameMode;
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
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private Circle cBorderForClippedBackground;
    @FXML private ImageView ivBackgroundBig;
    @FXML private ImageView ivBackgroundClipped;
    
    private EGameMode gameMode = EGameMode.GAME_MODE__PREVIEW;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeBigBackgroundImage();
        this.initializeClippedBackgroundImage();
        this.initializeBorderForClippedBackgroundImage();
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }

    private void initializeBigBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize big Background image"); // NOI18N
        
        ivBackgroundBig.setFitHeight(1080.0d);
        ivBackgroundBig.setFitWidth(1920.0d);
        
        final Image img = ResourcesFacade.getDefault().getImageLoader().loadImage(IResources.IMG_146664_1920x1080);
        ivBackgroundBig.setImage(img);
    }

    private void initializeClippedBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize clipped Background image"); // NOI18N
        
        ivBackgroundClipped.setFitHeight(768.0d);
        ivBackgroundClipped.setFitWidth(1366.0d);
        
        final Image img = ResourcesFacade.getDefault().getImageLoader().loadImage(IResources.IMG_146664_1366x768);
        ivBackgroundClipped.setImage(img);
        
        // clip image by circle
        final Circle clipCircle = new Circle(300.0d);
        clipCircle.setLayoutX(1366.0d / 2);
        clipCircle.setLayoutY(768.0d / 2);
        ivBackgroundClipped.setClip(clipCircle);
    }
    
    private void initializeBorderForClippedBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize Border for clipped Background image"); // NOI18N
        
        final DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.CORNFLOWERBLUE);
        dropShadow.setSpread(0.15);
 
        cBorderForClippedBackground.setEffect(dropShadow);
    }
    
    private boolean checkAreGameButtonsNotAllowedToReceiveUserEvents() {
        if (
                gameMode.equals(EGameMode.GAME_MODE__ATTENTION)
                || gameMode.equals(EGameMode.GAME_MODE__PREVIEW)
                || gameMode.equals(EGameMode.GAME_MODE__HIGHSCORE)
                || gameMode.equals(EGameMode.GAME_MODE__SUCCESS)
        ) {
            return true;
        }
        
        return false;
    }
    
    public void onActionClickIndex1() {
        final boolean areGameButtonsNotAllowedToReceiveUserEvents = this.checkAreGameButtonsNotAllowedToReceiveUserEvents();
        if (areGameButtonsNotAllowedToReceiveUserEvents) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "Game-Buttons aren't allowed to received UserEvents in GameMode: " + gameMode); // NOI18N
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 1"); // NOI18N
        
    }
    
    public void onActionClickIndex2() {
        final boolean areGameButtonsNotAllowedToReceiveUserEvents = this.checkAreGameButtonsNotAllowedToReceiveUserEvents();
        if (areGameButtonsNotAllowedToReceiveUserEvents) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "Game-Buttons aren't allowed to received UserEvents in GameMode: " + gameMode); // NOI18N
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 2"); // NOI18N
    }
    
    public void onActionClickIndex3() {
        final boolean areGameButtonsNotAllowedToReceiveUserEvents = this.checkAreGameButtonsNotAllowedToReceiveUserEvents();
        if (areGameButtonsNotAllowedToReceiveUserEvents) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "Game-Buttons aren't allowed to received UserEvents in GameMode: " + gameMode); // NOI18N
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 3"); // NOI18N
    }
    
    public void onActionClickIndex4() {
        final boolean areGameButtonsNotAllowedToReceiveUserEvents = this.checkAreGameButtonsNotAllowedToReceiveUserEvents();
        if (areGameButtonsNotAllowedToReceiveUserEvents) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "Game-Buttons aren't allowed to received UserEvents in GameMode: " + gameMode); // NOI18N
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 4"); // NOI18N
    }
    
    public void onActionClickIndex5() {
        final boolean areGameButtonsNotAllowedToReceiveUserEvents = this.checkAreGameButtonsNotAllowedToReceiveUserEvents();
        if (areGameButtonsNotAllowedToReceiveUserEvents) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "Game-Buttons aren't allowed to received UserEvents in GameMode: " + gameMode); // NOI18N
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 5"); // NOI18N
    }
    
    public void onActionPlay() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action Play"); // NOI18N
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
