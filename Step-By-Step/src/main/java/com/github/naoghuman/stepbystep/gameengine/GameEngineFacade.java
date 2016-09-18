/*
 * Copyright (C) 2016 PRo
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
package com.github.naoghuman.stepbystep.gameengine;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.util.Optional;

/**
 *
 * @author PRo
 */
public final class GameEngineFacade {
    
    private static final Optional<GameEngineFacade> instance = Optional.of(new GameEngineFacade());
    
    public static final GameEngineFacade getDefault() {
        return instance.get();
    }
    
    private GameEngineFacade() {
        this.initialize();
    }
    
    private void initialize() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameEngine"); // NOI18N
        
        this.initializeGameMode();
    }
    
    private void initializeGameMode() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameMode"); // NOI18N
        
    }
    
}
