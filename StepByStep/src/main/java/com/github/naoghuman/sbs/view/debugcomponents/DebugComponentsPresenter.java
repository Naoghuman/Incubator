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
package com.github.naoghuman.sbs.view.debugcomponents;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.sbs.configuration.IActionConfiguration;
import com.github.naoghuman.sbs.debug.DebugConsole;
import com.github.naoghuman.sbs.gameengine.EGameMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 *
 * @author Naoghuman
 */
public class DebugComponentsPresenter implements Initializable {
    
    @FXML private Button bSimulate;
    @FXML private CheckBox cbShowHideDebugConsole;
    @FXML private ComboBox<EGameLevel> cbSimulateGameLevel;
    @FXML private ComboBox<EGameMode> cbSimulateGameMode;
    @FXML private TextArea taDebugConsole;
    @FXML private VBox vbDebugOptions;
    
    private EventHandler<ActionEvent> ehShowHideDebugConsole;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().info(this.getClass(), "Initialize DebugComponentsPresenter"); // NOI18N
        
        this.initializeDebugConsole();
        this.initializeDebugOptions();
        this.initializeSimulateGameLevel();
        this.initializeSimulateGameMode();
    }

    private void initializeDebugConsole() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize DebugConsole"); // NOI18N
        
        taDebugConsole.setFont(new Font("SansSerief", 10.0d));
        taDebugConsole.setPrefSize(600.0d, 1052.0d);
        
        ehShowHideDebugConsole = event -> {
            DebugConsole.getDefault().debug(this.getClass(), "On action show/hide DebugConsole(ActionEvent)"); // NOI18N
        
            final Object source = event.getSource();
            if(source instanceof CheckBox) {
                final CheckBox checkBox = (CheckBox) source;
                final boolean showDebugConsole = checkBox.isSelected();

                final TransferData data = new TransferData();
                data.setActionId(IActionConfiguration.ON_ACTION__SHOW_HIDE_DEBUG_CONSOLE);
                data.setBoolean(showDebugConsole);

                ActionFacade.INSTANCE.handle(data);
            }
        };
        cbShowHideDebugConsole.setOnAction(ehShowHideDebugConsole);
    }

    private void initializeDebugOptions() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize DebugOptions"); // NOI18N
        
        vbDebugOptions.setPrefSize(600.0d, 1052.0d);
    }

    private void initializeSimulateGameLevel() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize simulate GameLevel"); // NOI18N
        
        cbSimulateGameLevel.getItems().addAll(EGameLevel.values());
        cbSimulateGameLevel.setCellFactory(new Callback<ListView<EGameLevel>, ListCell<EGameLevel>>() {
            @Override
            public ListCell<EGameLevel> call(ListView<EGameLevel> listView) {
                return new ListCell<EGameLevel>() {
                    @Override
                    protected void updateItem(EGameLevel item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        });
        cbSimulateGameLevel.getSelectionModel().selectFirst();
    }

    private void initializeSimulateGameMode() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize simulate GameMode"); // NOI18N

        cbSimulateGameMode.getItems().addAll(EGameMode.values());
        cbSimulateGameMode.setCellFactory(new Callback<ListView<EGameMode>, ListCell<EGameMode>>() {
            @Override
            public ListCell<EGameMode> call(ListView<EGameMode> listView) {
                return new ListCell<EGameMode>() {
                    @Override
                    protected void updateItem(EGameMode item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        });
        cbSimulateGameMode.getSelectionModel().selectFirst();
    }
    
    public TextArea getDebugConsole() {
        return taDebugConsole;
    }
    
    public VBox getDebugOptions() {
        return vbDebugOptions;
    }
    
    public void onActionSimulate() {
        DebugConsole.getDefault().debug(this.getClass(), "On action Simulate"); // NOI18N
        
    }
    
    public void onActionResetDebugConsole() {
        DebugConsole.getDefault().debug(this.getClass(), "On action reset DebugConsole"); // NOI18N
        
        if (!cbShowHideDebugConsole.isSelected()) {
            return;
        }
        
        cbShowHideDebugConsole.setOnAction(null);
        cbShowHideDebugConsole.setSelected(false);
        cbShowHideDebugConsole.setOnAction(ehShowHideDebugConsole);
    }
    
}
