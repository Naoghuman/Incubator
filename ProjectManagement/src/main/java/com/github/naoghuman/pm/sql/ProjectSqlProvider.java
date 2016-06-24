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
import com.github.naoghuman.pm.model.ProjectModel;
import com.github.naoghuman.pm.model.api.IEntityModel;
import java.util.Collections;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public class ProjectSqlProvider implements IEntityModel {
    
    private static ProjectSqlProvider instance = null;
    
    public static ProjectSqlProvider getDefault() {
        if (instance == null) {
            instance = new ProjectSqlProvider();
        }
        
        return instance;
    }
    
    private ProjectSqlProvider() {
    
    }
    
    public void createOrUpdate(ProjectModel model) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Create or update: " + model.getTitle()); // NOI18N
        
        if (Objects.equals(model.getId(), DEFAULT_ID__PROJECT_MODEL)) {
            model.setId(System.currentTimeMillis());
            DatabaseFacade.INSTANCE.getCrudService().create(model);
        }
        else {
            DatabaseFacade.INSTANCE.getCrudService().update(model);
        }
    }
    
    public void delete(Long idToDelete) {
        DatabaseFacade.INSTANCE.getCrudService().delete(ProjectModel.class, idToDelete);
    }
    
    public ObservableList<ProjectModel> findAll() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Find all"); // NOI18N
        
        final ObservableList<ProjectModel> models = FXCollections.observableArrayList();     
        models.addAll(DatabaseFacade.INSTANCE.getCrudService()
                .findByNamedQuery(ProjectModel.class, NAMED_QUERY__NAME__PROJECT_MODEL__FIND_ALL));
        Collections.sort(
                models,
                (model1, model2) -> 
                    Integer.compare(model1.getPosition(), model2.getPosition())
                );
        
        return models;
    }
    
    public ProjectModel findById(Long id) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Find by id: " + id); // NOI18N
        
        final ProjectModel model = DatabaseFacade.INSTANCE.getCrudService()
                .findById(ProjectModel.class, id);
        
        return model;
    }
    
    public void updatePositions(ObservableList<ProjectModel> models) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Update positions"); // NOI18N
        
        DatabaseFacade.INSTANCE.getCrudService().beginTransaction();
        
        models.forEach(model -> {
            DatabaseFacade.INSTANCE.getCrudService().update(model, false);
        });
        
        DatabaseFacade.INSTANCE.getCrudService().commitTransaction();
        
    }
    
}
