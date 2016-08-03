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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
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
@Table(name = IEntityModel.TABLE_NAME__PROJECT_MODEL)
@NamedQueries({
    @NamedQuery(
            name = IEntityModel.NAMED_QUERY__NAME__PROJECT_MODEL__FIND_ALL,
            query = IEntityModel.NAMED_QUERY__QUERY__PROJECT_MODEL__FIND_ALL)//,
//    @NamedQuery(
//            name = IEntityModel.NAMED_QUERY__NAME__FIND_ALL_FOR_NAVIGATION_HISTORY,
//            query = IEntityModel.NAMED_QUERY__QUERY__FIND_ALL_FOR_NAVIGATION_HISTORY)
})
public class ProjectModel implements Comparable<ProjectModel>, Externalizable, IEntityModel {
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID__PROJECT_MODEL;

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
    
    // START  POSITION ---------------------------------------------------------
    private IntegerProperty positionProperty;
    private int _position = 0;

    @Column(name = COLUMN_NAME__POSITION)
    public int getPosition() {
        if (this.positionProperty == null) {
            return _position;
        } else {
            return positionProperty.get();
        }
    }

    public final void setPosition(int position) {
        if (this.positionProperty == null) {
            _position = position;
        } else {
            this.positionProperty.set(position);
        }
    }

    public IntegerProperty positionProperty() {
        if (positionProperty == null) {
            positionProperty = new SimpleIntegerProperty(this,
                    COLUMN_NAME__POSITION, _position);
        }
        return positionProperty;
    }
    // END  POSITION -----------------------------------------------------------
    
    // START  GENERATIONTIME ---------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = System.currentTimeMillis();

    @Column(name = COLUMN_NAME__GENERATION_TIME)
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
            generationTimeProperty = new SimpleLongProperty(this,
                    COLUMN_NAME__GENERATION_TIME, _generationTime);
        }
        return generationTimeProperty;
    }
    // END  GENERATIONTIME -----------------------------------------------------
    
    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = NO_TITLE;
    
    @Column(name = COLUMN_NAME__TITLE)
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
            titleProperty = new SimpleStringProperty(this, COLUMN_NAME__TITLE, _title);
        }
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    // START  COLOR ------------------------------------------------------------
    private static final String EXPRESSION_PREFIX = "-fx-background-color: rgb("; // NOI18N
    private static final String EXPRESSION_SUFFIX = ");"; // NOI18N
    private static final String SIGN_COMMA = ","; // NOI18N
    
    private StringProperty colorAsStyleProperty = null;
    private String _colorAsStyle = SIGN__EMPTY;
    
    public Color convertEntityAttributeToColor() {
        final String colorAsStyle = this.getColorAsStyle();
        final String colorParts = colorAsStyle.substring(EXPRESSION_PREFIX.length(), 
                colorAsStyle.length() - EXPRESSION_SUFFIX.length());
        final String[] splittedColorParts = colorParts.split(SIGN_COMMA);
        final double red = Double.parseDouble(splittedColorParts[0]) / 255.0d;
        final double green = Double.parseDouble(splittedColorParts[1]) / 255.0d;
        final double blue = Double.parseDouble(splittedColorParts[2]) / 255.0d;
        final double opactiy = 1.0d;
        final Color color = new Color(red, green, blue, opactiy);
        
        return color;
    }
    
    public void convertColorToDatabaseColumn(Color color) {
        final StringBuilder colorAsStyle = new StringBuilder();
        colorAsStyle.append(EXPRESSION_PREFIX);
        colorAsStyle.append((int)(color.getRed() * 255.0));
        colorAsStyle.append(SIGN_COMMA);
        colorAsStyle.append((int)(color.getGreen() * 255.0));
        colorAsStyle.append(SIGN_COMMA);
        colorAsStyle.append((int)(color.getBlue() * 255.0));
        colorAsStyle.append(EXPRESSION_SUFFIX);
        
        if (this.colorAsStyleProperty == null) {
            _colorAsStyle = colorAsStyle.toString();
        } else {
            this.colorAsStyleProperty.set(colorAsStyle.toString());
        }
    }
    
    @Column(name = COLUMN_NAME__COLOR_AS_STYLE)
    public String getColorAsStyle() {
        if (this.colorAsStyleProperty == null) {
            return _colorAsStyle;
        } else {
            return colorAsStyleProperty.get();
        }
    }
    
    public void setColorAsStyle(String colorAsStyle) {
        if (this.colorAsStyleProperty == null) {
            _colorAsStyle = colorAsStyle;
        } else {
            this.colorAsStyleProperty.set(colorAsStyle);
        }
    }
    
    public StringProperty colorProperty() {
        if (colorAsStyleProperty == null) {
            colorAsStyleProperty = new SimpleStringProperty(this, 
                    COLUMN_NAME__COLOR_AS_STYLE, _colorAsStyle);
        }
        return colorAsStyleProperty;
    }
    // END  COLOR --------------------------------------------------------------
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getGenerationTime())
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
        
        final ProjectModel other = (ProjectModel) obj;
        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .isEquals();
    }
    
    @Override
    public int compareTo(ProjectModel other) {
        return new CompareToBuilder()
                .append(other.getGenerationTime(), this.getGenerationTime())
                .append(other.getTitle(), this.getTitle())
                .append(other.getId(), this.getId())
                .toComparison();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(COLUMN_NAME__ID, this.getId())
                .append(COLUMN_NAME__POSITION, this.getPosition())
                .append(COLUMN_NAME__GENERATION_TIME, this.getGenerationTime())
                .append(COLUMN_NAME__TITLE, this.getTitle())
                .append(COLUMN_NAME__COLOR_AS_STYLE, this.getColorAsStyle())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeInt(this.getPosition());
        out.writeLong(this.getGenerationTime());
        out.writeObject(this.getTitle());
        out.writeObject(this.getColorAsStyle());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setPosition(in.readInt());
        this.setGenerationTime(in.readLong());
        this.setTitle(String.valueOf(in.readObject()));
        this.setColorAsStyle(String.valueOf(in.readObject()));
    }
    
}
