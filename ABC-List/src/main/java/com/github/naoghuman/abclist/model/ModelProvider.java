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
    
    public Exercise getExercise(long parentId) {
        return new Exercise(parentId);
    }
    
    public Exercise getExercise(long id, long parentId) {
        return new Exercise(id, parentId);
    }
    
    public Exercise getExercise(long id, long parentId, long generationTime) {
        return new Exercise(id, parentId, generationTime);
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
