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
package com.github.naoghuman.dreaming.sounds.audioclipbox;

import com.github.naoghuman.dreaming.sounds.configuration.IDefaultConfiguration;
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
import com.github.naoghuman.dreaming.sounds.configuration.IAudioClipBoxConfiguration;

/**
 *
 * @author Naoghuman
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = IAudioClipBoxConfiguration.ENTITY__TABLE_NAME__SOUNDBOX)
@NamedQueries({
    @NamedQuery(
            name = IAudioClipBoxConfiguration.NAMED_QUERY__NAME__FIND_ALL_IN_TOPIC,
            query = IAudioClipBoxConfiguration.NAMED_QUERY__QUERY__FIND_ALL_IN_TOPIC)
})
public class AudioClipBox implements Comparable<AudioClipBox>, Externalizable, IDefaultConfiguration, IAudioClipBoxConfiguration {
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = System.currentTimeMillis();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AUDIOCLIPBOX__COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, AUDIOCLIPBOX__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  PARENT-ID --------------------------------------------------------
    private LongProperty parentIdProperty;
    private long _parentId = System.currentTimeMillis();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AUDIOCLIPBOX__COLUMN_NAME__PARENT_ID)
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
            parentIdProperty = new SimpleLongProperty(this, AUDIOCLIPBOX__COLUMN_NAME__PARENT_ID, _parentId);
        }
        
        return parentIdProperty;
    }
    // END  PARENT-ID ----------------------------------------------------------
    
    // START  SOUND ------------------------------------------------------------
    private StringProperty soundProperty = null;
    private String _sound = SIGN__EMPTY;
    
    @Column(name = AUDIOCLIPBOX__COLUMN_NAME__SOUND)
    public String getSound() {
        if (soundProperty == null) {
            return _sound;
        } else {
            return soundProperty.get();
        }
    }
    
    public void setSound(String sound) {
        if (soundProperty == null) {
            _sound = sound;
        } else {
            soundProperty.set(sound);
        }
    }
    
    public StringProperty soundProperty() {
        if (soundProperty == null) {
            soundProperty = new SimpleStringProperty(this, AUDIOCLIPBOX__COLUMN_NAME__SOUND, _sound);
        }
        
        return soundProperty;
    }
    // END  SOUND --------------------------------------------------------------
    
    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = SIGN__EMPTY;
    
    @Column(name = AUDIOCLIPBOX__COLUMN_NAME__TITLE)
    public String getTitle() {
        if (titleProperty == null) {
            return _title;
        } else {
            return titleProperty.get();
        }
    }
    
    public void setTitle(String title) {
        if (titleProperty == null) {
            _title = title;
        } else {
            titleProperty.set(title);
        }
    }
    
    public StringProperty titleProperty() {
        if (titleProperty == null) {
            titleProperty = new SimpleStringProperty(this, AUDIOCLIPBOX__COLUMN_NAME__TITLE, _title);
        }
        
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    @Override
    public int compareTo(AudioClipBox other) {
        int compareTo = Long.compare(this.getId(), other.getId());
        if (compareTo == 0) {
            return compareTo;
        }
        
        compareTo = Long.compare(this.getParentId(), other.getParentId());
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
        
        final AudioClipBox other = (AudioClipBox) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        
        if (this.getParentId() != other.getParentId()) {
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
        hash = 79 * hash + (int) (this.getParentId() ^ (this.getParentId() >>> 32));
        hash = 79 * hash + Objects.hashCode(this.getTitle());
        
        return hash;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeLong(this.getParentId());
        out.writeObject(this.getSound());
        out.writeObject(this.getTitle());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setParentId(in.readLong());
        this.setSound(String.valueOf(in.readObject()));
        this.setTitle(String.valueOf(in.readObject()));
    }
    
}
