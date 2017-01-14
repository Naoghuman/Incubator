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
import com.github.naoghuman.abclist.model.ExerciseTerm;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.lib.database.api.DatabaseFacade;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PRo
 */
public class SqlProviderTest implements IDefaultConfiguration {
    
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
    public void testCreateExerciseTerm() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateExerciseTerm()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm = DatabaseFacade.getDefault()
                .getCrudService("testCreateExerciseTerm()")
                .create(ModelProvider.getDefault().getExerciseTerm());
        
        ExerciseTerm exerciseTermFromDatabase = DatabaseFacade.getDefault()
                .getCrudService("testCreateExerciseTerm()")
                .findById(ExerciseTerm.class, exerciseTerm.getId());
        
        assertNotNull(exerciseTermFromDatabase);
        assertEquals(DEFAULT_ID, exerciseTerm.getId());
        assertEquals(DEFAULT_ID, exerciseTerm.getExerciseId());
        assertEquals(DEFAULT_ID, exerciseTerm.getTermId());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        exerciseTerm = ModelProvider.getDefault().getExerciseTerm();
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm);
        
        exerciseTermFromDatabase = DatabaseFacade.getDefault()
                .getCrudService("testCreateExerciseTerm()")
                .findById(ExerciseTerm.class, exerciseTerm.getId());
        
        assertNotNull(exerciseTermFromDatabase);
        assertNotEquals(DEFAULT_ID, exerciseTermFromDatabase.getId());
        assertEquals(exerciseTerm.getId(), exerciseTermFromDatabase.getId());
        assertEquals(DEFAULT_ID, exerciseTerm.getExerciseId());
        assertEquals(DEFAULT_ID, exerciseTerm.getTermId());
    }

    @Test
    public void testCreateOrUpdateExercise() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateOrUpdateExercise()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateExercise()")
                .create(ModelProvider.getDefault().getExercise(System.currentTimeMillis()));
        
        Exercise exerciseFromDatabase1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateExercise()")
                .findById(Exercise.class, exercise1.getId());
        
        assertNotNull(exerciseFromDatabase1);
        assertEquals(exercise1.getId(), exerciseFromDatabase1.getId());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise2 = ModelProvider.getDefault().getExercise();
        SqlProvider.getDefault().createOrUpdateExercise(exercise2);
        
        Exercise exerciseFromDatabase2 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateExercise()")
                .findById(Exercise.class, exercise2.getId());
        
        assertNotNull(exerciseFromDatabase2);
        assertNotEquals(DEFAULT_ID, exerciseFromDatabase2.getId());
        assertEquals(exercise2.getId(), exerciseFromDatabase2.getId());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise3 = ModelProvider.getDefault().getExercise();
        SqlProvider.getDefault().createOrUpdateExercise(exercise3);
        
        exercise3.setReady(true);
        SqlProvider.getDefault().createOrUpdateExercise(exercise3);
        
        Exercise exerciseFromDatabase3 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateExercise()")
                .findById(Exercise.class, exercise3.getId());
        
        assertNotNull(exerciseFromDatabase3);
        assertNotEquals(DEFAULT_ID, exerciseFromDatabase3.getId());
        assertEquals(exercise3.getId(), exerciseFromDatabase3.getId());
        assertEquals(true, exerciseFromDatabase3.isReady());
        assertEquals(exercise3.isReady(), exerciseFromDatabase3.isReady());
    }
    
    @Test
    public void testCreateOrUpdateTerm() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateOrUpdateTerm()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTerm()")
                .create(ModelProvider.getDefault().getTerm("Test1"));
        
        Term termFromDatabase1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTerm()")
                .findById(Term.class, term1.getId());
        
        assertNotNull(termFromDatabase1);
        assertEquals(term1.getId(), termFromDatabase1.getId());
        assertEquals(term1.getTitle(), termFromDatabase1.getTitle());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term2 = ModelProvider.getDefault().getTerm("Test2");
        SqlProvider.getDefault().createOrUpdateTerm(term2);
        
        Term termFromDatabase2 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTerm()")
                .findById(Term.class, term2.getId());
        
        assertNotNull(termFromDatabase2);
        assertNotEquals(DEFAULT_ID, termFromDatabase2.getId());
        assertEquals(term2.getId(), termFromDatabase2.getId());
        assertEquals(term2.getTitle(), termFromDatabase2.getTitle());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term3 = ModelProvider.getDefault().getTerm("Test3");
        SqlProvider.getDefault().createOrUpdateTerm(term3);
        
        term3.setTitle("Test3 aaaaaaaa");
        SqlProvider.getDefault().createOrUpdateTerm(term3);
        
        Term termFromDatabase3 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTerm()")
                .findById(Term.class, term3.getId());
        
        assertNotNull(termFromDatabase3);
        assertNotEquals(DEFAULT_ID, termFromDatabase3.getId());
        assertEquals(term3.getId(), termFromDatabase3.getId());
        assertEquals("Test3 aaaaaaaa", termFromDatabase3.getTitle());
        assertEquals(term3.getTitle(), termFromDatabase3.getTitle());
    }
    
    @Test
    public void testCreateOrUpdateTopic() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateOrUpdateTopic()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Topic topic1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTopic()")
                .create(ModelProvider.getDefault().getTopic("Topic1"));
        
        Topic topicFromDatabase1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTopic()")
                .findById(Topic.class, topic1.getId());
        
        assertNotNull(topicFromDatabase1);
        assertEquals(topic1.getId(), topicFromDatabase1.getId());
        assertEquals(topic1.getTitle(), topicFromDatabase1.getTitle());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Topic topic2 = ModelProvider.getDefault().getTopic("Topic2");
        SqlProvider.getDefault().createOrUpdateTopic(topic2);
        
        Topic topicFromDatabase2 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTopic()")
                .findById(Topic.class, topic2.getId());
        
        assertNotNull(topicFromDatabase2);
        assertNotEquals(DEFAULT_ID, topicFromDatabase2.getId());
        assertEquals(topic2.getId(), topicFromDatabase2.getId());
        assertEquals(topic2.getTitle(), topicFromDatabase2.getTitle());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Topic topic3 = ModelProvider.getDefault().getTopic("Topic3");
        SqlProvider.getDefault().createOrUpdateTopic(topic3);
        
        topic3.setTitle("Topic3 aaaaaaaa");
        SqlProvider.getDefault().createOrUpdateTopic(topic3);
        
        Topic topicFromDatabase3 = DatabaseFacade.getDefault()
                .getCrudService("testCreateOrUpdateTopic()")
                .findById(Topic.class, topic3.getId());
        
        assertNotNull(topicFromDatabase3);
        assertNotEquals(DEFAULT_ID, topicFromDatabase3.getId());
        assertEquals(topic3.getId(), topicFromDatabase3.getId());
        assertEquals("Topic3 aaaaaaaa", topicFromDatabase3.getTitle());
        assertEquals(topic3.getTitle(), topicFromDatabase3.getTitle());
    }
    
    @Test
    public void testFindAllTerms() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllTerms()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<Term> terms = FXCollections.observableArrayList();
        terms.addAll(SqlProvider.getDefault().findAllTerms());
        assertTrue(terms.isEmpty());
        
        Term term1 = ModelProvider.getDefault().getTerm("Term1");
        SqlProvider.getDefault().createOrUpdateTerm(term1);
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTerms());
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 1);
        
        Term term2 = ModelProvider.getDefault().getTerm("Term2");
        SqlProvider.getDefault().createOrUpdateTerm(term2);
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTerms());
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 2);
    }
    
    @Test
    public void testFindAllTermsWithTitle() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllTermsWithTitle()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<Term> terms = FXCollections.observableArrayList();
        Term term3 = ModelProvider.getDefault().getTerm("Term3");
        SqlProvider.getDefault().createOrUpdateTerm(term3);
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTermsWithTitle("hello?"));
        assertTrue(terms.isEmpty());
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTermsWithTitle("Term3"));
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 1);
    }
    
}
