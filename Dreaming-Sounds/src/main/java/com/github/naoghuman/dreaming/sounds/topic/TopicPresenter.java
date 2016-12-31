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
package com.github.naoghuman.dreaming.sounds.topic;

import com.github.naoghuman.dreaming.sounds.soundbox.SoundBox;
import com.github.naoghuman.dreaming.sounds.soundbox.SoundBoxPresenter;
import com.github.naoghuman.dreaming.sounds.soundbox.SoundBoxView;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Naoghuman
 */
public class TopicPresenter implements Initializable {
    
    @FXML private HBox hbSoundBoxes;
    @FXML private Label lTitle;
    @FXML private ScrollPane spSoundBoxes;
    
    private Topic topic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize TopicPresenter"); // NOI18N
        
    }
    
    public void configure(Topic topic) {
        LoggerFacade.getDefault().debug(this.getClass(), "configure"); // NOI18N
        
        this.topic = topic;
        
        lTitle.setText(this.topic.getTitle());
    }
    
    public void onActionAddSoundBox() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action add SoundBox"); // NOI18N

        final SoundBoxView soundBoxView = new SoundBoxView();
        final SoundBoxPresenter soundBoxViewPresenter = soundBoxView.getRealPresenter();
        
        final SoundBox soundBox = new SoundBox();
        soundBox.setTitle("" + System.currentTimeMillis()); // NOI18N
        soundBoxViewPresenter.configure(soundBox);
        
        hbSoundBoxes.getChildren().add(soundBoxView.getView());
        
        final int size = hbSoundBoxes.getChildren().size();
        double width = size * 300.0d;
        if (size > 1) {
            width += (size - 1) * 14.0d;
        }
        hbSoundBoxes.setPrefWidth(width);
    }
    
}
