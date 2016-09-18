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
import com.github.naoghuman.stepbystep.gameengine.gamemode.EGameMode;
import com.github.naoghuman.stepbystep.gameengine.gamemode.EGameModeType;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeAttention;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeDefault;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeError;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeHelp;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeHighscore;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModePreview;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeRemember;
import com.github.naoghuman.stepbystep.gameengine.gamemode.GameModeSuccess;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;

/**
 *
 * @author PRo
 */
public final class GameEngineFacade {
    
    private static final Optional<GameEngineFacade> instance = Optional.of(new GameEngineFacade());
    
    public static final GameEngineFacade getDefault() {
        return instance.get();
    }
    
    private EGameMode currentGameMode = EGameMode.GAME_MODE__DEFAULT;
    
    private GameModeDefault gameModeDefault = null;
    private SequentialTransition stSwitchToGameMode = null;
    
    private GameEngineFacade() {
        
    }
    
    public void initialize() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameEngine"); // NOI18N
        
        this.initializeGameMode();
    }
    
    private void initializeGameMode() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameMode"); // NOI18N
        
        gameModeDefault = new GameModeDefault();
        
        final GameModeAttention gmAttention = new GameModeAttention();
        gameModeDefault.setNextGameMode(gmAttention);
        
        final GameModeError gmError = new GameModeError();
        gmAttention.setNextGameMode(gmError);
        
        final GameModeHelp gmHelp = new GameModeHelp();
        gmError.setNextGameMode(gmHelp);
        
        final GameModeHighscore gmHighscore = new GameModeHighscore();
        gmHelp.setNextGameMode(gmHighscore);
        
        final GameModePreview gmPreview = new GameModePreview();
        gmHighscore.setNextGameMode(gmPreview);
        
        final GameModeRemember gmRemember = new GameModeRemember();
        gmPreview.setNextGameMode(gmRemember);
        
        final GameModeSuccess gmSuccess = new GameModeSuccess();
        gmRemember.setNextGameMode(gmSuccess);
    }
    
    public void onActionSwitchToGameMode(EGameMode newGameMode) {
        LoggerFacade.getDefault().debug(this.getClass(), "On Action switch to GameMode: " + newGameMode.toString()); // NOI18N

        if (
                stSwitchToGameMode != null
                && stSwitchToGameMode.getStatus().equals(Animation.Status.RUNNING)
        ) {
            stSwitchToGameMode.stop();
        }
        stSwitchToGameMode = new SequentialTransition();
        
        // Fade out old GameMode
        gameModeDefault.setGameModeType(EGameModeType.FADE_OUT);
        final FadeTransition ftFadeOut = gameModeDefault.handle(currentGameMode);
        
        // Fade in new GameMode
        gameModeDefault.setGameModeType(EGameModeType.FADE_IN);
        final FadeTransition ftFadeIn = gameModeDefault.handle(newGameMode);
        currentGameMode = newGameMode;
        
        // Play animations
        if (ftFadeOut != null) {
            stSwitchToGameMode.getChildren().add(ftFadeOut);
        }
        stSwitchToGameMode.getChildren().add(ftFadeIn);
        
        stSwitchToGameMode.playFromStart();
    }
    
}
