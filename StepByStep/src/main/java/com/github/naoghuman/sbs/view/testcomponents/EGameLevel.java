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
package com.github.naoghuman.sbs.view.testcomponents;

/**
 *
 * @author Naoghuman
 */
enum EGameLevel {
    
    LEVEL_1(1, "Level 1"), // NOI18N
    LEVEL_2(2, "Level 2"), // NOI18N
    LEVEL_3(3, "Level 3"), // NOI18N
    LEVEL_4(4, "Level 4"), // NOI18N
    LEVEL_5(5, "Level 5"); // NOI18N
    
    private final int level;
    
    private final String name;
    
    EGameLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }
    
    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
