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
package com.github.naoghuman.abclist.exercise.exercisedialog;

import com.github.naoghuman.abclist.configuration.IExerciseConfiguration;
import com.github.naoghuman.abclist.exercise.ETime;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Naoghuman
 */
public class ExerciseDialogPresenter implements Initializable, IExerciseConfiguration {
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    @FXML private Label lTimeCounter;
    @FXML private TextField tfUserInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ExerciseDialogPresenter"); // NOI18N
        
        this.initializeTextFieldUserInput();
    }
    
    private void initializeTextFieldUserInput() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize TextField UserInput"); // NOI18N
        
        tfUserInput.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                this.onActionPressEnter();
            }
        });
        
        Platform.runLater(() -> {
            tfUserInput.requestFocus();
        });
    }
    
    public void configure(ETime time, PropertyChangeListener propertyChangeListener) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure"); // NOI18N
        
        lTimeCounter.setText(time.toString());
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void onActionPauseExrcise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action pause Exercise"); // NOI18N
    }
    
    public void onActionPressEnter() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action press Enter"); // NOI18N
        
        final String userInput = tfUserInput.getText().trim();
        if (userInput.isEmpty()) {
            LoggerFacade.getDefault().warn(this.getClass(), "Empty User input - not a valid [Term]"); // NOI18N
            return;
        }
        
        LoggerFacade.getDefault().debug(this.getClass(), "User typed: " + userInput); // NOI18N
        final Term term = ModelProvider.getDefault().getDefaultTerm(userInput);
        propertyChangeSupport.firePropertyChange(PROP__EXERCISE_DIALOG__USER_TYPED_TERM, null, term);
        
        tfUserInput.setText(null);
        Platform.runLater(() -> {
            tfUserInput.requestFocus();
        });
    }
    
    public void onActionStopExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action stop Exercise"); // NOI18N
        
        propertyChangeSupport.firePropertyChange(PROP__EXERCISE_DIALOG__USER_STOP_EXERCISE, null, null);
    }
    
}
