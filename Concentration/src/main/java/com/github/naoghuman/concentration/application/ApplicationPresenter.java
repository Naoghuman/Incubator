/*
 * Copyright (C) 2016 PRo
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
package com.github.naoghuman.concentration.application;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 *
 * @author PRo
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    private final FadeTransition ftMenuShowOrHideIt = new FadeTransition(Duration.millis(75.0d));
    
    @FXML private Button bMenu;
    
    private double currentMenuOpacity = 0.0d;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
        assert (bMenu != null) : "fx:id=\"bMenu\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        bMenu.setOpacity(0.0d);
        ftMenuShowOrHideIt.setNode(bMenu);
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    public void onActionMenuClick() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action MenuClick"); // NOI18N
        
    }
    
    public void onActionMouseEntered() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action MouseEntered"); // NOI18N
        
        if (ftMenuShowOrHideIt.getStatus().equals(Animation.Status.RUNNING)) {
            ftMenuShowOrHideIt.stop();
            currentMenuOpacity = bMenu.getOpacity();
        }
        
        bMenu.setManaged(Boolean.TRUE);
        ftMenuShowOrHideIt.setFromValue(currentMenuOpacity);
        ftMenuShowOrHideIt.setToValue(1.0d);
        ftMenuShowOrHideIt.setOnFinished((ActionEvent event) -> {
            currentMenuOpacity = bMenu.getOpacity();
        });
        
        ftMenuShowOrHideIt.playFromStart();
    }
    
    public void onActionMouseExited() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action MouseExited"); // NOI18N
        
        if (ftMenuShowOrHideIt.getStatus().equals(Animation.Status.RUNNING)) {
            ftMenuShowOrHideIt.stop();
            currentMenuOpacity = bMenu.getOpacity();
        }
        
        ftMenuShowOrHideIt.setFromValue(currentMenuOpacity);
        ftMenuShowOrHideIt.setToValue(0.0d);
        ftMenuShowOrHideIt.setOnFinished((ActionEvent event) -> {
            currentMenuOpacity = bMenu.getOpacity();
            bMenu.setManaged(Boolean.FALSE);
        });
        
        ftMenuShowOrHideIt.playFromStart();
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
