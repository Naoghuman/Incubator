/*
 * Copyright (C) 2015 PRo
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
package com.github.naoghuman.lib.map.generator.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
 * TODO add list<mapmarker>
 *
 * @author PRo
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Map")
@NamedQueries({
    @NamedQuery(
            name = "Map.SelectAll",
            query = "SELECT m from Map m")
})
public class Map implements Comparable<Map>, Externalizable, Serializable {

    public Map() {
        this.initialize();
    }
    
    private void initialize() {
        
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = 0L; //FILE__DREAM__DEFAULT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
            idProperty = new SimpleLongProperty(this, "id", _id);
        }
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  GENERATIONTIME ---------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = System.currentTimeMillis();

    @Column(name = "generationtime")
    public long getGenerationTime() {
        if (this.generationTimeProperty == null) {
            return _generationTime;
        } else {
            return generationTimeProperty.get();
        }
    }

    public final void setGenerationTime(long generationTime) {
        if (this.generationTimeProperty == null) {
            _generationTime = generationTime;
        } else {
            this.generationTimeProperty.set(generationTime);
        }
    }

    public LongProperty generationTimeProperty() {
        if (generationTimeProperty == null) {
            generationTimeProperty = new SimpleLongProperty(this, "generationtime", _generationTime);
        }
        return generationTimeProperty;
    }
    // END  GENERATIONTIME -----------------------------------------------------
    
    // START  FAVORITE ---------------------------------------------------------
    private BooleanProperty favoriteProperty = null;
    private boolean _favorite = Boolean.FALSE;
    
    @Column(name = "favorite")
    public Boolean isFavorite() {
        if (this.favoriteProperty == null) {
            return _favorite;
        } else {
            return favoriteProperty.get();
        }
    }
    
    public void setFavorite(Boolean isFavorite) {
        if (this.favoriteProperty == null) {
            _favorite = isFavorite;
        } else {
            this.favoriteProperty.set(isFavorite);
        }
    }
    
    public BooleanProperty favoriteProperty() {
        if (favoriteProperty == null) {
            favoriteProperty = new SimpleBooleanProperty(this, "favorite", _favorite);
        }
        return favoriteProperty;
    }
    // END  FAVORITE -----------------------------------------------------------
    
    // START  DESCRIPTION ------------------------------------------------------
    private StringProperty descriptionProperty = null;
    private String _description = ""; //SIGN__EMPTY;
    
    @Column(name = "description")
    public String getDescription() {
        if (this.descriptionProperty == null) {
            return _description;
        } else {
            return descriptionProperty.get();
        }
    }
    
    public void setDescription(String description) {
        if (this.descriptionProperty == null) {
            _description = description;
        } else {
            this.descriptionProperty.set(description);
        }
    }
    
    public StringProperty descriptionProperty() {
        if (descriptionProperty == null) {
            descriptionProperty = new SimpleStringProperty(this, "description", _description);
        }
        return descriptionProperty;
    }
    // END  DESCRIPTION --------------------------------------------------------
    
    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = ""; //SIGN__EMPTY;
    
    @Column(name = "title")
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
            titleProperty = new SimpleStringProperty(this, "title", _title);
        }
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    // START  MAP-ICON-URL -----------------------------------------------------
    private StringProperty mapIconUrlProperty = null;
    private String _mapIconUrl = ""; //SIGN__EMPTY;
    
    @Column(name = "mapIconUrl")
    public String getMapIconUrl() {
        if (this.mapIconUrlProperty == null) {
            return _mapIconUrl;
        } else {
            return mapIconUrlProperty.get();
        }
    }
    
    public void setMapIconUrl(String mapIconUrl) {
        if (this.mapIconUrlProperty == null) {
            _mapIconUrl = mapIconUrl;
        } else {
            this.mapIconUrlProperty.set(mapIconUrl);
        }
    }
    
    public StringProperty mapIconUrlProperty() {
        if (mapIconUrlProperty == null) {
            mapIconUrlProperty = new SimpleStringProperty(this, "mapIconUrl", _mapIconUrl);
        }
        return mapIconUrlProperty;
    }
    // END  MAP-ICON-URL -------------------------------------------------------
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Long.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Map other = (Map) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int compareTo(Map other) {
        /*
        TODO
         - die neusten maps sind oben
         - compare generationtime
            - if equal compare title
            - if equal id
        */
        return Long.compare(other.getGenerationTime(), this.getGenerationTime());
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeLong(this.getGenerationTime());
        out.writeBoolean(this.isFavorite());
        out.writeObject(this.getDescription());
        out.writeObject(this.getTitle());
        out.writeObject(this.getMapIconUrl());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setGenerationTime(in.readLong());
        this.setFavorite(in.readBoolean());
        this.setDescription(String.valueOf(in.readObject()));
        this.setTitle(String.valueOf(in.readObject()));
        this.setMapIconUrl(String.valueOf(in.readObject()));
    }
    
}
