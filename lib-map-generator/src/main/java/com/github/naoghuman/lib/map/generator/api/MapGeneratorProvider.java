/*
 * Copyright (C) 2015 PRo
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
package com.github.naoghuman.lib.map.generator.api;

import com.github.naoghuman.lib.map.generator.MapGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author PRo
 */
public class MapGeneratorProvider {
    
    private static IMapGenerator instance = null;
    
    public static IMapGenerator getDefault() {
        
        if (instance == null) {
            instance = new MapGenerator();
        }
        
        return instance;
    }
    
    private MapGeneratorProvider() { }
    
    public EventHandler<ActionEvent> getOnActionShowMapConfiguration() {
        return null;
    }
    
    public EventHandler<ActionEvent> getOnActionShowMapHelp() {
        return null;
    }
    
    public EventHandler<ActionEvent> getOnActionShowMapWizard() {
        return null;
    }
    
}
