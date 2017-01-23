/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.abclist.view.application;

import com.github.naoghuman.abclist.configuration.IActionConfiguration;
import com.github.naoghuman.abclist.converter.DateConverter;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.TransferData;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;

/**
 *
 * @author Naoghuman
 */
public class NavigationListTreeCell extends TreeCell<Object> implements IActionConfiguration {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // NOI18N
    private final ContextMenu contextMenu = new ContextMenu();
    private final Date date = new Date();
    private final MenuItem menuItem = new MenuItem();
    private final MenuItem menuItem2 = new MenuItem();

    public NavigationListTreeCell() {
        contextMenu.getItems().add(menuItem);
    }

    private void configureMenuItem(Object item) {
        if (item instanceof Exercise) {
            menuItem.setText("Open Exercise"); // NOI18N
            menuItem.setOnAction(value -> {
                final TransferData transferData = new TransferData();
                transferData.setActionId(ACTION__APPLICATION__OPEN_EXERCISE);
                
                final Exercise exercise = (Exercise) item;
                transferData.setObject(exercise);
                
                ActionFacade.getDefault().handle(transferData);
            });

            if (contextMenu.getItems().contains(menuItem2)) {
                contextMenu.getItems().remove(menuItem2);
            }
        }

        if (item instanceof Topic) {
            menuItem2.setText("Open Topic"); // NOI18N
            menuItem2.setOnAction(value -> {
                final TransferData transferData = new TransferData();
                transferData.setActionId(ACTION__APPLICATION__OPEN_TOPIC);
                
                final Topic topic = (Topic) item;
                transferData.setObject(topic);
                
                ActionFacade.getDefault().handle(transferData);
            });
            
            if (!contextMenu.getItems().contains(menuItem2)) {
                contextMenu.getItems().add(menuItem2);
            }

            menuItem.setText("New Exercise"); // NOI18N
            menuItem.setOnAction(value -> {
                final TransferData transferData = new TransferData();
                transferData.setActionId(ACTION__APPLICATION__CREATE_NEW_EXERCISE);
                
                final Topic topic = (Topic) item;
                transferData.setObject(topic);
                
                ActionFacade.getDefault().handle(transferData);
            });
        }
    }

    private void configureMouseClick(Object item) {
        if (item instanceof Exercise) {
            this.setOnMouseClicked(value -> {
                final int mouseClickCount = value.getClickCount();
                if (mouseClickCount >= 2) {
                    final TransferData transferData = new TransferData();
                    transferData.setActionId(ACTION__APPLICATION__OPEN_EXERCISE);

                    final Exercise exercise = (Exercise) item;
                    transferData.setObject(exercise);

                    ActionFacade.getDefault().handle(transferData);
                }
            });
        }
    }

    private String getDisplayText(Object item) {
        if (item instanceof Exercise) {
            final Exercise exercise = (Exercise) item;

            final StringBuilder sb = new StringBuilder();
            date.setTime(exercise.getGenerationTime());
            sb.append(simpleDateFormat.format(date));
            sb.append(" ["); // NOI18N
            sb.append("done (");
            sb.append(exercise.isReady() ? "v" : "-");
            sb.append(")");
            sb.append("]"); // NOI18N

            return sb.toString();
        }

        if (item instanceof Topic) {
            final Topic topic = (Topic) item;

            final StringBuilder sb = new StringBuilder();
            if (DateConverter.getDefault().isDateInNewRange(topic.getGenerationTime())) {
                sb.append("New | ");
            }
            sb.append(topic.getTitle());
            sb.append(" ("); // NOI18N
            sb.append(topic.getExercises());
            sb.append(")"); // NOI18N

            return sb.toString();
        }

        return null;
    }

    private Tooltip getTooltip(Object item) {
        if (item instanceof Topic) {
            final Topic topic = (Topic) item;

            final StringBuilder sb = new StringBuilder();
            sb.append("Topic '"); // NOI18N
            sb.append(topic.getTitle());
            sb.append("' contains "); // NOI18N
            sb.append(topic.getExercises());
            sb.append(" Exercises."); // NOI18N

            return new Tooltip(sb.toString());
        }

        return null;
    }

    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        this.configureMenuItem(item);
        this.configureMouseClick(item);
        this.setContextMenu(!empty ? contextMenu : null);
        this.setGraphic(null);
        this.setText(!empty ? this.getDisplayText(item) : null);
        this.setTooltip(this.getTooltip(item));
    }
    
}
