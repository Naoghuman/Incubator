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
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IActionConfiguration, IRegisterActions {
    
    @FXML private AnchorPane apTileBackground;
    @FXML private BorderPane bpBackground;
    @FXML private BorderPane bpTile;
    @FXML private ImageView ivBackgroundImage;
    @FXML private TitledPane tpBackground;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeBackgroundImage();
        this.initializeMenuBackground();
        this.initializeMenuTile();
        
        this.registerActions();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    
        Platform.runLater(() -> {
            tpBackground.setExpanded(true);
        });
    }
    
    private void initializeBackgroundImage() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize Background image"); // NOI18N
        
        ivBackgroundImage.setFitWidth(1280.0d);
        ivBackgroundImage.setFitHeight(720.0d);
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
    
    private void onActionResetBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Background image"); // NOI18N
        
        ivBackgroundImage.setImage(null);
    }
    
    private void onActionResetTileBackground() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Tile background"); // NOI18N
        
        apTileBackground.setBackground(null);
    }
    
    private void onActionShowBackgroundImage(String url) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show Background image"); // NOI18N
        // https://initiate.alphacoders.com/images/107/cropped-1280-720-10767.png?5608
        // https://initiate.alphacoders.com/images/742/cropped-1280-720-742.jpg?6785
        
        try {
            final Image image = new Image(url, 1280.0d, 720.0d, true, true);
            ivBackgroundImage.setImage(image);
        } catch (NullPointerException | IllegalArgumentException ex) {
            LoggerFacade.INSTANCE.error(this.getClass(), 
                    "Can't load the Background image with the URL: " + url, ex); // NOI18N
            
            this.onActionResetBackgroundImage();
        }
    }
    
    private void onActionShowTileBackground(Background background) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show Tile background"); // NOI18N
        
        apTileBackground.setBackground(background);
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
        
        this.registerOnActionResetBackgroundImage();
        this.registerOnActionResetTileImage();
        this.registerOnActionShowBackgroundImage();
        this.registerOnActionShowTileImage();
    }

    private void registerOnActionResetBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on Action reset Background image"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__RESET_BACKGROUND_IMAGE,
                (ActionEvent event) -> {
                    this.onActionResetBackgroundImage();
                });
    }

    private void registerOnActionResetTileImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on Action reset Tile background"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__RESET_TILE_BACKGROUND,
                (ActionEvent event) -> {
                    this.onActionResetTileBackground();
                });
    }

    private void registerOnActionShowBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on Action show Background image"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__SHOW_BACKGROUND_IMAGE,
                (ActionEvent event) -> {
                    final TransferData data = (TransferData) event.getSource();
                    final String url = data.getString();
                    this.onActionShowBackgroundImage(url);
                });
    }

    private void registerOnActionShowTileImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register on Action show Tile background"); // NOI18N
        
        ActionFacade.INSTANCE.register(
                ON_ACTION__SHOW_TILE_BACKGROUND,
                (ActionEvent event) -> {
                    final TransferData data = (TransferData) event.getSource();
                    final Background background = (Background) data.getObject();
                    this.onActionShowTileBackground(background);
                });
    }
    
}
