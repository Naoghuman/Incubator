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
package com.github.naoghuman.lib.tag.demo.view.description;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tag.demo.configuration.IActionConfiguration;
import com.github.naoghuman.lib.tag.demo.view.ELibTagType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Naoghuman
 */
public class DescriptionPresenter implements Initializable, IRegisterActions {

    @FXML private Label lDescription;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTagIkonliPresenter"); // NOI18N
        
    }
    
    private void onActionShowDescription(ELibTagType libTagType) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show Description: " + libTagType); // NOI18N
        
        lDescription.setText(libTagType.name());
    }

    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in DescriptionPresenter"); // NOI18N
    
        this.registerOnActionShowDescription();
    }

    private void registerOnActionShowDescription() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action show Description"); // NOI18N
        
        ActionFacade.getDefault().register(
                IActionConfiguration.ON_ACTION__SHOW_DESCRIPTION,
                (event) -> {
                    final TransferData data = (TransferData) event.getSource();
                    final ELibTagType libTagType = (ELibTagType) data.getObject();
                    
                    this.onActionShowDescription(libTagType);
                });
    }
    
}
