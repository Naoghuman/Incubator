/*
 * Copyright (C) 2016 Naoghuman
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
package com.github.naoghuman.pm.model;

import com.github.naoghuman.pm.model.api.IEntityModel;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
@Table(name = IEntityModel.TABLE_NAME__DAILY_MODEL)
@NamedQueries({
    @NamedQuery(
            name = IEntityModel.NAMED_QUERY__NAME__DAILY_MODEL__FIND_ALL,
            query = IEntityModel.NAMED_QUERY__QUERY__DAILY_MODEL__FIND_ALL)//,
//    @NamedQuery(
//            name = IEntityModel.NAMED_QUERY__NAME__FIND_ALL_FOR_NAVIGATION_HISTORY,
//            query = IEntityModel.NAMED_QUERY__QUERY__FIND_ALL_FOR_NAVIGATION_HISTORY)
})
public class DailySectionModel implements Comparable<DailySectionModel>, Externalizable, IEntityModel {
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID__DAILY_MODEL;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_NAME__ID)
    public long getId() {
        if (this.idProperty == null) {
            return _id;
        } else {
            return idProperty.get();
        }
    }

    public final void setId(long id) {
        if (this.idProperty == null) {
            _id = id;
        } else {
            this.idProperty.set(id);
        }
    }

    public LongProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleLongProperty(this, COLUMN_NAME__ID, _id);
        }
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  DAILY-DATE -------------------------------------------------------
    private StringProperty dailyDateProperty = null;
    private String _dailyDate = NO_DAILY_DATE;
    
    @Column(name = COLUMN_NAME__DAILY_DATE)
    public String getDailyDate() {
        if (this.dailyDateProperty == null) {
            return _dailyDate;
        } else {
            return dailyDateProperty.get();
        }
    }
    
    public void setDailyDate(String dailyDate) {
        if (this.dailyDateProperty == null) {
            _dailyDate = dailyDate;
        } else {
            this.dailyDateProperty.set(dailyDate);
        }
    }
    
    public StringProperty dailyDateProperty() {
        if (dailyDateProperty == null) {
            dailyDateProperty = new SimpleStringProperty(this, 
                    COLUMN_NAME__DAILY_DATE, _dailyDate);
        }
        return dailyDateProperty;
    }
    // END  DAILY-DATE ---------------------------------------------------------
    
    // TODO Save here list with append project-ids
    // START  PROJECT-ID -------------------------------------------------------
    private LongProperty projectIdProperty;
    private long _projectId = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_NAME__PROJECT_ID)
    public long getProjectId() {
        if (this.projectIdProperty == null) {
            return _projectId;
        } else {
            return projectIdProperty.get();
        }
    }

    public final void setProjectId(long projectId) {
        if (this.projectIdProperty == null) {
            _projectId = projectId;
        } else {
            this.projectIdProperty.set(projectId);
        }
    }

    public LongProperty projectIdProperty() {
        if (projectIdProperty == null) {
            projectIdProperty = new SimpleLongProperty(this, COLUMN_NAME__PROJECT_ID, _projectId);
        }
        return projectIdProperty;
    }
    // END  PROJECT-ID ---------------------------------------------------------
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getDailyDate())
                .append(this.getProjectId())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj == this) {
            return false;
        }
        
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        
        final DailySectionModel other = (DailySectionModel) obj;
        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getDailyDate(), other.getDailyDate())
                .append(this.getProjectId(), other.getProjectId())
                .isEquals();
    }
    
    @Override
    public int compareTo(DailySectionModel other) {
        return new CompareToBuilder()
                .append(other.getDailyDate(), this.getDailyDate())
                .append(other.getProjectId(), this.getProjectId())
                .append(other.getId(), this.getId())
                .toComparison();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(COLUMN_NAME__ID, this.getId())
                .append(COLUMN_NAME__DAILY_DATE, this.getDailyDate())
                .append(COLUMN_NAME__PROJECT_ID, this.getProjectId())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeChars(this.getDailyDate());
        out.writeLong(this.getProjectId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setDailyDate(String.valueOf(in.readObject()));
        this.setProjectId(in.readLong());
    }
    
}
