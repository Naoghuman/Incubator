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
package com.github.naoghuman.lib.tag.core;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public final class TagProvider {

    private static final Optional<TagProvider> instance = Optional.of(new TagProvider());

    /**
     * Returns a singleton instance from the class <code>TagProvider</code>.
     *
     * @return a singleton instance from the class <code>TagProvider</code>.
     */
    public static final TagProvider getDefault() {
        return instance.get();
    }

    private ObservableList<MappedTag> mappedTags = FXCollections.observableArrayList();
    private ObservableList<Tag> existingTags = FXCollections.observableArrayList();

    private TagProvider() {

    }

    public void add(Tag tag) {
        existingTags.add(tag);
    }

    public void add(MappedTag mappedTag) {
        mappedTags.add(mappedTag);
    }

    public void addAllMappedTags(ObservableList<MappedTag> mappedTags) {
        this.mappedTags.clear();
        this.mappedTags.addAll(mappedTags);
    }
    
    public ObservableList<MappedTag> getAllMappedTags() {
        return mappedTags;
    }
    
    public ObservableList<MappedTag> getAllMappedTags(long parentId) {
        // Precondition
        if (mappedTags.isEmpty()) {
            return FXCollections.observableArrayList();
        }
        
        // Catch them
        final ObservableList<MappedTag> allMappedTagsWithParentId = FXCollections.observableArrayList();
        mappedTags
                .stream()
                .filter((mappedTag) -> (mappedTag.getParentId() == parentId))
                .forEach((mappedTag) -> {
                    allMappedTagsWithParentId.add(mappedTag);
                });
        
        return allMappedTagsWithParentId;
    }

    public void addAllTags(ObservableList<Tag> existingTags) {
        this.existingTags.clear();
        this.existingTags.addAll(existingTags);
    }
    
    public ObservableList<Tag> getAllTags() {
        return existingTags;
    }
    
    public ObservableList<Tag> getAllTags(ObservableList<MappedTag> mappedTags) {
        final ObservableList<Tag> allTagsFromParent = FXCollections.observableArrayList();
        mappedTags
                .stream()
                .forEach((mappedTag) -> {
                    existingTags.stream()
                            .filter((tag) -> (mappedTag.getTagId() == tag.getId()))
                            .forEach((tag) -> {
                                allTagsFromParent.add(tag);
                            });
                });
        
        return allTagsFromParent;
    }
    
    public ObservableList<Tag> getAllTagsFromParent(long parentId) {
        // Preconditions
        if (mappedTags.isEmpty() || existingTags.isEmpty()) {
            return FXCollections.observableArrayList();
        }
        
        // Catch all MappedTags
        final ObservableList<MappedTag> allMappedTagsWithParentId = this.getAllMappedTags(parentId);
        if (allMappedTagsWithParentId.isEmpty()) {
            return FXCollections.observableArrayList();
        }
        
        // Catch all Tags
        final ObservableList<Tag> allTagsFromParent = this.getAllTags(allMappedTagsWithParentId);

        return allTagsFromParent;
    }
    
    public Optional<Tag> getTag(long tagId) {
        final Optional<Tag> oTag = existingTags
                .stream()
                .filter((tag) -> (tag.getId() == tagId) )
                .findFirst();
        
        return oTag;
    }

    public boolean remove(Tag tag) {
        return existingTags.remove(tag);
    }

    public boolean remove(MappedTag mappedTag) {
        return mappedTags.remove(mappedTag);
    }
    
    public boolean removeAllMappedTagsFromParent(long parentId) {
        return mappedTags
                .removeIf((mappedTag) -> (mappedTag.getParentId() == parentId));
    }

}
