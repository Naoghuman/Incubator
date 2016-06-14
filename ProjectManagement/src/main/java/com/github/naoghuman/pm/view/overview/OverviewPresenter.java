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
package com.github.naoghuman.pm.view.overview;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.dialog.DialogProvider;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Naoghuman
 */
public class OverviewPresenter implements Initializable {
    
    @FXML private ListView lvProjectOverview;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void onActionCreateProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action create Project"); // NOI18N
        
        final TextInputDialog dialog = DialogProvider.getDialogCreateProject();
        final Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        
        final String name = result.get().trim();
        if (name.isEmpty()) {
            return;
        }
        LoggerFacade.INSTANCE.debug(this.getClass(), "------------->><<"); // NOI18N
        
        final TransferData transferData = new TransferData();
//        transferData.setActionId(ACTION__CREATE__CATEGORY);
//        transferData.setLong(matrixId);
        transferData.setString(name);
        ActionFacade.INSTANCE.handle(transferData);
    }
    
}
