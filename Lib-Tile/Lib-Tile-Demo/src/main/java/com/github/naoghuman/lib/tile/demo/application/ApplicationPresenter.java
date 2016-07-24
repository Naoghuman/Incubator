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
package com.github.naoghuman.lib.tile.demo.application;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tile.demo.configuration.IActionConfiguration;
import com.github.naoghuman.lib.tile.demo.view.menu.background.BackgroundPresenter;
import com.github.naoghuman.lib.tile.demo.view.menu.background.BackgroundView;
import com.github.naoghuman.lib.tile.demo.view.menu.tile.TilePresenter;
import com.github.naoghuman.lib.tile.demo.view.menu.tile.TileView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private BorderPane bpBackground;
    @FXML private BorderPane bpTile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeMenuBackground();
        this.initializeMenuTile();
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }

    private void initializeMenuBackground() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize menu Background"); // NOI18N
        
        final BackgroundView view = new BackgroundView();
        final BackgroundPresenter presenter = view.getRealPresenter();
        presenter.registerActions();
        
        bpBackground.setCenter(view.getView());
    }

    private void initializeMenuTile() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize menu Tile"); // NOI18N
        
        final TileView view = new TileView();
        final TilePresenter presenter = view.getRealPresenter();
        presenter.registerActions();
        
        bpTile.setCenter(view.getView());
    }
    
    private void onActionLoadBackgroundImage(String url) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action load Background image"); // NOI18N
        
        System.out.println("url: " + url);
    }
    
    private void onActionResetBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Background image"); // NOI18N
        
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
        
        this.registerOnActionLoadBackgroundImage();
        this.registerOnActionResetBackgroundImage();
    }

    private void registerOnActionLoadBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on Action load Background image"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                IActionConfiguration.ON_ACTION__SHOW_BACKGROUND_IMAGE,
                (ActionEvent event) -> {
                    final TransferData data = (TransferData) event.getSource();
                    final String url = data.getString();
                    this.onActionLoadBackgroundImage(url);
                });
    }

    private void registerOnActionResetBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on Action reset Background image"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                IActionConfiguration.ON_ACTION__RESET_BACKGROUND_IMAGE,
                (ActionEvent event) -> {
                    this.onActionResetBackgroundImage();
                });
    }
    
}
