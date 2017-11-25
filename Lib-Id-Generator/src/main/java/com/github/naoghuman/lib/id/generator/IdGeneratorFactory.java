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

/**
 *
 * @author Naoghuman
 */
public final class IdGeneratorFactory {
    
    private static final String UNDERLINE = "_"; // NOI18N
    
    public final static String generateId(
            final Class path,
            final Class type
    ) {
        return IdGeneratorFactory.generateId(path, type, Optional.empty());
    }
    
    public final static String generateId(
            final Class            path,
            final Class            type,
            final Optional<String> optional
    ) {
        System.out.println("---------------------------------------------------------------");
        System.out.println(String.format("generateId(%s, %s, %s)", path.getCanonicalName(), type.getSimpleName(), 
                optional.isPresent() ? optional.get() : "Optional.empty()")); // NOI18N
        
        final StringBuilder sb = new StringBuilder();
        sb.append(path.getCanonicalName());
        sb.append(UNDERLINE);
        sb.append(type.getSimpleName());
        
        if (optional.isPresent()) {
            sb.append(UNDERLINE);
            sb.append(optional.get());
        }
        
        sb.append(UNDERLINE);
        sb.append(System.nanoTime());
        
        System.out.println("---------------------------------------------------------------");
        System.out.println(String.format("Generated ID: %s", sb.toString()));
        System.out.println("---------------------------------------------------------------");
        
        return sb.toString();
    }
    
}
