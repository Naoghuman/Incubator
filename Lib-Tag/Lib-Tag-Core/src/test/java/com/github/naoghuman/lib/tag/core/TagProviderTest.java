/*
 * Copyright (C) 2016 PRo
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
import static jdk.nashorn.internal.objects.Global.instance;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author PRo
 */
public class TagProviderTest {
    
    public TagProviderTest() {
    }
    
    @Before
    public void setUp() {
        TagProvider.getDefault().getAllMappedTags().clear();
        TagProvider.getDefault().getAllTags().clear();
    }

    @Test
    public void testGetDefault() {
        TagProvider resultTagProvider = TagProvider.getDefault();
        assertNotNull("resultTagProvider can't be NULL", resultTagProvider);
    }

    @Test
    public void testAddTag() {
        assertTrue("getAllTags() should be empty", TagProvider.getDefault().getAllTags().isEmpty());
        
        Tag tag = new Tag();
        TagProvider.getDefault().add(tag);
        assertTrue("getAllTags().size() should be 1", TagProvider.getDefault().getAllTags().size() == 1);
    }

    @Test
    public void testAddMappedTag() {
        assertTrue("getAllMappedTags() should be empty", TagProvider.getDefault().getAllMappedTags().isEmpty());
        
        MappedTag mappedTag = new MappedTag();
        TagProvider.getDefault().add(mappedTag);
        assertTrue("getAllMappedTags().size() should be 1", TagProvider.getDefault().getAllMappedTags().size() == 1);
    }

    @Test
    public void testAddAllMappedTags() {
        assertTrue("getAllMappedTags() should be empty", TagProvider.getDefault().getAllMappedTags().isEmpty());
        
        ObservableList<MappedTag> mappedTags = FXCollections.observableArrayList();
        mappedTags.add(new MappedTag());
        mappedTags.add(new MappedTag());
        mappedTags.add(new MappedTag());
        TagProvider.getDefault().addAllMappedTags(mappedTags);
        assertTrue("getAllMappedTags().size() should be 3", TagProvider.getDefault().getAllMappedTags().size() == 3);
    }

    @Test
    public void testAddAllTags() {
        assertTrue("getAllTags() should be empty", TagProvider.getDefault().getAllTags().isEmpty());
        
        ObservableList<Tag> tags = FXCollections.observableArrayList();
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        TagProvider.getDefault().addAllTags(tags);
        assertTrue("getAllTags().size() should be 3", TagProvider.getDefault().getAllTags().size() == 3);
    }

//    @Test
//    public void testGetAllTagsFromParent() {
//        System.out.println("getAllTagsFromParent");
//        long parentId = 0L;
//        TagProvider instance = null;
//        ObservableList<Tag> expResult = null;
//        ObservableList<Tag> result = instance.getAllTagsFromParent(parentId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }


    @Test
    public void testGetTag() {
        Tag tag = new Tag();
        tag.setId(-1L);
        TagProvider.getDefault().add(tag);
        
        Optional<Tag> result = TagProvider.getDefault().getTag(0L);
        assertFalse("result.isPresent() must be FALSE", result.isPresent());
        
        result = TagProvider.getDefault().getTag(-1L);
        assertTrue("result.isPresent() must be TRUE", result.isPresent());
    }

    @Test
    public void testRemoveTag() {
        Tag tag1 = new Tag();
        tag1.setId(-11L);
        TagProvider.getDefault().add(tag1);
        
        Tag tag2 = new Tag();
        tag2.setId(-222L);
        TagProvider.getDefault().add(tag2);
        assertTrue("getAllTags().size() should be 2", TagProvider.getDefault().getAllTags().size() == 2);
        
        boolean expectedTrue = TagProvider.getDefault().remove(tag1);
        assertTrue("expectedTrue should be TRUE", expectedTrue);
        assertTrue("getAllTags().size() should be 1", TagProvider.getDefault().getAllTags().size() == 1);
        
        Optional<Tag> result = TagProvider.getDefault().getTag(-222L);
        assertTrue("result.isPresent() must be TRUE", result.isPresent());
        assertEquals("result.get().getId() must be -222L", result.get().getId(), -222L);
    }

    @Test
    public void testRemoveMappedTag() {
        MappedTag mappedTag1 = new MappedTag();
        mappedTag1.setParentId(-222L);
        mappedTag1.setTagId(-1111L);
        TagProvider.getDefault().add(mappedTag1);
        
        MappedTag mappedTag2 = new MappedTag();
        mappedTag2.setParentId(-222L);
        mappedTag2.setTagId(-22222L);
        TagProvider.getDefault().add(mappedTag2);
        assertTrue("getAllMappedTags().size() should be 2", TagProvider.getDefault().getAllMappedTags().size() == 2);
        assertTrue("getAllMappedTags(-222L).size() should be 2", TagProvider.getDefault().getAllMappedTags(-222L).size() == 2);
        
        boolean expectedTrue = TagProvider.getDefault().remove(mappedTag1);
        assertTrue("expectedTrue should be TRUE", expectedTrue);
        assertTrue("getAllMappedTags(-222L).size() should be 1", TagProvider.getDefault().getAllMappedTags().size() == 1);
        
        ObservableList<MappedTag> result = TagProvider.getDefault().getAllMappedTags(-222L);
        MappedTag mt = result.get(0);
        assertEquals("mt.getParentId() must be -222L", mt.getParentId(), -222L);
        assertEquals("mt.getTagId() must be -22222L", mt.getTagId(), -22222L);
    }

    @Test
    public void testRemoveAllMappedTagsFromParent() {
        MappedTag mappedTag1 = new MappedTag();
        mappedTag1.setParentId(-222L);
        mappedTag1.setTagId(-1111L);
        TagProvider.getDefault().add(mappedTag1);
        
        MappedTag mappedTag2 = new MappedTag();
        mappedTag2.setParentId(-222L);
        mappedTag2.setTagId(-22222L);
        TagProvider.getDefault().add(mappedTag2);
        
        MappedTag mappedTag3 = new MappedTag();
        mappedTag3.setParentId(-3L);
        mappedTag3.setTagId(-1111L);
        TagProvider.getDefault().add(mappedTag3);
        assertTrue("getAllMappedTags().size() should be 3", TagProvider.getDefault().getAllMappedTags().size() == 3);
        
        boolean expectedTrue = TagProvider.getDefault().removeAllMappedTagsFromParent(-222L);
        assertTrue("expectedTrue should be TRUE", expectedTrue);
        assertTrue("getAllMappedTags().size() should be 1", TagProvider.getDefault().getAllMappedTags().size() == 1);
        
        ObservableList<MappedTag> result = TagProvider.getDefault().getAllMappedTags(-3L);
        MappedTag mt = result.get(0);
        assertEquals("mt.getParentId() must be -3L", mt.getParentId(), -3L);
        assertEquals("mt.getTagId() must be -1111L", mt.getTagId(), -1111L);
    }
    
}
