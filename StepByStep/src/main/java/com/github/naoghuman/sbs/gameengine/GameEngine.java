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
package com.github.naoghuman.sbs.gameengine;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.sbs.configuration.IActionConfiguration;
import com.github.naoghuman.sbs.debug.DebugConsole;
import com.github.naoghuman.sbs.view.gameinformations.GameInformationsManager;
import java.util.Optional;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Naoghuman
 */
public final class GameEngine implements IRegisterActions {
    
    private static final int INDEX_1 = 1;
    private static final int INDEX_2 = 2;
    private static final int INDEX_3 = 3;
    private static final int INDEX_4 = 4;
    private static final int INDEX_5 = 5;
    
    private static final Font FONT_SIZE_56 = new Font(56.0d);
    
    private static final Optional<GameEngine> instance = Optional.of(new GameEngine());
    
    private static final String CLICKABLE_COLOR_BASE = "-fx-base: BLANCHEDALMOND;"; // NOI18N
    private static final String NOT_CLICKABLE_COLOR_BASE = "-fx-background-color: BLANCHEDALMOND;"; // NOI18N
    // Style the buttons as circles.
//    private static final String NOT_CLICKABLE_COLOR_BASE = "-fx-background-color: BLANCHEDALMOND; -fx-background-radius: 50;"; // NOI18N
    
    private static final String CLICKABLE_COLOR_1 = "-fx-base: #ef9a9a;"; // NOI18N
    private static final String NOT_CLICKABLE_COLOR_1 = "-fx-background-color: #ef9a9a;"; // NOI18N
    
    private static final String CLICKABLE_COLOR_2 = "-fx-base: #fff59d;"; // NOI18N
    private static final String NOT_CLICKABLE_COLOR_2 = "-fx-background-color: #fff59d;"; // NOI18N
    
    private static final String CLICKABLE_COLOR_3 = "-fx-base: #eeeeee;"; // NOI18N
    private static final String NOT_CLICKABLE_COLOR_3 = "-fx-background-color: #eeeeee;"; // NOI18N
    
    private static final String CLICKABLE_COLOR_4 = "-fx-base: #81d4fa;"; // NOI18N
    private static final String NOT_CLICKABLE_COLOR_4 = "-fx-background-color: #81d4fa;"; // NOI18N
    
    private static final String CLICKABLE_COLOR_5 = "-fx-base: #c5e1a5;"; // NOI18N
    private static final String NOT_CLICKABLE_COLOR_5 = "-fx-background-color: #c5e1a5;"; // NOI18N
    
    public static final GameEngine getDefault() {
        return instance.get();
    }
    
    private final ObservableList<Integer> precalculatedElements = FXCollections.observableArrayList();
    
    private int index = 0;
    private int level = 1;
    
    private Button bGameElement1;
    private Button bGameElement2;
    private Button bGameElement3;
    private Button bGameElement4;
    private Button bGameElement5;
    
    private EGameMode currentGameMode = EGameMode.GAME_MODE__PREVIEW;
    
    private GameEngine() {
        this.initialize();
    }
    
