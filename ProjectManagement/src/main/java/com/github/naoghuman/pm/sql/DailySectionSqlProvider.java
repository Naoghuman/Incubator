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
import com.github.naoghuman.pm.model.DailySectionModel;
import com.github.naoghuman.pm.model.api.IEntityModel;
import static com.github.naoghuman.pm.model.api.IEntityModel.NAMED_QUERY__NAME__PROJECT_MODEL__FIND_ALL;
import java.util.Collections;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public class DailySectionSqlProvider implements IEntityModel {
    
    private static DailySectionSqlProvider instance = null;
    
    public static DailySectionSqlProvider getDefault() {
        if (instance == null) {
            instance = new DailySectionSqlProvider();
        }
        
        return instance;
    }
    
    private DailySectionSqlProvider() {
    
    }
    
    public void createOrUpdate(DailySectionModel model) {
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
        LoggerFacade.INSTANCE.debug(this.getClass(), "Delete: " + idToDelete); // NOI18N
        
        DatabaseFacade.INSTANCE.getCrudService().delete(DailySectionModel.class, idToDelete);
    }
    
    public DailySectionModel findById(Long id) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Find by id: " + id); // NOI18N
        
        final DailySectionModel model = DatabaseFacade.INSTANCE.getCrudService()
                .findById(DailySectionModel.class, id);
        
        return model;
    }

    public ObservableList<DailySectionModel> findAll() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Find all"); // NOI18N
        
        final ObservableList<DailySectionModel> models = FXCollections.observableArrayList();     
        models.addAll(DatabaseFacade.INSTANCE.getCrudService()
                .findByNamedQuery(DailySectionModel.class, NAMED_QUERY__NAME__DAILY_MODEL__FIND_ALL));
        Collections.sort(
                models,
                (model1, model2) -> 
                    model1.getDailyDate().compareTo(model2.getDailyDate())
                );
        
        return models;
    }
    
}
