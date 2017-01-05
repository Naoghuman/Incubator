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
package com.github.naoghuman.abclist.application;

import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.abclist.sql.SqlProvider;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private SplitPane spApplication;
    @FXML private TreeView<Object> tvAbcList;
    @FXML private VBox vbExercises;
    
    private final TreeItem<Object> rootItem = new TreeItem<> ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeTreeView();

        this.registerActions();
        
        this.onActionRefreshTreeView();
    }
    
    private void initializeTreeView() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize TreeView"); // NOI18N
        
        tvAbcList.setCellFactory((TreeView<Object> p) -> new AbcListTreeCell());
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
    private void onActionCreateNewExercise(Topic topic) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new Exercise"); // NOI18N
        
        // Create a new Exercise
        final Exercise exercise = ModelProvider.getDefault().getDefaultExercise(topic.getId());
        SqlProvider.getDefault().createOrUpdate(exercise);
        
        // Open the exercise TODO
        
        // Show it
        this.onActionRefreshTreeView();
        
        // Expand the TreeItem
        final Optional<TreeItem<Object>> ti = rootItem.getChildren().stream()
                .filter(treeItem -> ((Topic) treeItem.getValue()).equals(topic))
                .findFirst();
        if (ti.isPresent()) {
            ti.get().setExpanded(Boolean.TRUE);
        }
    }
    
    public void onActionCreateNewTopic() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new Topic"); // NOI18N
        
        final TextInputDialog dialog = new TextInputDialog(); // NOI18N
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Create Topic"); // NOI18N
        dialog.setResizable(Boolean.FALSE);
        dialog.setTitle("Topic Wizard"); // NOI18N
        
        final Optional<String> result = dialog.showAndWait();
        if (
                result.isPresent()
                && !result.get().isEmpty()
        ) {
            // Create a new Topic
            final Topic topic = ModelProvider.getDefault().getDefaultTopic(result.get());
            SqlProvider.getDefault().createOrUpdate(topic);
            
            // Show it
            this.onActionRefreshTreeView();
        }
    }

    private void onActionOpenExercise(Exercise exercise) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action open Exercise"); // NOI18N
        
    }

    private void onActionRefreshTreeView() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh TreeView"); // NOI18N
        
        rootItem.getChildren().clear();
        
        final ObservableList<Topic> topics = SqlProvider.getDefault().findAllTopics();
        topics.forEach(topic -> {
            final TreeItem<Object> tiTopic = new TreeItem<>(topic);
            final ObservableList<Exercise> exercises = SqlProvider.getDefault().findAllExercisesWithParentId(topic.getId());
            exercises.forEach(exercise -> {
                final TreeItem<Object> tiExercise = new TreeItem<>(exercise);
                tiTopic.getChildren().add(tiExercise);
            });
            
            rootItem.getChildren().add(tiTopic);
        });
        
        tvAbcList.setRoot(rootItem);
    }
    
    private final class AbcListTreeCell extends TreeCell<Object> {
        
        private final ContextMenu contextMenu = new ContextMenu();
        private final MenuItem menuItem = new MenuItem();
        
        public AbcListTreeCell() {
            contextMenu.getItems().add(menuItem);
        }
        
        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);

            this.configureMenuItem(item);
            this.setContextMenu(!empty ? contextMenu : null);
            this.setGraphic(null);
            this.setText(!empty ? this.getDisplayText(item) : null);
        }
        
        private String getDisplayText(Object item) {
            if (item instanceof Exercise) {
                final Exercise exercise = (Exercise) item;
                return exercise.toString(); // TODO
            }
            
            if (item instanceof Topic) {
                final Topic topic = (Topic) item;
                return topic.getTitle();
            }
            
            return null;
        }
        
        private void configureMenuItem(Object item) {
            if (item instanceof Exercise) {
                final Exercise exercise = (Exercise) item;
                menuItem.setText("Open exercise"); // NOI18N
                ApplicationPresenter.this.onActionOpenExercise(exercise);
            }
            
            if (item instanceof Topic) {
                final Topic topic = (Topic) item;
                menuItem.setText("New exercise"); // NOI18N
                menuItem.setOnAction(value -> {
                    ApplicationPresenter.this.onActionCreateNewExercise(topic);
                });
            }
        }
        
    }
    
}
