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
package com.github.naoghuman.pm.model.api;

/**
 *
 * @author Naoghuman
 */
public interface IProjectModel {
    
    public static final long DEFAULT_ID__PROJECT_MODEL = -1967_08_28_343L;
    
    public static final String NO_TITLE = "<no title>"; // NOI18N
    public static final String SIGN__EMPTY = ""; // NOI18N
    
    public static final String NAMED_QUERY__NAME__FIND_ALL = "ProjectModel.findAll"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL = "SELECT pm FROM ProjectModel pm"; // NOI18N
    
    public static final String PROJECT_MODEL__COLUMN_NAME__COLOR_AS_STYLE = "color-as-style"; // NOI18N
    public static final String PROJECT_MODEL__COLUMN_NAME__GENERATION_TIME = "generationTime"; // NOI18N
    public static final String PROJECT_MODEL__COLUMN_NAME__ID = "id"; // NOI18N
    public static final String PROJECT_MODEL__COLUMN_NAME__POSITION = "position"; // NOI18N
    public static final String PROJECT_MODEL__COLUMN_NAME__TITLE = "title"; // NOI18N
    
    public static final String TABLE_NAME__PROJECT_MODEL = "ProjectModel"; // NOI18N
    
}
