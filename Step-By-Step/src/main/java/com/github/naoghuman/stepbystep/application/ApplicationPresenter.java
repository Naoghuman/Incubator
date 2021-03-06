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
package com.github.naoghuman.stepbystep.application;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.stepbystep.background.BackgroundView;
import com.github.naoghuman.stepbystep.gameengine.GameEngineFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {

    @FXML private StackPane spApplication;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeBackground();
        
        GameEngineFacade.getDefault().initialize();
        
        this.registerActions();
    }
    
    public void initializeBackground() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Background"); // NOI18N
        
        final BackgroundView view = new BackgroundView();
        spApplication.getChildren().add(0, view.getView());
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
