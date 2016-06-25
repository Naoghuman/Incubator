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
package com.github.naoghuman.pm.view.navigationoverview.projectitem;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.dialog.itemmenupopup.ItemMenuPopupPresenter;
import com.github.naoghuman.pm.dialog.itemmenupopup.ItemMenuPopupView;
import com.github.naoghuman.pm.model.ProjectModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

/**
 *
 * @author Naoghuman
 */
public class ProjectItemPresenter implements Initializable {
    
    @FXML private Label lProjectName;
    @FXML private Pane pProjectColor;
    
    private Parent parent;
    private ProjectModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Initialize ProjectItemPresenter"); // NOI18N
        
    }

    public void configure(Parent parent, ProjectModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Configure: " + model.toString()); // NOI18N
        
        this.parent = parent;
        this.model = model;
        
        lProjectName.setText(model.getTitle());
        pProjectColor.setStyle(model.getColorAsStyle());
    }
    
    public Parent getParent() {
        return parent;
    }
    
    public long getProjectId() {
        return model.getId();
    }
    
    public ProjectModel getProjectModel() {
        return model;
    }
    
    public void onMouseClickedShowItemMenuPopup(MouseEvent event) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On mouse clicked show ItemMenu popup"); // NOI18N
        
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        
        final ItemMenuPopupView view = new ItemMenuPopupView();
        final ItemMenuPopupPresenter presenter = view.getRealPresenter();
        presenter.configure(popup, model);
        popup.getContent().add(view.getView());
        
        popup.show(parent, event.getScreenX(), event.getScreenY());
    }
    
}
