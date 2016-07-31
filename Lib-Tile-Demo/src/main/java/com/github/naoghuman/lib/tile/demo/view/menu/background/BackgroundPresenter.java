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
package com.github.naoghuman.lib.tile.demo.view.menu.background;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tile.demo.configuration.IActionConfiguration;
import com.github.naoghuman.lib.tile.demo.configuration.IApplicationConfiguration;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * <ul>
 * <li>Open https://wall.alphacoders.com</li>
 * <li>Search for example -> landscape</li>
 * <li>Choose a wallpaper</li>
 * <li>Crop to 1280x720 (under -> More Resolustion -> 16:9)</li>
 * <li>Submit your changes</li>
 * <li>Copy the graphic address from the cropped image</li>
 * <li>Use the copied graphic address in the TextField</li>
 * </ul>
 * 
 * @author Naoghuman
 */
public class BackgroundPresenter implements Initializable, IActionConfiguration, IRegisterActions {
    
    @FXML private ColorPicker cpBackgroundColor;
    @FXML private TextField tfUrlBackgroundImage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize BackgroundPresenter"); // NOI18N
        
        this.initializeColorPickerForBackgroundColor();
        this.initializeTextFieldForBackgroundImage();
    }
    
    private void initializeColorPickerForBackgroundColor() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ColorPicker for background color"); // NOI18N
        
        cpBackgroundColor.setValue(IApplicationConfiguration.DEFAULT_BACKGROUND_COLOR);
    }
    
    private void initializeTextFieldForBackgroundImage() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize TextField for background image"); // NOI18N
        
        final Tooltip tt = new Tooltip("Images will be resized to 1280x720" // NOI18N
                + "\nEmpty URL reset the background image"); // NOI18N
        tfUrlBackgroundImage.setTooltip(tt);
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Register actions in BackgroundPresenter"); // NOI18N
    }
    
    public void onActionResetBackgroundColor() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Background color"); // NOI18N
        
        cpBackgroundColor.setValue(IApplicationConfiguration.DEFAULT_BACKGROUND_COLOR);
        ActionFacade.INSTANCE.handle(ON_ACTION__RESET_BACKGROUND_COLOR);
    }
    
    public void onActionResetBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action reset Background image"); // NOI18N
        
        tfUrlBackgroundImage.setText(null);
        ActionFacade.INSTANCE.handle(ON_ACTION__RESET_BACKGROUND_IMAGE);
    }
    
    public void onActionShowBackgroundColor() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show Background color"); // NOI18N
        
        final TransferData data = new TransferData();
        data.setActionId(ON_ACTION__SHOW_BACKGROUND_COLOR);
        
        final Color backgroundColor = cpBackgroundColor.getValue();
        data.setObject(backgroundColor);
        
        ActionFacade.INSTANCE.handle(data);
    }
    
    public void onActionShowBackgroundImage() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action show Background image"); // NOI18N
        
        // Check URL
        final String url = tfUrlBackgroundImage.getText().trim();
        if (
                (url == null)
                || (url.isEmpty())
        ) {
            // Reset the Background image
            ActionFacade.INSTANCE.handle(ON_ACTION__RESET_BACKGROUND_IMAGE);
            return;
        }
        
        // Load new Background image
        final TransferData data = new TransferData();
        data.setActionId(ON_ACTION__SHOW_BACKGROUND_IMAGE);
        data.setString(url);
        
        ActionFacade.INSTANCE.handle(data);
    }
    
}
