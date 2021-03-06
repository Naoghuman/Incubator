/*
 * Copyright (C) 2016 Name
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
package com.github.naoghuman.testfascore;

import com.airhacks.afterburner.injection.Injector;
import com.github.naoghuman.lib.database.core.DatabaseFacade;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.preferences.core.PreferencesFacade;
import com.github.naoghuman.lib.properties.core.PropertiesFacade;
import com.github.naoghuman.testfascore.application.ApplicationPresenter;
import com.github.naoghuman.testfascore.application.ApplicationView;
import com.github.naoghuman.testfascore.configuration.ApplicationConfiguration;
import com.github.naoghuman.testfascore.scanner.ProjectSampleScanner;
import java.util.List;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Name
 */
public class StartClassFromProjectSampler implements ApplicationConfiguration {
    
    private static final Optional<StartClassFromProjectSampler> INSTANCE = Optional.of(new StartClassFromProjectSampler());

    /**
     * Returns a singleton instance from the class {@code StartClassFromProjectSampler}.
     * 
     * @return a singleton instance from the class {@code StartClassFromProjectSampler}.
     */
    public static final StartClassFromProjectSampler getDefault() {
        return INSTANCE.get();
    }

    public void init(List<String> projectWhiteAndBlackList, List<String> sampleWhiteAndBlackList) throws Exception {
        System.out.println("*****: 1");
        
        PropertiesFacade.getDefault().register(KEY__APPLICATION__RESOURCE_BUNDLE);
        
        final char borderSign = this.getProperty(KEY__APPLICATION__BORDER_SIGN).charAt(0);
        final String message = this.getProperty(KEY__APPLICATION__MESSAGE_START);
        final String title = this.getProperty(KEY__APPLICATION__TITLE) + this.getProperty(KEY__APPLICATION__VERSION);
        LoggerFacade.getDefault().message(borderSign, 80, String.format(message, title));
        
        final Boolean dropPreferencesFileAtStart = Boolean.FALSE;
        PreferencesFacade.getDefault().init(dropPreferencesFileAtStart);
        
        DatabaseFacade.getDefault().register(this.getProperty(KEY__APPLICATION__DATABASE));
        
        ProjectSampleScanner.getDefault().scan(projectWhiteAndBlackList, sampleWhiteAndBlackList);
    }
    
    public void start(Stage primaryStage) throws Exception {
        System.out.println("*****: 2");
        final ApplicationView applicationView = new ApplicationView();
        final ApplicationPresenter applicationPresenter = applicationView.getRealPresenter();
        
        final Scene scene = new Scene(applicationView.getView(), 1280, 720);
        primaryStage.setTitle(this.getProperty(KEY__APPLICATION__TITLE) + this.getProperty(KEY__APPLICATION__VERSION));
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
           we.consume();
           
           this.onCloseRequest();
        });
        
        primaryStage.show();
        applicationPresenter.initializeAfterWindowIsShowing();
    }
    
    private String getProperty(String propertyKey) {
        return PropertiesFacade.getDefault().getProperty(KEY__APPLICATION__RESOURCE_BUNDLE, propertyKey);
    }
    
    private void onCloseRequest() {
        // afterburner.fx
        Injector.forgetAll();
        
        // Database
        DatabaseFacade.getDefault().shutdown();
        
        // Message
        final char borderSign = this.getProperty(KEY__APPLICATION__BORDER_SIGN).charAt(0);
        final String message = this.getProperty(KEY__APPLICATION__MESSAGE_STOP);
        final String title = this.getProperty(KEY__APPLICATION__TITLE) + this.getProperty(KEY__APPLICATION__VERSION);
        LoggerFacade.getDefault().message(borderSign, 80, String.format(message, title));
        
        // Timer
        final PauseTransition pt = new PauseTransition(DURATION__125);
        pt.setOnFinished((ActionEvent event) -> {
            Platform.exit();
        });
        pt.playFromStart();
    }
    
}
