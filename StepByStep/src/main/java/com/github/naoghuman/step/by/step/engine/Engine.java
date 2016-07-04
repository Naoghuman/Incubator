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
package com.github.naoghuman.step.by.step.engine;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.util.Optional;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Naoghuman
 */
public final class Engine {
    
    private static final Font FONT_SIZE_56 = new Font(56.0d);
    
    private static final Optional<Engine> instance = Optional.of(new Engine());
    
    public static final Engine getDefault() {
        return instance.get();
    }
    
    private final ObservableList<Integer> precalculatedElements = FXCollections.observableArrayList();
    
    private int counterForRandomIndex = -1;
    private int level = 1;
    
    private Button bGameButton1;
    private Button bGameButton2;
    private Button bGameButton3;
    private Button bGameButton4;
    private Button bGameButton5;
    private Text tLevel;
    private Text tLevelInfo;
    
    private EGameMode gameMode = EGameMode.GAME_MODE__PREVIEW;
    
    private Engine() {
        this.initialize();
    }
    
    private void initialize() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize Engine"); // NOI18N
        
    }

    public void initializePrecalculatedElements() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize precalculated Elements"); // NOI18N
        
        precalculatedElements.clear();
        
        final Random random = new Random();
        for (int i = 0; i < 500; i++) {
            final int nextInt = random.nextInt(5) + 1;
            precalculatedElements.add(nextInt);
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "Precalculated Elements: " + precalculatedElements.toString()); // NOI18N
    }
    
    /*
        GAME_MODE__ATTENTION = false
        GAME_MODE__ERROR     = false
        GAME_MODE__HELP      = false
        GAME_MODE__HIGHSCORE = false
        GAME_MODE__PREVIEW   = false
        GAME_MODE__REMEMBER  = true
        GAME_MODE__SUCCESS   = false
    */
    public boolean checkUserCanClickGameButtons() {
        if (gameMode.equals(EGameMode.GAME_MODE__REMEMBER)) {
            return true;
        }
        
        return false;
    }
    
    /*
        GAME_MODE__ATTENTION = 
        GAME_MODE__ERROR     = 
        GAME_MODE__HELP      = 
        GAME_MODE__HIGHSCORE = 
        GAME_MODE__PREVIEW   = true
        GAME_MODE__REMEMBER  = 
        GAME_MODE__SUCCESS   = 
    */
//    public boolean checkUserCanClickPlayButton() {
//        if (
//                gameMode.equals(EGameMode.GAME_MODE__PREVIEW)
//                || gameMode.equals(EGameMode.GAME_MODE__HIGHSCORE)
//                || gameMode.equals(EGameMode.GAME_MODE__SUCCESS)
//        ) {
//            return true;
//        }
//        
//        return false;
//    }
    
    public SequentialTransition createCounterAnimation() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Create Counter animation"); // NOI18N
        
        final SequentialTransition sequentialTransition = new SequentialTransition();
        
        // 3
        FadeTransition fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
        fadeTransition.setDelay(Duration.millis(125.0d));
        sequentialTransition.getChildren().add(fadeTransition);
        
        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tLevel);
        fadeTransition.setOnFinished(event -> {
            tLevel.setText("2"); // NOI18N
        });
        sequentialTransition.getChildren().add(fadeTransition);
        
        // 2
        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
        sequentialTransition.getChildren().add(fadeTransition);
        
        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tLevel);
        fadeTransition.setOnFinished(event -> {
            tLevel.setText("1"); // NOI18N
        });
        sequentialTransition.getChildren().add(fadeTransition);
        
        // 1
        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
        sequentialTransition.getChildren().add(fadeTransition);
        
        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tLevel);
        fadeTransition.setOnFinished(event -> {
            tLevel.setText("Level"); // NOI18N
            tLevel.setFont(FONT_SIZE_56);
            
            tLevelInfo.setManaged(true);
            tLevelInfo.setVisible(true);
        });
        sequentialTransition.getChildren().add(fadeTransition);
        
        // Level info
        final ParallelTransition parallelTransition = this.createLevelInfoAnimation();
        sequentialTransition.getChildren().add(parallelTransition);
        
        return sequentialTransition;
    }
    
    private FadeTransition createFadeAnimation(double fromValue, double toValue, Node node) {
        final FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5d));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setNode(node);
        
        return fadeTransition;
    }
    
    private ParallelTransition createLevelInfoAnimation() {
        final ParallelTransition parallelTransition = new ParallelTransition();
        
        FadeTransition fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
        parallelTransition.getChildren().add(fadeTransition);
        
        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevelInfo);
        parallelTransition.getChildren().add(fadeTransition);
        
        return parallelTransition;
    }
    
    public EGameMode getGameMode() {
        return gameMode;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getNextRandomElement() {
        ++counterForRandomIndex;
        if (counterForRandomIndex >= precalculatedElements.size()) {
            counterForRandomIndex = 0;
            this.initializePrecalculatedElements();
        }
        
        return precalculatedElements.get(counterForRandomIndex);
    }
    
    public void increaseLevel() {
        ++level;
    }
    
    public boolean isGameMode(EGameMode gameMode) {
        return this.gameMode.equals(gameMode);
    }
    
    public void registerGameButtons(
            Button bGameButton1, Button bGameButton2,
            Button bGameButton3, Button bGameButton4,
            Button bGameButton5
    ) {
        this.bGameButton1 = bGameButton1;
        this.bGameButton2 = bGameButton2;
        this.bGameButton3 = bGameButton3;
        this.bGameButton4 = bGameButton4;
        this.bGameButton5 = bGameButton5;
    }
    
    public void registerLevelInfo(Text tLevel, Text tLevelInfo) {
        this.tLevel = tLevel;
        this.tLevelInfo = tLevelInfo;
    }
    
    public void resetLevel() {
        level = 1;
    }
    
    public void switchToGameMode(EGameMode gameMode) {
        this.gameMode = gameMode;
    }
    
}
