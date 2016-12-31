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
package com.github.naoghuman.dreaming.sounds.topic;

import com.github.naoghuman.dreaming.sounds.configuration.IDefaultConfiguration;
import com.github.naoghuman.dreaming.sounds.configuration.ITopicConfiguration;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;
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

/**
 *
 * @author Naoghuman
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = ITopicConfiguration.ENTITY__TABLE_NAME__TOPIC)
@NamedQueries({
    @NamedQuery(
            name = ITopicConfiguration.NAMED_QUERY__NAME__FIND_ALL,
            query = ITopicConfiguration.NAMED_QUERY__QUERY__FIND_ALL)//,
//    @NamedQuery(
//            name = ITopicConfiguration.NAMED_QUERY__NAME__FIND_ALL_FOR_NAVIGATION_HISTORY,
//            query = ITopicConfiguration.NAMED_QUERY__QUERY__FIND_ALL_FOR_NAVIGATION_HISTORY)
})
public class Topic implements Comparable<Topic>, Externalizable, IDefaultConfiguration, ITopicConfiguration {
       
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = System.currentTimeMillis();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TOPIC__COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, TOPIC__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = SIGN__EMPTY;
    
    @Column(name = TOPIC__COLUMN_NAME__TITLE)
    public String getTitle() {
        if (this.titleProperty == null) {
            return _title;
        } else {
            return titleProperty.get();
        }
    }
    
    public void setTitle(String title) {
        if (this.titleProperty == null) {
            _title = title;
        } else {
            this.titleProperty.set(title);
        }
    }
    
    public StringProperty titleProperty() {
        if (titleProperty == null) {
            titleProperty = new SimpleStringProperty(this, TOPIC__COLUMN_NAME__TITLE, _title);
        }
        
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    @Override
    public int compareTo(Topic other) {
        int compareTo = Long.compare(this.getId(), other.getId());
        if (compareTo == 0) {
            return compareTo;
        }
        
        compareTo = this.getTitle().compareTo(other.getTitle());
        
        return compareTo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Topic other = (Topic) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        
        if (!Objects.equals(this.getTitle(), other.getTitle())) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 79 * hash + Objects.hashCode(this.getTitle());
        
        return hash;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeObject(this.getTitle());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setTitle(String.valueOf(in.readObject()));
    }
    
}
