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

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import javafx.animation.FadeTransition;

/**
 *
 * @author Naoghuman
 */
public class GameModeHelp extends GameMode {

    @Override
    public boolean responsible(EGameMode next) {
        final boolean responsible = next.equals(EGameMode.GAME_MODE__HELP);
        return responsible;
    }

    @Override
    public FadeTransition createFadeInOutTransition(EGameModeType gameModeType) {
        LoggerFacade.getDefault().info(this.getClass(), "Create FadeInOutTransition: " + gameModeType); // NOI18N
        
        FadeTransition fadeTransition = null;
        switch (gameModeType) {
            case FADE_IN: { fadeTransition = this.createFadeInTransition(); }
            case FADE_OUT: { fadeTransition = this.createFadeOutTransition(); }
        }
        
        return fadeTransition;
    }

    @Override
    public FadeTransition createFadeInTransition() {
        LoggerFacade.getDefault().info(this.getClass(), "Create FadeInTransition"); // NOI18N
        
        return null;
    }

    @Override
    public FadeTransition createFadeOutTransition() {
        LoggerFacade.getDefault().info(this.getClass(), "Create FadeOutTransition"); // NOI18N
        
        return null;
    }
    
}
