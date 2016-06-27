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
package com.github.naoghuman.pm.dialog.dailysectionchooserdialog;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.DailySectionModel;
import com.github.naoghuman.pm.sql.api.SqlFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author Naoghuman
 */
public class DailySectionChooserDialogPresenter implements Initializable {
    
    @FXML private ListView<DailySectionModel> lvDailySections;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailySectionChooserDialogPresenter"); // NOI18N
        
        this.initializeCombBox();
        
        this.onActionLoadDataInComboBox();
    }

    private void initializeCombBox() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ComboBox"); // NOI18N
        
        // Define rendering of the list of values in ComboBox drop down. 
        lvDailySections.setCellFactory(listView -> new ListCell<DailySectionModel>() {
            @Override
            public void updateItem(DailySectionModel item, boolean empty) {
                super.updateItem(item, empty);
                
                if (
                        item == null
                        || empty
                ) {
                    super.setText(null);
                } else {
                    super.setText(item.getDailyDate());
                }
            }
        });
    }
    
    public DailySectionModel getDailySection() {
        return lvDailySections.getSelectionModel().getSelectedItem();
    }

    private void onActionLoadDataInComboBox() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action load data in ComboBox"); // NOI18N
        
        final ObservableList<DailySectionModel> models = SqlFacade.INSTANCE.getDailySectionSqlProvider().findAll();
        lvDailySections.getItems().addAll(models);
        
        lvDailySections.getSelectionModel().selectFirst();
    }
    
}
