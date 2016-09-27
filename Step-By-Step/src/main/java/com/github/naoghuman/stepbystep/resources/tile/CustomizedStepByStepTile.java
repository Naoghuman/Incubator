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

import com.github.naoghuman.lib.tile.core.CustomizedTile;
import com.github.naoghuman.lib.tile.transparenttextures.TransparentTexturesTile;
import java.util.Optional;

/**
 *
 * @author Naoghuman
 */
public final class CustomizedStepByStepTile extends CustomizedTile {
    
    private static final Optional<CustomizedStepByStepTile> instance = Optional.of(new CustomizedStepByStepTile());
    
    public static final CustomizedStepByStepTile getDefault() {
        return instance.get();
    }

    private CustomizedStepByStepTile() {
        super("", "", 0.0d, 0.0d, ""); // NOI18N
        
        this.initialize();
    }
    
    private void initialize() {
        this.configure();
    }

    @Override
    protected void configure() {
        super.getTiles().put(TransparentTexturesTile.TT_3PX_TILE.getImageName(), TransparentTexturesTile.TT_3PX_TILE);
        super.getTiles().put(TransparentTexturesTile.TT_BILLIE_HOLIDAY.getImageName(), TransparentTexturesTile.TT_BILLIE_HOLIDAY);
        super.getTiles().put(TransparentTexturesTile.TT_BINDING_LIGTH.getImageName(), TransparentTexturesTile.TT_BINDING_LIGTH);
        super.getTiles().put(TransparentTexturesTile.TT_BRIGHT_SQUARES.getImageName(), TransparentTexturesTile.TT_BRIGHT_SQUARES);
        super.getTiles().put(TransparentTexturesTile.TT_BRUSHED_ALUM.getImageName(), TransparentTexturesTile.TT_BRUSHED_ALUM);
        super.getTiles().put(TransparentTexturesTile.TT_CLEAN_GRAY_PAPER.getImageName(), TransparentTexturesTile.TT_CLEAN_GRAY_PAPER);
        super.getTiles().put(TransparentTexturesTile.TT_CONCRETE_WALL_3.getImageName(), TransparentTexturesTile.TT_CONCRETE_WALL_3);
        super.getTiles().put(TransparentTexturesTile.TT_CROSS_SCRATCHES.getImageName(), TransparentTexturesTile.TT_CROSS_SCRATCHES);
        super.getTiles().put(TransparentTexturesTile.TT_DIAMOND_UPHOLSTERY.getImageName(), TransparentTexturesTile.TT_DIAMOND_UPHOLSTERY);
        super.getTiles().put(TransparentTexturesTile.TT_GRAPHY_DARK.getImageName(), TransparentTexturesTile.TT_GRAPHY_DARK);
        super.getTiles().put(TransparentTexturesTile.TT_GREY_JEAN.getImageName(), TransparentTexturesTile.TT_GREY_JEAN);
    }

}
