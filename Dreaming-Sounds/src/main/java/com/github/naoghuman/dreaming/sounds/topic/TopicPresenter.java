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

import com.github.naoghuman.dreaming.sounds.audioclipbox.AudioClipBox;
import com.github.naoghuman.dreaming.sounds.audioclipbox.AudioClipBoxPresenter;
import com.github.naoghuman.dreaming.sounds.audioclipbox.AudioClipBoxView;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

/**
 *
 * @author Naoghuman
 */
public class TopicPresenter implements Initializable {
    
    @FXML private HBox hbAudioClipBoxes;
    @FXML private Label lTitle;
    @FXML private ScrollPane sbAudioClipBoxes;
    
    private Topic topic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize TopicPresenter"); // NOI18N
        
    }
    
    public void configure(Topic topic) {
        LoggerFacade.getDefault().debug(this.getClass(), "configure"); // NOI18N
        
        this.topic = topic;
        
        lTitle.setText(this.topic.getTitle());
        if (true) { // TODO vm-option == DEBUG
            lTitle.setOnMouseClicked(value -> {
                if (value.getClickCount() == 2) {
                    this.onMouseClickedChangeTitle();
                }
            });
        }
    }
    
    public void onActionAddAudioClipBox() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action add AudioClipBox"); // NOI18N

        final AudioClipBoxView audioClipBoxView = new AudioClipBoxView();
        final AudioClipBoxPresenter audioClipBoxViewPresenter = audioClipBoxView.getRealPresenter();
        
        final AudioClipBox audioClipBox = new AudioClipBox();
        audioClipBox.setTitle("" + System.currentTimeMillis()); // NOI18N
        audioClipBoxViewPresenter.configure(audioClipBox);
        
        hbAudioClipBoxes.getChildren().add(audioClipBoxView.getView());
        
        final int size = hbAudioClipBoxes.getChildren().size();
        double width = size * 300.0d;
        if (size > 1) {
            width += (size - 1) * 14.0d;
        }
        hbAudioClipBoxes.setPrefWidth(width);
    }
    
    private void onMouseClickedChangeTitle() {
        LoggerFacade.getDefault().debug(this.getClass(), "On mouse clicked change Title"); // NOI18N

        final TextInputDialog dialog = new TextInputDialog(lTitle.getText());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Change title"); // NOI18N
        dialog.setResizable(Boolean.FALSE);
        dialog.setTitle("Topic"); // NOI18N
        
        final Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isEmpty()) {
            lTitle.setText(result.get());
        }
        
        // TODO save to db
    }
    
}
