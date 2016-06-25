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
package com.github.naoghuman.pm.view.dailysectionsoverview;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.dialog.DialogProvider;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Naoghuman
 */
public class DailySectionsOverviewPresenter implements Initializable {
    
    @FXML private Button bNewDailySection;
    @FXML private TabPane tpDailySections;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailySectionPresenter"); // NOI18N
        
        this.initializeNewDailySection();
    }
    
    private void initializeNewDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailySectionPresenter"); // NOI18N
        
        bNewDailySection.setTooltip(new Tooltip("Creates a new Daily Section")); // NOI18N
        LoggerFacade.INSTANCE.error(this.getClass(), "TODO use property"); // NOI18N
    }
    
    public void onActionShowNewDailySectionDialog() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show new DailySection dialog"); // NOI18N

        // User choose the new date for the DailyArea
        final String date = DialogProvider.showNewDailySectionDialog();
        if (date == null) {
            return;
        }
        
        // Check if the DailyArea is always open
        final Optional<Tab> result = tpDailySections.getTabs().stream()
                .filter(tab -> { 
                    return tab.getText().equals(date); 
                })
                .findFirst();
        if (result.isPresent()) {
            tpDailySections.getSelectionModel().select(result.get());
            return;
        }
        
        // TODO create new DailyAreaModel (add to tab, save to db)
        
        // Create a new Tab
        final Tab tab = new Tab();
        tab.setClosable(true);
        tab.setText(date);
        
        // TODO order later the tabs
        tpDailySections.getTabs().add(0, tab);
        tpDailySections.getSelectionModel().select(tab);
        
        // TODO Save the new DailyArea to database
        // TODO action Update navigation-dailysections
        
    }
    
}
