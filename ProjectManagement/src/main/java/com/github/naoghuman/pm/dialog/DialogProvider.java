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
package com.github.naoghuman.pm.dialog;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.dialog.projectdialog.ProjectDialogPresenter;
import com.github.naoghuman.pm.dialog.projectdialog.ProjectDialogView;
import com.github.naoghuman.pm.model.ProjectModel;
import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 *
 * @author Naoghuman
 */
public class DialogProvider {
    
    public static void showEditProjectDialog(ProjectModel model) {
        LoggerFacade.INSTANCE.debug(DialogProvider.class, "Show edit Project dialog"); // NOI18N
        LoggerFacade.INSTANCE.trace(DialogProvider.class, "TODO add size to the dialog"); // NOI18N
        LoggerFacade.INSTANCE.trace(DialogProvider.class, "TODO use properties"); // NOI18N
        
        final Dialog<ProjectModel> dialog = new Dialog<>();
        dialog.setTitle("Edit project"); // NOI18N
        dialog.setHeaderText("Updated the project details."); // NOI18N
        dialog.setResizable(false);
        
        final ProjectDialogView view = new ProjectDialogView();
        final ProjectDialogPresenter presenter = view.getRealPresenter();
        presenter.configure(model);
        dialog.getDialogPane().setContent(view.getView());
        
        final ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
	dialog.setResultConverter((ButtonType buttonType) -> {
            if (
                    buttonType != null
                    && buttonType.equals(buttonTypeOk)
                    && presenter.isChanged()
            ) {
                return view.getRealPresenter().getProject();
            }
            
            return null;
        });
        
        final Optional<ProjectModel> result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        
        LoggerFacade.INSTANCE.error(DialogProvider.class, "TODO fire event with changed ProjectModel"); // NOI18N
    }
    
    public static ProjectModel showNewProjectDialog() {
        LoggerFacade.INSTANCE.debug(DialogProvider.class, "Show new Project dialog"); // NOI18N
        LoggerFacade.INSTANCE.trace(DialogProvider.class, "TODO add size to the dialog"); // NOI18N
        LoggerFacade.INSTANCE.trace(DialogProvider.class, "TODO use properties"); // NOI18N
        
        final Dialog<ProjectModel> dialog = new Dialog<>();
        dialog.setTitle("New project"); // NOI18N
        dialog.setHeaderText("Creates a new project."); // NOI18N
        dialog.setResizable(false);
        
        final ProjectDialogView view = new ProjectDialogView();
        dialog.getDialogPane().setContent(view.getView());
        
        final ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
	dialog.setResultConverter((ButtonType buttonType) -> {
            if (
                    buttonType != null
                    && buttonType.equals(buttonTypeOk)
            ) {
                return view.getRealPresenter().getProject();
            }
            
            return null;
        });
        
        final Optional<ProjectModel> result = dialog.showAndWait();
        if (!result.isPresent()) {
            return null;
        }
        
        final ProjectModel model = view.getRealPresenter().getProject();
        
        return model;
    }
    
}
