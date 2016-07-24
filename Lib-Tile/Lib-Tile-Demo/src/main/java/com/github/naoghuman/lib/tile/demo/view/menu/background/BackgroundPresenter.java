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
package com.github.naoghuman.lib.tile.demo.view.menu.background;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tile.demo.configuration.IActionConfiguration;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Naoghuman
 */
public class BackgroundPresenter implements Initializable, IRegisterActions {
    
    @FXML private TextField tfUrlBackgroundImage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize BackgroundPresenter"); // NOI18N
        
        this.initializeTextField();
    }
    
    private void initializeTextField() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize TextField"); // NOI18N
        
        final Tooltip tt = new Tooltip("Empty URL reset the background image");
        tfUrlBackgroundImage.setTooltip(tt);
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in BackgroundPresenter"); // NOI18N
    }
    
    public void onActionLoadBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action load Background image"); // NOI18N
        
        // Check URL
        final String url = tfUrlBackgroundImage.getText().trim();
        if (
                (url == null)
                || (url.isEmpty())
        ) {
            // Reset the Background image
            ActionFacade.INSTANCE.handle(IActionConfiguration.ON_ACTION__RESET_BACKGROUND_IMAGE);
            return;
        }
        
        // Load new Background image
        final TransferData data = new TransferData();
        data.setActionId(IActionConfiguration.ON_ACTION__SHOW_BACKGROUND_IMAGE);
        data.setString(url);
        
        ActionFacade.INSTANCE.handle(data);
    }
    
}
