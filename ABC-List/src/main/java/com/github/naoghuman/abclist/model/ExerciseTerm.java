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

import com.github.naoghuman.abclist.configuration.IDefaultConfiguration;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.github.naoghuman.abclist.configuration.IExerciseTermConfiguration;

/**
 *
 * @author Naoghuman
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = IExerciseTermConfiguration.ENTITY__TABLE_NAME__EXERCISE_TERM)
@NamedQueries({
    @NamedQuery(
            name = IExerciseTermConfiguration.NAMED_QUERY__NAME__FIND_ALL_TERMS_WITH_EXERCISE_ID,
            query = IExerciseTermConfiguration.NAMED_QUERY__QUERY__FIND_ALL_TERMS_WITH_EXERCISE_ID)
})
public class ExerciseTerm implements Comparable<ExerciseTerm>, Externalizable, IDefaultConfiguration, IExerciseTermConfiguration {
    
    public ExerciseTerm() {
        this(DEFAULT_ID, DEFAULT_ID);
    }
    
    public ExerciseTerm(long exerciseId, long termId) {
        this.init(exerciseId, termId);
    }
    
    private void init(long exerciseId, long termId) {
        this.setExerciseId(exerciseId);
        this.setTermId(termId);
    }
    
    // START  EXERCISE-ID ------------------------------------------------------
    private LongProperty exerciseIdProperty;
    private long _exerciseId = DEFAULT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = EXERCISE_TERM__COLUMN_NAME__EXERCISE_ID)
    public long getExerciseId() {
        if (exerciseIdProperty == null) {
            return _exerciseId;
        } else {
            return exerciseIdProperty.get();
        }
    }

    public final void setExerciseId(long exerciseId) {
        if (exerciseIdProperty == null) {
            _exerciseId = exerciseId;
        } else {
            exerciseIdProperty.set(exerciseId);
        }
    }

    public LongProperty exerciseIdProperty() {
        if (exerciseIdProperty == null) {
            exerciseIdProperty = new SimpleLongProperty(this, EXERCISE_TERM__COLUMN_NAME__EXERCISE_ID, _exerciseId);
        }
        
        return exerciseIdProperty;
    }
    // END  EXERCISE-ID --------------------------------------------------------
	
    // START  TERM-ID ----------------------------------------------------------
    private LongProperty termIdProperty;
    private long _termId = DEFAULT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = EXERCISE_TERM__COLUMN_NAME__TERM_ID)
    public long getTermId() {
        if (termIdProperty == null) {
            return _termId;
        } else {
            return termIdProperty.get();
        }
    }

    public final void setTermId(long termId) {
        if (termIdProperty == null) {
            _termId = termId;
        } else {
            termIdProperty.set(termId);
        }
    }

    public LongProperty termIdProperty() {
        if (termIdProperty == null) {
            termIdProperty = new SimpleLongProperty(this, EXERCISE_TERM__COLUMN_NAME__TERM_ID, _termId);
        }
        
        return termIdProperty;
    }
    // END  TERM-ID ------------------------------------------------------------
	
    @Override
    public int compareTo(ExerciseTerm other) {
        return new CompareToBuilder()
                .append(this.getExerciseId(), other.getExerciseId())
                .append(this.getTermId(), other.getTermId())
                .toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (
                obj == null
                || this.getClass() != obj.getClass()
	) {
            return false;
        }
        
        final ExerciseTerm other = (ExerciseTerm) obj;
        return new EqualsBuilder()
                .append(this.getExerciseId(), other.getExerciseId())
                .append(this.getTermId(), other.getTermId())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getExerciseId())
                .append(this.getTermId())
                .toHashCode();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(EXERCISE_TERM__COLUMN_NAME__EXERCISE_ID, this.getExerciseId())
                .append(EXERCISE_TERM__COLUMN_NAME__TERM_ID, this.getTermId())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getExerciseId());
        out.writeLong(this.getTermId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setExerciseId(in.readLong());
        this.setTermId(in.readLong());
    }
    
}
