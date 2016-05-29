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
package com.github.naoghuman.lib.map.generator.navigation;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author PRo
 */
public class NavigationPresenter implements Initializable {
    
    @FXML private BorderPane bpMaps;
    @FXML private BorderPane bpMapMarkers;
    @FXML private BorderPane bpMapMarkerElements;
    @FXML private SplitPane spNavigation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize(URL, ResourceBundle) NavigationPresenter");
        
        assert (bpMaps != null)              : "fx:id=\"bpMaps\" was not injected: check your FXML file 'Navigation.fxml'."; // NOI18N
        assert (bpMapMarkers != null)        : "fx:id=\"bpMapMarkers\" was not injected: check your FXML file 'Navigation.fxml'."; // NOI18N
        assert (bpMapMarkerElements != null) : "fx:id=\"bpMapMarkerElements\" was not injected: check your FXML file 'Navigation.fxml'."; // NOI18N
    
    }
    
}
