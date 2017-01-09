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
import com.github.naoghuman.abclist.configuration.IExerciseConfiguration;
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

/**
 *
 * @author Naoghuman
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = IExerciseConfiguration.ENTITY__TABLE_NAME__EXERCISE)
@NamedQueries({
    @NamedQuery(
            name = IExerciseConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_PARENT_ID,
            query = IExerciseConfiguration.NAMED_QUERY__QUERY__FIND_ALL_WITH_PARENT_ID)
})
public class Exercise implements Comparable<Exercise>, Externalizable, IDefaultConfiguration, IExerciseConfiguration {
    
    public Exercise() {
        this(DEFAULT_ID, DEFAULT_ID);
    }
    
    public Exercise(long parentId) {
        this(DEFAULT_ID, parentId, System.currentTimeMillis());
    }
    
    public Exercise(long id, long parentId) {
        this(id, parentId, System.currentTimeMillis());
    }
    
    public Exercise(long id, long parentId, long generationTime) {
        this.init(id, parentId, generationTime);
    }
    
    private void init(long id, long parentId, long generationTime) {
        this.setId(id);
        this.setParentId(parentId);
        this.setGenerationTime(generationTime);
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = EXERCISE__COLUMN_NAME__ID)
    public long getId() {
        if (idProperty == null) {
            return _id;
        } else {
            return idProperty.get();
        }
    }

    public final void setId(long id) {
        if (idProperty == null) {
            _id = id;
        } else {
            idProperty.set(id);
        }
    }

    public LongProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleLongProperty(this, EXERCISE__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  PARENT-ID --------------------------------------------------------
    private LongProperty parentIdProperty;
    private long _parentId = DEFAULT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = EXERCISE__COLUMN_NAME__PARENT_ID)
    public long getParentId() {
        if (parentIdProperty == null) {
            return _parentId;
        } else {
            return parentIdProperty.get();
        }
    }

    public final void setParentId(long parentId) {
        if (parentIdProperty == null) {
            _parentId = parentId;
        } else {
            parentIdProperty.set(parentId);
        }
    }

    public LongProperty parentIdProperty() {
        if (parentIdProperty == null) {
            parentIdProperty = new SimpleLongProperty(this, EXERCISE__COLUMN_NAME__PARENT_ID, _parentId);
        }
        
        return parentIdProperty;
    }
    // END  PARENT-ID ----------------------------------------------------------
	
    // START  GENERATIONTIME ---------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = System.currentTimeMillis();

    @Column(name = EXERCISE__COLUMN_NAME__GENERATION_TIME)
    public long getGenerationTime() {
        if (generationTimeProperty == null) {
            return _generationTime;
        } else {
            return generationTimeProperty.get();
        }
    }

    public final void setGenerationTime(long generationTime) {
        if (generationTimeProperty == null) {
            _generationTime = generationTime;
        } else {
            generationTimeProperty.set(generationTime);
        }
    }

    public LongProperty generationTimeProperty() {
        if (generationTimeProperty == null) {
            generationTimeProperty = new SimpleLongProperty(this, EXERCISE__COLUMN_NAME__GENERATION_TIME, _generationTime);
        }
        return generationTimeProperty;
    }
    // END  GENERATIONTIME -----------------------------------------------------
    
    @Override
    public int compareTo(Exercise other) {
        return new CompareToBuilder()
                .append(other.getGenerationTime(), this.getGenerationTime())
                .append(other.getId(), this.getId())
                .append(other.getParentId(), this.getParentId())
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
        
        final Exercise other = (Exercise) obj;
        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getParentId(), other.getParentId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getParentId())
                .append(this.getGenerationTime())
                .toHashCode();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(EXERCISE__COLUMN_NAME__ID, this.getId())
                .append(EXERCISE__COLUMN_NAME__PARENT_ID, this.getParentId())
                .append(EXERCISE__COLUMN_NAME__GENERATION_TIME, this.getGenerationTime())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeLong(this.getParentId());
        out.writeLong(this.getGenerationTime());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setParentId(in.readLong());
        this.setGenerationTime(in.readLong());
    }
    
}
