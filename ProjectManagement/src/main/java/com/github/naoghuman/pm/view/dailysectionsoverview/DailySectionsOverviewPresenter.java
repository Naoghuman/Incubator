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

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.configuration.INavigationOverviewConfiguration;
import com.github.naoghuman.pm.model.DailySectionModel;
import com.github.naoghuman.pm.model.ProjectModel;
import com.github.naoghuman.pm.view.dailysectionsoverview.dailysectioncontent.DailySectionContentPresenter;
import com.github.naoghuman.pm.view.dailysectionsoverview.dailysectioncontent.DailySectionContentView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class DailySectionsOverviewPresenter implements Initializable, IRegisterActions {
    
    @FXML private Button bNewDailySection;
    @FXML private TabPane tpDailySections;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailySectionPresenter"); // NOI18N
        
        this.initializeNewDailySectionButton();
        
        this.registerActions();
    }
    
    private void initializeNewDailySectionButton() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize new DailySection button"); // NOI18N
        
        bNewDailySection.setTooltip(new Tooltip("Creates a new Daily Section")); // NOI18N
        LoggerFacade.INSTANCE.error(this.getClass(), "TODO use property"); // NOI18N
    }
    
    private void onActionOpenDailySection(DailySectionModel model) {
        // Check if the DailyArea is always open
        final String dailyDate = model.getDailyDate();
        final Optional<Tab> result = tpDailySections.getTabs().stream()
                .filter(tab -> { 
                    return tab.getText().equals(dailyDate); 
                })
                .findFirst();
        
        if (result.isPresent()) {
            tpDailySections.getSelectionModel().select(result.get());
            return;
        }

        // Otherwise create a new Tab
        final Tab tab = new Tab();
        tab.setClosable(true);
        
        final DailySectionContentView view = new DailySectionContentView();
        tab.setContent(view.getView());
        tab.setText(dailyDate);
        tab.setUserData(view.getRealPresenter()); 

        tpDailySections.getTabs().add(0, tab);
        tpDailySections.getSelectionModel().select(tab);
        LoggerFacade.INSTANCE.trace(this.getClass(), "TODO User can filter how to order the tabs"); // NOI18N
    }
    
    private void onActionOpenProjectInDailySection(ProjectModel model) {
        /*
        * TODO
           - If NO DailySection is open then a dialog will open ask the user
             in which DailySection the user will open the Project.
           - (v) If DailySections are open then the Project will added to the selected
             DailySection.
        */
        // Check if no DailySection is open
        if (tpDailySections.getTabs().isEmpty()) {
            LoggerFacade.INSTANCE.trace(this.getClass(), "TODO show dialog which DailySection <- Project"); // NOI18N
            return;
        }
        
        // Add the ProjectModel to the selected DailySection
        final Tab selectedTab = tpDailySections.getSelectionModel().getSelectedItem();
        final DailySectionContentPresenter presenter = (DailySectionContentPresenter) selectedTab.getUserData();
        presenter.addProjectToDailySection(model);
    }
    
    public void onActionShowNewDailySectionDialog() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show new DailySection dialog"); // NOI18N

        ActionFacade.INSTANCE.handle(INavigationOverviewConfiguration.ON_ACTION__SHOW_NEW_DAILY_SECTION_DIALOG);
    }

    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in DailySectionsOverviewPresenter"); // NOI18N
        
        this.registerOnActionCreateNewDailySection();
        this.registerOnActionOpenDailySection();
        this.registerOnActionOpenProjectInDailySection();
        this.registerOnActionUpdateDailySections();
    }
    
    private void registerOnActionCreateNewDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action create new DailySection"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                INavigationOverviewConfiguration.ON_ACTION__CREATE_NEW_DAILY_SECTION,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action create new DailySections"); // NOI18N

                    /*
                     TODO
                      - get transferdata
                      - listview
                      - database
                    */
                }
        );
    }
    
    private void registerOnActionOpenDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action open DailySection"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                INavigationOverviewConfiguration.ON_ACTION__OPEN_DAILY_SECTION,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action open DailySections"); // NOI18N

                    final TransferData transferData = (TransferData) event.getSource();
                    final DailySectionModel model = (DailySectionModel) transferData.getObject();
                    this.onActionOpenDailySection(model);
                }
        );
    }
    
    private void registerOnActionOpenProjectInDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action open Project in DailySection"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                INavigationOverviewConfiguration.ON_ACTION__OPEN_PROJECT_IN_DAILY_SECTION,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action open Project in DailySections"); // NOI18N

                    final TransferData transferData = (TransferData) event.getSource();
                    final ProjectModel model = (ProjectModel) transferData.getObject();
                    this.onActionOpenProjectInDailySection(model);
                }
        );
    }
    
    private void registerOnActionUpdateDailySections() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action update DailySections"); // NOI18N
//        
//        ActionFacade.INSTANCE.register(
//                ON_ACTION__UPDATE_DAILY_SECTIONS,
//                (ActionEvent event) -> {
//                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action update DailySections"); // NOI18N
//
////                    this.initializeDailySectionNavigation();
//                }
//        );
    }
    
}
