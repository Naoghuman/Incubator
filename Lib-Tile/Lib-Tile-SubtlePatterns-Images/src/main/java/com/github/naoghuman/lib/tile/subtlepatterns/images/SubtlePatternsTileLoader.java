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
package com.github.naoghuman.lib.tile.subtlepatterns.images;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import com.github.naoghuman.lib.tile.core.Tile;
import com.github.naoghuman.lib.tile.core.TileLoader;
import com.github.naoghuman.lib.tile.core.DefaultTileLoader;
import java.util.Optional;

/**
 *
 * @author Naoghuman
 */
public class SubtlePatternsTileLoader implements TileLoader {
	
    private static final Optional<SubtlePatternsTileLoader> instance = Optional.of(new SubtlePatternsTileLoader());

    public static final SubtlePatternsTileLoader getDefault() {
        return instance.get();
    }

    private SubtlePatternsTileLoader() {

    }

    @Override
    public String getPrefix() {
        return "sp-"; // NOI18N
    }

    @Override
    public boolean isSupported(final Tile tile) {
        final String name = tile.getName();
        final boolean isSupported = 
                (name != null)
                && (!name.trim().isEmpty())
                && (name.startsWith(this.getPrefix()));

        return isSupported;
    }

    @Override
    public Background loadAsBackground(final Tile tile) {
        return DefaultTileLoader.getDefault().loadAsBackground(SubtlePatternsTileLoader.getDefault(), tile);
    }

    @Override
    public Image loadAsImage(final Tile tile) {
        return DefaultTileLoader.getDefault().loadAsImage(SubtlePatternsTileLoader.getDefault(), tile);
    }

}
