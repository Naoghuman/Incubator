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
package com.github.naoghuman.sbs.application;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.sbs.debug.DebugConsole;
import com.github.naoghuman.sbs.gameengine.GameEngine;
import com.github.naoghuman.sbs.view.backgroundimages.BackgroundImagesView;
import com.github.naoghuman.sbs.view.gamearea.GameAreaPresenter;
import com.github.naoghuman.sbs.view.gamearea.GameAreaView;
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
    
    @FXML private StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().configureTestComponents();
        DebugConsole.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeBackgroundImages();
        this.initializeGameArea();
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    public void initializeBackgroundImages() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize BackgroundImages"); // NOI18N
        
        final BackgroundImagesView view = new BackgroundImagesView();
        stackPane.getChildren().add(0, view.getView());
    }
    
    private void initializeGameArea() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize GameArea"); // NOI18N
        
        final GameAreaView view = new GameAreaView();
        final GameAreaPresenter presenter = view.getRealPresenter();
        presenter.registerActions();
        
        stackPane.getChildren().add(view.getView());
    }
    
    @Override
    public void registerActions() {
        DebugConsole.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
        
        GameEngine.getDefault().registerActions();
    }
    
}
