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
package com.github.naoghuman.pm.dialog.projectdialog;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.ProjectModel;
import com.github.naoghuman.pm.model.api.IProjectModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Naoghuman
 */
public class ProjectDialogPresenter implements Initializable {
    
    @FXML private ColorPicker cpProjectColor;
    @FXML private TextField tfProjectName;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ProjectContentPresenter"); // NOI18N
        
    }
    
    public ProjectModel getProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Get Project"); // NOI18N
        
        final ProjectModel model = new ProjectModel();
        model.setColor(cpProjectColor.getValue());
        
        final String projectName = tfProjectName.getText().trim();
        final String title = projectName.isEmpty() ? IProjectModel.NO_TITLE : projectName;
        model.setTitle(title);
        
        return model;
    }
    
}
