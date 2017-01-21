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
import com.github.naoghuman.abclist.configuration.IExerciseTermConfiguration;
import static com.github.naoghuman.abclist.configuration.IExerciseTermConfiguration.NO_TERMS_FOUND;
import com.github.naoghuman.abclist.model.ExerciseTerm;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.lib.database.api.DatabaseFacade;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;

/**
 *
 * @author Naoghuman
 */
public class ExerciseTermSqlService implements IDefaultConfiguration, IExerciseTermConfiguration {
    
    private static final Optional<ExerciseTermSqlService> instance = Optional.of(new ExerciseTermSqlService());

    public static final ExerciseTermSqlService getDefault() {
        return instance.get();
    }
    
    private ExerciseTermSqlService() {
        
    }
    
    public long countAllExerciseTermsWithTermId(long termId) {
        final Query query = DatabaseFacade.getDefault().getCrudService()
                .getEntityManager()
                .createNamedQuery(NAMED_QUERY__NAME__COUNT_ALL_EXERCISE_TERMS_WITH_TERM_ID);
        query.setParameter(EXERCISE_TERM__COLUMN_NAME__TERM_ID, termId);
        
        long countedTermsWithoutParent = NO_TERMS_FOUND;
        try {
            countedTermsWithoutParent = (long) query.getSingleResult();
        } catch (Exception e) {
            
        }
        
        return countedTermsWithoutParent;
    }
    
    public void create(ExerciseTerm exerciseTerm) {
        exerciseTerm.setId(System.currentTimeMillis());
        DatabaseFacade.getDefault().getCrudService().create(exerciseTerm);
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
    
    public ObservableList<Term> findAllTermsInExerciseTerm(ObservableList<ExerciseTerm> exerciseTerms) {
        final ObservableList<Term> allTermsInExerciseTerms = FXCollections.observableArrayList();
        exerciseTerms.stream()
                .map((exerciseTerm) -> DatabaseFacade.getDefault().getCrudService().findById(Term.class, exerciseTerm.getTermId()))
                .forEach((term) -> {
                    allTermsInExerciseTerms.add(term);
                });
        
        Collections.sort(allTermsInExerciseTerms);
        
        return allTermsInExerciseTerms;
    }
    
    public ObservableList<Term> findAllTermsInExerciseTermWithoutParent(ObservableList<Term> terms) {
        final ObservableList<Term> allTermsWithOutParent = FXCollections.observableArrayList();
        
        long counterTermInExercise = NO_TERMS_FOUND;
        for (Term term : terms) {
            counterTermInExercise = NO_TERMS_FOUND;
            counterTermInExercise = ExerciseTermSqlService.getDefault().countAllExerciseTermsWithTermId(term.getId());
            if (Objects.equals(counterTermInExercise, NO_TERMS_FOUND)) {
                allTermsWithOutParent.add(term);
            }
        }
        
        Collections.sort(allTermsWithOutParent);

        return allTermsWithOutParent;
    }
    
}