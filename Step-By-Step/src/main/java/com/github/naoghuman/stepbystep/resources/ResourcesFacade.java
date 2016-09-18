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
package com.github.naoghuman.stepbystep.resources;

import com.github.naoghuman.stepbystep.resources.image.ImageLoader;
import java.util.Optional;

/**
 *
 * @author Naoghuman
 */
public final class ResourcesFacade {
    
    private static final Optional<ResourcesFacade> instance = Optional.of(new ResourcesFacade());
    
    public static final ResourcesFacade getDefault() {
        return instance.get();
    }
    
    private ImageLoader imageLoader = null;
    
    private ResourcesFacade() {
        this.init();
    }
    
    private void init() {
        imageLoader = new ImageLoader();
    }
    
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    
}