    private void initialize() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize GameEngine"); // NOI18N
        
    }

    private void initializePrecalculatedElements() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize precalculated Elements"); // NOI18N
        
        precalculatedElements.clear();
        this.add100PrecalculatedElements();
        
        DebugConsole.getDefault().debug(this.getClass(), "Precalculated Elements: " + precalculatedElements.toString()); // NOI18N
    }
    
    private void add100PrecalculatedElements() {
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final int nextInt = random.nextInt(5) + 1;
            precalculatedElements.add(nextInt);
        }
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
        if (currentGameMode.equals(EGameMode.GAME_MODE__REMEMBER)) {
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
//                currentGameMode.equals(EGameMode.GAME_MODE__PREVIEW)
//                || currentGameMode.equals(EGameMode.GAME_MODE__HIGHSCORE)
//                || currentGameMode.equals(EGameMode.GAME_MODE__SUCCESS)
//        ) {
//            return true;
//        }
//        
//        return false;
//    }
    
//    public SequentialTransition createCounterAnimation() {
//        DebugConsole.getDefault().debug(this.getClass(), "Create Counter animation"); // NOI18N
//        
//        final SequentialTransition sequentialTransition = new SequentialTransition();
//        
//        // 3
//        FadeTransition fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
//        fadeTransition.setDelay(Duration.millis(500.0d));
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tLevel);
//        fadeTransition.setOnFinished(event -> {
//            tLevel.setText("2"); // NOI18N
//        });
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        // 2
//        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tLevel);
//        fadeTransition.setOnFinished(event -> {
//            tLevel.setText("1"); // NOI18N
//        });
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        // 1
//        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tLevel);
//        fadeTransition.setOnFinished(event -> {
//            tLevel.setText("Level"); // NOI18N
//            tLevel.setFont(FONT_SIZE_56);
//            
//            tLevelInfo.setManaged(true);
//            tLevelInfo.setVisible(true);
//        });
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        // Level info
//        final ParallelTransition parallelTransition = this.createLevelInfoAnimation();
//        sequentialTransition.getChildren().add(parallelTransition);
//        
//        return sequentialTransition;
//    }
    
    private FadeTransition createFadeAnimation(double fromValue, double toValue, Node node) {
        final FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5d));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setNode(node);
        
        return fadeTransition;
    }

//    public SequentialTransition createGameButtonsAnimation() {
//        DebugConsole.getDefault().debug(this.getClass(), "Create GameButtons animation"); // NOI18N
//        
//        final SequentialTransition sequentialTransition = new SequentialTransition();
//        
//        /*
//        TODO
//         - später wird abhängig vom level die dauer des aktiven buttons, 
//           sowie die dauer zwischen den aktivierungen dem level angepasst.
//            - je mehr levels, desto schneller wird das spiel bis max geschwindigkeit
//              ~level 50?
//        */
//        this.resetIndex();
//        
//        for (int i = 1; i <= this.getLevel(); i++) {
//            final int currentIndex = this.getIndex();
//            final int randomElement = this.getRandomElement(currentIndex);
//            
//            final PauseTransition ptShowColor = new PauseTransition();
//            ptShowColor.setDuration(Duration.millis(750.0d));
//            ptShowColor.setOnFinished(event -> {
//                final Button btn = this.getGameButton(randomElement);
//                btn.setStyle(null);
//                btn.setStyle(this.getGameButtonColorNotClickable(randomElement));
//                
//                tLevelInfo.setText(this.getLevel() + " / " + (currentIndex + 1)); // NOI18N
//                DebugConsole.getDefault().debug(this.getClass(), "Create for current index=" // NOI18N
//                        + (currentIndex + 1) + " the RandomElement=" + randomElement); // NOI18N
//            });
//            sequentialTransition.getChildren().add(ptShowColor);
//            
//            final PauseTransition ptHideColor = new PauseTransition();
//            ptHideColor.setDuration(Duration.millis(1250.0d));
//            ptHideColor.setOnFinished(event -> {
//                final Button btn = this.getGameButton(randomElement);
//                btn.setStyle(null);
//                btn.setStyle(NOT_CLICKABLE_COLOR_BASE);
//            });
//            sequentialTransition.getChildren().add(ptHideColor);
//            
//            this.increaseIndex();
//        }
//        
//        return sequentialTransition;
//    }

//    public SequentialTransition createGameModeInformationAnimation(EGameMode currentGameMode) {
//        DebugConsole.getDefault().debug(this.getClass(), "Create GameModeInformation for: " + currentGameMode.toString()); // NOI18N
//        
//        final SequentialTransition sequentialTransition = new SequentialTransition();
//        
//        tPrepareYourSelf.setText(currentGameMode.toString());
//        tPrepareYourSelf.setManaged(true);
//        tPrepareYourSelf.setVisible(true);
//        
//        FadeTransition fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tPrepareYourSelf);
//        fadeTransition.setDelay(Duration.millis(250.0d));
//        
//        fadeTransition = this.createFadeAnimation(1.0d, 0.0d, tPrepareYourSelf);
//        fadeTransition.setDelay(Duration.millis(1000.0d));
//        fadeTransition.setOnFinished(event -> {
//            tPrepareYourSelf.setManaged(false);
//            tPrepareYourSelf.setVisible(false);
//        });
//        sequentialTransition.getChildren().add(fadeTransition);
//        
//        return sequentialTransition;
//    }
    
