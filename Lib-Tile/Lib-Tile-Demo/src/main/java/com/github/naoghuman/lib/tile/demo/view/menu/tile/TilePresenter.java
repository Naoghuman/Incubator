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

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

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
        
    }
    
    public void onActionResetTileChoose() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Tile choose"); // NOI18N
        
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in TilePresenter"); // NOI18N
        
    }
    
}
