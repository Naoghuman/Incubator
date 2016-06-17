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
package com.github.naoghuman.pm.view.overview.item;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.ProjectModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author PRo
 */
public class ItemPresenter implements Initializable {
    
    @FXML private Label lProjectName;
    @FXML private Pane pDaDProject;
    
    private ProjectModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ItemPresenter"); // NOI18N
        
    }
    
    public void initialize(ProjectModel model) {
        this.model = model;
        
        // XXX test
        lProjectName.setText(model.getTitle());
    }
    
    public void onActionCountMinusForProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action count minus for project"); // NOI18N
        
    }
    
    public void onActionCountPlusForProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action count plus for project"); // NOI18N
        
    }
    
}
