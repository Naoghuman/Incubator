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
package com.github.naoghuman.sbs.view.gamearea;

import com.github.naoghuman.sbs.debug.DebugConsole;
import com.github.naoghuman.sbs.gameengine.EGameMode;
import com.github.naoghuman.sbs.gameengine.GameEngine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Naoghuman
 */
public class GameComponentsPresenter implements Initializable {
    
    @FXML private BorderPane bpGameComponents;
    @FXML private Button bGameElement1;
    @FXML private Button bGameElement2;
    @FXML private Button bGameElement3;
    @FXML private Button bGameElement4;
    @FXML private Button bGameElement5;
    @FXML private Button bPlayGame;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().info(this.getClass(), "Initialize GameComponentsPresenter"); // NOI18N
        
        GameEngine.getDefault().registerGameButtons(bGameElement1, bGameElement2, 
                bGameElement3, bGameElement4, bGameElement5);
        
        this.initializeGameElements();
        this.initializeButtonPlayGame();
        
//        this.configureTestComponents();
        
    }
    
    private void initializeButtonPlayGame() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize Button PlayGame"); // NOI18N
        
        final boolean showPlayButton = true;
        this.switchPlayButtonToGameMode(EGameMode.GAME_MODE__PREVIEW, showPlayButton);
    }
    
    private void initializeGameElements() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize GameElements"); // NOI18N
        
        GameEngine.getDefault().setGameElementsColorBaseNotClickable();
    }
    
    public void configureTestComponents() {
        DebugConsole.getDefault().debug(this.getClass(), "Configure TestComponents"); // NOI18N
        
//        bpGameComponents.setLeft(DebugConsole.getDefault().getDebugConsole());
//        bpGameComponents.setRight(DebugConsole.getDefault().getDebugOptions());
    }
    
    public void onActionClickGameElement1() {
        DebugConsole.getDefault().debug(this.getClass(), "On action click GameElement1"); // NOI18N
        
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            DebugConsole.getDefault().debug(this.getClass(), "GameElement1 can't clicked in " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        DebugConsole.getDefault().debug(this.getClass(), "On action click Index 1"); // NOI18N
    }
    
    public void onActionClickGameElement2() {
        DebugConsole.getDefault().debug(this.getClass(), "On action click GameElement2"); // NOI18N
        
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            DebugConsole.getDefault().debug(this.getClass(), "GameElement2 can't clicked in " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        DebugConsole.getDefault().debug(this.getClass(), "On action click Index 2"); // NOI18N
    }
    
    public void onActionClickGameElement3() {
        DebugConsole.getDefault().debug(this.getClass(), "On action click GameElement3"); // NOI18N
        
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            DebugConsole.getDefault().debug(this.getClass(), "GameElement3 can't clicked in " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        DebugConsole.getDefault().debug(this.getClass(), "On action click Index 3"); // NOI18N
    }
    
    public void onActionClickGameElement4() {
        DebugConsole.getDefault().debug(this.getClass(), "On action click GameElement4"); // NOI18N
        
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            DebugConsole.getDefault().debug(this.getClass(), "GameElement4 can't clicked in " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        DebugConsole.getDefault().debug(this.getClass(), "On action click Index 4"); // NOI18N
    }
    
    public void onActionClickGameElement5() {
        DebugConsole.getDefault().debug(this.getClass(), "On action click GameElement5"); // NOI18N
        
        final boolean canUserClickGameButtons = GameEngine.getDefault().checkUserCanClickGameButtons();
        if (!canUserClickGameButtons) {
            DebugConsole.getDefault().debug(this.getClass(), "GameElement5 can't clicked in " // NOI18N
                    + GameEngine.getDefault().getGameMode());
            return;
        }
        
        DebugConsole.getDefault().debug(this.getClass(), "On action click Index 5"); // NOI18N
    }
    
    public void onActionPlayGame() {
        DebugConsole.getDefault().debug(this.getClass(), "On action PlayGame"); // NOI18N
        
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
//        if (GameEngine.getDefault().isGameMode(EGameMode.GAME_MODE__PREVIEW)) {
//            GameEngine.getDefault().switchToGameMode(EGameMode.GAME_MODE__ATTENTION);
//            
//            final boolean showLevelInfo = true;
////            this.switchLevelInfoToGameMode(EGameMode.GAME_MODE__ATTENTION, showLevelInfo);
//            
//            final boolean showPlayButton = false;
//            this.switchPlayButtonToGameMode(EGameMode.GAME_MODE__ATTENTION, showPlayButton);
//        }
//        
//        // EGameMode.GAME_MODE__ATTENTION
//        final SequentialTransition stGameModeAttention = new SequentialTransition();
//        
//        final SequentialTransition stGameModeInformationAttention = GameEngine.getDefault()
//                .createGameModeInformationAnimation(EGameMode.GAME_MODE__ATTENTION);
//        stGameModeAttention.getChildren().add(stGameModeInformationAttention);
//        
//        final SequentialTransition stCounterAnimation = GameEngine.getDefault().createCounterAnimation();
//        stGameModeAttention.getChildren().add(stCounterAnimation);
//        
//        final SequentialTransition stGameButtonsAnimation = GameEngine.getDefault().createGameButtonsAnimation();
//        stGameModeAttention.getChildren().add(stGameButtonsAnimation);
//        
//        stGameModeAttention.setOnFinished(event -> {
//            /*
//            TODO
//              - Remove the LevelInfo
//              - Show the UserInfo
//              - Show the LevelInfo (resetet)
//              - Activate UserInput
//                 - If wrong show EGameMode.GAME_MODE__ERROR
//                    - User have more life? NEW FEATURE
//                       - If no then show EGameMode.GAME_MODE__HIGHSCORE
//                       - If yes then REPEAT the round EGameMode.GAME_MODE__ATTENTION
//                 - If right show NEXT round EGameMode.GAME_MODE__ATTENTION
//            */
//            // EGameMode.GAME_MODE__REMEMBER
//            final SequentialTransition stGameModeRemember = new SequentialTransition();
//            
//            final SequentialTransition stGameModeInformationRemember = GameEngine.getDefault().
//                    createGameModeInformationAnimation(EGameMode.GAME_MODE__REMEMBER);
//            stGameModeRemember.getChildren().add(stGameModeInformationRemember);
//        
//            stGameModeRemember.playFromStart();
//        });
//        
//        stGameModeAttention.playFromStart();
    }
    
    private void switchPlayButtonToGameMode(EGameMode gameMode, boolean showPlayButton) {
        if (gameMode.equals(EGameMode.GAME_MODE__ATTENTION)) {
            bPlayGame.setText("Play"); // NOI18N
            bPlayGame.setVisible(showPlayButton);
        }
        
        if (gameMode.equals(EGameMode.GAME_MODE__PREVIEW)) {
            bPlayGame.setText("Start new game"); // NOI18N
            bPlayGame.setVisible(showPlayButton);
        }
    }
    
}
