/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.lib.id.generator;

import java.util.Optional;
import javafx.scene.layout.AnchorPane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naoghuman
 */
public class IdGeneratorFactoryTest {
    
    public IdGeneratorFactoryTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGenerateIdWithOptionalEmpty() {
        Class path = IdGeneratorFactoryTest.class;
        Class type = AnchorPane.class;
        
        String result = IdGeneratorFactory.generateId(path, type);
        
        assertTrue(result.startsWith(path.getCanonicalName()));
        assertTrue(result.contains("_AnchorPane_"));
    }

    @Test
    public void testGenerateId_Class_Optional() {
        Class path = IdGeneratorFactoryTest.class;
        Class type = AnchorPane.class;
        Optional<String> optional = Optional.of("Hello-World");
        
        String result = IdGeneratorFactory.generateId(path, type, optional);
        
        assertTrue(result.startsWith(path.getCanonicalName()));
        assertTrue(result.contains("_AnchorPane_Hello-World_"));
    }
    
}
