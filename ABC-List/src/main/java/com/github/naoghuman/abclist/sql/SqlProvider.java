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
import com.github.naoghuman.abclist.configuration.ITopicConfiguration;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.lib.database.api.DatabaseFacade;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public class SqlProvider {
    
    private static final Optional<SqlProvider> instance = Optional.of(new SqlProvider());

    public static final SqlProvider getDefault() {
        return instance.get();
    }
    
    private SqlProvider() {
        
    }
    
    public void createOrUpdate(Exercise exercise) {
        if (Objects.equals(exercise.getId(), IDefaultConfiguration.DEFAULT_ID)) {
            exercise.setId(System.currentTimeMillis());
            DatabaseFacade.getDefault().getCrudService().create(exercise);
        }
        else {
            DatabaseFacade.getDefault().getCrudService().update(exercise);
        }
    }
    
    public void createOrUpdate(Topic topic) {
        if (Objects.equals(topic.getId(), IDefaultConfiguration.DEFAULT_ID)) {
            topic.setId(System.currentTimeMillis());
            DatabaseFacade.getDefault().getCrudService().create(topic);
        }
        else {
            DatabaseFacade.getDefault().getCrudService().update(topic);
        }
    }
    
    public ObservableList<Exercise> findAllExercisesWithParentId(long parentId) {
        final ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(IExerciseConfiguration.EXERCISE__COLUMN_NAME__PARENT_ID, parentId);
        final List<Exercise> allExercises = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Exercise.class, IExerciseConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_PARENT_ID, parameters);

        exercises.addAll(allExercises);
        Collections.sort(exercises);

        return exercises;
    }
    
    public ObservableList<Topic> findAllTopics() {
        final ObservableList<Topic> topics = FXCollections.observableArrayList();
        final List<Topic> allTopics = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Topic.class, ITopicConfiguration.NAMED_QUERY__NAME__FIND_ALL);
        
        topics.addAll(allTopics);
        Collections.sort(topics);

        return topics;
    }
    
}
