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
package com.github.naoghuman.five.points.application;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
//    @FXML private ScrollPane spDoing;
//    @FXML private ScrollPane spDone;
    @FXML private ScrollPane spPlaning;
    
    @FXML private VBox vbDoing;
    @FXML private VBox vbDone;
    @FXML private VBox vbPlaning;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
        assert (vbDoing != null)   : "fx:id=\"vbDoing\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        assert (vbDone != null)    : "fx:id=\"vbDone\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        assert (vbPlaning != null) : "fx:id=\"vbPlaning\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    public void onActionAddTheme() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action add Theme"); // NOI18N
        
        Button b = new Button();
        b.setMaxWidth(Double.MAX_VALUE);
        vbPlaning.getChildren().add(b);
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
