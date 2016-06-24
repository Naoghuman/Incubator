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
package com.github.naoghuman.pm.sql;

import com.github.naoghuman.lib.database.api.DatabaseFacade;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.pm.model.DailyModel;
import com.github.naoghuman.pm.model.api.IEntityModel;
import java.util.Objects;

/**
 *
 * @author Naoghuman
 */
public class DailySqlProvider implements IEntityModel {
    
    private static DailySqlProvider instance = null;
    
    public static DailySqlProvider getDefault() {
        if (instance == null) {
            instance = new DailySqlProvider();
        }
        
        return instance;
    }
    
    private DailySqlProvider() {
    
    }
    
    public void createOrUpdate(DailyModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Create or update: " + model.getId()); // NOI18N
        
        if (Objects.equals(model.getId(), DEFAULT_ID__DAILY_MODEL)) {
            model.setId(System.currentTimeMillis());
            DatabaseFacade.INSTANCE.getCrudService().create(model);
        }
        else {
            DatabaseFacade.INSTANCE.getCrudService().update(model);
        }
    }
    
    public void delete(Long idToDelete) {
        DatabaseFacade.INSTANCE.getCrudService().delete(DailyModel.class, idToDelete);
    }
    
    public DailyModel findById(Long id) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Find by id: " + id); // NOI18N
        
        final DailyModel model = DatabaseFacade.INSTANCE.getCrudService()
                .findById(DailyModel.class, id);
        
        return model;
    }
    
}
