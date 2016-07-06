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
import com.github.naoghuman.pm.configuration.INavigationOverviewConfiguration;
import com.github.naoghuman.pm.dialog.DialogProvider;
import com.github.naoghuman.pm.model.DailySectionModel;
import com.github.naoghuman.pm.model.ProjectModel;
import com.github.naoghuman.pm.sql.api.SqlFacade;
import com.github.naoghuman.pm.view.navigationoverview.dailysectionitem.DailySectionItemCell;
import com.github.naoghuman.pm.view.navigationoverview.dailysectionitem.DailySectionItemPresenter;
import com.github.naoghuman.pm.view.navigationoverview.dailysectionitem.DailySectionItemView;
import com.github.naoghuman.pm.view.navigationoverview.projectitem.ProjectItemCell;
import com.github.naoghuman.pm.view.navigationoverview.projectitem.ProjectItemPresenter;
import com.github.naoghuman.pm.view.navigationoverview.projectitem.ProjectItemView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Naoghuman
 */
public class NavigationOverviewPresenter implements Initializable, INavigationOverviewConfiguration, IRegisterActions {
    
    private static final int SELECTED_INDEX__DAILY_SECTIONS = 1;
    private static final int SELECTED_INDEX__PROJECTS = 0;
    
    @FXML private Button bNewDailySection;
    @FXML private Button bNewProject;
    @FXML private ListView<DailySectionItemPresenter> lvDailySectionsNavigation;
    @FXML private ListView<ProjectItemPresenter> lvProjectsNavigation;
    @FXML private TabPane tpNavigationOverview;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize NavigationOverviewPresenter"); // NOI18N
        
        this.initializeButtons();
        this.initializeDailySectionsNavigation();
        this.initializeProjectsNavigation();
        
