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
package com.github.naoghuman.pm.view.dailyarea;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.dialog.DialogProvider;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Naoghuman
 */
public class DailyAreaPresenter implements Initializable {
    
    @FXML private TabPane tpDailyAreaOverview;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailyAreaOverviewPresenter"); // NOI18N
        
    }
    
    public void onActionShowNewDailyDialog() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show new Daily dialog"); // NOI18N

        // User choose the new date for the DailyArea
        final String date = DialogProvider.showNewDailyDialog();
        if (date == null) {
            return;
        }
        
        // Check if the DailyArea is always open
        final Optional<Tab> result = tpDailyAreaOverview.getTabs().stream()
                .filter(tab -> { 
                    return tab.getText().equals(date); 
                })
                .findFirst();
        if (result.isPresent()) {
            tpDailyAreaOverview.getSelectionModel().select(result.get());
            return;
        }
        
        // TODO create new DailyAreaModel (add to tab, save to db)
        
        // Create a new Tab
        final Tab tab = new Tab();
        tab.setClosable(true);
        tab.setText(date);
        
        // TODO order later the tabs
        tpDailyAreaOverview.getTabs().add(0, tab);
        tpDailyAreaOverview.getSelectionModel().select(tab);
        
        // TODO Save the new DailyArea to database
        
    }
    
}
