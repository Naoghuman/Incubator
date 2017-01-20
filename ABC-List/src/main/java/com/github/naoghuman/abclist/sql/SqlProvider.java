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
public class SqlProvider implements IDefaultConfiguration, IExerciseTermConfiguration {
    
    private static final Optional<SqlProvider> instance = Optional.of(new SqlProvider());

    public static final SqlProvider getDefault() {
        return instance.get();
    }
    
    private SqlProvider() {
        
    }
    
    public long countAllExerciseTermsWithTermId(long termId) {
        return SqlServiceExerciseTerm.getDefault().countAllExerciseTermsWithTermId(termId);
    }
    
    public void createExerciseTerm(ExerciseTerm exerciseTerm) {
        SqlServiceExerciseTerm.getDefault().create(exerciseTerm);
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
    
    public void createTopic(Topic topic) {
        SqlServiceTopic.getDefault().create(topic);
    }

    public void deleteAllExerciseTermsWithExerciseId(long exerciseId) {
        SqlServiceExerciseTerm.getDefault().deleteAllExerciseTermsWithExerciseId(exerciseId);
    }
    
    public ObservableList<ExerciseTerm> findAllExerciseTermsWithExerciseId(long exerciseId) {
        return SqlServiceExerciseTerm.getDefault().findAllExerciseTermsWithExerciseId(exerciseId);
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
    
    public ObservableList<Term> findAllTermsInExerciseTerm(ObservableList<ExerciseTerm> exerciseTerms) {
        return SqlServiceExerciseTerm.getDefault().findAllTermsInExerciseTerm(exerciseTerms);
    }

    public ObservableList<Term> findAllTermsInExerciseTermWithoutParent() {
        final ObservableList<Term> terms = FXCollections.observableArrayList();
        terms.addAll(this.findAllTerms());
        
        return SqlServiceExerciseTerm.getDefault().findAllTermsInExerciseTermWithoutParent(terms);
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
        return SqlServiceTopic.getDefault().findAllTopics();
    }
    
    public void updateTopic(Topic topic) {
        SqlServiceTopic.getDefault().update(topic);
    }
    
}
