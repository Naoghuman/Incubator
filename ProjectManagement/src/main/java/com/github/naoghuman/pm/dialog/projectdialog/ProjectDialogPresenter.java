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
import com.github.naoghuman.pm.model.api.IEntityModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author Naoghuman
 */
public class ProjectDialogPresenter implements Initializable {
    
    @FXML private ColorPicker cpProjectColor;
    @FXML private TextField tfProjectName;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ProjectContentPresenter"); // NOI18N
        
    }
    
    public void configure(ProjectModel model) {
        tfProjectName.setText(model.getTitle());
        cpProjectColor.setValue(model.convertEntityAttributeToColor());
    }
    
    public Color getColor() {
        return cpProjectColor.getValue();
    }
    
    public String getTitle() {
        final String projectName = tfProjectName.getText().trim();
        final String title = projectName.isEmpty() ? IEntityModel.NO_TITLE : projectName;
        
        return title;
    }
    
    public boolean isChanged(ProjectModel originalModel) {
        final boolean isTitleChanged = !originalModel.getTitle().equals(this.getTitle());
        final boolean isColorChanged = !originalModel.convertEntityAttributeToColor().equals(cpProjectColor.getValue());
        final boolean isChanged = isTitleChanged || isColorChanged;
        System.out.println("isChanged: " + isChanged);
        System.out.println("originalModel.getTitle():"+originalModel.getTitle()+"==model.getTitle():"+this.getTitle());
        System.out.println("originalModel.color: " + originalModel.convertEntityAttributeToColor()+"==cp: " +this.getColor());
        
        return isChanged;
    }
    
}
