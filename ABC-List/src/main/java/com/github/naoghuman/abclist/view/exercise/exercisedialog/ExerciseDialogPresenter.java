/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.abclist.view.exercise.exercisedialog;

import com.github.naoghuman.abclist.configuration.IActionConfiguration;
import com.github.naoghuman.abclist.configuration.IExerciseConfiguration;
import com.github.naoghuman.abclist.view.exercise.ETime;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 *
 * @author Naoghuman
 */
public class ExerciseDialogPresenter implements Initializable, IActionConfiguration, IExerciseConfiguration {
    
    private final PauseTransition pauseTransition = new PauseTransition();
    
    @FXML private Button bPauseOrPlay;
    @FXML private Label lTimeCounter;
    @FXML private TextField tfUserInput;
    
    private long exerciseId;
    private int exerciseTime = 60;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [ExerciseDialogPresenter]"); // NOI18N
        
        this.initializeTextFieldUserInput();
    }
    
    private void initializeTextFieldUserInput() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [TextField] [UserInput]"); // NOI18N
        
        tfUserInput.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                this.onActionPressEnter();
            }
        });
        
        Platform.runLater(() -> {
            tfUserInput.requestFocus();
        });
    }
    
    public void configure(long exerciseId, ETime time) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure"); // NOI18N
        
        this.exerciseId = exerciseId;
        
        lTimeCounter.setText(time.toString());
        exerciseTime = time.getSeconds();
        
        pauseTransition.setAutoReverse(false);
        pauseTransition.setDelay(Duration.millis(125.0d));
        pauseTransition.setDuration(Duration.seconds(1.0d));
        pauseTransition.setOnFinished(value -> {
            --exerciseTime;
            this.onActionShowTime(exerciseTime);
            
            if (exerciseTime > 0) {
                pauseTransition.playFromStart();
            }
            else {
                this.onActionStopExercise(ACTION__EXERCISE_DIALOG__EXERCISE_IS_READY + exerciseId);
            }
        });
        
        pauseTransition.playFromStart();
    }
    
    public void onActionPressEnter() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action press [Enter]"); // NOI18N
        
        final String userInput = tfUserInput.getText().trim();
        LoggerFacade.getDefault().debug(this.getClass(), "User typed: " + userInput); // NOI18N
        if (userInput.isEmpty()) {
            LoggerFacade.getDefault().warn(this.getClass(), "Empty User input - not a valid [Term]"); // NOI18N
            return;
        }
	
        final TransferData transferModel = new TransferData();
        transferModel.setActionId(ACTION__EXERCISE_DIALOG__USER_TYPED_TERM + exerciseId);
        System.out.println("-> " + ACTION__EXERCISE_DIALOG__USER_TYPED_TERM + exerciseId);// XXX
        
        transferModel.setString(userInput);
        ActionFacade.getDefault().handle(transferModel);
        
        Platform.runLater(() -> {
            tfUserInput.setText(null);
            tfUserInput.requestFocus();
        });
    }
    
    private void onActionShowTime(int _exerciseTime) {
//        LoggerFacade.getDefault().debug(this.getClass(), "On action show Time: " + _exerciseTime); // NOI18N
        
        final SimpleDateFormat df = new SimpleDateFormat("mm:ss"); // NOI18N
        final String formattedTime = df.format(_exerciseTime * 1000);
        lTimeCounter.setText(formattedTime);
    }
    
    private void onActionStopExercise(String actionId) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action stop [Exercise]: [" + actionId + "]"); // NOI18N
        
        if (pauseTransition.getStatus().equals(Animation.Status.RUNNING)) {
            pauseTransition.stop();
        }
        
        ActionFacade.getDefault().handle(actionId);
    }
    
    public void onActionUserPauseExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action User pause [Exercise]"); // NOI18N
        
        if (
                pauseTransition.getStatus().equals(Animation.Status.RUNNING)
                && bPauseOrPlay.getText().equals("Pause") // NOI18N
        ) {
            pauseTransition.pause();
            bPauseOrPlay.setText("Play"); // NOI18N
            
            return;
        }
        
        if (
                pauseTransition.getStatus().equals(Animation.Status.PAUSED)
                && bPauseOrPlay.getText().equals("Play") // NOI18N
        ) {
            pauseTransition.play();
            bPauseOrPlay.setText("Pause"); // NOI18N
        }
    }
    
    public void onActionUserStopExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action User stop [Exercise]"); // NOI18N
        
        this.onActionStopExercise(ACTION__EXERCISE_DIALOG__USER_STOP_EXERCISE + exerciseId);
    }
    
}