//    private ParallelTransition createLevelInfoAnimation() {
//        final ParallelTransition parallelTransition = new ParallelTransition();
//        
//        FadeTransition fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevel);
//        parallelTransition.getChildren().add(fadeTransition);
//        
//        fadeTransition = this.createFadeAnimation(0.0d, 1.0d, tLevelInfo);
//        parallelTransition.getChildren().add(fadeTransition);
//        
//        return parallelTransition;
//    }
    
    private int getIndex() {
        return index;
    }
    
    private Button getGameButton(int index) {
        Button btn = null;
        switch (index) {
            case INDEX_1: { btn = bGameElement1; break; }
            case INDEX_2: { btn = bGameElement2; break; }
            case INDEX_3: { btn = bGameElement3; break; }
            case INDEX_4: { btn = bGameElement4; break; }
            case INDEX_5: { btn = bGameElement5; break; }
        }
        
        return btn;
    }
    
    private String getGameButtonColorClickable(int index) {
        String color = null;
        switch (index) {
            case INDEX_1: { color = CLICKABLE_COLOR_1; break; }
            case INDEX_2: { color = CLICKABLE_COLOR_2; break; }
            case INDEX_3: { color = CLICKABLE_COLOR_3; break; }
            case INDEX_4: { color = CLICKABLE_COLOR_4; break; }
            case INDEX_5: { color = CLICKABLE_COLOR_5; break; }
        }
        
        return color;
    }
    
    private String getGameButtonColorNotClickable(int index) {
        String color = null;
        switch (index) {
            case INDEX_1: { color = NOT_CLICKABLE_COLOR_1; break; }
            case INDEX_2: { color = NOT_CLICKABLE_COLOR_2; break; }
            case INDEX_3: { color = NOT_CLICKABLE_COLOR_3; break; }
            case INDEX_4: { color = NOT_CLICKABLE_COLOR_4; break; }
            case INDEX_5: { color = NOT_CLICKABLE_COLOR_5; break; }
        }
        
        return color;
    }
    
    public EGameMode getGameMode() {
        return currentGameMode;
    }
    
    public int getLevel() {
        return level;
    }
    
    private int getRandomElement(int index) {
        if (index >= precalculatedElements.size()) {
            this.add100PrecalculatedElements();
        }
        
        return precalculatedElements.get(index);
    }
    
    private void increaseIndex() {
        ++index;
    }
    
    public void increaseLevel() {
        ++level;
    }
    
    public boolean isGameMode(EGameMode gameMode) {
        return this.currentGameMode.equals(gameMode);
    }

    private void onActionSimulateGameMode(EGameMode gameMode) {
        DebugConsole.getDefault().debug(this.getClass(), "On Action simulate GameMode: " + gameMode.toString()); // NOI18N
        
        /*
        TODO
         what mean simulate gamemode?
         - reset actual gamemode
            - remove the old game-information if shown
            - stop animations
            - stop timeers.
            - ...
         - show new game-informations
         - start in dependency from the new gamemode with animations
        */
    }
    
    public void registerDebugConsole(TextArea taDebugConsole) {
        
    }
    
    public void registerGameButtons(
            Button bGameElement1, Button bGameElement2,
            Button bGameElement3, Button bGameElement4,
            Button bGameElement5
    ) {
        this.bGameElement1 = bGameElement1;
        this.bGameElement2 = bGameElement2;
        this.bGameElement3 = bGameElement3;
        this.bGameElement4 = bGameElement4;
        this.bGameElement5 = bGameElement5;

        // See class LinerotationForEndPoints in TestPackages how to calculate 
        // this endpoints (used also the tool SceneView).
        final double centerX = 200.0;
        final double centerY = 200.0;
        final double halfButtonWidth = 50.0;
        final double halfButtonHeight = 50.0;
        this.bGameElement1.setLayoutX(centerX - halfButtonWidth);
        this.bGameElement1.setLayoutY(centerY - 200.0 - halfButtonHeight);
        
        this.bGameElement2.setLayoutX(centerX + 192.47137451171875 - halfButtonWidth);
        this.bGameElement2.setLayoutY(centerY - 64.0634765625 - halfButtonHeight);
        
        this.bGameElement3.setLayoutX(centerX + 119.953857421875 - halfButtonWidth);
        this.bGameElement3.setLayoutY(centerY + 164.2001953125 - halfButtonHeight);
        
        this.bGameElement4.setLayoutX(centerX - 119.953857421875 - halfButtonWidth);
        this.bGameElement4.setLayoutY(centerY + 164.2001953125 - halfButtonHeight);
        
        this.bGameElement5.setLayoutX(centerX - 192.47137451171875 - halfButtonWidth);
        this.bGameElement5.setLayoutY(centerY - 64.0634765625 - halfButtonHeight);
    }

    @Override
    public void registerActions() {
        DebugConsole.getDefault().debug(this.getClass(), "Register actions in GameEngine"); // NOI18N
        
        GameInformationsManager.getDefault().registerActions();
        
        this.registerOnActionSimulateGameMode();
    }
    
    private void registerOnActionSimulateGameMode() {
        DebugConsole.getDefault().debug(this.getClass(), "Register on Action simulate GameMode"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                IActionConfiguration.ON_ACTION__SIMULATE_GAME_MODE,
                (ActionEvent event) -> {
                    DebugConsole.getDefault().debug(this.getClass(), "On Action simulate GameMode"); // NOI18N
                    
                    final TransferData data = (TransferData) event.getSource();
                    final EGameMode newGameMode = (EGameMode) data.getObject();
                    this.onActionSimulateGameMode(newGameMode);
                });
    }
    
