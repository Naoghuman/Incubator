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
package com.github.naoghuman.dreaming.sounds.configuration;

/**
 *
 * @author Naoghuman
 */
public interface IAudioClipBoxConfiguration {
    
    public static final String ENTITY__TABLE_NAME__SOUNDBOX = "AudioClipBox"; // NOI18N
    
    public static final String NAMED_QUERY__NAME__FIND_ALL_IN_TOPIC = "AudioClipBox.findAllInTopic"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_IN_TOPIC = "SELECT acb FROM AudioClipBox acb WHERE acb.parentId == :parentId"; // NOI18N
    
    public static final String AUDIOCLIPBOX__COLUMN_NAME__ID = "id"; // NOI18N
    public static final String AUDIOCLIPBOX__COLUMN_NAME__PARENT_ID = "parentId"; // NOI18N
    public static final String AUDIOCLIPBOX__COLUMN_NAME__SOUND = "sound"; // NOI18N
    public static final String AUDIOCLIPBOX__COLUMN_NAME__TITLE = "title"; // NOI18N
    
}
