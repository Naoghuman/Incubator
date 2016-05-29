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
package com.github.naoghuman.lib.card.core.api;

import com.github.naoghuman.lib.card.core.api.IConfiguration;
import com.github.naoghuman.lib.card.core.model.CardModel;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Naoghuman
 */
public interface ICardModel extends Comparable<CardModel> {
    
    // START  ID ---------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = IConfiguration.COLUMN_NAME__ID)
    public long getId();
    public void setId(long id);
    public LongProperty idProperty();
    // END  ID -----------------------------------------------------------------
    
    // START  GENERATIONTIME ---------------------------------------------------
    @Column(name = IConfiguration.COLUMN_NAME__GENERATION_TIME)
    public long getGenerationTime();
    public void setGenerationTime(long generationTime);
    public LongProperty generationTimeProperty();
    // END  GENERATIONTIME -----------------------------------------------------
    
    // START  ANSWER -----------------------------------------------------------
    @Column(name = IConfiguration.COLUMN_NAME__ANSWER)
    public String getAnswer();
    public void setAnswer(String answer);
    public StringProperty answerProperty();
    // END  ANSWER -------------------------------------------------------------
    
    // START  QUESTION ---------------------------------------------------------
    @Column(name = IConfiguration.COLUMN_NAME__QUESTION)
    public String getQuestion();
    public void setQuestion(String question);
    public StringProperty questionProperty();
    // END  QUESTION -----------------------------------------------------------

    @Override
    public int compareTo(CardModel other);
    
}
