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
package com.github.naoghuman.step.by.step.engine;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.util.Optional;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public final class Engine {
    
    private static final Optional<Engine> instance = Optional.of(new Engine());
    
    public static final Engine getDefault() {
        return instance.get();
    }
    
    private final ObservableList<Integer> precalculatedElements = FXCollections.observableArrayList();
    
    private int counterForRandomIndex = -1;
    
    private Engine() {
        this.initialize();
    }
    
    private void initialize() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize Engine"); // NOI18N
        
    }

    public void initializePrecalculatedElements() {
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize precalculated Elements"); // NOI18N
        
        precalculatedElements.clear();
        
        final Random random = new Random();
        for (int i = 0; i < 500; i++) {
            final int nextInt = random.nextInt(5) + 1;
            precalculatedElements.add(nextInt);
        }
        
        LoggerFacade.INSTANCE.debug(this.getClass(), "Precalculated Elements: " + precalculatedElements.toString()); // NOI18N
    }
    
    public int getNextRandomElement() {
        ++counterForRandomIndex;
        if (counterForRandomIndex >= precalculatedElements.size()) {
            counterForRandomIndex = 0;
            this.initializePrecalculatedElements();
        }
        
        return precalculatedElements.get(counterForRandomIndex);
    }
    
}
