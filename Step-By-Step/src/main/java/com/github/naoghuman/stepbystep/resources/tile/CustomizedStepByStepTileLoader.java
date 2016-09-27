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
package com.github.naoghuman.stepbystep.resources.tile;

import com.github.naoghuman.lib.tile.core.CustomizedAbstractTileLoader;
import com.github.naoghuman.lib.tile.core.Tile;
import com.github.naoghuman.lib.tile.transparenttextures.images.TransparentTexturesTileLoader;

import java.util.Optional;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.layout.Background;

/**
 * 
 * @author Naoghuman
 */
public final class CustomizedStepByStepTileLoader extends CustomizedAbstractTileLoader {
    
    private static final Optional<CustomizedStepByStepTileLoader> instance = Optional.of(new CustomizedStepByStepTileLoader());

    
    public static final CustomizedStepByStepTileLoader getDefault() {
        return instance.get();
    }

    private CustomizedStepByStepTileLoader() {
        super();
    }

    @Override
    protected void configure() {
        super.getAbstractTileLoaders().put("tt-", TransparentTexturesTileLoader.getDefault()); // NOI18N
    }
    
    public Background getRandomTile() {
        final ObservableList<Tile> tiles = CustomizedStepByStepTile.getDefault().getTilesAsList();
        final Random random = new Random();
        final int randomIndex = random.nextInt(tiles.size());
        final Background background = super.loadAsBackground(tiles.get(randomIndex));
        
        return background;
    }

    @Override
    protected String getPrefixes() {
        return "tt-"; // NOI18N
    }

}
