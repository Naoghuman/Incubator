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
package com.github.naoghuman.abclist.exercise;

import com.github.naoghuman.abclist.exercise.sign.SignPresenter;
import com.github.naoghuman.abclist.exercise.sign.SignView;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 */
public class ExercisePresenter implements Initializable {
    
    @FXML private Button bStartExercise;
    @FXML private ComboBox<ETime> cbTime;
    @FXML private VBox vbSigns;
    
    private Exercise exercise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ExercisePresenter"); // NOI18N
        
        this.initializeComboBoxTime();
        this.initializeSigns();
    }
    
    private void initializeComboBoxTime() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ComboBox Time"); // NOI18N
        
        cbTime.setCellFactory((ListView<ETime> listview) -> new ListCell<ETime>() {
            @Override
            public void updateItem(ETime item, boolean empty) {
                super.updateItem(item, empty);
                this.setGraphic(null);
                this.setText(!empty ? item.toString() : null);
            }
        });
        
        final ObservableList<ETime> olTimes = FXCollections.observableArrayList();
        olTimes.addAll(ETime.values());
        cbTime.getItems().addAll(olTimes);
        cbTime.getSelectionModel().selectFirst();
        
    }
    
    private void initializeSigns() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Signs"); // NOI18N
        
        vbSigns.getChildren().clear();
        for (ESigns sign : ESigns.values()) {
            final SignView signView = new SignView();
            final SignPresenter signPresenter = signView.getRealPresenter();
            signPresenter.configure(sign.name());
            
            final Parent view = signView.getView();
            view.setId(sign.name());
            vbSigns.getChildren().add(view);
        }
    }
    
    public void configure(Exercise exercise) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure"); // NOI18N
        
        this.exercise = exercise;
        
    }
    
    public void onActionStartExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action start Exercise"); // NOI18N
        
        /*
        TODO
         - extract [Exercise] time (show it)
        */
        final ETime time = cbTime.getSelectionModel().getSelectedItem();
        LoggerFacade.getDefault().debug(this.getClass(), "Start Exercise with Time [" + time.toString() + "]"); // NOI18N
        
    }
    
    private enum ESigns {
        
        A, B, C, D, E, F, G, H, I, J,
        K, L, M, N, O, P, Q, R, S, T,
        U, V, W, X, Y, Z;
        
    }
    
    private enum ETime {
        
        MIN_01_00(60 , "01:00"), // NOI18N
        MIN_02_00(120, "02:00"), // NOI18N
        MIN_03_00(180, "03:00"), // NOI18N
        MIN_05_00(300, "05:00"), // NOI18N
        MIN_10_00(600, "10:00"); // NOI18N
        
        private final int seconds;
        
        private final String presentation;
        
        ETime(int seconds, String presentation) {
            this.seconds = seconds;
            this.presentation = presentation;
        }
        
        public int getSeconds() {
            return seconds;
        }

        @Override
        public String toString() {
            return presentation;
        }
        
    }
    
}
