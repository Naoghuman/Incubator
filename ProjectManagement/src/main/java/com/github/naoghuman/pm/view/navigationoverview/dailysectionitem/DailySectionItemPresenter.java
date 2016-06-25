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
package com.github.naoghuman.pm.view.navigationoverview.dailysectionitem;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.DailySectionModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 *
 * @author Naoghuman
 */
public class DailySectionItemPresenter implements Initializable {
    
    @FXML private Label lDailySectionName;
    
    private Parent parent;
    private DailySectionModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailySectionItemPresenter"); // NOI18N
        
    }

    public void configure(Parent parent, DailySectionModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Configure: " + model.toString()); // NOI18N
        
        this.parent = parent;
        this.model = model;
        
        lDailySectionName.setText(model.getDailyDate());
    }
    
    public String getDailyDate() {
        return model.getDailyDate();
    }
    
    public Parent getParent() {
        return parent;
    }
    
}
