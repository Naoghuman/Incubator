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
package com.github.naoghuman.umleditor.application;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.umleditor.classdiagram.ClassDiagramProvider;
import com.github.naoghuman.umleditor.configuration.IActionConfiguration;
import com.github.naoghuman.umleditor.view.menu.MenuView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private StackPane spMenu;
    @FXML private TabPane tpDiagrams;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
        assert (spMenu != null) : "fx:id=\"spMenu\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeMenuLayer();
        
        this.registerActions();
    }
    
    private void initializeMenuLayer() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Menu layer"); // NOI18N
        
        final MenuView view = new MenuView();
        spMenu.getChildren().add(view.getView());
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    private void onActionNewClassDiagram() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action new ClassDiagram"); // NOI18N
    
        // TODO changed diagram have * in header
        // TODO close changed tab shows save-question-dialog
        final Tab tab = ClassDiagramProvider.getDefault().createNewTabForClassDiagram();
        tpDiagrams.getTabs().add(tab);
        
        if (!tab.isSelected()) {
            tpDiagrams.getSelectionModel().select(tab);
        }
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    
        this.registerOnActionNewClassDiagram();
    }
    
    private void registerOnActionNewClassDiagram() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action new ClassDiagram"); // NOI18N
    
        ActionFacade.getDefault().register(
                IActionConfiguration.ON_ACTION__NEW_CLASS_DIAGRAM,
                (event) -> {
                    this.onActionNewClassDiagram();
                });
    }
    
}
