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
package com.github.naoghuman.lib.tile.transparenttextures;

import com.github.naoghuman.lib.tile.core.Tile;
import com.github.naoghuman.lib.tile.core.DefaultTileLoader;

/**
 *
 * @author Naoghuman
 */
public enum TransparentTexturesTile implements Tile {

    TT_3PX_TILE("tt-3px-tile.png", "3Px Tile", 100, 100, "Gre3g", "http://gre3g.livejournal.com/"), // NOI18N
    TT_45_DEGREE_FABRIC_DARK("tt-45-degree-fabric-dark.png", "45 Degree Fabric (Dark)", 315, 315, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_45_DEGREE_FABRIC_LIGHT("tt-45-degree-fabric-light.png", "45 Degree Fabric (Light)", 315, 315, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_60_LINES("tt-60-lines.png", "60° lines", 31, 31, "Atle Mo", "http://atle.co/"), // NOI18N

    TT_ABSURDITY("tt-absurdity.png", "Absurdity", 4, 4, "Carlos Valdez", "http://saveder.blogspot.de/"), // NOI18N
    TT_AG_SQUARE("tt-ag-square.png", "AG Square", 50, 50, "Erikdel"), // NOI18N
    TT_ALWAYS_GREY("tt-always-grey.png", "Always Grey", 35, 35, "Stefan Aleksić", "http://www.facebook.com/stefanaleksic88"), // NOI18N
    TT_ARABESQUE("tt-arabesque.png", "Arabesque", 110, 110, "David Sanchez", "http://www.twitter.com/davidsancar"), // NOI18N
    TT_ARCHES("tt-arches.png", "Arches", 103, 23, "Kim Ruddock"), // NOI18N
    TT_ARGYLE("tt-argyle.png", "Argyle", 106, 96, "Will Monson"), // NOI18N
    TT_ASFALT_DARK("tt-asfalt-dark.png", "Asfalt (Dark)", 466, 349, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_ASFALT_LIGHT("tt-asfalt-light.png", "Asfalt (Light)", 466, 349, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_ASSAULT("tt-assault.png", "Assault", 154, 196, "Hendrik Lammers", "http://www.hendriklammers.com/"), // NOI18N
    TT_AXIOM_PATTERN("tt-axiom-pattern.png", "Axiom Pattern", 81, 81, "Struck Axiom", "http://struck.com/"), // NOI18N
    TT_AZ_SUBTLE("tt-az-subtle.png", "Az Subtle", 100, 100, "Anli.", "http://azmind.com/"), // NOI18N

    TT_BACK_PATTERN("tt-back-pattern.png", "Back Pattern", 28, 28, "M"), // NOI18N
    TT_BASKETBALL("tt-basketball.png", "Basketball", 227, 196, "Mike Hearn", "https://www.mikehearn.com/"), // NOI18N
    TT_BATTHERN("tt-batthern.png", "Batthern", 100, 99, "Factorio.us Collective", "http://www.factorious.co/"), // NOI18N
    TT_BEDGE_GRUNGE("tt-bedge-grunge.png", "Bedge Grunge", 588, 375, "Alex Tapein"), // NOI18N
    TT_BEIGE_PAPER("tt-beige-paper.png", "Beige Paper", 200, 200, "Konstantin Ivanov", "http://twitter.com/phenix_h_k"), // NOI18N
    TT_BILLIE_HOLIDAY("tt-billie-holiday.png", "Billie Holiday", 100, 100, "Thomas Myrman"), // NOI18N
    TT_BINDING_DARK("tt-binding-dark.png", "Binding Dark", 180, 180, "Tia Newbury"), // NOI18N
    TT_BINDING_LIGTH("tt-binding-light.png", "Binding Light", 180, 180, "Tia Newbury"), // NOI18N
    TT_BLACK_FELT("tt-black-felt.png", "Black Felt", 531, 337, "E. van Zummeren", "http://evanzummeren.com/"), // NOI18N
    TT_BLACK_LINEN_1("tt-black-linen-1.png", "Black Linen", 482, 490, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_BLACK_LINEN_2("tt-black-linen-2.png", "Black Linen 2", 640, 640, "Atle Mo", "http://atle.co/"), // NOI18N
    //TT_BLACK_LOZENGE("tt-black-lozenge.png", "", 38, 38, "", ""), // NOI18N
    TT_BLACK_MAMBA("tt-black-mamba.png", "Black Mamba", 192, 192, "Federica Pelzel", "https://about.me/federicca"), // NOI18N
    TT_BLACK_ORCHID("tt-black-orchid.png", "Black Orchid", 300, 300, "Hybridixstudio", "https://www.hybridixstudio.com/"), // NOI18N
    TT_BLACK_PAPER("tt-black-paper.png", "Black Paper", 400, 400, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_BLACK_SCALES("tt-black-scales.png", "Black Scales", 40, 40, "Alex Parker", "http://twitter.com/misterparker"), // NOI18N
    TT_BLACK_THREAD("tt-black-thread.png", "Black Thread", 49, 28, "Listvetra", "http://www.listvetra.ru/"), // NOI18N
    TT_BLACK_THREAD_LIGHT("tt-black-thread-light.png", "Black Thread (Light)", 49, 28, "Listvetra", "http://www.listvetra.ru/"), // NOI18N
    TT_BLACK_TWILL("tt-black-twill.png", "Black Twill", 14, 14, "Cary Fleming"), // NOI18N
    TT_BLIZZARD("tt-blizzard.png", "Blizzard", 25, 25, "Alexandre Naud", "http://www.alexandrenaud.fr/"), // NOI18N
    TT_BLU_STRIPES("tt-blu-stripes.png", "Blu Stripes", 100, 100, "Seb Jachec", "http://twitter.com/iamsebj"), // NOI18N
    TT_BO_PLAY("tt-bo-play.png", "Bo Play", 42, 22, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_BRICK_WALL("tt-brick-wall.png", "Brick Wall", 110, 69, "Benjamin Ward"), // NOI18N
    TT_BRICK_WALL_DARK("tt-brick-wall-dark.png", "Brick Wall (Dark)", 110, 69, "Benjamin Ward"), // NOI18N
    TT_BRIGHT_SQUARES("tt-bright-squares.png", "Bright Squares", 297, 297, "Waseem Dahman", "http://twitter.com/dwaseem"), // NOI18N
    TT_BRILLIANT("tt-brilliant.png", "Brilliant", 3, 3, "Carlos Valdez", "http://saveder.blogspot.de/"), // NOI18N
    TT_BROKEN_NOISE("tt-broken-noise.png", "Broken Noise", 476, 476, "Vincent Klaiber"), // NOI18N
    TT_BRUSHED_ALUM("tt-brushed-alum.png", "Brushed Alum", 400, 400, "Tim Ward"), // NOI18N
    TT_BRUSHED_ALUM_DARK("tt-brushed-alum-dark.png", "Brushed Alum Dark", 400, 400, "Tim Ward"), // NOI18N
    TT_BURIED("tt-buried.png", "Buried", 350, 350, "Hendrik Lammers", "http://www.hendriklammers.com/"), // NOI18N

    TT_CANDYHOLE("tt-candyhole.png", "Candyhole", 25, 25, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_CARBON_FIBRE("tt-carbon-fibre.png", "Carbon Fibre", 24, 22, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_CARBON_FIBRE_BIG("tt-carbon-fibre-big.png", "Carbon Fibre Big", 20, 22, "Factorio.us Collective", "http://www.factorious.co/"), // NOI18N
    TT_CARBON_FIBRE_V2("tt-carbon-fibre-v2.png", "Carbon Fibre V2", 32, 36, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_CARDBOARD("tt-cardboard.png", "Cardboard", 600, 600, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_CARDBOARD_FLAT("tt-cardboard-flat.png", "Cardboard Flat", 256, 256, "Appleshadow"), // NOI18N
    TT_CARTOGRAPHER("tt-cartographer.png", "Cartographer", 500, 499, "Sam Feyaerts", "http://sam.feyaerts.me/"), // NOI18N
    TT_CHECKERED_LIGHT_EMBOSS("tt-checkered-light-emboss.png", "Checkered Light Emboss", 60, 60, "Alex Parker", "https://twitter.com/misterparker"), // NOI18N
    TT_CHECKERED_PATTERN("tt-checkered-pattern.png", "Checkered Pattern", 72, 72, "Radoslaw Rzepecki", "http://designcocktails.com/"), // NOI18N
    TT_CHURCH("tt-church.png", "Church", 100, 100, "j Boo"), // NOI18N
    TT_CIRCLES("tt-circles.png", "Circles", 16, 22, "Blunia", "http://www.es.blunia.com/"), // NOI18N
    TT_CLASSY_FABRIC("tt-classy-fabric.png", "Classy Fabric", 102, 102, "Richard Tabor", "http://purtypixels.com/"), // NOI18N
    TT_CLEAN_GRAY_PAPER("tt-clean-gray-paper.png", "Clean Gray Paper", 512, 512, "Paul Phönixweiß"), // NOI18N
    TT_CLEAN_TEXTILE("tt-clean-textile.png", "Clean Textile", 420, 420, "N8rx"), // NOI18N
    TT_CLIMPEK("tt-climpek.png", "Climpek", 44, 44, "Wassim", "http://www.blugraphic.com/"), // NOI18N
    TT_CLOTH_ALIKE("tt-cloth-alike.png", "Cloth Alike", 102, 78, "Peax Webdesign", "http://www.peax-webdesign.com/"), // NOI18N
    TT_CONCRETE_WALL("tt-concrete-wall.png", "Concrete Wall", 520, 520, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_CONCRETE_WALL_2("tt-concrete-wall-2.png", "Concrete Wall 2", 597, 545, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_CONCRETE_WALL_3("tt-concrete-wall-3.png", "Concrete Wall 3", 400, 299, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_CONNECTED("tt-connected.png", "Connected", 160, 160, "Mark Collins"), // NOI18N
    TT_CORRUGATION("tt-corrugation.png", "Corrugation", 8, 5, "Anna Litvinuk", "https://graphicriver.net/user/annalitviniuk"), // NOI18N
    TT_CREAM_DUST("tt-cream-dust.png", "Cream Dust", 50, 50, "Thomas Myrman"), // NOI18N
    TT_CREAM_PAPER("tt-cream-paper.png", "Cream Paper", 158, 144, "Devin Holmes"), // NOI18N
    TT_CREAM_PIXELS("tt-cream-pixels.png", "Cream Pixels", 160, 160, "Mizanur Rahman", "https://www.behance.net/rexmizan"), // NOI18N
    TT_CRISP_PAPER_RUFFLES("tt-crisp-paper-ruffles.png", "Crisp Paper Ruffles", 481, 500, "Tish"), // NOI18N
    TT_CRISSXCROSS("tt-crissxcross.png", "Crissxcross", 512, 512, "Ashton"), // NOI18N
    TT_CROSSWORD("tt-crossword.png", "Crossword", 400, 400, "Ideawebme", "http://www.ideaweb.me/"), // NOI18N
    TT_CROSS_SCRATCHES("tt-cross-scratches.png", "Cross Scratches", 256, 256, "Andrey Ovcharov", "https://www.ovcharov.me/"), // NOI18N
    TT_CROSS_STRIPES("tt-cross-stripes.png", "Cross Stripes", 6, 6, "Stefan Aleksic", "http://www.facebook.com/stefanaleksic88"), // NOI18N
    TT_CUBES("tt-cubes.png", "Cubes", 67, 100, "Sander Ottens", "http://experimint.nl/"), // NOI18N
    TT_CUTCUBE("tt-cutcube.png", "Cutcube", 20, 36, "Michael Atkins", "http://cubecolour.co.uk/"), // NOI18N

    TT_DARK_BRICK_WALL("tt-dark-brick-wall.png", "Dark Brick Wall", 96, 96, "Alex Parker", "http://www.alexparker.me/"), // NOI18N
    TT_DARK_CIRCLES("tt-dark-circles.png", "Dark Circles", 10, 12, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_DARK_DENIM("tt-dark-denim.png", "Dark Denim", 145, 145, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_DARK_DENIM_3("tt-dark-denim-3.png", "Dark Denim 3", 420, 326, "Brandon Jacoby"), // NOI18N
    TT_DARK_DOT("tt-dark-dot.png", "Dark Dot", 5, 5, "Tsvetelin Nikolov", "https://dribbble.com/bscsystem"), // NOI18N
    TT_DARK_DOTTED_2("tt-dark-dotted-2.png", "Dark Dotted 2", 7, 7, "Venam"), // NOI18N
    TT_DARK_EXA("tt-dark-exa.png", "Dark Exa", 18, 30, "Venam"), // NOI18N
    TT_DARK_FISH_SKIN("tt-dark-fish-skin.png", "Dark Fish Skin", 6, 12, "Petr Sulc", "http://www.petrsulc.com/"), // NOI18N
    TT_DARK_GEOMETRIC("tt-dark-geometric.png", "Dark Geometric", 70, 70, "Mike Warner", "http://miketheindian.com/"), // NOI18N
    TT_DARK_LEATHER("tt-dark-leather.png", "Dark Leather", 398, 484, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_DARK_MATTER("tt-dark-matter.png", "Dark Matter", 7, 7, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_DARK_MOSAIC("tt-dark-mosaic.png", "Dark Mosaic", 300, 295, "John Burks"), // NOI18N
    TT_DARK_STRIPES("tt-dark-stripes.png", "Dark Stripes", 50, 50, "Stefan Aleksic", "https://www.facebook.com/stefanaleksic88"), // NOI18N
    TT_DARK_STRIPES_LIGHT("tt-dark-stripes-light.png", "Dark Stripes (Light)", 50, 50, "Stefan Aleksic", "https://www.facebook.com/stefanaleksic88"), // NOI18N
    TT_DARK_TIRE("tt-dark-tire.png", "Dark Tire", 250, 250, "Wilmotte Bastien", "https://dribbble.com/bastienwilmotte"), // NOI18N
    TT_DARK_WALL("tt-dark-wall.png", "Dark Wall", 300, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_DARK_WOOD("tt-dark-wood.png", "Dark Wood", 512, 512, "Omar Alvarado"), // NOI18N
    TT_DARTH_STRIPE("tt-darth-stripe.png", "Darth Stripe", 511, 511, "Ashton"), // NOI18N
    TT_DEBUT_DARK("tt-debut-dark.png", "Debut Dark", 200, 200, "Luke McDonald", "https://www.lukemcdonald.com/"), // NOI18N
    TT_DEBUT_LIGHT("tt-debut-light.png", "Debut Light", 200, 200, "Luke McDonald", "https://www.lukemcdonald.com/"), // NOI18N
    TT_DIAGMONDS_DARK("tt-diagmonds-dark.png", "Diagmonds (Dark)", 141, 142, "INS", "https://www.flickr.com/photos/ins"), // NOI18N
    TT_DIAGMONDS_LIGHT("tt-diagmonds-light.png", "Diagmonds (Light)", 141, 142, "INS", "https://www.flickr.com/photos/ins"), // NOI18N
    TT_DIAGONALES_DECALEES("tt-diagonales-decalees.png", "Diagonales Decalees", 144, 48, "Graphiste", "http://www.peax-webdesign.com/"), // NOI18N
    TT_DIAGONAL_NOISE("tt-diagonal-noise.png", "Diagonal Noise", 100, 100, "Christopher Burton"), // NOI18N
    TT_DIAGONAL_STRIPED_BRICK("tt-diagonal-striped-brick.png", "Diagonal Striped Brick", 150, 150, "Alex Smith"), // NOI18N
    TT_DIAGONAL_WAVES("tt-diagonal-waves.png", "Diagonal Waves", 38, 38, "CoolPatterns", "http://coolpatterns.net/"), // NOI18N
    TT_DIAMONDS_ARE_FOREVER("tt-diamonds-are-forever.png", "Diamonds Are Forever", 24, 18, "Tom Neal"), // NOI18N
    TT_DIAMOND_EYES("tt-diamond-eyes.png", "Diamond Eyes", 33, 25, "AJ Troxell", "http://ajtroxell.com/"), // NOI18N
    TT_DIAMOND_UPHOLSTERY("tt-diamond-upholstery.png", "Diamond Upholstery", 200, 200, "Dimitar Karaytchev"), // NOI18N
    TT_DIMENSION("tt-dimension.png", "Dimension", 43, 50, "Luuk van Baars", "http://luuk.ca/"), // NOI18N
    TT_DIRTY_OLD_BLACK_SHIRT("tt-dirty-old-black-shirt.png", "Dirty Old Black Shirt", 250, 250, "Paul Reulat", "https://twitter.com/PaulReulat"), // NOI18N
    TT_DOTNOISE_LIGHT_GREY("tt-dotnoise-light-grey.png", "Dotnoise Light Grey", 100, 100, "Nikolalek"), // NOI18N
    TT_DOUBLE_LINED("tt-double-lined.png", "Double Lined", 150, 64, "Adam Anlauf"), // NOI18N
    TT_DUST("tt-dust.png", "Dust", 400, 300, "Dominik Kiss", "http://werk.sk/w/"), // NOI18N

    TT_ECAILLES("tt-ecailles.png", "Ecailles", 48, 20, "Graphiste", "http://www.peax-webdesign.com/"), // NOI18N
    TT_EGG_SHELL("tt-egg-shell.png", "Egg Shell", 256, 256, "Paul Phönixweiß"), // NOI18N
    TT_ELASTOPLAST("tt-elastoplast.png", "Elastoplast", 37, 37, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_ELEGANT_GRID("tt-elegant-grid.png", "Elegant Grid", 16, 28, "GraphicsWall", ""), // NOI18N
    TT_EMBOSSED_PAPER("tt-embossed-paper.png", "Embossed Paper", 8, 10, "Badhon Ebrahim", ""), // NOI18N
    TT_ESCHERESQUE("tt-escheresque.png", "Escheresque", 46, 29, "Jan Meeus", ""), // NOI18N
    TT_ESCHERESQUE_DARK("tt-escheresque-dark.png", "Escheresque Dark", 46, 29, "Ste Patten"), // NOI18N
    TT_EXCLUSIVE_PAPER("tt-exclusive-paper.png", "Exclusive Paper", 560, 420, "Atle Mo", "http://atle.co/"), // NOI18N

    TT_FABRIC_1_DARK("tt-fabric-1-dark.png", "Fabric 1 (Dark)", 400, 400, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_FABRIC_1_LIGHT("tt-fabric-1-light.png", "Fabric 1 (Light)", 400, 400, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_FABRIC_OF_SQUARES("tt-fabric-of-squares.png", "Fabric Of Squares", 410, 410, "Heliodor Jalba", ""), // NOI18N
    TT_FABRIC_PLAID("tt-fabric-plaid.png", "Fabric (Plaid)", 200, 200, "James Basoo", ""), // NOI18N
    TT_FAKE_BRICK("tt-fake-brick.png", "Fake Brick", 76, 76, "Marat"), // NOI18N
    TT_FAKE_LUXURY("tt-fake-luxury.png", "Fake Luxury", 16, 26, "Factorio.us Collective", ""), // NOI18N
    TT_FANCY_DEBOSS("tt-fancy-deboss.png", "Fancy Deboss", 18, 13, "Daniel Beaton", ""), // NOI18N
    TT_FARMER("tt-farmer.png", "Farmer", 349, 349, "Fabian Schultz", ""), // NOI18N
    TT_FELT("tt-felt.png", "Felt", 500, 466, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_FIRST_AID_KIT("tt-first-aid-kit.png", "First Aid Kit", 99, 99, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_FLOWERS("tt-flowers.png", "Flowers", 150, 150, "Unknown"), // NOI18N
    TT_FLOWER_TRAIL("tt-flower-trail.png", "Flower Trail", 16, 16, "Paridhi"), // NOI18N
    TT_FOGGY_BIRDS("tt-foggy-birds.png", "Foggy Birds", 206, 206, "Pete Fecteau", ""), // NOI18N
    TT_FOOD("tt-food.png", "Food", 300, 300, "Ilya"), // NOI18N
    // football-no-lines are 2 pictures (dark-light) tweak it TODO
    TT_FOOTBALL_NO_LINES("tt-football-no-lines.png", "Football (No Yardlines)", 714, 240, "Mike Hearn", ""), // NOI18N
    TT_FRENCH_STUCCO("tt-french-stucco.png", "French Stucco", 400, 355, "Christopher Buecheler", ""), // NOI18N
    TT_FRESH_SNOW("tt-fresh-snow.png", "Fresh Snow", 500, 500, "Kerstkaarten", ""), // NOI18N

    TT_GOLD_SCALE("tt-gold-scale.png", "Gold Scale", 25, 25, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_GPLAY("tt-gplay.png", "Gplay", 188, 178, "Dimitrie Hoekstra", ""), // NOI18N
    TT_GRADIENT_SQUARES("tt-gradient-squares.png", "Gradient Squares", 202, 202, "Brankic1979", ""), // NOI18N
    TT_GRAPHCODERS_LIL_FIBER("tt-graphcoders-lil-fiber.png", "Graphcoders Lil Fiber", 21, 35, "Badhon Ebrahim", ""), // NOI18N
    TT_GRAPHY_DARK("tt-graphy-dark.png", "Graphy (Dark)", 80, 160, "We Are Pixel8", ""), // NOI18N
    TT_GRAPHY_LIGHT("tt-graphy-light.png", "Graphy (Light)", 80, 160, "We Are Pixel8", ""), // NOI18N
    TT_GRAVEL("tt-gravel.png", "Gravel", 222, 206, "Mike Hearn", ""), // NOI18N
    TT_GRAY_FLORAL("tt-gray-floral.png", "Gray Floral", 150, 124, "Lauren", ""), // NOI18N
    TT_GRAY_LINES("tt-gray-lines.png", "Gray Lines", 150, 150, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_GRAY_SAND("tt-gray-sand.png", "Gray Sand", 211, 211, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_GREEN_CUP("tt-green-cup.png", "Green Cup", 400, 400, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_GREEN_DUST_AND_SCRATCHES("tt-green-dust-and-scratches.png", "Green Dust and Scratches", 296, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_GREEN_FIBERS("tt-green-fibers.png", "Green Fibers", 200, 200, "Matteo Di Capua", ""), // NOI18N
    TT_GREEN_GOBBLER("tt-green-gobbler.png", "Green Gobbler", 39, 39, "Simon Meek", ""), // NOI18N
    TT_GREYZZ("tt-greyzz.png", "Greyzz", 149, 139, "Infographiste", ""), // NOI18N
    TT_GREY_JEAN("tt-grey-jean.png", "Grey Jean", 150, 150, "Omur Uluask", ""), // NOI18N
    TT_GREY_SANDBAG("tt-grey-sandbag.png", "Grey Sandbag", 100, 98, "Diogo Silva", ""), // NOI18N
    TT_GREY_WASHED_WALL("tt-grey-washed-wall.png", "Grey Washed Wall", 350, 259, "Sagive SEO", ""), // NOI18N
    TT_GRID("tt-grid.png", "Grid", 310, 310, "Dominik Kiss", ""), // NOI18N
    TT_GRID_ME("tt-grid-me.png", "Grid Me", 50, 50, "Arno Gregorian", ""), // NOI18N
    TT_GRID_NOISE("tt-grid-noise.png", "Grid Noise", 98, 98, "Daivid Serif"), // NOI18N
    TT_GRILLED_NOISE("tt-grilled-noise.png", "Grilled Noise", 170, 180, "Dertig Media", ""), // NOI18N
    TT_GROOVEPAPER("tt-groovepaper.png", "Groovepaper", 300, 300, "Isaac", ""), // NOI18N
    TT_GRUNGE_WALL("tt-grunge-wall.png", "Grunge Wall", 500, 375, "Adam Anlauf", ""), // NOI18N
    TT_GUN_METAL("tt-gun-metal.png", "Gun Metal", 10, 10, "Nikolay Boltachev", ""), //

    TT_HANDMADE_PAPER("tt-handmade-paper.png", "Handmade Paper", 100, 100, "Le Marquis"), // NOI18N
    TT_HEXABUMP("tt-hexabump.png", "Hexabump", 19, 33, "Norbert Levajsics", ""), // NOI18N
    TT_HEXELLENCE("tt-hexellence.png", "Hexellence", 150, 173, "Kim Ruddock", ""), // NOI18N
    TT_HIXS_EVOLUTION("tt-hixs-evolution.png", "Hixs Evolution", 35, 34, "Damian Rivas", ""), // NOI18N
    TT_HOFFMAN("tt-hoffman.png", "Hoffman", 16, 16, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_HONEY_IM_SUBTLE("tt-honey-im-subtle.png", "Honey I'm Subtle", 179, 132, "Alex M. Balling", ""), // NOI18N

    TT_ICE_AGE("tt-ice-age.png", "Ice Age", 400, 400, "Gjermund Gustavsen", ""), // NOI18N
    TT_INFLICTED("tt-inflicted.png", "Inflicted", 240, 240, "Hugo Loning", ""), // NOI18N
    TT_INSPIRATION_GEOMETRY("tt-inspiration-geometry.png", "Inspiration Geometry", 412, 412, "Welsley", ""), // NOI18N
    TT_IRON_GRIP("tt-iron-grip.png", "Iron Grip", 300, 301, "Tony Kinard", ""), // NOI18N

    TT_KINDA_JEAN("tt-kinda-jean.png", "Kinda Jean", 147, 147, "Graphiste", ""), // NOI18N
    TT_KNITTED_NETTING("tt-knitted-netting.png", "Knitted Netting", 8, 8, "Anna Litvinuk", ""), // NOI18N
    TT_KNITTED_SEATER("tt-knitted-sweater.png", "Knitted Sweater", 250, 250, "Victoria Spahn", ""), // NOI18N
    TT_KUJI("tt-kuji.png", "Kuji", 30, 30, "Josh Green", "https://joshgreendesign.com/"), // NOI18N

    TT_LARGE_LEATHER("tt-large-leather.png", "Large Leather", 400, 343, "Elemis", ""), // NOI18N
    TT_LEATHER("tt-leather.png", "Leather", 300, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_LIGHT_ALUMINUM("tt-light-aluminum.png", "Light Aluminum", 282, 282, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_LIGHT_GRAY("tt-light-gray.png", "Light Gray", 301, 621, "Brenda Lay", ""), // NOI18N
    TT_LIGHT_GREY_FLORAL_MOTIF("tt-light-grey-floral-motif.png", "Light Grey Floral Motif", 32, 56, "GraphicsWall", ""), // NOI18N
    TT_LIGHT_HONEYCOMB("tt-light-honeycomb.png", "Light Honeycomb", 270, 289, "Federica Pelzel", ""), // NOI18N
    TT_LIGHT_HONEYCOMB_DARK("tt-light-honeycomb-dark.png", "Light Honeycomb (Dark)", 270, 289, "Federica Pelzel", ""), // NOI18N
    TT_LIGHT_MESH("tt-light-mesh.png", "Light Mesh", 256, 256, "Wilmotte Bastien", ""), // NOI18N
    TT_LIGHT_PAPER_FIBERS("tt-light-paper-fibers.png", "Light Paper Fibers", 500, 300, "Jorge Fuentes", ""), // NOI18N
    TT_LIGHT_SKETCH("tt-light-sketch.png", "Light Sketch", 600, 600, "Dan Kruse", ""), // NOI18N
    TT_LIGHT_TOAST("tt-light-toast.png", "Light Toast", 200, 200, "Pippin Lee", ""), // NOI18N
    TT_LIGHT_WOOL("tt-light-wool.png", "Light Wool", 190, 191, "Andy", ""), // NOI18N
    TT_LINED_PAPER("tt-lined-paper.png", "Lined Paper", 300, 224, "Are Sundnes", ""), // NOI18N
    TT_LINED_PAPER_2("tt-lined-paper-2.png", "Lined Paper 2", 412, 300, "Gjermund Gustavsen", ""), // NOI18N
    TT_LITTLE_KNOBS("tt-little-knobs.png", "Little Knobs", 10, 10, "Amos", ""), // NOI18N
    TT_LITTLE_PLUSES("tt-little-pluses.png", "Little Pluses", 300, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_LITTLE_TRIANGLES("tt-little-triangles.png", "Little Triangles", 10, 11, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_LOW_CONTRAST_LINEN("tt-low-contrast-linen.png", "Low Contrast Linen", 256, 256, "Jordan Pittman"), // NOI18N
    TT_LYONNETTE("tt-lyonnette.png", "Lyonnette", 90, 90, "Tish", ""), // NOI18N

    TT_MAZE_BLACK("tt-maze-black.png", "Maze Black", 46, 23, "Peax", ""), // NOI18N
    TT_MAZE_WHITE("tt-maze-white.png", "Maze White", 46, 23, "Peax", ""), // NOI18N
    TT_MBOSSED("tt-mbossed.png", "Mbossed", 26, 26, "Alex Parker", ""), // NOI18N
    TT_MEDIC_PACKAGING_FOIL("tt-medic-packaging-foil.png", "Medic Packaging Foil", 8, 8, "pixilated", ""), // NOI18N
    TT_MERELY_CUBED("tt-merely-cubed.png", "Merely Cubed", 16, 16, "Etienne Rallion", ""), // NOI18N
    TT_MICRO_CARBON("tt-micro-carbon.png", "Micro Carbon", 4, 4, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_MIRRORED_SQUARES("tt-mirrored-squares.png", "Mirrored Squares", 166, 166, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_MOCHA_GRUNGE("tt-mocha-grunge.png", "Mocha Grunge", 400, 400, "Joel Klein", ""), // NOI18N
    TT_MOONING("tt-mooning.png", "Mooning", 400, 400, "Joel Klein", ""), // NOI18N
    TT_MOULING("tt-moulin.png", "Moulin", 20, 20, "Venam", ""), // NOI18N
    TT_MY_LITTLE_PLAID_DARK("tt-my-little-plaid-dark.png", "My Little Plaid (Dark)", 54, 54, "Pete Fecteau", ""), // NOI18N
    TT_MY_LITTLE_PLAID_LIGHT("tt-my-little-plaid-light.png", "My Little Plaid (Light)", 54, 54, "Pete Fecteau", ""), // NOI18N
    
    TT_NAMI("tt-nami.png", "Nami", 16, 16, "Dertig Media", ""), // NOI18N
    TT_NASTY_FABRIC("tt-nasty-fabric.png", "Nasty Fabric", 198, 200, "Badhon Ebrahim", ""), // NOI18N
    TT_NATURAL_PAPER("tt-natural-paper.png", "Natural Paper", 523, 384, "Mihaela Hinayon"), // NOI18N
    TT_NAVY("tt-navy.png", "Navy", 600, 385, "Ethan Hamilton", ""), // NOI18N
    TT_NICE_SNOW("tt-nice-snow.png", "Nice Snow", 250, 250, "Kerstkaarten", ""), // NOI18N
    TT_NISTRI("tt-nistri.png", "Nistri", 38, 38, "Markus Reiter", ""), // NOI18N
    TT_NOISE_LINES("tt-noise-lines.png", "Noise Lines", 60, 59, "Thomas Zucx"), // NOI18N
    TT_NOISE_PATTERN_WITH_SUBTLE_CROSS_LINES("tt-noise-pattern-with-subtle-cross-lines.png", "Noise Pattern With Subtle Cross Lines", 240, 240, "Viszt Peter", ""), // NOI18N
    TT_NOISY("tt-noisy.png", "Noisy", 300, 300, "Mladjan Antic", ""), // NOI18N
    TT_NOISY_GRID("tt-noisy-grid.png", "Noisy Grid", 150, 150, "Vectorpile", ""), // NOI18N
    TT_NOISY_NET("tt-noisy-net.png", "Noisy Net", 200, 200, "Tom McArdle", ""), // NOI18N
    TT_NORWEGIAN_ROSE("tt-norwegian-rose.png", "Norwegian Rose", 48, 48, "Fredrik Scheide"), // NOI18N
    TT_NOTEBOOK("tt-notebook.png", "Notebook", 300, 154, "HQvectors", ""), // NOI18N
    TT_NOTEBOOK_DARK("tt-notebook-dark.png", "Notebook (Dark)", 300, 154, "HQvectors", ""), // NOI18N

    TT_OFFICE("tt-office.png", "Office", 70, 70, "Andres Rigo", ""), // NOI18N
    TT_OLD_HUSKS("tt-old-husks.png", "Old Husks", 500, 500, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_OLD_MAP("tt-old-map.png", "Old Map", 256, 256, "Andreas Föhl", ""), // NOI18N
    TT_OLD_MATHEMATICS("tt-old-mathematics.png", "Old Mathematics", 200, 200, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_OLD_MOON("tt-old-moon.png", "Old Moon", 300, 300, "Nick Batchelor", ""), // NOI18N
    TT_OLD_WALL("tt-old-wall.png", "Old Wall", 300, 300, "Bartosz Kaszubowski", ""), // NOI18N
    TT_OTIS_REDDING("tt-otis-redding.png", "Otis Redding", 100, 100, " Thomas Myrman", ""), // NOI18N
    TT_OUTLETS("tt-outlets.png", "Outlets", 4, 8, "Michal Chovanec", ""), // NOI18N

    TT_P1("tt-p1.png", "P1", 8, 9, "Dima Shiper", ""), // NOI18N
    TT_P2("tt-p2.png", "P2", 4, 5, "Dima Shiper", ""), // NOI18N
    TT_P4("tt-p4.png", "P4", 8, 8, "Dima Shiper", ""), // NOI18N
    TT_P5("tt-p5.png", "P5", 8, 8, "Dima Shiper", ""), // NOI18N
    TT_P6("tt-p6.png", "P6", 8, 8, "Dima Shiper", ""), // NOI18N
    TT_PADDED("tt-padded.png", "Padded", 160, 160, "Chris Baldie", ""), // NOI18N
    TT_PADDED_LIGHT("tt-padded-light.png", "Padded (Light)", 160, 160, "Chris Baldie", ""), // NOI18N
    TT_PAPER("tt-paper.png", "Paper", 500, 593, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_PAPER_1("tt-paper-1.png", "Paper 1", 400, 400, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_PAPER_2("tt-paper-2.png", "Paper 2", 280, 280, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_PAPER_3("tt-paper-3.png", "Paper 3", 276, 276, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_PAPER_FIBERS("tt-paper-fibers.png", "Paper Fibers", 410, 410, "Heliodor jalba", ""), // NOI18N
    TT_PAVEN("tt-paven.png", "Paven", 20, 20, "Josh Green", "https://joshgreendesign.com/"), // NOI18N
    TT_PERFORATED_WHITE_LEATHER("tt-perforated-white-leather.png", "Perforated White Leather", 300, 300, "Dmitry"), // NOI18N
    TT_PINEAPPLE_CUT("tt-pineapple-cut.png", "Pineapple Cut", 36, 62, "Audee Mirza", ""), // NOI18N
    TT_PINSTRIPED_SUIT("tt-pinstriped-suit.png", "Pinstriped Suit", 400, 333, "Alex Berkowitz", ""), // NOI18N
    TT_PINSTRIPE_DARK("tt-pinstripe-dark.png", "Pinstripe (Dark)", 50, 500, "Brandon", ""), // NOI18N
    TT_PINSTRIPE_LIGHT("tt-pinstripe-light.png", "Pinstripe (Light)", 50, 500, "Brandon", ""), // NOI18N
    TT_PIXEL_WEAVE("tt-pixel-weave.png", "Pixel Weave", 64, 64, "Dax Kieran", ""), // NOI18N
    TT_POLAROID("tt-polaroid.png", "Polaroid", 58, 36, "Daniel Beaton", ""), // NOI18N
    TT_POLONEZ_PATTERN("tt-polonez-pattern.png", "Polonez Pattern", 300, 300, "Radoslaw Rzepecki", ""), // NOI18N
    TT_POLYESTER_LITE("tt-polyester-lite.png", "Polyester Lite", 17, 22, "Jeremy", ""), // NOI18N
    TT_POOL_TABLE("tt-pool-table.png", "Pool Table", 256, 256, "Caveman", ""), // NOI18N
    TT_PROJECT_PAPER("tt-project-paper.png", "Project Paper", 105, 105, "Rafael Almeida", ""), // NOI18N
    TT_PSYCHEDELIC("tt-psychedelic.png", "Psychedelic", 84, 72, "Pixeden", ""), // NOI18N
    TT_PS_NEUTRAL("tt-ps-neutral.png", "Ps Neutral", 16, 16, "Gluszczenko", ""), // NOI18N
    TT_PURTY_WOOD("tt-purty-wood.png", "Purty Wood", 400, 400, "Richard Tabor", ""), // NOI18N
    TT_PW_PATTERN("tt-pw-pattern.png", "Pw Pattern", 188, 188, "Peax", ""), // NOI18N
    TT_PYRAMID("tt-pyramid.png", "Pyramid", 16, 16, "Jeff Wall"), // NOI18N

    TT_QUILT("tt-quilt.png", "Quilt", 25, 24, "Josh Green", "https://joshgreendesign.com/"), // NOI18N

    TT_RANOM_GREY_VARIATIONS("tt-random-grey-variations.png", "Random Grey Variations", 200, 200, "Stefan Aleksic", ""), // NOI18N
    TT_RAVENNA("tt-ravenna.png", "Ravenna", 387, 201, "Sentel", ""), // NOI18N
    TT_REAL_CARBON_FIBRE("tt-real-carbon-fibre.png", "Real Carbon Fibre", 56, 56, "Alfred Lee"), // NOI18N
    TT_REBEL("tt-rebel.png", "Rebel", 320, 360, "Hendrik Lammers", ""), // NOI18N
    TT_REDOX_01("tt-redox-01.png", "Redox 01", 600, 375, "Hendrik Lammers", ""), // NOI18N
    TT_REDOX_02("tt-redox-02.png", "Redox 02", 600, 340, "Hendrik Lammers", ""), // NOI18N
    TT_RETICULAR_TISSUE("tt-reticular-tissue.png", "Reticular Tissue", 25, 25, "Anna Litvinuk", ""), // NOI18N
    TT_RETINA_DUST("tt-retina-dust.png", "Retina Dust", 200, 200, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_RETINA_WOOD("tt-retina-wood.png", "Retina Wood", 512, 512, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_RETRO_INTRO("tt-retro-intro.png", "Retro Intro", 109, 109, "Bilal Ketab", ""), // NOI18N
    TT_RICE_PAPER("tt-rice-paper.png", "Rice Paper", 500, 500, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_RICE_PAPER_2("tt-rice-paper-2.png", "Rice Paper #2", 485, 485, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_RICE_PAPER_3("tt-rice-paper-3.png", "Rice Paper #3", 250, 250, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_ROBOTS("tt-robots.png", "Robots", 200, 200, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_ROCKY_WALL("tt-rocky-wall.png", "Rocky Wall", 500, 500, "Projecteightyfive", ""), // NOI18N
    TT_ROUGH_CLOTH("tt-rough-cloth.png", "Rough Cloth", 320, 320, "Bartosz Kaszubowski", ""), // NOI18N
    TT_ROUGH_CLOTH_LIGHT("tt-rough-cloth-light.png", "Rough Cloth (Light)", 320, 320, "Bartosz Kaszubowski", ""), // NOI18N
    TT_ROUGH_DIAGONAL("tt-rough-diagonal.png", "Rough Diagonal", 256, 256, "Jorick van Hees", ""), // NOI18N
    TT_RUBBER_GRIP("tt-rubber-grip.png", "Rubber Grip", 5, 20, "Sinisha", ""), // NOI18N

    TT_SANDPAPER("tt-sandpaper.png", "Sandpaper", 348, 500, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_SATIN_WEAVE("tt-satin-weave.png", "Satin Weave", 24, 12, "Merrin Macleod", ""), // NOI18N
    TT_SCRIBBLE_LIGHT("tt-scribble-light.png", "Scribble Light", 304, 306, "Tegan Male", ""), // NOI18N
    TT_SHATTERED_DARK("tt-shattered-dark.png", "Shattered (Dark)", 500, 500, "Luuk van Baars", ""), // NOI18N
    TT_SHATTERED_LIGHT("tt-shattered-light.png", "Shattered (Light)", 500, 500, "Luuk van Baars", ""), // NOI18N
    TT_SHINE_CARO("tt-shine-caro.png", "Shine Caro", 9, 9, "mediumidee", ""), // NOI18N
    TT_SHINE_DOTTED("tt-shine-dotted.png", "Shine Dotted", 6, 5, "mediumidee", ""), // NOI18N
    TT_SHLEY_TREE_1("tt-shley-tree-1.png", "Shley Tree 1", 300, 409, "Derek Ramsey", ""), // NOI18N
    TT_SHLEY_TREE_2("tt-shley-tree-2.png", "Shley Tree 2", 400, 414, "Mike Hearn", ""), // NOI18N
    TT_SILVER_SCALES("tt-silver-scales.png", "Silver Scales", 40, 40, "Alex Parker", ""), // NOI18N
    TT_SIMPLE_DASHED("tt-simple-dashed.png", "Simple Dashed", 14, 14, "Venam", ""), // NOI18N
    TT_SIMPLE_HORIZONTAL_LIGHT("tt-simple-horizontal-light.png", "Simple Horizontal Light", 4, 4, "Fabricio"), // NOI18N
    TT_SKELETAL_WEAVE("tt-skeletal-weave.png", "Skeletal Weave", 25, 25, "Angelica", ""), // NOI18N
    TT_SKEWED_PRINT("tt-skewed-print.png", "Skewed Print", 330, 320, "Hendrik Lammers", ""), // NOI18N
    TT_SKIN_SIDE_UP("tt-skin-side-up.png", "Skin Side Up", 320, 360, "Hendrik Lammers", ""), // NOI18N
    TT_SKULLS("tt-skulls.png", "Skulls", 400, 400, "Adam"), // NOI18N
    TT_SLASH_IT("tt-slash-it.png", "Slash It", 9, 9, "Venam", ""), // NOI18N
    TT_SMALL_CRACKLE_BRIGTH("tt-small-crackle-bright.png", "Small Crackle Bright", 14, 14, "Markus Tinner", ""), // NOI18N
    TT_SMALL_CROSSES("tt-small-crosses.png", "Small Crosses", 10, 10, "Ian Dmitry"), // NOI18N
    TT_SMOOTH_WALL_DARK("tt-smooth-wall-dark.png", "Smooth Wall (Dark)", 358, 358, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_SMOOTH_WALL_LIGHT("tt-smooth-wall-light.png", "Smooth Wall (Light)", 358, 358, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_SNEAKER_MESH_FABRIC("tt-sneaker-mesh-fabric.png", "Sneaker Mesh Fabric", 150, 111, "Victor Bejar", ""), // NOI18N
    TT_SNOW("tt-snow.png", "Snow", 500, 500, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_SOFT_CIRCLE_SCALE("tt-soft-circle-scale.pngs", "Soft Circle Scales", 256, 56, "Ian Soper", ""), // NOI18N
    TT_SOFT_KILL("tt-soft-kill.png", "Soft Kill", 28, 48, "Factorio.us Collective", ""), // NOI18N
    TT_SOFT_PAD("tt-soft-pad.png", "Soft Pad", 8, 8, "Badhon Ebrahim", ""), // NOI18N
    TT_SOFT_WALLPAPER("tt-soft-wallpaper.png", "Soft Wallpaper", 666, 666, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_SOLID("tt-solid.png", "Solid", 500, 500, "Hendrik Lammers", ""), // NOI18N
    TT_SOS("tt-sos.png", "Sos", 25, 25, "JBasoo", ""), // NOI18N
    TT_SPRINKLES("tt-sprinkles.png", "Sprinkles", 10, 5, "Rebecca Litt", ""), // NOI18N
    TT_SQUAIRY("tt-squairy.png", "Squairy", 200, 200, "Tia Newbury"), // NOI18N
    TT_SQUARED_METAL("tt-squared-metal.png", "Squared Metal", 132, 132, "doot0", ""), // NOI18N
    TT_SQUARES("tt-squares.png", "Squares", 32, 32, "Jaromir Kavan", ""), // NOI18N
    TT_STACKED_CIRCLES("tt-stacked-circles.png", "Stacked Circles", 9, 9, "Saqib", ""), // NOI18N
    TT_STARDUST("tt-stardust.png", "Stardust", 798, 798, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_STARRING("tt-starring.png", "Starring", 35, 39, "Agus Riyadi", ""), // NOI18N
    TT_STITCHED_WOOL("tt-stitched-wool.png", "Stitched Wool", 224, 128, "Badhon Ebrahim", ""), // NOI18N
    TT_STRANGE_BULLSEYES("tt-strange-bullseyes.png", "Strange Bullseyes", 300, 300, "Christopher Buecheler", ""), // NOI18N
    TT_STRAWS("tt-straws.png", "Straws", 16, 16, "Pavel", ""), // NOI18N
    TT_STRESSED_LINEN("tt-stressed-linen.png", "Stressed Linen", 256, 256, "Jordan Pittman"), // NOI18N
    TT_STUCCO("tt-stucco.png", "Stucco", 250, 249, "Bartosz Kaszubowski", ""), // NOI18N
    TT_SUBTLENET("tt-subtlenet.png", "Subtlenet", 60, 60, "Designova", ""), // NOI18N
    TT_SUBTLE_CARBON("tt-subtle-carbon.png", "Subtle Carbon", 18, 15, "Designova", ""), // NOI18N
    TT_SUBTLE_DARK_VERTICAL("tt-subtle-dark-vertical.png", "Subtle Dark Vertical", 40, 40, "Cody L", ""), // NOI18N
    TT_SUBTLE_DOTS("tt-subtle-dots.png", "Subtle Dots", 27, 15, "Designova", ""), // NOI18N
    TT_SUBTLE_FRECKLES("tt-subtle-freckles.png", "Subtle Freckles", 198, 198, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_SUBTLE_GREY("tt-subtle-grey.png", "Subtle Grey", 397, 322, "Haris Sumic", ""), // NOI18N
    TT_SUBTLE_GRUNGE("tt-subtle-grunge.png", "Subtle Grunge", 400, 400, "Breezi", ""), // NOI18N
    TT_SUBTLE_STRIPES("tt-subtle-stripes.png", "Subtle Stripes", 40, 40, "Raasa", ""), // NOI18N
    TT_SUBTLE_SURFACE("tt-subtle-surface.png", "Subtle Surface", 16, 8, "Designova", ""), // NOI18N
    TT_SUBTLE_WHITE_FEATHERS("tt-subtle-white-feathers.png", "Subtle White Feathers", 500, 333, "Viahorizon", ""), // NOI18N
    TT_SUBTLE_ZEBRA_3D("tt-subtle-zebra-3d.png", "Subtle Zebra 3D", 121, 38, "Mike Warner", ""), // NOI18N
    TT_SWIRL("tt-swirl.png", "Swirl", 200, 200, "Peter Chon", ""), // NOI18N
    
    TT_TACTILE_NOISE_DARK("tt-tactile-noise-dark.png", "Tactile Noise (Dark)", 48, 48, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_TACTILE_NOISE_LIGHT("tt-tactile-noise-light.png", "Tactile Noise (Light)", 48, 48, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_TAPESTRY("tt-tapestry.png", "Tapestry", 72, 61, "Pixeden", ""), // NOI18N
    TT_TASKY("tt-tasky.png", "Tasky", 10, 10, "Michal Chovanec", ""), // NOI18N
    TT_TEX2RES1("tt-tex2res1.png", "Tex2Res1", 500, 500, "Janos Koos", ""), // NOI18N
    TT_TEX2RES2("tt-tex2res2.png", "Tex2Res2", 500, 500, "Janos Koos", ""), // NOI18N
    TT_TEX2RES3("tt-tex2res3.png", "Tex2Res3", 500, 500, "Janos Koos", ""), // NOI18N
    TT_TEX2RES4("tt-tex2res4.png", "Tex2Res4", 500, 500, "Janos Koos", ""), // NOI18N
    TT_TEX2RES5("tt-tex2res5.png", "Tex2Res5", 500, 500, "Janos Koos", ""), // NOI18N
    TT_TEXTURED_PAPER("tt-textured-paper.png", "Textured Paper", 500, 500, "Stephen Gilbert", ""), // NOI18N
    TT_TEXTURED_STRIPES("tt-textured-stripes.png", "Textured Stripes", 256, 256, "V Hartikainen", ""), // NOI18N
    TT_TEXTURETASTIC_GRAY("tt-texturetastic-gray.png", "Texturetastic Gray", 476, 476, "Adam Pickering", ""), // NOI18N
    TT_TICKS("tt-ticks.png", "Ticks", 400, 400, "Laura Gilbert Gilardenghi", ""), // NOI18N
    TT_TILEABLE_WOOD("tt-tileable-wood.png", "Tileable Wood", 400, 317, "Elemis", ""), // NOI18N
    TT_TILEABLE_WOOD_COLORED("tt-tileable-wood-colored.png", "Tileable Wood Colored", 400, 317, "Elemis", ""), // NOI18N
    TT_TINY_GRID("tt-tiny-grid.png", "Tiny Grid", 26, 26, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_TRANSLUCENT_FIBRES("tt-translucent-fibres.png", "Translucent Fibres", 16, 16, "Angelica", ""), // NOI18N
    TT_TRANSPARENT_SQUARE_TILES("tt-transparent-square-tiles.png", "Transparent Square Tiles", 252, 230, "Nathan Spady", ""), // NOI18N
    TT_TREE_BARK("tt-tree-bark.png", "Tree Bark", 350, 350, "GetDiscount", ""), // NOI18N
    TT_TRIANGLES("tt-triangles.png", "Triangles", 33, 36, "Ivan Ginev", ""), // NOI18N
    TT_TRIANGLES_2("tt-triangles-2.png", "Triangles 2", 72, 72, "Pixeden", ""), // NOI18N
    TT_TRIANGULAR("tt-triangular.png", "Triangular", 60, 60, "Dax Kieran", ""), // NOI18N
    TT_TWEED("tt-tweed.png", "Tweed", 200, 200, "Simon Leo"), // NOI18N
    TT_TWINKLE_TWINKLE("tt-twinkle-twinkle.png", "Twinkle Twinkle", 360, 300, "Badhon Ebrahim", ""), // NOI18N
    TT_TXTURE("tt-txture.png", "Txture", 400, 300, "Anatoli Nicolae", ""), // NOI18N
    TT_TYPE("tt-type.png", "Type", 200, 200, "Atle Mo", "http://atle.co/"), // NOI18N

    TT_USE_YOUR_ILLUSION("tt-use-your-illusion.png", "Use Your Illusion", 54, 58, "Mohawk Studios", ""), // NOI18N

    TT_VAIO("tt-vaio.png", "Vaio", 37, 28, "Zigzain", ""), // NOI18N
    TT_VERTICAL_CLOTH("tt-vertical-cloth.png", "Vertical Cloth", 399, 400, "Krisp Designs", ""), // NOI18N
    TT_VICHY("tt-vichy.png", "Vichy", 70, 70, "Olivier Pineda", ""), // NOI18N
    TT_VINTAGE_SPECKLES("tt-vintage-speckles.png", "Vintage Speckles", 400, 300, "David Pomfret", ""), // NOI18N

    TT_WALL_4_LIGHT("tt-wall-4-light.png", "Wall #4 Light", 300, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WASHI("tt-washi.png", "Washi", 100, 100, "Carolynne", ""), // NOI18N
    TT_WAVECUT("tt-wavecut.png", "Wavecut", 162, 15, "Ian Soper", ""), // NOI18N
    TT_WAVE_GRID("tt-wave-grid.png", "Wave Grid", 100, 100, "DomainsInfo", ""), // NOI18N
    TT_WEAVE("tt-weave.png", "Weave", 35, 31, "Catherine", ""), // NOI18N
    TT_WET_SNOW("tt-wet-snow.png", "Wet Snow", 250, 250, "Kerstkaarten", ""), // NOI18N
    TT_WHITEY("tt-whitey.png", "Whitey", 654, 654, "Ant Eksiler", ""), // NOI18N
    TT_WHITE_BED_SHEET("tt-white-bed-sheet.png", "White Bed Sheet", 54, 54, "Badhon Ebrahim", ""), // NOI18N
    TT_WHITE_BRICK_WALL("tt-white-brick-wall.png", "White Brick Wall", 24, 16, "Listvetra", ""), // NOI18N
    TT_WHITE_BRUSHED("tt-white-brushed.png", "White Brushed", 114, 114, "Andre Schouten", ""), // NOI18N
    TT_WHITE_CARBON("tt-white-carbon.png", "White Carbon", 8, 8, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WHITE_CARBONFIBER("tt-white-carbonfiber.png", "White Carbonfiber", 4, 4, "Badhon Ebrahim", ""), // NOI18N
    TT_WHITE_DIAMOND_DARK("tt-white-diamond-dark.png", "White Diamond (Dark)", 128, 224, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WHITE_DIAMOND_LIGHT("tt-white-diamond-light.png", "White Diamond", 128, 224, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WHITE_LEATHER("tt-white-leather.png", "White Leather", 300, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WHITE_LINEN("tt-white-linen.png", "White Linen", 400, 300, "Fabian Schultz", ""), // NOI18N
    TT_WHITE_PAPERBOARD("tt-white-paperboard.png", "White Paperboard", 256, 252, "Chaos"), // NOI18N
    TT_WHITE_PLASTER("tt-white-plaster.png", "White Plaster", 300, 300, "Phil Maurer", ""), // NOI18N
    TT_WHITE_SAND("tt-white-sand.png", "White Sand", 211, 211, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WHITE_TEXTURE("tt-white-texture.png", "White Texture", 102, 102, "Dmitry"), // NOI18N
    TT_WHITE_TILES("tt-white-tiles.png", "White Tiles", 800, 250, "Another One", ""), // NOI18N
    TT_WHITE_WALL("tt-white-wall.png", "White Wall", 800, 600, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WHITE_WALL_3("tt-white-wall-3.png", "White Wall 3", 150, 150, "Viahorizon", ""), // NOI18N
    TT_WHITE_WALL_3_2("tt-white-wall-3-2.png", "White Wall 3[2]", 500, 500, "Luca", ""), // NOI18N
    TT_WHITE_WAVE("tt-white-wave.png", "White Wave", 23, 12, "Rohit Arun Rao"), // NOI18N
    TT_WIDE_RECTANGLES("tt-wide-rectangles.png", "Wide Rectangles", 32, 14, "Petr Sulc", ""), // NOI18N
    TT_WILD_FLOWERS("tt-wild-flowers.png", "Wild Flowers", 350, 350, "Themes Tube", ""), // NOI18N
    TT_WILD_OLIVA("tt-wild-oliva.png", "Wild Oliva", 198, 200, "Badhon Ebrahim", ""), // NOI18N
    TT_WINE_CORK("tt-wine-cork.png", "Wine Cork", 300, 300, "Atle Mo", "http://atle.co/"), // NOI18N
    TT_WOOD("tt-wood.png", "Wood", 700, 700, "Cloaks", ""), // NOI18N
    TT_WOOD_PATTERN("tt-wood-pattern.png", "Wood Pattern", 203, 317, "Alexey Usoltsev"), // NOI18N
    TT_WORN_DOTS("tt-worn-dots.png", "Worn Dots", 200, 200, "Matt McDaniel", ""), // NOI18N
    TT_WOVEN("tt-woven.png", "Woven", 42, 42, "Max Rudberg", ""), // NOI18N
    TT_WOVEN_LIGHT("tt-woven-light.png", "Woven (Light)", 42, 42, "Max Rudberg", ""), // NOI18N

    TT_XV("tt-xv.png", "Xv", 294, 235, "Lasma", ""), // NOI18N

    TT_ZIG_ZAG("tt-zig-zag.png", "Zig Zag", 10, 10, "Dmitriy Prodchenko", ""); // NOI18N

    private final double height; // (must)
    private final double width; // (must)

    private final String autor;
    private final String header; // (must)
    private final String name; // (must)
    private final String url;

    TransparentTexturesTile(
            final String name, final String header,
            final double width, final double height,
            final String autor
    ) {
        this(name, header, width, height, autor, ""); // NOI18N
    }

    TransparentTexturesTile(
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
    public String getUrl() {
        return url;
    }

    @Override
    public double getWidth() {
        return width;
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
