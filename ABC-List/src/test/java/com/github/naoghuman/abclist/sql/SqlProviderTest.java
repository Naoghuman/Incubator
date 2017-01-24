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
import java.util.Optional;
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
    
    public SqlProviderTest() {
    }
    
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
    public void testCountAllExerciseTermsWithTermId() {
        
    }

    @Test
    public void testCreateExercise() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateExercise()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateExercise()")
                .create(ModelProvider.getDefault().getExercise(System.currentTimeMillis()));
        
        Exercise exerciseFromDatabase1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateExercise()")
                .findById(Exercise.class, exercise1.getId());
        
        assertNotNull(exerciseFromDatabase1);
        assertEquals(exercise1.getId(), exerciseFromDatabase1.getId());
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateExercise()")
                .delete(Exercise.class, exercise1.getId());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise2 = ModelProvider.getDefault().getExercise();
        SqlProvider.getDefault().createExercise(exercise2);
        
        Exercise exerciseFromDatabase2 = DatabaseFacade.getDefault()
                .getCrudService("testCreateExercise()")
                .findById(Exercise.class, exercise2.getId());
        
        assertNotNull(exerciseFromDatabase2);
        assertNotEquals(DEFAULT_ID, exerciseFromDatabase2.getId());
        assertEquals(exercise2.getId(), exerciseFromDatabase2.getId());
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateExercise()")
                .delete(Exercise.class, exercise2.getId());
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
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateExerciseTerm()")
                .delete(ExerciseTerm.class, exerciseTerm.getId());
        
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
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateExerciseTerm()")
                .delete(ExerciseTerm.class, exerciseTerm.getId());
    }

    @Test
    public void testCreateTerm() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateTerm()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateTerm()")
                .create(ModelProvider.getDefault().getTerm("Test1"));
        
        Term termFromDatabase1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateTerm()")
                .findById(Term.class, term1.getId());
        
        assertNotNull(termFromDatabase1);
        assertEquals(term1.getId(), termFromDatabase1.getId());
        assertEquals(term1.getTitle(), termFromDatabase1.getTitle());
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateTerm()")
                .delete(Term.class, term1.getId());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term2 = ModelProvider.getDefault().getTerm("Test2");
        SqlProvider.getDefault().createTerm(term2);
        
        Term termFromDatabase2 = DatabaseFacade.getDefault()
                .getCrudService("testCreateTerm()")
                .findById(Term.class, term2.getId());
        
        assertNotNull(termFromDatabase2);
        assertNotEquals(DEFAULT_ID, termFromDatabase2.getId());
        assertEquals(term2.getId(), termFromDatabase2.getId());
        assertEquals(term2.getTitle(), termFromDatabase2.getTitle());
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateTerm()")
                .delete(Term.class, term2.getId());
    }

    @Test
    public void testCreateTopic() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testCreateTopic()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        final Topic topic = ModelProvider.getDefault().getTopic("Topic1");
        topic.setId(System.currentTimeMillis());
        
        Topic topic1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateTopic()")
                .create(ModelProvider.getDefault().getTopic("Topic1"));
        
        Topic topicFromDatabase1 = DatabaseFacade.getDefault()
                .getCrudService("testCreateTopic()")
                .findById(Topic.class, topic1.getId());
        
        assertNotNull(topicFromDatabase1);
        assertEquals(topic1.getId(), topicFromDatabase1.getId());
        assertEquals(topic1.getTitle(), topicFromDatabase1.getTitle());
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateTopic()")
                .delete(Topic.class, topic1.getId());
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Topic topic2 = ModelProvider.getDefault().getTopic("Topic2");
        SqlProvider.getDefault().createTopic(topic2);
        
        Topic topicFromDatabase2 = DatabaseFacade.getDefault()
                .getCrudService("testCreateTopic()")
                .findById(Topic.class, topic2.getId());
        
        assertNotNull(topicFromDatabase2);
        assertNotEquals(DEFAULT_ID, topicFromDatabase2.getId());
        assertEquals(topic2.getId(), topicFromDatabase2.getId());
        assertEquals(topic2.getTitle(), topicFromDatabase2.getTitle());
        
        DatabaseFacade.getDefault()
                .getCrudService("testCreateTopic()")
                .delete(Topic.class, topic2.getId());
    }

    @Test
    public void testDeleteAllExerciseTermsWithExerciseId() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testDeleteAllExerciseTermsWithExerciseId()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm et1 = ModelProvider.getDefault().getExerciseTerm(123L, 1L);
        SqlProvider.getDefault().createExerciseTerm(et1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm et2 = ModelProvider.getDefault().getExerciseTerm(123L, 2L);
        SqlProvider.getDefault().createExerciseTerm(et2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(123L);
        
        assertFalse(exerciseTerms.isEmpty());
        assertTrue(exerciseTerms.size() == 2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        SqlProvider.getDefault().deleteAllExerciseTermsWithExerciseId(123L);
        
        exerciseTerms.clear();
        exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(123L);
        assertTrue(exerciseTerms.isEmpty());
    }

    @Test
    public void testFindAllExerciseTermsWithExerciseId() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllExerciseTermsWithExerciseId()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm et1 = ModelProvider.getDefault().getExerciseTerm(1234L, 11L);
        SqlProvider.getDefault().createExerciseTerm(et1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm et2 = ModelProvider.getDefault().getExerciseTerm(1234L, 21L);
        SqlProvider.getDefault().createExerciseTerm(et2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(1234L);
        
        assertFalse(exerciseTerms.isEmpty());
        assertTrue(exerciseTerms.size() == 2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        SqlProvider.getDefault().deleteAllExerciseTermsWithExerciseId(1234L);
    }

    @Test
    public void testFindAllExercisesWithTopicId() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllExercisesWithTopicId()"); // NOI18N

        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise1 = ModelProvider.getDefault().getExercise();
        exercise1.setTopicId(111L);
        SqlProvider.getDefault().createExercise(exercise1);
        
        ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        exercises.addAll(SqlProvider.getDefault().findAllExercisesWithTopicId(111L));
        assertFalse(exercises.isEmpty());
        assertTrue(exercises.size() == 1);
        assertTrue(exercises.get(0).getTopicId() == 111L);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise2 = ModelProvider.getDefault().getExercise();
        exercise2.setTopicId(111L);
        SqlProvider.getDefault().createExercise(exercise2);
        
        exercises.clear();
        exercises.addAll(SqlProvider.getDefault().findAllExercisesWithTopicId(111L));
        assertFalse(exercises.isEmpty());
        assertTrue(exercises.size() == 2);
        assertTrue(exercises.get(0).getId() == exercise2.getId());
        assertTrue(exercises.get(0).getTopicId() == 111L);
        assertTrue(exercises.get(1).getId() == exercise1.getId());
        assertTrue(exercises.get(1).getTopicId() == 111L);
        
        // ---------------------------------------------------------------------
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllExercisesWithTopicId()")
                .delete(Exercise.class, exercise1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllExercisesWithTopicId()")
                .delete(Exercise.class, exercise2.getId());
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
        SqlProvider.getDefault().createTerm(term1);
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTerms());
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term2 = ModelProvider.getDefault().getTerm("Term2");
        SqlProvider.getDefault().createTerm(term2);
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTerms());
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 2);
        assertEquals("Term1", terms.get(0).getTitle());
        assertEquals("Term2", terms.get(1).getTitle());
        
        // ---------------------------------------------------------------------
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTerms()")
                .delete(Term.class, term1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTerms()")
                .delete(Term.class, term2.getId());
    }

    @Test
    public void testFindAllTermsInExerciseTerm() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllTermsInExerciseTerms()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term1 = ModelProvider.getDefault().getTerm("Term1");
        SqlProvider.getDefault().createTerm(term1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }

        Term term2 = ModelProvider.getDefault().getTerm("Term2");
        SqlProvider.getDefault().createTerm(term2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm1 = ModelProvider.getDefault().getExerciseTerm(1111L, term1.getId());
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm2 = ModelProvider.getDefault().getExerciseTerm(1111L, term2.getId());
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(1111L);
        ObservableList<Term> terms = SqlProvider.getDefault().findAllTermsInExerciseTerm(exerciseTerms);
        
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 2);
        assertEquals("Term1", terms.get(0).getTitle());
        assertEquals("Term2", terms.get(1).getTitle());
        
        // ---------------------------------------------------------------------
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsInExerciseTerms()")
                .delete(Term.class, term1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsInExerciseTerms()")
                .delete(Term.class, term2.getId());
        
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsInExerciseTerms()")
                .delete(ExerciseTerm.class, exerciseTerm1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsInExerciseTerms()")
                .delete(ExerciseTerm.class, exerciseTerm2.getId());
    }

    @Test
    public void testFindAllTermsInExerciseTermWithoutParent() {
        
    }

    @Test
    public void testFindAllTermsWithTitle() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllTermsWithTitle()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term1 = ModelProvider.getDefault().getTerm("Term1");
        SqlProvider.getDefault().createTerm(term1);
        
        ObservableList<Term> terms = FXCollections.observableArrayList();
        terms.addAll(SqlProvider.getDefault().findAllTermsWithTitle("hello?"));
        assertTrue(terms.isEmpty());
        
        terms.clear();
        terms.addAll(SqlProvider.getDefault().findAllTermsWithTitle("Term1"));
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 1);
        assertEquals("Term1", terms.get(0).getTitle());
        
        // ---------------------------------------------------------------------
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTerms()")
                .delete(Term.class, term1.getId());
    }

    @Test
    public void testFindAllTermsWithTopicId() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllTermsWithTopicId()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Topic topic1 = ModelProvider.getDefault().getTopic("topic1");
        SqlProvider.getDefault().createTopic(topic1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term1 = ModelProvider.getDefault().getTerm("Term1");
        SqlProvider.getDefault().createTerm(term1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term2 = ModelProvider.getDefault().getTerm("Term2");
        SqlProvider.getDefault().createTerm(term2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Term term3 = ModelProvider.getDefault().getTerm("Term3");
        SqlProvider.getDefault().createTerm(term3);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise1 = ModelProvider.getDefault().getExercise();
        exercise1.setTopicId(topic1.getId());
        SqlProvider.getDefault().createExercise(exercise1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm1 = ModelProvider.getDefault().getExerciseTerm(exercise1.getId(), term1.getId());
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm2 = ModelProvider.getDefault().getExerciseTerm(exercise1.getId(), term2.getId());
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Exercise exercise2 = ModelProvider.getDefault().getExercise();
        exercise2.setTopicId(topic1.getId());
        SqlProvider.getDefault().createExercise(exercise2);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm3 = ModelProvider.getDefault().getExerciseTerm(exercise2.getId(), term2.getId());
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm3);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ExerciseTerm exerciseTerm4 = ModelProvider.getDefault().getExerciseTerm(exercise2.getId(), term3.getId());
        SqlProvider.getDefault().createExerciseTerm(exerciseTerm4);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<Term> terms = SqlProvider.getDefault().findAllTermsWithTopicId(topic1.getId());
        assertFalse(terms.isEmpty());
        assertTrue(terms.size() == 3);
        assertEquals("Term1", terms.get(0).getTitle());
        assertEquals("Term2", terms.get(1).getTitle());
        assertEquals("Term3", terms.get(2).getTitle());
        
        // ---------------------------------------------------------------------
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(Topic.class, topic1.getId());
        
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(Term.class, term1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(Term.class, term2.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(Term.class, term3.getId());
        
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(Exercise.class, exercise1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(Exercise.class, exercise2.getId());
        
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(ExerciseTerm.class, exerciseTerm1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(ExerciseTerm.class, exerciseTerm2.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(ExerciseTerm.class, exerciseTerm3.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTermsWithTopicId()")
                .delete(ExerciseTerm.class, exerciseTerm4.getId());
    }

    @Test
    public void testFindAllTopics() {
        LoggerFacade.getDefault().own(SqlProviderTest.class, "testFindAllTerms()"); // NOI18N
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        ObservableList<Topic> topics = FXCollections.observableArrayList();
        topics.addAll(SqlProvider.getDefault().findAllTopics());
        assertTrue(topics.isEmpty());
        
        Topic topic1 = ModelProvider.getDefault().getTopic("topic1");
        SqlProvider.getDefault().createTopic(topic1);
        
        topics.clear();
        topics.addAll(SqlProvider.getDefault().findAllTopics());
        assertFalse(topics.isEmpty());
        assertTrue(topics.size() == 1);
        
        // ---------------------------------------------------------------------
        try { Thread.sleep(15); } catch (Exception e) { }
        
        Topic topic2 = ModelProvider.getDefault().getTopic("topic2");
        SqlProvider.getDefault().createTopic(topic2);
        
        topics.clear();
        topics.addAll(SqlProvider.getDefault().findAllTopics());
        assertFalse(topics.isEmpty());
        assertTrue(topics.size() == 2);
        assertEquals("topic1", topics.get(0).getTitle());
        assertEquals("topic2", topics.get(1).getTitle());
        
        // ---------------------------------------------------------------------
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTopics()")
                .delete(Topic.class, topic1.getId());
        DatabaseFacade.getDefault()
                .getCrudService("testFindAllTopics()")
                .delete(Topic.class, topic2.getId());
    }

    @Test
    public void testFindExerciseTerm() {
        
    }

    @Test
    public void testIsExerciseTermMarkAsWrong() {
        
    }

    @Test
    public void testUpdateExercise() {
        
    }

    @Test
    public void testUpdateExerciseTerm() {
        
    }

    @Test
    public void testUpdateTerm() {
        
    }

    @Test
    public void testUpdateTopic() {
        
    }
    
}
