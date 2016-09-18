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
package com.github.naoghuman.stepbystep.gameengine.gamemode;

import javafx.animation.FadeTransition;

/**
 *
 * @author Naoghuman
 */
public abstract class GameMode {
    
    private EGameModeType gameModeType = null;
    private GameMode gameMode = null;
    
    public abstract FadeTransition createFadeInOutTransition(EGameModeType gameModeType);
    public abstract FadeTransition createFadeInTransition();
    public abstract FadeTransition createFadeOutTransition();
    public abstract boolean responsible(EGameMode gamemode);
    
    public void setGameModeType(EGameModeType gameModeType) {
        this.gameModeType = gameModeType;
    }
    
    public void setNextGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
    
    public FadeTransition handle(EGameMode gamemode) {
        final boolean responsible = this.responsible(gamemode);
        if (responsible) {
            return this.createFadeInOutTransition(gameModeType);
        }
        else if (gameMode != null) {
            return gameMode.handle(gamemode);
        }
        
        return null;
    }
    
}
