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
package com.github.naoghuman.lib.tile.demo.view.menu.tile;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tile.core.Tile;
import com.github.naoghuman.lib.tile.demo.configuration.IActionConfiguration;
import com.github.naoghuman.lib.tile.demo.view.menu.tile.transparenttexturesitem.TransparentTexturesItemCell;
import com.github.naoghuman.lib.tile.demo.view.menu.tile.transparenttexturesitem.TransparentTexturesItemPresenter;
import com.github.naoghuman.lib.tile.demo.view.menu.tile.transparenttexturesitem.TransparentTexturesItemView;
import com.github.naoghuman.lib.tile.transparenttextures.TransparentTexturesTile;
import com.github.naoghuman.lib.tile.transparenttextures.images.TransparentTexturesTileLoader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;

/**
 *
 * @author Naoghuman
 */
public class TilePresenter implements Initializable, IRegisterActions {
    
    @FXML private ListView lvTransparentTextures;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize TilePresenter"); // NOI18N
        
        this.initializeTransparentTextures();
    }

    private void initializeTransparentTextures() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize TransparentTextures"); // NOI18N
        
        lvTransparentTextures.getItems().clear();
        lvTransparentTextures.setCellFactory(value -> new TransparentTexturesItemCell());
        
        Platform.runLater(() -> {
            final ObservableList<TransparentTexturesTile> tiles = FXCollections.observableArrayList();
            tiles.addAll(TransparentTexturesTile.values());

            final List<TransparentTexturesItemPresenter> presenters = tiles.stream()
                    .map((TransparentTexturesTile tile) -> {
                        final TransparentTexturesItemView view = new TransparentTexturesItemView();
                        final TransparentTexturesItemPresenter presenter = view.getRealPresenter();
                        presenter.configure(view.getView(), tile);

                        return presenter;
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        
            lvTransparentTextures.getItems().addAll(presenters);
        });
        
        lvTransparentTextures.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue instanceof TransparentTexturesItemPresenter) {
                    final TransparentTexturesItemPresenter presenter = (TransparentTexturesItemPresenter) newValue;
                    final Tile tile = presenter.getTile();
                    TilePresenter.this.onActionShowTileBackground(tile);
                }
            }
        });
    }
    
    public void onActionResetTileBackground() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Tile background"); // NOI18N
        
        lvTransparentTextures.getSelectionModel().clearSelection();
        ActionFacade.INSTANCE.handle(IActionConfiguration.ON_ACTION__RESET_TILE_BACKGROUND);
    }

    private void onActionShowTileBackground(Tile tile) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show Tile background"); // NOI18N
        
		final TransferData data = new TransferData();
		data.setActionId(IActionConfiguration.ON_ACTION__SHOW_TILE_BACKGROUND);
		
		final Background background = TransparentTexturesTileLoader.getDefault().loadAsBackground(tile);
		data.setObject(background);
		
		ActionFacade.INSTANCE.handle(data);
	}
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in TilePresenter"); // NOI18N
        
    }
    
}
