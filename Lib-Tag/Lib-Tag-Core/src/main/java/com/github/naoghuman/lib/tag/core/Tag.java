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
package com.github.naoghuman.lib.tag.core;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author Naoghuman
 */
public class Tag implements Comparable<Tag>, Externalizable {
    
    String convertColorToString(Color color) {
        final StringBuilder sb = new StringBuilder();
        sb.append(color.getRed());
        sb.append(";"); // NOI18N
        sb.append(color.getGreen());
        sb.append(";"); // NOI18N
        sb.append(color.getBlue());
        sb.append(";"); // NOI18N
        sb.append(color.getOpacity());
        
        return sb.toString();
    }
    
    Color convertStringToColor(String color) {
        final String[] colorParts = color.split(";");
        Color convertedColor = Color.BLACK;
        try {
            final double red = Double.parseDouble(colorParts[0]);
            final double green = Double.parseDouble(colorParts[1]);
            final double blue = Double.parseDouble(colorParts[2]);
            final double opacity = Double.parseDouble(colorParts[3]);
            convertedColor = new Color(red, green, blue, opacity);
        }
        catch (Exception ex) {
            
        }
        
        return convertedColor;
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = System.currentTimeMillis();

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
            idProperty = new SimpleLongProperty(this, "id", _id); // NOI18N
        }
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------

    // START  DESCRIPTION ------------------------------------------------------
    private StringProperty descriptionProperty = null;
    private String _description = "<no description>"; // NOI18N
    
    public String getDescription() {
        if (descriptionProperty == null) {
            return _description;
        } else {
            return descriptionProperty.get();
        }
    }
    
    public void setDescription(String description) {
        if (descriptionProperty == null) {
            _description = description;
        } else {
            descriptionProperty.set(description);
        }
    }
    
    public StringProperty descriptionProperty() {
        if (descriptionProperty == null) {
            descriptionProperty = new SimpleStringProperty(this, "description", _description); // NOI18N
        }
        return descriptionProperty;
    }
    // END  DESCRIPTION --------------------------------------------------------

    // START  TITLE ------------------------------------------------------------
    private StringProperty titleProperty = null;
    private String _title = "<no title>"; // NOI18N
    
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
            titleProperty = new SimpleStringProperty(this, "title", _title); // NOI18N
        }
        return titleProperty;
    }
    // END  TITLE --------------------------------------------------------------
    
    // START COLOR -------------------------------------------------------------
    private ObjectProperty<Color> colorProperty = null;
    private Color _color = Color.BLACK;
    
    public Color getColor() {
        if (colorProperty == null) {
            return _color;
        } else {
            return colorProperty.get();
        }
    }
    
    public void setColor(Color color) {
        if (colorProperty == null) {
            _color = color;
        } else {
            colorProperty.set(color);
        }
    }
    
    public ObjectProperty<Color> colorProperty() {
        if (colorProperty == null) {
            colorProperty = new SimpleObjectProperty(this, "color", _color); // NOI18N
        }
        return colorProperty;
    }
    // END COLOR ---------------------------------------------------------------

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 89 * hash + Objects.hashCode(this.getTitle());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.getTitle(), other.getTitle())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Tag other) {
        int compareTo = this.getTitle().compareTo(other.getTitle());
        if (compareTo == 0) {
            return Long.compare(this.getId(), other.getId());
        }
        
        return compareTo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Tag[");
        sb.append("id=").append(this.getId());
        sb.append(", title=").append(this.getTitle());
        sb.append(", description=").append(this.getDescription());
        sb.append("]");
        
        return sb.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeObject(this.getDescription());
        out.writeObject(this.getTitle());
        out.writeObject(this.convertColorToString(this.getColor()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setDescription(String.valueOf(in.readObject()));
        this.setTitle(String.valueOf(in.readObject()));
        this.setColor(this.convertStringToColor(String.valueOf(in.readObject())));
    }

}
