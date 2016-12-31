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
public interface ISoundBoxConfiguration {
    
    public static final String ENTITY__TABLE_NAME__SOUNDBOX = "SoundBox"; // NOI18N
    
    public static final String NAMED_QUERY__NAME__FIND_ALL_IN_TOPIC = "SoundBox.findAllInTopic"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_IN_TOPIC = "SELECT sb FROM SoundBox sb"; // NOI18N
    
    public static final String SOUNDBOX__COLUMN_NAME__ID = "id"; // NOI18N
    public static final String SOUNDBOX__COLUMN_NAME__SOUND = "sound"; // NOI18N
    public static final String SOUNDBOX__COLUMN_NAME__TITLE = "title"; // NOI18N
    
}
