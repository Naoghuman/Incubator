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
package com.github.naoghuman.lib.tag.components;

import com.github.naoghuman.lib.tag.core.Tag;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Naoghuman
 */
public final class TagComponentsFacade {
    
    private static final Optional<TagComponentsFacade> instance = Optional.of(new TagComponentsFacade());
    
    public static final TagComponentsFacade getDefault() {
        return instance.get();
    }
    
    private TagComponentsFacade() {
        
    }
    
    private FlowPane getFlowPaneForTags() {
        // TODO add css style
        final FlowPane flowPane = new FlowPane(7.0d, 7.0d);
        
        return flowPane;
    }
    
    public FlowPane getFlowPaneForTagsAsButtons(ObservableList<Button> tags) {
        final FlowPane flowPane = this.getFlowPaneForTags();
        flowPane.getChildren().addAll(tags);
        
        return flowPane;
    }
    
    public FlowPane getFlowPaneForTagsAsLabels(ObservableList<Label> tags) {
        final FlowPane flowPane = this.getFlowPaneForTags();
        flowPane.getChildren().addAll(tags);
        
        return flowPane;
    }
    
    public Button getTagAsButton(Tag tag) {
        // TODO add css style
        final Button button = new Button();
        button.setText(tag.getTitle());
        button.setTooltip(new Tooltip(tag.getDescription()));
        button.setUserData(tag);
        
        return button;
    }
    
    public Label getTagAsLabel(Tag tag) {
        // TODO add css style
        final Label label = new Label();
        label.setText(tag.getTitle());
        label.setTooltip(new Tooltip(tag.getDescription()));
        label.setUserData(tag);
        
        return label;
    }
    
}
