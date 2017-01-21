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
package com.github.naoghuman.abclist.view.exercise;

/**
 *
 * @author Naoghuman
 */
public enum ETime {
    
    MIN_00_30(30 , "00:30"), // NOI18N // XXX REMOVE 
    MIN_01_30(90 , "01:30"), // NOI18N
    MIN_03_00(180, "03:00"), // NOI18N
    MIN_05_00(300, "05:00"), // NOI18N
    MIN_10_00(600, "10:00"); // NOI18N

    private final int seconds;

    private final String presentation;

    ETime(int seconds, String presentation) {
        this.seconds = seconds;
        this.presentation = presentation;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return presentation;
    }
        
}
