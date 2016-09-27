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
package com.github.naoghuman.stepbystep.view.gameinformations;

import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.util.Optional;
import javafx.scene.text.Font;

/**
 *
 * @author Naoghuman
 */
public class GameInformationsManager implements IRegisterActions {
    
    private static final Font FONT_SIZE_128 = new Font(128.0d);
    private static final Optional<GameInformationsManager> instance = Optional.of(new GameInformationsManager());
    
    public static final GameInformationsManager getDefault() {
        return instance.get();
    }
    
    private GameInformationsPresenter presenter;
    
    private GameInformationsManager() {
        this.initialize();
    }
    
    private void initialize() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameInformationsManager"); // NOI18N
        
        this.initializeGameInformations();
    }
    
    private void initializeGameInformations() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameInformations"); // NOI18N
        
        final GameInformationsView view = new GameInformationsView();
        presenter = view.getRealPresenter();
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in GameInformationsManager"); // NOI18N
        
    }
    
    
    /*
    private void initializeLevelInfo() {
        DebugConsole.getDefault().info(this.getClass(), "Initialize LevelInfo"); // NOI18N
        
        final boolean showLevelInfo = false;
        this.switchLevelInfoToGameMode(EGameMode.GAME_MODE__PREVIEW, showLevelInfo);
    }
    */
    
//    private void switchLevelInfoToGameMode(EGameMode gameMode, boolean showLevelInfo) {
//        if (gameMode.equals(EGameMode.GAME_MODE__ATTENTION)) {
//            tLevel.setText("3"); // NOI18N
//            tLevel.setFont(FONT_SIZE_128);
//            tLevel.setOpacity(0.0d);
//            tLevel.setManaged(showLevelInfo);
//            tLevel.setVisible(showLevelInfo);
//            
//            tLevelInfo.setText(GameEngine.getDefault().getLevel() + " / 0"); // NOI18N
//            tLevelInfo.setOpacity(0.0d);
//            tLevelInfo.setManaged(!showLevelInfo);
//            tLevelInfo.setVisible(!showLevelInfo);
//        }
//        
//        if (gameMode.equals(EGameMode.GAME_MODE__PREVIEW)) {
//            tPrepareYourSelf.setText("Attention"); // NOI18N
//            tPrepareYourSelf.setOpacity(0.0d);
//            tPrepareYourSelf.setManaged(false);
//            tPrepareYourSelf.setVisible(false);
//            
//            tLevel.setText("Level"); // NOI18N
//            tLevel.setManaged(showLevelInfo);
//            tLevel.setVisible(showLevelInfo);
//            
//            GameEngine.getDefault().resetLevel();
////            GameEngine.getDefault().increaseLevel();
////            GameEngine.getDefault().increaseLevel();
////            GameEngine.getDefault().increaseLevel();
////            GameEngine.getDefault().increaseLevel();
////            GameEngine.getDefault().increaseLevel();
////            GameEngine.getDefault().increaseLevel();
//            tLevelInfo.setText(GameEngine.getDefault().getLevel() + " / 0"); // NOI18N
//            tLevelInfo.setManaged(showLevelInfo);
//            tLevelInfo.setVisible(showLevelInfo);
//        }
//    }
    
}
