/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.abclist.exercise.sign;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Naoghuman
 */
public class SignPresenter implements Initializable {
    
    @FXML private FlowPane fpWords;
    @FXML private HBox hbSign;
    @FXML private Label lSign;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize SignPresenter"); // NOI18N
        
        // TODO only [Sign]s with [Word]s are visible
//        hbSign.managedProperty().bind(Bindings.isNotEmpty(fpWords.getChildren()));
//        hbSign.visibleProperty().bind(Bindings.isNotEmpty(fpWords.getChildren()));
    }
    
    public void configure(String sign) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure sign [" + sign + "]"); // NOI18N
        
        lSign.setText(sign + ":"); // NOI18N
    }
    
}
