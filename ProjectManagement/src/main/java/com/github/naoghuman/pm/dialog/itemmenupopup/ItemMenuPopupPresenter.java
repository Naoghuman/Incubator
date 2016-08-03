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
package com.github.naoghuman.pm.dialog.itemmenupopup;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.configuration.INavigationOverviewConfiguration;
import com.github.naoghuman.pm.dialog.DialogProvider;
import com.github.naoghuman.pm.model.ProjectModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Popup;

/**
 *
 * @author Naoghuman
 */
public class ItemMenuPopupPresenter implements Initializable, INavigationOverviewConfiguration {
    
    private Popup popup;
    private ProjectModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ItemMenuPopupPresenter"); // NOI18N
        
    }
    
    public void configure(Popup popup, ProjectModel model) {
        this.popup = popup;
        this.model = model;
    }
    
    public void onActionDeleteProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action delete Project"); // NOI18N

        popup.hide();
        
        final long idToDelete = model.getId();
        final String projectTitle = model.getTitle();
        DialogProvider.showDeleteProjectDialog(idToDelete, projectTitle);
    }
    
    public void onActionEditProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action edit Project"); // NOI18N
        /*
         - Show EditProjectDialog
         - Save to database
            - ProjectModel
            - DailyModel
         - Update Overview
         - Update Daily
        */
        
        popup.hide();
        
        final ProjectModel changedModel = DialogProvider.showEditProjectDialog(model);
        if (changedModel == null) {
            return; // no changes
        }
    }
    
    public void onActionRemoveFromDaily() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action remove from daily"); // NOI18N
        /*
         - Save to database
         - Update Daily
        */
        
        popup.hide();
    }
    
}
