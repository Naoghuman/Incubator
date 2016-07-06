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
package com.github.naoghuman.pm.view.dailysectionsoverview.projectcontent;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.ProjectModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Naoghuman
 */
public class ProjectContentPresenter implements Initializable {
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ProjectContentPresenter"); // NOI18N
        
    }
    
    public void configure(ProjectModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Configure ProjectModel"); // NOI18N
        
    }
    
}
