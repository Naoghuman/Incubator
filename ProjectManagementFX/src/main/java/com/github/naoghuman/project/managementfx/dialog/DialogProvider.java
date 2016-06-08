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
package com.github.naoghuman.project.managementfx.dialog;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;

/**
 *
 * @author Naoghuman
 */
public class DialogProvider {
    
    public static TextInputDialog getDialogCreateProject() {
        LoggerFacade.INSTANCE.debug(DialogProvider.class, "Get dialog create Project"); // NOI18N
        
        final TextInputDialog dialog = new TextInputDialog(""); // NOI18N
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Project dialog"); // NOI18N
        dialog.setHeaderText("Create new Project"); // NOI18N
        
        return dialog;
    }
    
}
