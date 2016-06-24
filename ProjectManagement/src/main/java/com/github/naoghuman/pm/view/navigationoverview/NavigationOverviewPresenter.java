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
import com.github.naoghuman.pm.view.navigationoverview.item.ItemCell;
import com.github.naoghuman.pm.view.navigationoverview.item.ItemPresenter;
import com.github.naoghuman.pm.view.navigationoverview.item.ItemView;
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
import javafx.scene.control.ListView;

/**
 *
 * @author Naoghuman
 */
public class NavigationOverviewPresenter implements Initializable, IActionConfiguration, IRegisterActions {
    
    @FXML private ListView lvProjectNavigation;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize NavigationOverviewPresenter"); // NOI18N
        
        this.initializeListView();
        
        this.registerActions();
    }
    
    private void initializeListView() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ListView"); // NOI18N
        
        lvProjectNavigation.getItems().clear();
        lvProjectNavigation.setCellFactory(value -> new ItemCell());
        
        final ObservableList<ProjectModel> models = SqlFacade.INSTANCE.getProjectSqlProvider().findAll();
        if (models.isEmpty()) {
            return;
        }
        
        final List<ItemPresenter> presenters = models.stream()
                .map((ProjectModel model) -> {
                    final ItemView view = new ItemView();
                    final ItemPresenter presenter = view.getRealPresenter();
                    presenter.configure(view.getView(), model);
                    
                    return presenter;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        lvProjectNavigation.getItems().addAll(presenters);
    }
    
    public void onActionCreateProject() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action create Project"); // NOI18N
        
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
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__CREATE_NEW_PROJECT,
                (ActionEvent event) -> {
                    LoggerFacade.INSTANCE.debug(this.getClass(), "On action create project"); // NOI18N

                    final TransferData transferData = (TransferData) event.getSource();
                    final ProjectModel model = (ProjectModel) transferData.getObject();
                    
                    final ItemView view = new ItemView();
                    final ItemPresenter presenter = view.getRealPresenter();
                    presenter.configure(view.getView(), model);
                    
                    lvProjectNavigation.getItems().add(0, presenter);
                    
                    // Do database stuff
                    SqlFacade.INSTANCE.getProjectSqlProvider().createOrUpdate(model);
                    
                    if (lvProjectNavigation.getItems().size() <= 1) {
                        return;
                    }
                    
                    final ObservableList<ProjectModel> models = FXCollections.observableArrayList();
                    final AtomicInteger position = new AtomicInteger(0);
                    lvProjectNavigation.getItems()
                            .stream()
                            .filter(item -> { 
                                return item != null;
                            })
                            .forEach(item -> {
                                final ItemPresenter itemPresenter = (ItemPresenter) item;
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

                    this.initializeListView();
                }
        );
    }
    
}
