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
package com.github.naoghuman.lib.card.core.model;

import com.github.naoghuman.lib.card.core.api.IConfiguration;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Naoghuman
 */
public class CategoryModel implements Comparable<CategoryModel>, Externalizable, IConfiguration {
    
    private static final long serialVersionUID = 1L;
    
    public CategoryModel() {
        
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID__CATEGORY_MODEL;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, COLUMN_NAME__ID, _id);
        }
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  SECTION-ID -------------------------------------------------------
    private LongProperty sectionIdProperty;
    private long _sectionId = System.currentTimeMillis();

    @Column(name = COLUMN_NAME__SECTION_ID)
    public long getSectionId() {
        if (sectionIdProperty == null) {
            return _sectionId;
        } else {
            return sectionIdProperty.get();
        }
    }

    public final void setSectionId(long sectionId) {
        if (sectionIdProperty == null) {
            _sectionId = sectionId;
        } else {
            sectionIdProperty.set(sectionId);
        }
    }

    public LongProperty sectionIdProperty() {
        if (sectionIdProperty == null) {
            sectionIdProperty = new SimpleLongProperty(this, COLUMN_NAME__SECTION_ID, _sectionId);
        }
        return sectionIdProperty;
    }
    // END  SECTION-ID ---------------------------------------------------------
    
    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = SIGN__EMPTY;
    
    @Column(name = COLUMN_NAME__TITLE)
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
            titleProperty = new SimpleStringProperty(this, COLUMN_NAME__TITLE, _title);
        }
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getSectionId())
                .append(this.getTitle())
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
        
        final CategoryModel other = (CategoryModel) obj;
        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getSectionId(), other.getSectionId())
                .append(this.getTitle(), other.getTitle())
                .isEquals();
    }
    
    @Override
    public int compareTo(CategoryModel other) {
        return new CompareToBuilder()
                .append(this.getId(), other.getId())
                .append(this.getSectionId(), other.getSectionId())
                .append(this.getTitle(), other.getTitle())
                .toComparison();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(COLUMN_NAME__ID, this.getId())
                .append(COLUMN_NAME__SECTION_ID, this.getSectionId())
                .append(COLUMN_NAME__TITLE, this.getTitle())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeLong(this.getSectionId());
        out.writeObject(StringEscapeUtils.escapeHtml4(this.getTitle()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setSectionId(in.readLong());
        this.setTitle(StringEscapeUtils.unescapeHtml4(String.valueOf(in.readObject())));
    }
    
}
