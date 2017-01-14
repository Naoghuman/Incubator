/*
 * Copyright (C) 2017 PRo
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
package com.github.naoghuman.abclist.sql;

import com.github.naoghuman.abclist.configuration.IDefaultConfiguration;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.lib.database.api.DatabaseFacade;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PRo
 */
public class SqlProviderTest {
    
    private final static String TABLE_WITH_SUFFIX = "SqlProviderTestTable.odb"; // NOI18N
    private final static String TABLE = "SqlProviderTestTable"; // NOI18N
    
    @BeforeClass
    public static void setUpClass() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "setUpClass()"); // NOI18N
//        LoggerFacade.getDefault().deactivate(Boolean.TRUE);
        
        DatabaseFacade.getDefault().register(TABLE_WITH_SUFFIX);
    }
    
    @AfterClass
    public static void tearDownClass() {
//        LoggerFacade.getDefault().deactivate(Boolean.FALSE);
        LoggerFacade.getDefault().own(SqlProviderTest.class, "tearDownClass()"); // NOI18N
        
        DatabaseFacade.getDefault().shutdown();
        DatabaseFacade.getDefault().drop(TABLE_WITH_SUFFIX);
    }

    @Test
    public void testGetDefault() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testGetDefault()"); // NOI18N
        
        SqlProvider result = SqlProvider.getDefault();
        assertNotNull(result);
    }

    @Test
    public void testCreateOrUpdate_Exercise() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateOrUpdate_Exercise()"); // NOI18N
        
        // ---------------------------------------------------------------------
        Exercise exercise = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdate_Exercise()")
                .create(ModelProvider.getDefault().getExercise(System.currentTimeMillis()));
        
        Exercise exerciseFromDatabase = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdate_Exercise()")
                .findById(Exercise.class, exercise.getId());
        
        assertNotNull(exerciseFromDatabase);
        assertEquals(exercise.getId(), exerciseFromDatabase.getId());
        
        // ---------------------------------------------------------------------
        exercise = ModelProvider.getDefault().getExercise();
        SqlProvider.getDefault().createOrUpdate(exercise);
        
        exerciseFromDatabase = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdate_Exercise()")
                .findById(Exercise.class, exercise.getId());
        
        assertNotNull(exerciseFromDatabase);
        assertNotEquals(IDefaultConfiguration.DEFAULT_ID, exerciseFromDatabase.getId());
        assertEquals(exercise.getId(), exerciseFromDatabase.getId());
    }
    
}
