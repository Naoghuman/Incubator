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
package com.github.naoghuman.sbs.debug;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.sbs.view.testcomponents.TestComponentsPresenter;
import com.github.naoghuman.sbs.view.testcomponents.TestComponentsView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.Level;

/**
 *
 * @author Naoghuman
 */
public final class DebugConsole {
    
    // 2016-07-04 08:40:08,832  <->  yyyy-MM-dd HH:mm:ss,SSS
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS"); // NOI18N
    
    private static final Optional<DebugConsole> instance = Optional.of(new DebugConsole());
    
    public static final DebugConsole getDefault() {
        return instance.get();
    }
    
    private boolean shouldDebugInfoPrinted = true;
    
    private TextArea taDebugConsole;
    private VBox vbDebugOptions;
    
    private DebugConsole() {
        this.initialize();
    }

    private void initialize() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize DebugConsole"); // NOI18N
        
    }
    
    public void configureTestComponents() {
        final TestComponentsView view = new TestComponentsView();
        final TestComponentsPresenter presenter = view.getRealPresenter();
        taDebugConsole = presenter.getDebugConsole();
        vbDebugOptions = presenter.getDebugOptions();
        
        this.debug(this.getClass(), "Configure TestComponents"); // NOI18N
    }
    
    public void debug(Class clazz, String msg) {
        LoggerFacade.INSTANCE.debug(this.getClass(), msg);
        
        if (!shouldDebugInfoPrinted || taDebugConsole == null) {
            return;
        }
        
        final String formattedMessage = this.formatMessage(clazz, msg, Level.DEBUG);
        taDebugConsole.appendText(formattedMessage);
    }
    
    public void error(Class clazz, String msg) {
        LoggerFacade.INSTANCE.error(this.getClass(), msg);
        
        if (!shouldDebugInfoPrinted || taDebugConsole == null) {
            return;
        }
        
        final String formattedMessage = this.formatMessage(clazz, msg, Level.ERROR);
        taDebugConsole.appendText(formattedMessage);
    }

    private String formatMessage(Class clazz, String msg, Level level) {
        final StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        sb.append("  "); // NOI18N
        sb.append(level);
        sb.append((level.toString().length() == 4) ? " " : ""); // NOI18N
        sb.append(" "); // NOI18N
        sb.append(msg);
        sb.append("    "); // NOI18N
        sb.append("[").append(clazz.getSimpleName()).append("]"); // NOI18N
        sb.append("\n"); // NOI18N
        
        return sb.toString();
    }
    
    public TextArea getDebugConsole() {
        return taDebugConsole;
    }
    
    public VBox getDebugOptions() {
        return vbDebugOptions;
    }
    
    public void info(Class clazz, String msg) {
        LoggerFacade.INSTANCE.info(this.getClass(), msg);
        
        if (!shouldDebugInfoPrinted || taDebugConsole == null) {
            return;
        }
        
        final String formattedMessage = this.formatMessage(clazz, msg, Level.INFO);
        taDebugConsole.appendText(formattedMessage);
    }
    
    /*
    TODO Flag should 
     a) activate logging in the TextArea and (Development-Mode)
     b) Remove the TextArea when deactivated (Release-Mode)
    */
    public void setShouldDebugInfoPrinted(boolean shouldDebugInfoPrinted) {
        this.shouldDebugInfoPrinted = shouldDebugInfoPrinted;
    }
    
}
