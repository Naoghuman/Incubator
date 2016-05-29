/*
 * Copyright (C) 2015 PRo
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
package com.github.naoghuman.lib.map.generator.mapeditor;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author PRo
 */
public class MapEditorPresenter implements Initializable {
    
    @FXML private AnchorPane apLayerForMapMarkers;
    @FXML private ImageView ivMapBackground;
    @FXML private Label lMapMarkers;
    @FXML private Label lMaps;
    @FXML private ListView lvMapMarkers;
    @FXML private ListView lvMaps;
    @FXML private ScrollPane spMapEditorScroller;
    @FXML private SplitPane spMapEditorEditor;
    @FXML private SplitPane spMapEditorNavigation;
    @FXML private StackPane spMapEditorContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize MapEditorPresenter");
        
        assert (apLayerForMapMarkers != null)   : "fx:id=\"apLayerForMapMarkers\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (ivMapBackground != null)        : "fx:id=\"ivMapBackground\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (lMapMarkers != null)            : "fx:id=\"lMapMarkers\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (lMaps != null)                  : "fx:id=\"lMaps\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (lvMapMarkers != null)           : "fx:id=\"lvMapMarkers\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (lvMaps != null)                 : "fx:id=\"lvMaps\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (spMapEditorScroller != null)    : "fx:id=\"spMapEditorScroller\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (spMapEditorEditor != null)      : "fx:id=\"spMapEditorEditor\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (spMapEditorNavigation != null)  : "fx:id=\"spMapEditorNavigation\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        assert (spMapEditorContent != null)     : "fx:id=\"spMapEditorContent\" was not injected: check your FXML file 'MapEditor.fxml'."; // NOI18N
        
    }
    
}
