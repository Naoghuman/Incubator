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
    
    // START  DAILY ------------------------------------------------------------
    private StringProperty dailyProperty = null;
    private String _daily = NO_DAILY;
    
    @Column(name = COLUMN_NAME__DAILY)
    public String getDaily() {
        if (this.dailyProperty == null) {
            return _daily;
        } else {
            return dailyProperty.get();
        }
    }
    
    public void setDaily(String daily) {
        if (this.dailyProperty == null) {
            _daily = daily;
        } else {
            this.dailyProperty.set(daily);
        }
    }
    
    public StringProperty dailyProperty() {
        if (dailyProperty == null) {
            dailyProperty = new SimpleStringProperty(this, COLUMN_NAME__DAILY, _daily);
        }
        return dailyProperty;
    }
    // END  DAILY --------------------------------------------------------------
    
    // START  PROJECT-ID -------------------------------------------------------
    private LongProperty projectIdProperty;
    private long _projectId = DEFAULT_ID__DAILY_MODEL;

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
                .append(this.getDaily())
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
                .append(this.getDaily(), other.getDaily())
                .append(this.getProjectId(), other.getProjectId())
                .isEquals();
    }
    
    @Override
    public int compareTo(DailySectionModel other) {
        return new CompareToBuilder()
                .append(other.getDaily(), this.getDaily())
                .append(other.getProjectId(), this.getProjectId())
                .append(other.getId(), this.getId())
                .toComparison();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.getId()) // NOI18N
                .append("daily", this.getDaily()) // NOI18N
                .append("project-id", this.getProjectId()) // NOI18N
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeChars(this.getDaily());
        out.writeLong(this.getProjectId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setDaily(String.valueOf(in.readObject()));
        this.setProjectId(in.readLong());
    }
    
}
