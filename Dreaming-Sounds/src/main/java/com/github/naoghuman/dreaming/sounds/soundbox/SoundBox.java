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
package com.github.naoghuman.dreaming.sounds.soundbox;

import com.github.naoghuman.dreaming.sounds.configuration.IDefaultConfiguration;
import com.github.naoghuman.dreaming.sounds.configuration.ISoundBoxConfiguration;
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
@Table(name = ISoundBoxConfiguration.ENTITY__TABLE_NAME__SOUNDBOX)
@NamedQueries({
    @NamedQuery(
            name = ISoundBoxConfiguration.NAMED_QUERY__NAME__FIND_ALL_IN_TOPIC,
            query = ISoundBoxConfiguration.NAMED_QUERY__QUERY__FIND_ALL_IN_TOPIC)
})
public class SoundBox implements Comparable<SoundBox>, Externalizable, IDefaultConfiguration, ISoundBoxConfiguration {
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = System.currentTimeMillis();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = SOUNDBOX__COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, SOUNDBOX__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  SOUND ------------------------------------------------------------
    private StringProperty soundProperty = null;
    private String _sound = SIGN__EMPTY;
    
    @Column(name = SOUNDBOX__COLUMN_NAME__SOUND)
    public String getSound() {
        if (this.soundProperty == null) {
            return _sound;
        } else {
            return soundProperty.get();
        }
    }
    
    public void setSound(String sound) {
        if (this.soundProperty == null) {
            _sound = sound;
        } else {
            this.soundProperty.set(sound);
        }
    }
    
    public StringProperty soundProperty() {
        if (soundProperty == null) {
            soundProperty = new SimpleStringProperty(this, SOUNDBOX__COLUMN_NAME__SOUND, _sound);
        }
        
        return soundProperty;
    }
    // END  SOUND --------------------------------------------------------------
    
    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = SIGN__EMPTY;
    
    @Column(name = SOUNDBOX__COLUMN_NAME__TITLE)
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
            titleProperty = new SimpleStringProperty(this, SOUNDBOX__COLUMN_NAME__TITLE, _title);
        }
        
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    @Override
    public int compareTo(SoundBox other) {
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
        
        final SoundBox other = (SoundBox) obj;
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
        out.writeObject(this.getSound());
        out.writeObject(this.getTitle());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setSound(String.valueOf(in.readObject()));
        this.setTitle(String.valueOf(in.readObject()));
    }
    
}
