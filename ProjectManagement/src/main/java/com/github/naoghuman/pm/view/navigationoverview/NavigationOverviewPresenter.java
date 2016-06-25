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
package com.github.naoghuman.pm.view.navigationoverview;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.configuration.IActionConfiguration;
import com.github.naoghuman.pm.dialog.DialogProvider;
import com.github.naoghuman.pm.model.ProjectModel;
import com.github.naoghuman.pm.sql.api.SqlFacade;
import com.github.naoghuman.pm.view.navigationoverview.projectitem.ProjectItemCell;
import com.github.naoghuman.pm.view.navigationoverview.projectitem.ProjectItemPresenter;
import com.github.naoghuman.pm.view.navigationoverview.projectitem.ProjectItemView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Naoghuman
 */
public class NavigationOverviewPresenter implements Initializable, IActionConfiguration, IRegisterActions {
    
    private static final int SELECTED_INDEX__DAILY_SECTIONS = 1;
    private static final int SELECTED_INDEX__PROJECTS = 0;
    
    @FXML private Button bNewDailySectionOrProject;
    @FXML private ListView lvDailySectionsNavigation;
    @FXML private ListView lvProjectsNavigation;
    @FXML private TabPane tpNavigation;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize NavigationOverviewPresenter"); // NOI18N
        
        this.initializeDailySectionNavigation();
        this.initializeProjectNavigation();
        this.initializeTabPane();
        
        this.registerActions();
    }
    
    private void initializeDailySectionNavigation() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize DailySectionNavigation"); // NOI18N
        
    }
    
    private void initializeProjectNavigation() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ProjectNavigation"); // NOI18N
        
        lvProjectsNavigation.getItems().clear();
        lvProjectsNavigation.setCellFactory(value -> new ProjectItemCell());
        
        final ObservableList<ProjectModel> models = SqlFacade.INSTANCE.getProjectSqlProvider().findAll();
        if (models.isEmpty()) {
            return;
        }
        
        final List<ProjectItemPresenter> presenters = models.stream()
                .map((ProjectModel model) -> {
                    final ProjectItemView view = new ProjectItemView();
                    final ProjectItemPresenter presenter = view.getRealPresenter();
                    presenter.configure(view.getView(), model);
                    
                    return presenter;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        lvProjectsNavigation.getItems().addAll(presenters);
    }
    
    private void initializeTabPane() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize TabPane"); // NOI18N
        
        bNewDailySectionOrProject.setTooltip(new Tooltip("Creates a new Project")); // NOI18N
        
        final Tab tDailySection = tpNavigation.getTabs().get(SELECTED_INDEX__DAILY_SECTIONS);
        tDailySection.setOnSelectionChanged(event -> {
            bNewDailySectionOrProject.setTooltip(new Tooltip("Creates a new Daily Section")); // NOI18N
        });
        
        final Tab tProject = tpNavigation.getTabs().get(SELECTED_INDEX__PROJECTS);
        tProject.setOnSelectionChanged(event -> {
            bNewDailySectionOrProject.setTooltip(new Tooltip("Creates a new Project")); // NOI18N
        });
    }
    
    private void onActionNewDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action new DailySection"); // NOI18N
        
    }
    
    public void onActionNewDailySectionOrProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action new Project or DailySection"); // NOI18N
        
        final int selectedIndex = tpNavigation.getSelectionModel().getSelectedIndex();
        switch(selectedIndex) {
            case SELECTED_INDEX__PROJECTS      : { this.onActionNewProject(); break; }
            case SELECTED_INDEX__DAILY_SECTIONS: { this.onActionNewDailySection(); break; }
        }
    }
    
    private void onActionNewProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action new Project"); // NOI18N
        
        final ProjectModel model = DialogProvider.showNewProjectDialog();
        if (model == null) {
            return;
        }
        
        final TransferData transferData = new TransferData();
        transferData.setActionId(ON_ACTION__CREATE_NEW_PROJECT);
        transferData.setObject(model);
        ActionFacade.INSTANCE.handle(transferData);
    }

    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in OverviewPresenter"); // NOI18N
        
        this.registerOnActionCreateProject();
        this.registerOnActionUpdateProjects();
    }
    
    private void registerOnActionCreateProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action create project"); // NOI18N
        
        ActionFacade.INSTANCE.register(ON_ACTION__CREATE_NEW_PROJECT,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action create project"); // NOI18N

                    final TransferData transferData = (TransferData) event.getSource();
                    final ProjectModel model = (ProjectModel) transferData.getObject();
                    
                    final ProjectItemView view = new ProjectItemView();
                    final ProjectItemPresenter presenter = view.getRealPresenter();
                    presenter.configure(view.getView(), model);
                    lvProjectsNavigation.getItems().add(0, presenter);
                    
                    // Do some database stuff
                    SqlFacade.INSTANCE.getProjectSqlProvider().createOrUpdate(model);
                    
                    // Update positions in database
                    if (lvProjectsNavigation.getItems().size() <= 1) {
                        return;
                    }
                    
                    final ObservableList<ProjectModel> models = FXCollections.observableArrayList();
                    final AtomicInteger position = new AtomicInteger(0);
                    lvProjectsNavigation.getItems()
                            .stream()
                            .filter(item -> { 
                                return item != null;
                            })
                            .forEach(item -> {
                                final ProjectItemPresenter itemPresenter = (ProjectItemPresenter) item;
                                final ProjectModel projectModel = itemPresenter.getProjectModel();
                                projectModel.setPosition(position.get());
                                models.add(projectModel);

                                position.addAndGet(1);
                            });

                    SqlFacade.INSTANCE.getProjectSqlProvider().updatePositions(models);
                }
        );
    }
    
    private void registerOnActionUpdateProjects() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action update projects"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__UPDATE_PROJECTS,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action update projects"); // NOI18N

                    this.initializeProjectNavigation();
                }
        );
    }
    
}
