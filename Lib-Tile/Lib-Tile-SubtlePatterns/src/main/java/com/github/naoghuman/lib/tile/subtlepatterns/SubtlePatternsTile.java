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
package com.github.naoghuman.lib.tile.subtlepatterns;

import com.github.naoghuman.lib.tile.core.Tile;
import com.github.naoghuman.lib.tile.core.DefaultTileLoader;

/**
 *
 * @author Naoghuman
 */
public enum SubtlePatternsTile implements Tile {

    SP_CORK_WALLET("sp-cork-wallet.png", "Cork Wallet", 10, 10, "Atle Mo", "http://atle.co/");

    private final double height; // (must)
    private final double width; // (must)

    private final String autor;
    private final String header; // (must)
    private final String name; // (must)
    private final String url;

    SubtlePatternsTile(
            final String name, final String header,
            final double width, final double height,
            final String autor, final String url
    ) {
        this.name = name;
        this.header = header;
        this.width = width;
        this.height = height;
        this.autor = autor;
        this.url = url;

        DefaultTileLoader.getDefault().checkParameters(name, header, width, height);
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Tile ["); // NOI18N
        sb.append("name=").append(this.getName()); // NOI18N
        sb.append(", "); // NOI18N
        sb.append("header=").append(this.getHeader()); // NOI18N
        sb.append(", "); // NOI18N
        sb.append("w=").append(this.getWidth()); // NOI18N
        sb.append(", "); // NOI18N
        sb.append("h=").append(this.getHeight()); // NOI18N
        sb.append(", "); // NOI18N
        sb.append("autor=").append(this.getAutor()); // NOI18N
        
        final String url = this.getUrl();
        if (
                (url != null)
                && (!url.isEmpty())
        ) {
            sb.append(", "); // NOI18N
            sb.append("url=").append(url); // NOI18N
        }
                
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }

}
