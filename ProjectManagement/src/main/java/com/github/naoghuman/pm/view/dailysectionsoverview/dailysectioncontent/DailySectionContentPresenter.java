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
package com.github.naoghuman.pm.view.dailysectionsoverview.dailysectioncontent;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.ProjectModel;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 */
public class DailySectionContentPresenter implements Initializable {
    
    @FXML private ScrollPane spDailySectionContent;
    @FXML private VBox vbDailySectionContent;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize DailySectionContentPresenter"); // NOI18N
        
        this.initializeScrollPane();
    }
    
    private void initializeScrollPane() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize ScrollPane"); // NOI18N
        
        spDailySectionContent.viewportBoundsProperty().addListener(
                (ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) -> {
                    vbDailySectionContent.setPrefWidth(bounds.getWidth());
                });
    }

    public void addProjectToDailySection(ProjectModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Add Project to DailySection"); // NOI18N

        // Check if the Project is always open
        final boolean projectIsAlwaysAdded = this.onActionCheckAddProjectToDailySection(model);
        if (projectIsAlwaysAdded) {
            return;
        }
        
        // The Project will be open in new TitledPane
        this.onActionAddProjectToDailySection(model);
    }
    
    private boolean onActionCheckAddProjectToDailySection(ProjectModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action check add Project to DailySection"); // NOI18N
        
        final Optional<Node> result = vbDailySectionContent.getChildren().stream()
                .filter(item -> {
                    if (item instanceof TitledPane) {
                        final TitledPane titledPane = (TitledPane) item;
                        final ProjectModel modelToCheck = (ProjectModel) titledPane.getUserData();
                        if (model.getId() == modelToCheck.getId()) {
                            this.onActionIncreaseProjectCounter(titledPane, model.getTitle());
                            return true;
                        }
                    }
                    
                    return false;
                })
                .findFirst();
        
        boolean projectIsAlwaysAdded = false;
        if (
                result.isPresent()
                && result.get() != null
        ) {
            projectIsAlwaysAdded = true;
        }
        
        return projectIsAlwaysAdded;
    }
    
    private void onActionAddProjectToDailySection(ProjectModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action add Project to DailySection"); // NOI18N
        
        final TitledPane titledPane = new TitledPane();
        titledPane.setContent(new Button("the project details here"));
        titledPane.setText("(1) " + model.getTitle()); // NOI18N
        titledPane.setUserData(model);
        vbDailySectionContent.getChildren().add(0, titledPane);
        
        this.onActionEnsureTitledPaneIsVisible(titledPane);
    }
    
    private void onActionEnsureTitledPaneIsVisible(TitledPane titledPane) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action ensure TitledPane is visible"); // NOI18N
        
        final double height = spDailySectionContent.getContent().getBoundsInLocal().getHeight();
        final double y = titledPane.getBoundsInParent().getMaxY();
        spDailySectionContent.setVvalue(y / height);

        // Just for usability
        titledPane.requestFocus();
    }
    
    private void onActionIncreaseProjectCounter(TitledPane titledPane, String projectName) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action increase Project counter"); // NOI18N
        
        final String title = titledPane.getText();
        final String counted = title.substring(1, title.indexOf(')')); // NOI18N
        final int counter = Integer.parseInt(counted) + 1;
        titledPane.setText("(" + counter + ") " + projectName); // NOI18N
        
        LoggerFacade.INSTANCE.trace(this.getClass(), "TODO update database with the new counter"); // NOI18N
        
        this.onActionEnsureTitledPaneIsVisible(titledPane);
    }
    
}
