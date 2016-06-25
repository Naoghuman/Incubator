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
package com.github.naoghuman.pm.view.navigationoverview.projectitem;

import com.airhacks.afterburner.views.FXMLView;
import com.github.naoghuman.pm.model.ProjectModel;

/**
 *
 * @author Naoghuman
 */
public class ProjectItemView extends FXMLView {
    
    public ProjectItemPresenter configure(ProjectModel model) {
        final ProjectItemView view = new ProjectItemView();
        final ProjectItemPresenter presenter = view.getRealPresenter();
        presenter.configure(view.getView(), model);
        
        return presenter;
    }
    
    public ProjectItemPresenter getRealPresenter() {
        return (ProjectItemPresenter) super.getPresenter();
    }
    
}
