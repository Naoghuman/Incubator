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

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 *
 * @author Naoghuman
 */
public class GameInformationsPresenter implements Initializable {
    
    @FXML private Text tGameModeInformation;
    @FXML private Text tLevelDetailInformation;
    @FXML private Text tLevelInformation;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize GameInformationsPresenter"); // NOI18N
        
        /*
        TODO
         - ApplicationInformation
            - Title, Version
         - GameModeInformation
            - shows the state from the application
            - EGameMode.ATTENTION, -REMEMBER...
         - LevelInformation
            - Which level is active?
            - What are the user-states?
               - Which element is played, clicked in the round? (played/max - 2/10 - max=level) (EGameMode.REMEMBER)
               - Which element is shown (EGameMode.ATTENTION)
               - How many life the user have in this game.
        */
    }
    
    public Text getGameModeInformation() {
        return tGameModeInformation;
    }

    public Text getLevelDetailInformation() {
        return tLevelDetailInformation;
    }

    public Text getLevelInformation() {
        return tLevelInformation;
    }
    
}
