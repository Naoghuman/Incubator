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
package com.github.naoghuman.lib.map.generator.mapwizard.api;

import java.io.File;

/**
 *
 * @author PRo
 */
public interface IMapWizardConfiguration {
    
    public static final String ACTION__CREATE__MAP_WIZARD = "ACTION__CREATE__MAP_WIZARD"; // NOI18N
    public static final String ACTION__HIDE__MAP_WIZARD = "ACTION__HIDE__MAP_WIZARD"; // NOI18N
    
    public static final String IMG__DREAMMAP__MARKER = "Map-Marker.png"; // NOI18N
    
    public static final String PATH_TO_FOLDER__RESOURCES_IMAGES_MAP__WITH_USER_DIR =
            System.getProperty("user.dir") // NOI18N
            + File.separator + "resources" // NOI18N
            + File.separator + "images" // NOI18N
            + File.separator + "map" // NOI18N
            + File.separator;
    
    public static final String PATH_TO_FOLDER__RESOURCES_IMAGES_MAP__WITH_FILE =
            "file:" // NOI18N
            + "resources" // NOI18N
            + File.separator + "images" // NOI18N
            + File.separator + "map" // NOI18N
            + File.separator;
    
    public static final String PATH_TO_FOLDER__RESOURCES_IMAGES_MAPMARKER__WITH_USER_DIR =
            System.getProperty("user.dir") // NOI18N
            + File.separator + "resources" // NOI18N
            + File.separator + "images" // NOI18N
            + File.separator + "mapmarker" // NOI18N
            + File.separator;
    
    public static final String PATH_TO_FOLDER__RESOURCES_IMAGES_MAPMARKER__WITH_FILE =
            "file:" // NOI18N
            + "resources" // NOI18N
            + File.separator + "images" // NOI18N
            + File.separator + "mapmarker" // NOI18N
            + File.separator;
}
