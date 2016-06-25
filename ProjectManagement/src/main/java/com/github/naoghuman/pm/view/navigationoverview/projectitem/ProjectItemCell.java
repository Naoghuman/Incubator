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
package com.github.naoghuman.pm.view.navigationoverview.projectitem;

import com.github.naoghuman.pm.model.ProjectModel;
import com.github.naoghuman.pm.sql.api.SqlFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 *
 * @author Naoghuman
 */
public class ProjectItemCell extends ListCell<ProjectItemPresenter> {

    public ProjectItemCell() {
        // No logging
        this.initializeSetOnDragDetected();
        this.initializeSetOnDragOver();
        this.initializeSetOnDragEntered();
        this.initializeSetOnDragExited();
        this.initializeSetOnDragDropped();
        this.initializeSetOnDragDone();
    }
    
    private void initializeSetOnDragDetected() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize setOnDragDetected"); // NOI18N
        
        super.setOnDragDetected(event -> {
            if (super.getItem() == null) {
                return;
            }

            final Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            final ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(super.getItem().getProjectId()));
//            dragboard.setDragView(
//                    birdImages.get(
//                            items.indexOf(
//                                    getItem()
//                            )
//                    )
//            );
            dragboard.setContent(content);
            event.consume();
        });
    }
    
    private void initializeSetOnDragOver() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize setOnDragOver"); // NOI18N

        super.setOnDragOver(event -> {
            if (
                    event.getGestureSource() != this
                    && event.getDragboard().hasString()
            ) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });
    }
    
    private void initializeSetOnDragEntered() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize setOnDragEntered"); // NOI18N
        
        super.setOnDragEntered(event -> {
            if (
                    event.getGestureSource() != this
                    && event.getDragboard().hasString()
            ) {
                super.getItem().getParent().setOpacity(0.3);
            }
        });
    }
    
    private void initializeSetOnDragExited() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize setOnDragExited"); // NOI18N
        
        super.setOnDragExited(event -> {
            if (
                    event.getGestureSource() != this
                    && event.getDragboard().hasString()
            ) {
                super.getItem().getParent().setOpacity(1.0);
            }
        });
    }
    
    private void initializeSetOnDragDropped() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize setOnDragDropped"); // NOI18N

        super.setOnDragDropped(event -> {
            if (super.getItem() == null) {
                return;
            }

            final Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString()) {
                final List<ProjectItemPresenter> items = this.updateOverview(dragboard);
                this.updateDatabase(items);

                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }
    
    private void initializeSetOnDragDone() {
//        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize setOnDragDone"); // NOI18N
  
        super.setOnDragDone(DragEvent::consume);
    }
    
    private void updateDatabase(List<ProjectItemPresenter> items) {
        final ObservableList<ProjectModel> models = FXCollections.observableArrayList();
        final AtomicInteger position = new AtomicInteger(0);
        items.stream()
                .map((item) -> item.getProjectModel())
                .map((model) -> {
                    model.setPosition(position.get());
                    return model;
                }).forEach((model) -> {
                    models.add(model);
                    position.addAndGet(1);
                });

        SqlFacade.INSTANCE.getProjectSqlProvider().updatePositions(models);
    }
    
    @Override
    protected void updateItem(ProjectItemPresenter item, boolean empty) {
        super.updateItem(item, empty);

        if (
                empty
                || item == null
        ) {
            super.setGraphic(null);
        } else {
            super.setGraphic(item.getParent());
        }
    }
    
    private List<ProjectItemPresenter> updateOverview(Dragboard dragboard) {
        final ObservableList<ProjectItemPresenter> items = super.getListView().getItems();
        int draggedIndex = items.indexOf(dragboard.getString());
        ProjectItemPresenter draggedItem = null;
        for (ProjectItemPresenter item : items) {
            if (item.getProjectId() == Long.parseLong(dragboard.getString())) {
                draggedItem = item;
                draggedIndex = items.indexOf(item);
                super.getItem().getParent().setOpacity(1.0);
                break;
            }
        }
        final int thisIndex = items.indexOf(getItem());

        items.set(draggedIndex, getItem());
        items.set(thisIndex, draggedItem);

        final List<ProjectItemPresenter> itemsCopy = new ArrayList<>(items);
        super.getListView().getItems().setAll(itemsCopy);
        
        return itemsCopy;
    }
    
}
