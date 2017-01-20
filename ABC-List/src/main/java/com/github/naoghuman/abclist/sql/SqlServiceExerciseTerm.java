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
import com.github.naoghuman.lib.database.api.DatabaseFacade;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author Naoghuman
 */
public class SqlServiceExerciseTerm implements IDefaultConfiguration, IExerciseTermConfiguration {
    
    private static final Optional<SqlServiceExerciseTerm> instance = Optional.of(new SqlServiceExerciseTerm());

    public static final SqlServiceExerciseTerm getDefault() {
        return instance.get();
    }
    
    private SqlServiceExerciseTerm() {
        
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
    
}
