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
package com.github.naoghuman.abclist.model;

import java.util.Optional;

/**
 *
 * @author Naoghuman
 */
public class ModelProvider {
    
    private static final Optional<ModelProvider> instance = Optional.of(new ModelProvider());

    public static final ModelProvider getDefault() {
        return instance.get();
    }
    
    private ModelProvider() {
        
    }
    
    public Exercise getExercise() {
        return new Exercise();
    }
    
    public Exercise getExercise(long id) {
        return new Exercise(id);
    }
    
    public Exercise getExercise(long id, long topicId) {
        return new Exercise(id, topicId);
    }
    
    public Exercise getExercise(long id, long topicId, long generationTime) {
        return new Exercise(id, topicId, generationTime);
    }
    
    public ExerciseTerm getExerciseTerm() {
        return new ExerciseTerm();
    }
    
    public ExerciseTerm getExerciseTerm(long id) {
        return new ExerciseTerm(id);
    }
    
    public ExerciseTerm getExerciseTerm(long exerciseId, long termId) {
        return new ExerciseTerm(exerciseId, termId);
    }
    
    public ExerciseTerm getExerciseTerm(long id, long exerciseId, long termId) {
        return new ExerciseTerm(id, exerciseId, termId);
    }
    
    public Term getTerm(String term) {
        return new Term(term);
    }
    
    public Term getTerm(long id, String term) {
        return new Term(id, term);
    }
    
    public Term getTerm(long id, long generationTime, String term) {
        return new Term(id, generationTime, term);
    }
    
    public Topic getTopic(long id, String title) {
        return new Topic(id, title);
    }
    
    public Topic getTopic(String title) {
        return new Topic(title);
    }
    
}
