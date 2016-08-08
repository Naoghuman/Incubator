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
package com.github.naoghuman.sbs.view.gamearea;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.sbs.configuration.IActionConfiguration;
import com.github.naoghuman.sbs.debug.DebugConsole;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 *
 * @author Naoghuman
 */
public class GameAreaPresenter implements Initializable, IRegisterActions {
    
    @FXML private Button bShowRightMenu;
    @FXML private GridPane gpGameArea;
    @FXML private VBox vbLeftMenu;
    @FXML private VBox vbRightMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DebugConsole.getDefault().info(this.getClass(), "Initialize GameAreaPresenter"); // NOI18N
        
        this.initializeScreenSize();
    }
    
    private void initializeScreenSize() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize ScreenSize"); // NOI18N
        
        final Rectangle2D screenSize = Screen.getPrimary().getBounds();
        gpGameArea.setPrefSize(
                screenSize.getWidth() - 28.0d,
                screenSize.getHeight() - 28.0d);
    }
    
    private boolean isRigthMenuAdded() {
        return vbRightMenu.getChildren().size() > 1;
    }
    
    private void onActionShowHideLeftMenu(boolean showDebugConsole) {
        DebugConsole.getDefault().debug(this.getClass(), "On action show LeftMenu: " + showDebugConsole); // NOI18N
        
        if (showDebugConsole) {
            final TextArea taDebugConsole = DebugConsole.getDefault().getDebugConsole();
            vbLeftMenu.getChildren().add(taDebugConsole);
            VBox.setVgrow(taDebugConsole, Priority.ALWAYS);
            return;
        }
        
        vbLeftMenu.getChildren().remove(0);
        DebugConsole.getDefault().onActionResetDebugConsole();
    }
    
    public void onActionShowRightMenu() {
        DebugConsole.getDefault().debug(this.getClass(), "On action show RightMenu"); // NOI18N

        final boolean isRightMenuAdded = this.isRigthMenuAdded();
        if (isRightMenuAdded) {
            vbRightMenu.getChildren().remove(1);
            this.onActionShowHideLeftMenu(false);
            return;
        }
        
        final VBox vbDebugOptions = DebugConsole.getDefault().getDebugOptions();
        vbRightMenu.getChildren().add(vbDebugOptions);
        VBox.setVgrow(vbDebugOptions, Priority.ALWAYS);
    }

    @Override
    public void registerActions() {
        DebugConsole.getDefault().debug(this.getClass(), "Register actions in GameAreaPresenter"); // NOI18N
        
        this.registerOnActionShowHideDebugConsole();
    }

    private void registerOnActionShowHideDebugConsole() {
        DebugConsole.getDefault().debug(this.getClass(), "Register on action show/hide GameAreaPresenter"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                IActionConfiguration.ON_ACTION__SHOW_HIDE_DEBUG_CONSOLE,
                event -> {
                    final TransferData data = (TransferData) event.getSource();
                    final boolean showDebugConsole = data.getBoolean();
                    this.onActionShowHideLeftMenu(showDebugConsole);
                });
    }
    
}
