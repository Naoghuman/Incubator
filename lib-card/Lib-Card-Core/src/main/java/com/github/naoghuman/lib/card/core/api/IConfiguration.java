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

/**
 *
 * @author Naoghuman
 */
public interface IConfiguration {
    
    public static final Long DEFAULT_ID__CARD_MODEL     = 1_000_000_000_000L;
    public static final Long DEFAULT_ID__CATEGORY_MODEL = 1_000_100_000_000L;
    public static final Long DEFAULT_ID__SECTION_MODEL  = 1_000_200_000_000L;
    
    public static final String COLUMN_NAME__ANSWER = "answer"; // NOI18N
    public static final String COLUMN_NAME__CARD_ID = "cardId"; // NOI18N
    public static final String COLUMN_NAME__ID = "id"; // NOI18N
    public static final String COLUMN_NAME__GENERATION_TIME = "generationTime"; // NOI18N
    public static final String COLUMN_NAME__QUESTION = "question"; // NOI18N
    public static final String COLUMN_NAME__SECTION_ID = "sectionId"; // NOI18N
    public static final String COLUMN_NAME__TITLE = "title"; // NOI18N
    
    public static final String ENTITY__TABLE_NAME__CARD_MODEL = "CardModel"; // NOI18N
    
    public static final String NAMED_QUERY__NAME__CARD_FIND_ALL = "CardModel.findAll"; // NOI18N
    public static final String NAMED_QUERY__NAME__CARD_FIND_BY_ID = "CardModel.findById"; // NOI18N
    
    public static final String NAMED_QUERY__QUERY__CARD_FIND_ALL = "SELECT cm FROM CardModel cm"; // NOI18N
    public static final String NAMED_QUERY__QUERY__CARD_FIND_BY_ID = "SELECT cm FROM CardModel cm  WHERE cm.id = :id"; // NOI18N
    
    public static final String SIGN__EMPTY = ""; // NOI18N
    
}