        this.registerActions();
    }
    
    private void initializeButtons() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize Buttons"); // NOI18N
        
        bNewDailySection.setTooltip(new Tooltip("Creates a new Daily Section")); // NOI18N
        LoggerFacade.INSTANCE.trace(this.getClass(), "TODO use property"); // NOI18N
        
        bNewProject.setTooltip(new Tooltip("Creates a new Project")); // NOI18N
        LoggerFacade.INSTANCE.trace(this.getClass(), "TODO use property"); // NOI18N
    }
    
    private void initializeDailySectionsNavigation() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize DailySectionNavigation"); // NOI18N
        
        lvDailySectionsNavigation.getItems().clear();
        lvDailySectionsNavigation.setCellFactory(value -> new DailySectionItemCell());
        
        final ObservableList<DailySectionModel> models = SqlFacade.INSTANCE.getDailySectionSqlProvider().findAll();
        if (models.isEmpty()) {
            return;
        }
        
        final List<DailySectionItemPresenter> presenters = models.stream()
                .map((DailySectionModel model) -> {
                    final DailySectionItemView view = new DailySectionItemView();
                    final DailySectionItemPresenter presenter = view.getRealPresenter();
                    presenter.configure(view.getView(), model);
                    
                    return presenter;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        lvDailySectionsNavigation.getItems().addAll(presenters);
    }
    
    private void initializeProjectsNavigation() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ProjectNavigation"); // NOI18N
        
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
    
    public void onActionNewDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action new DailySection"); // NOI18N
        
        ActionFacade.INSTANCE.handle(ON_ACTION__SHOW_NEW_DAILY_SECTION_DIALOG);
    }
    
    public void onActionNewProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action new Project"); // NOI18N
        
        tpNavigationOverview.getSelectionModel().select(SELECTED_INDEX__PROJECTS);
        
        final ProjectModel model = DialogProvider.showNewProjectDialog();
        if (model == null) {
            return;
        }
        
        final Optional<ProjectItemPresenter> result = lvProjectsNavigation.getItems().stream()
                .filter(item -> {
                    final ProjectItemPresenter presenter = (ProjectItemPresenter) item;
                    boolean modelExists = false;
                    if (presenter.getProjectModel().getTitle().equals(model.getTitle())) {
                        modelExists = true;
                    }
                    
                    return modelExists;
                })
                .findFirst();
        if (
                result.isPresent()
                && result.get() != null
        ) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "The Project always exists. No new Project will created!"); // NOI18N
            LoggerFacade.INSTANCE.trace(this.getClass(), "TODO add user feedback"); // NOI18N
            return;
        }
        
        final TransferData transferData = new TransferData();
        transferData.setActionId(ON_ACTION__CREATE_NEW_PROJECT);
        transferData.setObject(model);
        ActionFacade.INSTANCE.handle(transferData);
    }
    
    private void onActionShowNewDailySectionDialog() {
        tpNavigationOverview.getSelectionModel().select(SELECTED_INDEX__DAILY_SECTIONS);
        
        final DailySectionModel model = DialogProvider.showNewDailySectionDialog();
        if (model == null) {
            return;
        }

        final Optional<DailySectionItemPresenter> result = lvDailySectionsNavigation.getItems().stream()
                .filter(item -> {
                    final DailySectionItemPresenter presenter = (DailySectionItemPresenter) item;
                    boolean modelExists = false;
                    if (presenter.getDailyDate().equals(model.getDailyDate())) {
                        modelExists = true;
                    }

                    return modelExists;
                })
                .findFirst();
        if (
                result.isPresent()
                && result.get() != null
        ) {
            LoggerFacade.INSTANCE.debug(this.getClass(), "The DailySection always exists. No new DailySection will created!"); // NOI18N
            LoggerFacade.INSTANCE.trace(this.getClass(), "TODO add user feedback"); // NOI18N
            return;
        }

        final TransferData transferData = new TransferData();
        transferData.setActionId(ON_ACTION__CREATE_NEW_DAILY_SECTION);
        transferData.setObject(model);
        ActionFacade.INSTANCE.handle(transferData);
    }

    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in NavigationOverviewPresenter"); // NOI18N
        
        this.registerOnActionCreateNewDailySection();
        this.registerOnActionCreateNewProject();
        this.registerOnActionDailySections();
        this.registerOnActionShowNewDailySectionDialog();
        this.registerOnActionUpdateProjects();
    }
    
    private void registerOnActionCreateNewDailySection() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action create new DailySection"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__CREATE_NEW_DAILY_SECTION,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action create DailySection"); // NOI18N

                    final TransferData transferData = (TransferData) event.getSource();
                    final DailySectionModel model = (DailySectionModel) transferData.getObject();
                    
                    final DailySectionItemView view = new DailySectionItemView();
                    final DailySectionItemPresenter presenter = view.getRealPresenter();
                    presenter.configure(view.getView(), model);
                    
                    lvDailySectionsNavigation.getItems().add(presenter);
                    Collections.sort(
                        lvDailySectionsNavigation.getItems(),
                        (presenter1, presenter2) -> 
                            presenter2.getDailyDate().compareTo(presenter1.getDailyDate())
                        );
                    lvDailySectionsNavigation.getSelectionModel().select(presenter);
                    
                    // Do some database stuff
                    SqlFacade.INSTANCE.getDailySectionSqlProvider().createOrUpdate(model);
                }
        );
    }
    
    private void registerOnActionCreateNewProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action create new Project"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__CREATE_NEW_PROJECT,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action create Project"); // NOI18N
                    LoggerFacade.INSTANCE.trace(this.getClass(), "TODO method block is to long"); // NOI18N

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
    
    private void registerOnActionDailySections() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action update DailySections"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__UPDATE_DAILY_SECTIONS,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action update DailySections"); // NOI18N

                    this.initializeDailySectionsNavigation();
                }
        );
    }
    
    private void registerOnActionShowNewDailySectionDialog() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action show new DailySection dialog"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__SHOW_NEW_DAILY_SECTION_DIALOG,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action show new DailySection dialog"); // NOI18N

                    this.onActionShowNewDailySectionDialog();
                }
        );
    }
    
    private void registerOnActionUpdateProjects() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on action update Projects"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__UPDATE_PROJECTS,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action update Projects"); // NOI18N

                    this.initializeProjectsNavigation();
                }
        );
    }
    
}
