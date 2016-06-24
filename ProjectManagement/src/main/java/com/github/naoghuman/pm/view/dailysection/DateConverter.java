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
package com.github.naoghuman.pm.view.dailysection;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Naoghuman
 */
public class DateConverter {
    
    public static final LocalDate convertStringToLocalDate(String date) {
        LocalDate localDate = LocalDate.now();
        
        try {
           final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // NOI18N
            localDate = LocalDate.parse(date, formatter); 
        } catch (DateTimeParseException e) {
            LoggerFacade.INSTANCE.error(DateConverter.class, 
                    "Can't convert " + date + " use LocalDate.now() instead", e); // NOI18N
        }
        
        return localDate;
    }
    
}
