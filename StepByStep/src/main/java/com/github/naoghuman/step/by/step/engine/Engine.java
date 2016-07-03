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
    
    public SequentialTransition createCounterAnimation(Text tLevel, Text tLevelInfo) {
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
        final ParallelTransition parallelTransition = this.createLevelInfoAnimation(tLevel, tLevelInfo);
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
    
    private ParallelTransition createLevelInfoAnimation(Text tLevel, Text tLevelInfo) {
        final ParallelTransition parallelTransition = new ParallelTransition();
        
        FadeTransition fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
        parallelTransition.getChildren().add(fadeTransition);
        
        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevelInfo);
        parallelTransition.getChildren().add(fadeTransition);
        
        return parallelTransition;
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
    
    public void resetLevel() {
        level = 1;
    }
    
}
