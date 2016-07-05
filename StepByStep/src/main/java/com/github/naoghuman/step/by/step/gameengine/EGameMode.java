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
package com.github.naoghuman.step.by.step.gameengine;

/**
 *
 * @author Naoghuman
 */
public enum EGameMode {
    
    GAME_MODE__PREVIEW("Preview"), // NOI18N
    GAME_MODE__ATTENTION("Attention"), // NOI18N
    GAME_MODE__REMEMBER("Remember"), // NOI18N
    GAME_MODE__SUCCESS("Success"), // NOI18N
    GAME_MODE__ERROR("Error"), // NOI18N
    GAME_MODE__HIGHSCORE("Highscore"), // NOI18N
    GAME_MODE__HELP("Help"); // NOI18N
    
    private String gameModeInformation = null;

    private EGameMode(String gameModeInformation) {
        this.gameModeInformation = gameModeInformation;
    }
    
    public String getGameModeInformation() {
        return gameModeInformation;
    }
    
}
