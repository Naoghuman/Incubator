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
package com.github.naoghuman.lib.map.generator.demo;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.map.generator.api.MapGeneratorProvider;
import com.github.naoghuman.lib.map.generator.mapwizard.api.IMapWizardConfiguration;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author PRo
 */
public class DemoPresenter implements Initializable, IMapWizardConfiguration, IRegisterActions {
    
    @FXML private AnchorPane apLayerDialog;
    @FXML private VBox vbDesktop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize DemoPresenter");
        
        assert (apLayerDialog != null) : "fx:id=\"apLayerDialog\" was not injected: check your FXML file 'Demo.fxml'."; // NOI18N
        assert (vbDesktop != null)     : "fx:id=\"vbDesktop\" was not injected: check your FXML file 'Demo.fxml'."; // NOI18N
        
        this.initializeMapEditor();
        
        this.registerActions();
        
        this.onActionDialogLayerHide();
    }
    
    private void initializeMapEditor() {
        final Parent mapEditor = MapGeneratorProvider.getDefault().getViewFromMapEditor();
        VBox.setVgrow(mapEditor, Priority.ALWAYS);
        vbDesktop.getChildren().add(mapEditor); 
    }
    
    private void onActionDialogLayerHide() {
        // TODO PRO remove drag+drop?
        // TODO PRO animation
        apLayerDialog.getChildren().clear();
        apLayerDialog.setVisible(Boolean.FALSE);
        apLayerDialog.setManaged(Boolean.FALSE);
    }
    
    private void onActionDialogLayerShow(Parent dialog) {
        // TASK PRO position dialog, drag+drop
        // TODO PRO animation
        apLayerDialog.getChildren().add(dialog);
        apLayerDialog.setVisible(Boolean.TRUE);
        apLayerDialog.setManaged(Boolean.TRUE);
    }
    
    public void onActionShowMapConfiguration() {
        
    }
    
    public void onActionShowMapHelp() {
        
    }
    
    public void onActionShowMapWizard() {
        final Parent mapWizard = MapGeneratorProvider.getDefault().getViewFromMapWizard();
        this.onActionDialogLayerShow(mapWizard);
    }

    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in DemoPresenter");
        
        this.registerOnActionHideMapWizard();
        this.registerOnActionShowMapWizard();
    }
    
    public void registerOnActionHideMapWizard() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action hide MapWizard");
        
        ActionFacade.INSTANCE.register(
                ACTION__HIDE__MAP_WIZARD,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action hide MapWizard");
                    
                    this.onActionDialogLayerHide();
                });
        
        this.onActionDialogLayerHide();
    }
    
    public void registerOnActionShowMapWizard() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action show MapWizard");
        
        ActionFacade.INSTANCE.register(
                ACTION__CREATE__MAP_WIZARD,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action show MapWizard");
                    
                    // TODO PRO save new map to database, update gui
                    final TransferData transferData = (TransferData) event.getSource();
                    System.out.println("Test-Event from: " + transferData.getActionId()); // XXX
                    
                    this.onActionDialogLayerHide();
                });
    }
    
}
