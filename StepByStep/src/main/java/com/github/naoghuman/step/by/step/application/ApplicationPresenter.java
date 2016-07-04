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
import com.github.naoghuman.step.by.step.gameengine.EGameMode;
import com.github.naoghuman.step.by.step.gameengine.GameEngine;
import com.github.naoghuman.step.by.step.resources.IResources;
import com.github.naoghuman.step.by.step.resources.ResourcesFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    private static final Font FONT_SIZE_128 = new Font(128.0d);
    
    @FXML private Button bGameButton1;
    @FXML private Button bGameButton2;
    @FXML private Button bGameButton3;
    @FXML private Button bGameButton4;
    @FXML private Button bGameButton5;
    @FXML private Button bPlayButton;
    @FXML private Circle cBorderForClippedBackground;
    @FXML private ImageView ivBackgroundBig;
    @FXML private ImageView ivBackgroundClipped;
    @FXML private Text tLevel;
    @FXML private Text tLevelInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        GameEngine.getDefault().registerGameButtons(bGameButton1, bGameButton2, bGameButton3, bGameButton4, bGameButton5);
        GameEngine.getDefault().registerLevelInfo(tLevel, tLevelInfo);
        
        this.initializeBigBackgroundImage();
        this.initializeClippedBackgroundImage();
        this.initializeBorderForClippedBackgroundImage();
        this.initializePlayButton();
        this.initializeLevelInfo();
        
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
    
    private void initializeLevelInfo() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize LevelInfo"); // NOI18N
        
        final boolean showLevelInfo = false;
        this.switchLevelInfoToGameMode(EGameMode.GAME_MODE__PREVIEW, showLevelInfo);
    }
    
    private void initializePlayButton() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize PlayButton"); // NOI18N
        
        final boolean showPlayButton = true;
        this.switchPlayButtonToGameMode(EGameMode.GAME_MODE__PREVIEW, showPlayButton);
    }
    
    public void onActionClickIndex1() {
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "User isn't allowed to click the Game-Buttons in GameMode: " 
                    + GameEngine.getDefault().getGameMode()); // NOI18N
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 1"); // NOI18N
        
    }
    
    public void onActionClickIndex2() {
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "User isn't allowed to click the Game-Buttons in GameMode: " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 2"); // NOI18N
    }
    
    public void onActionClickIndex3() {
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "User isn't allowed to click the Game-Buttons in GameMode: " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 3"); // NOI18N
    }
    
    public void onActionClickIndex4() {
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "User isn't allowed to click the Game-Buttons in GameMode: " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 4"); // NOI18N
    }
    
    public void onActionClickIndex5() {
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "User isn't allowed to click the Game-Buttons in GameMode: " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action click Index 5"); // NOI18N
    }
    
    public void onActionPlay() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action Play"); // NOI18N
        
        /*
         - Should the PlayButton change in GameMode.ATTENTION?
            - Then the user have to click on the PlayButton to start every round.
        
         - (v) change GameMode to GameMode.ATTENTION
         - (v) remove PlayButton
         - (v) show Timer (3-2-1)
            - (v) in the middle from the application
            - (v) use SequentialTransition to fade in and out the numbers
            - (v) Show LevelInformation when Time is finished.
         - show elements for round xy
            - change button color
            - use SequentialTransition to switch between the elements
               - With each element the LevelInformation is updated so the user
                 can prepare himself when the level is ready.
         - change to GameMode.REMEMBER
        */
        if (GameEngine.getDefault().isGameMode(EGameMode.GAME_MODE__PREVIEW)) {
            GameEngine.getDefault().switchToGameMode(EGameMode.GAME_MODE__ATTENTION);
            
            final boolean showLevelInfo = true;
            this.switchLevelInfoToGameMode(EGameMode.GAME_MODE__ATTENTION, showLevelInfo);
            
            final boolean showPlayButton = false;
            this.switchPlayButtonToGameMode(EGameMode.GAME_MODE__ATTENTION, showPlayButton);
        }
        
        final SequentialTransition sequentialTransition = new SequentialTransition();
        
        final SequentialTransition stCounterAnimation = GameEngine.getDefault().createCounterAnimation();
        sequentialTransition.getChildren().add(stCounterAnimation);
        
        final SequentialTransition stGameButtonsAnimation = GameEngine.getDefault().createGameButtonsAnimation();
        sequentialTransition.getChildren().add(stGameButtonsAnimation);
        
        sequentialTransition.playFromStart();
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
    private void switchLevelInfoToGameMode(EGameMode gameMode, boolean showLevelInfo) {
        if (gameMode.equals(EGameMode.GAME_MODE__ATTENTION)) {
            tLevel.setText("3"); // NOI18N
            tLevel.setFont(FONT_SIZE_128);
            tLevel.setOpacity(0.0d);
            tLevel.setManaged(showLevelInfo);
            tLevel.setVisible(showLevelInfo);
            
            tLevelInfo.setText(GameEngine.getDefault().getLevel() + " / 0"); // NOI18N
            tLevelInfo.setOpacity(0.0d);
            tLevelInfo.setManaged(!showLevelInfo);
            tLevelInfo.setVisible(!showLevelInfo);
        }
        
        if (gameMode.equals(EGameMode.GAME_MODE__PREVIEW)) {
            tLevel.setText("Level"); // NOI18N
            tLevel.setManaged(showLevelInfo);
            tLevel.setVisible(showLevelInfo);
            
            GameEngine.getDefault().resetLevel();
            tLevelInfo.setText(GameEngine.getDefault().getLevel() + " / 0"); // NOI18N
            tLevelInfo.setManaged(showLevelInfo);
            tLevelInfo.setVisible(showLevelInfo);
        }
    }
    
    private void switchPlayButtonToGameMode(EGameMode gameMode, boolean showPlayButton) {
        if (gameMode.equals(EGameMode.GAME_MODE__ATTENTION)) {
            bPlayButton.setText("Start"); // NOI18N
//            bPlayButton.setManaged(showPlayButton);
            bPlayButton.setVisible(showPlayButton);
        }
        
        if (gameMode.equals(EGameMode.GAME_MODE__PREVIEW)) {
            bPlayButton.setText("Play"); // NOI18N
//            bPlayButton.setManaged(showPlayButton);
            bPlayButton.setVisible(showPlayButton);
        }
    }
    
}
