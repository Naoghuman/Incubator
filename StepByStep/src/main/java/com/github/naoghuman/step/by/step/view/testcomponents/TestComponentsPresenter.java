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
package com.github.naoghuman.step.by.step.view.testcomponents;

import com.github.naoghuman.step.by.step.debug.DebugConsole;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Naoghuman
 */
public class TestComponentsPresenter implements Initializable {
    
    @FXML private ComboBox cbSimulateGameLevel;
    @FXML private ComboBox cbSimulateGameMode;
    @FXML private TextArea taDebugConsole;
    @FXML private VBox vbDebugOptions;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().info(this.getClass(), "Initialize TestComponentsPresenter"); // NOI18N
        
        this.initializeDebugConsole();
        this.initializeDebugOptions();
        this.initializeSimulateGameMode();
        this.initializeSimulateGameLevel();
    }

    private void initializeDebugConsole() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize DebugConsole"); // NOI18N
        
        taDebugConsole.setFont(new Font("SansSerief", 10.0d));
        taDebugConsole.setPrefSize(600.0d, 1052.0d);
    }

    private void initializeDebugOptions() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize DebugOptions"); // NOI18N
        
        vbDebugOptions.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7)"); // NOI18N
        vbDebugOptions.setPrefSize(600.0d, 1052.0d);
    }

    private void initializeSimulateGameMode() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize simulate GameMode"); // NOI18N
        
    }

    private void initializeSimulateGameLevel() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize simulate GameLevel"); // NOI18N
        
    }
    
    public TextArea getDebugConsole() {
        return taDebugConsole;
    }
    
    public VBox getDebugOptions() {
        return vbDebugOptions;
    }
    
    public void onActionShowTestComponents() {
        DebugConsole.getDefault().debug(this.getClass(), "On action show TestComponents"); // NOI18N
        
    }
    
    public void onActionSimulate() {
        DebugConsole.getDefault().debug(this.getClass(), "On action Simulate"); // NOI18N
        
    }
    
}
