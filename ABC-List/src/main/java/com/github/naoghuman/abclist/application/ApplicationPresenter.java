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
    @FXML private TreeView<Object> tvTopics;
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
        
        tvTopics.setCellFactory((TreeView<Object> p) -> new AbcListTreeCell());
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
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
            
            // Show it - refresh tvTopics
            this.onActionRefreshTreeView();
        }
    }

    private void onActionRefreshTreeView() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh TreeView"); // NOI18N
        
        rootItem.getChildren().clear();
        
        final ObservableList<Topic> topics = SqlProvider.getDefault().findAllTopics();
        TreeItem<Object> item;
        for (Topic topic : topics) {
            item = new TreeItem<>(topic);
            rootItem.getChildren().add(item);
        }
        
        tvTopics.setRoot(rootItem);
    }
    
    private final class AbcListTreeCell extends TreeCell<Object> {
        
        private final ContextMenu addMenu = new ContextMenu();
        private final MenuItem addMenuItem = new MenuItem();
        
        public AbcListTreeCell() {
            addMenu.getItems().add(addMenuItem);
        }
        
        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty) {
                this.setText(this.getDisplayText(item));
                
                addMenuItem.setText(this.getContextMenuText(item));
                this.setContextMenu(addMenu);
            }
            else {
                this.setText(null);
            }
            
            this.setGraphic(null);
        }
        
        private String getDisplayText(Object item) {
            if ( item instanceof Topic ) {
                return ((Topic) item).getTitle();
            }
            
            return null;
        }
        
        private String getContextMenuText(Object item) {
            if (item instanceof Topic) {
                return "This is a Country";
            }
            
            return null;
        }
        
    }
    
}
