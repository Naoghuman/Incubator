/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.abclist.configuration;

/**
 *
 * @author Naoghuman
 */
public interface IExerciseConfiguration {
    
    public static final String ACTION__EXERCISE_DIALOG__EXERCISE_IS_READY = "ACTION__EXERCISE_DIALOG__EXERCISE_IS_READY"; // NOI18N
    public static final String ACTION__EXERCISE_DIALOG__USER_STOP_EXERCISE = "ACTION__EXERCISE_DIALOG__USER_STOP_EXERCISE"; // NOI18N
    public static final String ACTION__EXERCISE_DIALOG__USER_TYPED_TERM = "ACTION__EXERCISE_DIALOG__USER_TYPED_TERM"; // NOI18N
    
    public static final String ENTITY__TABLE_NAME__EXERCISE = "Exercise"; // NOI18N
    
    public static final String NAMED_QUERY__NAME__FIND_ALL_WITH_PARENT_ID = "Exercise.findAllWithParentId"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_WITH_PARENT_ID = "SELECT e FROM Exercise e WHERE e.parentId == :parentId"; // NOI18N
    
    public static final String EXERCISE__COLUMN_NAME__ID = "id"; // NOI18N
    public static final String EXERCISE__COLUMN_NAME__GENERATION_TIME = "generationTime"; // NOI18N
    public static final String EXERCISE__COLUMN_NAME__PARENT_ID = "parentId"; // NOI18N
    public static final String EXERCISE__COLUMN_NAME__READY = "ready"; // NOI18N
    
}
