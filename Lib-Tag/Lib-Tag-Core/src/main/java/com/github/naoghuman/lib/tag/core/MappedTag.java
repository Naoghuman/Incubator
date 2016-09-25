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
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author Naoghuman
 */
public class MappedTag implements Comparable<MappedTag>, Externalizable {
    
    // START  PARENT-ID --------------------------------------------------------
    private LongProperty parentIdProperty;
    private long _parentId = System.currentTimeMillis();

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
            parentIdProperty = new SimpleLongProperty(this, "parent-id", _parentId); // NOI18N
        }
        return parentIdProperty;
    }
    // END  PARENT-ID ----------------------------------------------------------

    
    // START  TAG-ID -----------------------------------------------------------
    private LongProperty tagIdProperty;
    private long _tagId = System.currentTimeMillis();

    public long getTagId() {
        if (tagIdProperty == null) {
            return _tagId;
        } else {
            return tagIdProperty.get();
        }
    }

    public final void setTagId(long tagId) {
        if (tagIdProperty == null) {
            _tagId = tagId;
        } else {
            tagIdProperty.set(tagId);
        }
    }

    public LongProperty tagIdProperty() {
        if (tagIdProperty == null) {
            tagIdProperty = new SimpleLongProperty(this, "tag-id", _tagId); // NOI18N
        }
        return tagIdProperty;
    }
    // END  TAG-ID -----------------------------------------------------------

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.getParentId() ^ (this.getParentId() >>> 32));
        hash = 53 * hash + (int) (this.getTagId() ^ (this.getTagId() >>> 32));
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
        final MappedTag other = (MappedTag) obj;
        if (this.getParentId() != other.getParentId()) {
            return false;
        }
        if (this.getTagId() != other.getTagId()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MappedTag other) {
        int compareTo = Long.compare(this.getParentId(), other.getParentId());
        if (compareTo == 0) {
            return Long.compare(this.getTagId(), other.getTagId());
        }
        
        return compareTo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MappedTag[");
        sb.append("parentId=").append(this.getParentId());
        sb.append(", tagId=").append(this.getTagId());
        sb.append("]");
        
        return sb.toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getParentId());
        out.writeLong(this.getTagId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setParentId(in.readLong());
        this.setTagId(in.readLong());
    }

}
