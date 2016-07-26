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
package com.github.naoghuman.lib.tile.demo.view.menu.tile.transparenttexturesitem;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tile.transparenttextures.TransparentTexturesTile;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Naoghuman
 */
public final class TransparentTexturesItemPresenter implements Initializable {
    
    @FXML private Label lAutor;
    @FXML private Label lHeader;
    
    private Parent parent;
    private TransparentTexturesTile tile;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.trace(this.getClass(), "Initialize TransparentTexturesItemPresenter"); // NOI18N
    }
    
    public final void configure(Parent parent, TransparentTexturesTile tile) {
        LoggerFacade.INSTANCE.trace(this.getClass(), "Configure tile: " + tile.getName()); // NOI18N
        
        this.parent = parent;
        this.tile = tile;
        
        // Header
        lHeader.setText(tile.getHeader());
	
        // Autor
        lAutor.setText(tile.getAutor());
		
        // Url
        final String url = tile.getUrl();
        if (
                (url != null)
                && (!url.isEmpty())
        ) {
            // URL -> handling
            lAutor.setCursor(Cursor.CLOSED_HAND);
            lAutor.setOnMouseClicked(event -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URL(url).toURI());
                    } catch (URISyntaxException | IOException ex) {
                        LoggerFacade.INSTANCE.error(this.getClass(), "Can't open URL in system browser: " + url, ex); // NOI18N
                    }
                }
            });
        }
        else {
            // No URL - no handling
            lAutor.setFont(new Font("System Italic", 14.0d)); // NOI18N
        }
    }
    
    public final Parent getParent() {
        return parent;
    }
    
    public final TransparentTexturesTile getTile() {
        return tile;
    }
    
}
