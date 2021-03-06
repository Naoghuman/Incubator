/*
 * Copyright (C) 2016 Name
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
package com.github.naoghuman.lib.card.demo.application;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.card.demo.cardchooser.CardChooserView;
import com.github.naoghuman.lib.card.demo.cardeditor.CardEditorView;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 *
 * @author Name
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {

    @FXML private Tab tCardChooser;
    @FXML private Tab tCardEditor;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeTabChooser();
        this.initializeTabEditor();
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }

    private void initializeTabChooser() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize Tab Chooser"); // NOI18N
        
        final CardChooserView view = new CardChooserView();
        tCardChooser.setContent(view.getView());
    }

    private void initializeTabEditor() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize Tab Editor"); // NOI18N
        
        final CardEditorView view = new CardEditorView();
        tCardEditor.setContent(view.getView());
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
