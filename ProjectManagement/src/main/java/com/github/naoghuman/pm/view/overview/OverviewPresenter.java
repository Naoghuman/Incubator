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
package com.github.naoghuman.pm.view.overview;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.configuration.IActionConfiguration;
import com.github.naoghuman.pm.dialog.DialogProvider;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Naoghuman
 */
public class OverviewPresenter implements Initializable, IActionConfiguration, IRegisterActions {
    
    @FXML private ListView lvProjectOverview;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.registerActions();
    }
    
    public void onActionCreateProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action create Project"); // NOI18N
        
        final TextInputDialog dialog = DialogProvider.getDialogCreateProject();
        final Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        
        final String projectName = result.get().trim();
        if (projectName.isEmpty()) {
            return;
        }
        
        final TransferData transferData = new TransferData();
        transferData.setActionId(ON_ACTION__CREATE_PROJECT);
        transferData.setString(projectName);
        ActionFacade.INSTANCE.handle(transferData);
    }

    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in OverviewPresenter"); // NOI18N
        
        this.registerOnActionCreateProject();
    }
    
    private void registerOnActionCreateProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action create project"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__CREATE_PROJECT,
                (ActionEvent event) -> {
                    final TransferData transferData = (TransferData) event.getSource();
                    final String projectName = transferData.getString();
                    /*
                       a) Add new project to lvProjectOverview at index 0
                       b) Save project to database.
                          What is with the other projects. After that action all 
                          projects with a new index should be saved to the database.
                    
                       - Later when a project d&d then the new order should be saved
                    */
                }
        );
    }
    
}
