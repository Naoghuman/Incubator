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
package com.github.naoghuman.abclist.sql;

import com.github.naoghuman.abclist.configuration.IDefaultConfiguration;
import com.github.naoghuman.abclist.configuration.IExerciseConfiguration;
import com.github.naoghuman.abclist.configuration.IExerciseTermConfiguration;
import com.github.naoghuman.abclist.configuration.ITermConfiguration;
import com.github.naoghuman.abclist.configuration.ITopicConfiguration;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ExerciseTerm;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.lib.database.api.DatabaseFacade;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public class SqlProvider implements IDefaultConfiguration {
    
    private static final Optional<SqlProvider> instance = Optional.of(new SqlProvider());

    public static final SqlProvider getDefault() {
        return instance.get();
    }
    
    private SqlProvider() {
        
    }
    
    public void createExerciseTerm(ExerciseTerm exerciseTerm) {
        exerciseTerm.setId(System.currentTimeMillis());
        DatabaseFacade.getDefault().getCrudService().create(exerciseTerm);
    }
    
    public void createOrUpdateExercise(Exercise exercise) {
        if (Objects.equals(exercise.getId(), DEFAULT_ID)) {
            exercise.setId(System.currentTimeMillis());
            DatabaseFacade.getDefault().getCrudService().create(exercise);
        }
        else {
            DatabaseFacade.getDefault().getCrudService().update(exercise);
        }
    }
    
    public void createOrUpdateTerm(Term term) {
        if (Objects.equals(term.getId(), DEFAULT_ID)) {
            term.setId(System.currentTimeMillis());
            DatabaseFacade.getDefault().getCrudService().create(term);
        }
        else {
            DatabaseFacade.getDefault().getCrudService().update(term);
        }
    }
    
    public void createOrUpdateTopic(Topic topic) {
        if (Objects.equals(topic.getId(), DEFAULT_ID)) {
            topic.setId(System.currentTimeMillis());
            DatabaseFacade.getDefault().getCrudService().create(topic);
        }
        else {
            DatabaseFacade.getDefault().getCrudService().update(topic);
        }
    }

    public void deleteAllExerciseTermsWithExerciseId(long exerciseId) {
        final ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(exerciseId);
        
        DatabaseFacade.getDefault().getCrudService().beginTransaction();
        exerciseTerms.stream()
                .forEach(exerciseTerm -> {
                    DatabaseFacade.getDefault().getCrudService().getEntityManager().remove(exerciseTerm);
                });
        DatabaseFacade.getDefault().getCrudService().commitTransaction();
    }
    
    public ObservableList<ExerciseTerm> findAllExerciseTermsWithExerciseId(long exerciseId) {
        final ObservableList<ExerciseTerm> allTermsWithExerciseId = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(IExerciseTermConfiguration.EXERCISE_TERM__COLUMN_NAME__EXERCISE_ID, exerciseId);
        
        final List<ExerciseTerm> exerciseTerms = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(ExerciseTerm.class, IExerciseTermConfiguration.NAMED_QUERY__NAME__FIND_ALL_EXERCISE_TERMS_WITH_EXERCISE_ID, parameters);
        
        allTermsWithExerciseId.addAll(exerciseTerms);
        Collections.sort(allTermsWithExerciseId);

        return allTermsWithExerciseId;
    }
    
    public ObservableList<Exercise> findAllExercisesWithTopicId(long topicId) {
        final ObservableList<Exercise> allExercisesWithTopicId = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(IExerciseConfiguration.EXERCISE__COLUMN_NAME__TOPIC_ID, topicId);
        
        final List<Exercise> exercises = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Exercise.class, IExerciseConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_TOPIC_ID, parameters);

        allExercisesWithTopicId.addAll(exercises);
        Collections.sort(allExercisesWithTopicId);

        return allExercisesWithTopicId;
    }
    
    public ObservableList<Term> findAllTerms() {
        final ObservableList<Term> allTerms = FXCollections.observableArrayList();
        final List<Term> terms = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Term.class, ITermConfiguration.NAMED_QUERY__NAME__FIND_ALL);
        
        allTerms.addAll(terms);
        Collections.sort(allTerms);

        return allTerms;
    }
    
    public ObservableList<Term> findAllTermsInExerciseTerms(ObservableList<ExerciseTerm> exerciseTerms) {
        final ObservableList<Term> allTermsInExerciseTerms = FXCollections.observableArrayList();
        exerciseTerms.stream()
                .map((exerciseTerm) -> DatabaseFacade.getDefault().getCrudService().findById(Term.class, exerciseTerm.getTermId()))
                .forEach((term) -> {
                    allTermsInExerciseTerms.add(term);
                });
        
        Collections.sort(allTermsInExerciseTerms);
        
        return allTermsInExerciseTerms;
    }
	
    public ObservableList<Term> findAllTermsWithTitle(String title) {
        final ObservableList<Term> allTermsWithTitle = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(ITermConfiguration.TERM__COLUMN_NAME__TITLE, title);
        
        final List<Term> terms = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Term.class, ITermConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_TITLE, parameters);
        
        allTermsWithTitle.addAll(terms);
        Collections.sort(allTermsWithTitle);

        return allTermsWithTitle;
    }

    public ObservableList<Term> findAllTermsWithTopicId(long topicId) {
        final ObservableList<Term> allTermsWithTopicId = FXCollections.observableArrayList();
        
        final ObservableList<Exercise> observableListExercises = this.findAllExercisesWithTopicId(topicId);
        final Set<Long> uniqueTermIds = new LinkedHashSet<>();
        observableListExercises.stream()
                .forEach(exercise -> {
                    final ObservableList<ExerciseTerm> exerciseTerms = this.findAllExerciseTermsWithExerciseId(exercise.getId());
                    exerciseTerms.stream()
                            .forEach(exerciseTerm -> {
                                uniqueTermIds.add(exerciseTerm.getTermId());
                            });
                });
        
        uniqueTermIds.stream()
                .forEach(termId -> {
                    allTermsWithTopicId.add(DatabaseFacade.getDefault().getCrudService().findById(Term.class, termId));
                });
        Collections.sort(allTermsWithTopicId);

        return allTermsWithTopicId;
    }
    
    public ObservableList<Topic> findAllTopics() {
        final ObservableList<Topic> allTopics = FXCollections.observableArrayList();
        final List<Topic> topics = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Topic.class, ITopicConfiguration.NAMED_QUERY__NAME__FIND_ALL);
        
        allTopics.addAll(topics);
        Collections.sort(allTopics);

        return allTopics;
    }
    
}