//    public void registerLevelInfo(Text tPrepareYourSelf, Text tLevel, Text tLevelInfo) {
//        this.tPrepareYourSelf = tPrepareYourSelf;
//        this.tLevel = tLevel;
//        this.tLevelInfo = tLevelInfo;
//    }
    
    private void resetIndex() {
        index = 0;
    }
    
    public void resetLevel() {
        level = 1;
    }
    
    private void setGameElementsColorBaseClickable() {
        bGameElement1.setStyle(null);
        bGameElement1.setStyle(CLICKABLE_COLOR_BASE);
        
        bGameElement2.setStyle(null);
        bGameElement2.setStyle(CLICKABLE_COLOR_BASE);
        
        bGameElement3.setStyle(null);
        bGameElement3.setStyle(CLICKABLE_COLOR_BASE);
        
        bGameElement4.setStyle(null);
        bGameElement4.setStyle(CLICKABLE_COLOR_BASE);
        
        bGameElement5.setStyle(null);
        bGameElement5.setStyle(CLICKABLE_COLOR_BASE);
    }
    
    public void setGameElementsColorBaseNotClickable() {
        bGameElement1.setStyle(null);
        bGameElement1.setStyle(NOT_CLICKABLE_COLOR_BASE);
        
        bGameElement2.setStyle(null);
        bGameElement2.setStyle(NOT_CLICKABLE_COLOR_BASE);
        
        bGameElement3.setStyle(null);
        bGameElement3.setStyle(NOT_CLICKABLE_COLOR_BASE);
        
        bGameElement4.setStyle(null);
        bGameElement4.setStyle(NOT_CLICKABLE_COLOR_BASE);
        
        bGameElement5.setStyle(null);
        bGameElement5.setStyle(NOT_CLICKABLE_COLOR_BASE);
    }
    
    public void switchToGameMode(EGameMode gameMode) {
        this.currentGameMode = gameMode;
    }
    
}
