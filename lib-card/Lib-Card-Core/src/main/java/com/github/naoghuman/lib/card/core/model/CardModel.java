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

import com.github.naoghuman.lib.card.core.api.ICardModel;
import com.github.naoghuman.lib.card.core.api.IConfiguration;
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
import org.apache.commons.lang3.StringEscapeUtils;
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
@Table(name = IConfiguration.ENTITY__TABLE_NAME__CARD_MODEL)
@NamedQueries({
    @NamedQuery(
            name = IConfiguration.NAMED_QUERY__NAME__CARD_FIND_ALL,
            query = IConfiguration.NAMED_QUERY__QUERY__CARD_FIND_ALL),
    @NamedQuery(
            name = IConfiguration.NAMED_QUERY__NAME__CARD_FIND_BY_ID,
            query = IConfiguration.NAMED_QUERY__QUERY__CARD_FIND_BY_ID)
})
public class CardModel implements Externalizable, IConfiguration, ICardModel {
    
    private static final long serialVersionUID = 1L;
    
    public CardModel() {
        
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID__CARD_MODEL;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_NAME__ID)
    @Override
    public long getId() {
        if (idProperty == null) {
            return _id;
        } else {
            return idProperty.get();
        }
    }

    @Override
    public final void setId(long id) {
        if (idProperty == null) {
            _id = id;
        } else {
            idProperty.set(id);
        }
    }

    @Override
    public LongProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleLongProperty(this, COLUMN_NAME__ID, _id);
        }
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  GENERATIONTIME ---------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = System.currentTimeMillis();

    @Column(name = COLUMN_NAME__GENERATION_TIME)
    @Override
    public long getGenerationTime() {
        if (generationTimeProperty == null) {
            return _generationTime;
        } else {
            return generationTimeProperty.get();
        }
    }

    @Override
    public final void setGenerationTime(long generationTime) {
        if (generationTimeProperty == null) {
            _generationTime = generationTime;
        } else {
            generationTimeProperty.set(generationTime);
        }
    }

    @Override
    public LongProperty generationTimeProperty() {
        if (generationTimeProperty == null) {
            generationTimeProperty = new SimpleLongProperty(this, COLUMN_NAME__GENERATION_TIME, _generationTime);
        }
        return generationTimeProperty;
    }
    // END  GENERATIONTIME -----------------------------------------------------
    
    // START  ANSWER -----------------------------------------------------------
    private StringProperty answerProperty = null;
    private String _answer = SIGN__EMPTY;
    
    @Column(name = COLUMN_NAME__ANSWER)
    @Override
    public String getAnswer() {
        if (answerProperty == null) {
            return _answer;
        } else {
            return answerProperty.get();
        }
    }
    
    @Override
    public void setAnswer(String answer) {
        if (answerProperty == null) {
            _answer = answer;
        } else {
            answerProperty.set(answer);
        }
    }
    
    @Override
    public StringProperty answerProperty() {
        if (answerProperty == null) {
            answerProperty = new SimpleStringProperty(this, COLUMN_NAME__ANSWER, _answer);
        }
        return answerProperty;
    }
    // END  ANSWER -------------------------------------------------------------
    
    // START  QUESTION ---------------------------------------------------------
    private StringProperty questionProperty = null;
    private String _question = SIGN__EMPTY;
    
    @Column(name = COLUMN_NAME__QUESTION)
    @Override
    public String getQuestion() {
        if (questionProperty == null) {
            return _question;
        } else {
            return questionProperty.get();
        }
    }
    
    @Override
    public void setQuestion(String question) {
        if (questionProperty == null) {
            _question = question;
        } else {
            questionProperty.set(question);
        }
    }
    
    @Override
    public StringProperty questionProperty() {
        if (questionProperty == null) {
            questionProperty = new SimpleStringProperty(this, COLUMN_NAME__QUESTION, _question);
        }
        return questionProperty;
    }
    // END  QUESTION -----------------------------------------------------------
    
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
        
        final CardModel other = (CardModel) obj;
        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .isEquals();
    }
    
    @Override
    public int compareTo(CardModel other) {
        return new CompareToBuilder()
                .append(this.getId(), other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .toComparison();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(COLUMN_NAME__ID, this.getId())
                .append(COLUMN_NAME__GENERATION_TIME, this.getGenerationTime())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeLong(this.getGenerationTime());
        out.writeObject(StringEscapeUtils.escapeHtml4(this.getAnswer()));
        out.writeObject(StringEscapeUtils.escapeHtml4(this.getQuestion()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setGenerationTime(in.readLong());
        this.setAnswer(StringEscapeUtils.unescapeHtml4(String.valueOf(in.readObject())));
        this.setQuestion(StringEscapeUtils.unescapeHtml4(String.valueOf(in.readObject())));
    }
    
}
