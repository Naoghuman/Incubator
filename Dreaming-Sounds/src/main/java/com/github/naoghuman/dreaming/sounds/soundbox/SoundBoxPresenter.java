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
package com.github.naoghuman.dreaming.sounds.soundbox;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;

/**
 *
 * @author Naoghuman
 */
public class SoundBoxPresenter implements Initializable {
    
    @FXML private Label lTitle;
    
    private SoundBox soundBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize SoundBoxPresenter"); // NOI18N
        
    }
    
    public void configure(SoundBox soundBox) {
        LoggerFacade.getDefault().debug(this.getClass(), "configure"); // NOI18N
        
        this.soundBox = soundBox;
        
        lTitle.setText(this.soundBox.getTitle());
        
        lTitle.setText(this.soundBox.getTitle());
        if (true) { // TODO vm-option == DEBUG
            lTitle.setOnMouseClicked(value -> {
                if (value.getClickCount() == 2) {
                    this.onMouseClickedChangeTitle();
                }
            });
        }
    }
    
    private void onMouseClickedChangeTitle() {
        LoggerFacade.getDefault().debug(this.getClass(), "On mouse clicked change Title"); // NOI18N

        final TextInputDialog dialog = new TextInputDialog(lTitle.getText());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Change title"); // NOI18N
        dialog.setResizable(Boolean.FALSE);
        dialog.setTitle("AudioClip"); // NOI18N
        
        final Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isEmpty()) {
            lTitle.setText(result.get());
        }
        
        // TODO save to db
    }
    
}
