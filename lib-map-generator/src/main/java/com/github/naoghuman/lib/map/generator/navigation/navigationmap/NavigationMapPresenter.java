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
package com.github.naoghuman.lib.map.generator.navigation.navigationmap;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 *
 * @author PRo
 */
public class NavigationMapPresenter implements Initializable {
    
    @FXML private Button bNewMap;
    @FXML private ListView lvMaps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize(URL, ResourceBundle) NavigationMapPresenter");
        
        assert (bNewMap != null) : "fx:id=\"bNewMap\" was not injected: check your FXML file 'NavigationMap.fxml'."; // NOI18N
        assert (lvMaps != null)  : "fx:id=\"lvMaps\" was not injected: check your FXML file 'NavigationMap.fxml'."; // NOI18N
        
    }
    
}
