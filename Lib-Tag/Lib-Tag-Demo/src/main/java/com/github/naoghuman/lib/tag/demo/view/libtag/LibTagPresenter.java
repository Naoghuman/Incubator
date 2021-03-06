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
package com.github.naoghuman.lib.tag.demo.view.libtag;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Naoghuman
 */
public class LibTagPresenter implements Initializable {
    
//    @FXML private WebView wvLibTag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTagPresenter"); // NOI18N
        
//        this.initializeWebView();
    }

//    private void initializeWebView() {
//        LoggerFacade.getDefault().info(this.getClass(), "Initialize WebView"); // NOI18N
//        
//        final WebEngine webEngine = wvLibTag.getEngine();
//        webEngine.load("https://github.com/Naoghuman/Incubator/blob/master/Lib-Tag/README.md"); // NOI18N
//    }
    
}
