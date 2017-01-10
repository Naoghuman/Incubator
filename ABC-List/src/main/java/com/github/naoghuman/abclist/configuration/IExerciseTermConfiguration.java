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
package com.github.naoghuman.abclist.configuration;

/**
 *
 * @author Naoghuman
 */
public interface IExerciseTermConfiguration {
    
    public static final String ENTITY__TABLE_NAME__EXERCISE_TERM = "ExerciseTerm"; // NOI18N
    
    public static final String NAMED_QUERY__NAME__FIND_ALL_TERMS_WITH_EXERCISE_ID = "ExerciseTerm.findAllTermsWithExerciseId"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_TERMS_WITH_EXERCISE_ID = "SELECT et FROM ExerciseTerm et WHERE et.exerciseId == :exerciseId"; // NOI18N
    
    public static final String EXERCISE_TERM__COLUMN_NAME__EXERCISE_ID = "exerciseId"; // NOI18N
    public static final String EXERCISE_TERM__COLUMN_NAME__TERM_ID = "termId"; // NOI18N

}
