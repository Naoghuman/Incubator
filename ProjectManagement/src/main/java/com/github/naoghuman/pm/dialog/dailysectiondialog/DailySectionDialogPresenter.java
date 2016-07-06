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
package com.github.naoghuman.pm.dialog.dailysectiondialog;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.DailySectionModel;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Naoghuman
 */
public class DailySectionDialogPresenter implements Initializable {
    
    @FXML private DatePicker dpDaily;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize DailyDialogPresenter"); // NOI18N
        
        this.initializeDatePicker();
    }
    
    private void initializeDatePicker() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize DailyDialogPresenter"); // NOI18N
        
        dpDaily.setValue(LocalDate.now());
    }

    public String getDate() {
        final LocalDate localDate = dpDaily.getValue();
        final String date = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        return date;
    }

    public DailySectionModel getDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Get DailySection"); // NOI18N
        
        final DailySectionModel model = new DailySectionModel();
        final LocalDate localDate = dpDaily.getValue();
        final String dailyDate = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        model.setDailyDate(dailyDate);
        
        return model;
    }
    
}
