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

package com.github.naoghuman.umleditor.classdiagram;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;

/**
 *
 * @author Naoghuman
 */
public final class ClassDiagramProvider {
    
    private static final Optional<ClassDiagramProvider> instance = Optional.of(new ClassDiagramProvider());
    
    public static final ClassDiagramProvider getDefault() {
        return instance.get();
    }
    
    private int tabCounter = 1;

    private ClassDiagramProvider() {
        
    }
    
    public Tab createNewTabForClassDiagram() {
        LoggerFacade.getDefault().debug(this.getClass(), "Create new Tab for ClassDiagram"); // NOI18N
    
        final Tab tab = new Tab();
        tab.setClosable(Boolean.TRUE);
        tab.setText("Class Diagram " + tabCounter); // NOI18N
        tab.setContent(new Button("hello"));
        
        ++tabCounter;
        
        return tab;
    }

}
