/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.abclist.model;

import com.github.naoghuman.abclist.configuration.IDefaultConfiguration;
import com.github.naoghuman.abclist.configuration.IWordConfiguration;
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
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Naoghuman
 */
public class Term implements Comparable<Term>, Externalizable, IDefaultConfiguration, IWordConfiguration {
    
    public Term() {
        this(SIGN__EMPTY);
    }

    public Term(String term) {
        this(DEFAULT_ID, term);
    }

    public Term(long id, String term) {
        this.init(id, term);
    }
    
    private void init(long id, String term) {
        this.setId(id);
        this.setTerm(term);
    }
       
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WORD__COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, WORD__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  TERM -------------------------------------------------------------
    private StringProperty termProperty = null;
    private String _term = SIGN__EMPTY;
    
    @Column(name = WORD__COLUMN_NAME__TERM)
    public String getTerm() {
        if (termProperty == null) {
            return _term;
        } else {
            return termProperty.get();
        }
    }
    
    public void setTerm(String term) {
        if (termProperty == null) {
            _term = term;
        } else {
            termProperty.set(term);
        }
    }
    
    public StringProperty termProperty() {
        if (termProperty == null) {
            termProperty = new SimpleStringProperty(this, WORD__COLUMN_NAME__TERM, _term);
        }
        
        return termProperty;
    }
    // END  TERM ---------------------------------------------------------------
    
    @Override
    public int compareTo(Term other) {
        return new CompareToBuilder()
                .append(this.getTerm(), other.getTerm())
                .append(this.getId(), this.getId())
                .toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (
                obj == null
                || this.getClass() != obj.getClass()
	) {
            return false;
        }
        
        final Term other = (Term) obj;
        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getTerm(), other.getTerm())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getTerm())
                .toHashCode();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeObject(this.getTerm());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setTerm(String.valueOf(in.readObject()));
    }
    
}
