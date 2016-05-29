/*
 * Copyright (C) 2015 PRo
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
package com.github.naoghuman.lib.map.generator;

import com.github.naoghuman.lib.map.generator.api.IMapGenerator;
import com.github.naoghuman.lib.map.generator.demo.DemoView;
import com.github.naoghuman.lib.map.generator.mapeditor.MapEditorPresenter;
import com.github.naoghuman.lib.map.generator.mapeditor.MapEditorView;
import com.github.naoghuman.lib.map.generator.mapwindow.MapWindowPresenter;
import com.github.naoghuman.lib.map.generator.mapwindow.MapWindowView;
import com.github.naoghuman.lib.map.generator.mapwizard.MapWizardPresenter;
import com.github.naoghuman.lib.map.generator.mapwizard.MapWizardView;
import javafx.scene.Parent;

/**
 *
 * @author PRo
 */
public class MapGenerator implements IMapGenerator {
    
    private MapEditorPresenter mapEditorPresenter = null;
    private MapWindowPresenter mapWindowPresenter = null;
    private MapWizardPresenter mapWizardPresenter = null;
    
    private DemoView demoView = null;
    private MapEditorView mapEditorView = null;
    private MapWindowView mapWindowView = null;
    private MapWizardView mapWizardView = null;
    
    public MapGenerator() {
        this.init();
    }
    
    private void init() {
        demoView = new DemoView();
        
        mapEditorView = new MapEditorView();
        mapEditorPresenter = (MapEditorPresenter) mapEditorView.getPresenter();
        
        mapWindowView = new MapWindowView();
        mapWindowPresenter = (MapWindowPresenter) mapWindowView.getPresenter();
        
        mapWizardView = new MapWizardView();
        mapWizardPresenter = (MapWizardPresenter) mapWizardView.getPresenter();
    }

    @Override
    public MapEditorPresenter getPresenterFromMapEditor() {
        return mapEditorPresenter;
    }

    @Override
    public MapWindowPresenter getPresenterFromMapWindow() {
        return mapWindowPresenter;
    }

    @Override
    public MapWizardPresenter getPresenterFromMapWizard() {
        return mapWizardPresenter;
    }

    @Override
    public Parent getViewFromDemo() {
        return demoView.getView();
    }

    @Override
    public Parent getViewFromMapEditor() {
        return mapEditorView.getView();
    }

    @Override
    public Parent getViewFromMapWindow() {
        return mapWindowView.getView();
    }

    @Override
    public Parent getViewFromMapWizard() {
        return mapWizardView.getView();
    }
    
}
