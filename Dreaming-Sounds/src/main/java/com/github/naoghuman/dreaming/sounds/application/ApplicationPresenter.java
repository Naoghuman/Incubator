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
package com.github.naoghuman.dreaming.sounds.application;

import com.github.naoghuman.dreaming.sounds.topic.Topic;
import com.github.naoghuman.dreaming.sounds.topic.TopicPresenter;
import com.github.naoghuman.dreaming.sounds.topic.TopicView;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private VBox vbTopics;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
        assert (vbTopics != null) : "fx:id=\"vbTopics\" was not injected: check your FXML file 'application.fxml'."; // NOI18N
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().info(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
    public void onActionAddTopic() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action add Topic"); // NOI18N
        
        final TopicView topicView = new TopicView();
        final TopicPresenter topicPresenter = topicView.getRealPresenter();
        
        final Topic topic = new Topic();
        topic.setTitle("" + System.currentTimeMillis()); // NOI18N
        topicPresenter.configure(topic);
        
        vbTopics.getChildren().add(topicView.getView());
        
        final int size = vbTopics.getChildren().size();
        double height = size * 200.0d;
        if (size > 1) {
            height += (size - 1) * 14.0d;
        }
        vbTopics.setPrefHeight(height);
    }
    
}
