/*
 * Copyright (C) 2017 Naoghuman
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
package com.github.naoghuman.abclist.exercise.sign;

import com.github.naoghuman.abclist.exercise.ESign;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Naoghuman
 */
public class SignPresenter implements Initializable {
    
    private final ObservableList<Term> flowPaneTerms = FXCollections.observableArrayList();
    
    @FXML private FlowPane fpWords;
    @FXML private HBox hbSign;
    @FXML private Label lSign;
    
    private ESign sign;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize SignPresenter"); // NOI18N
        
        // TODO only [Sign]s with [Word]s are visible
//        hbSign.managedProperty().bind(Bindings.isNotEmpty(fpWords.getChildren()));
//        hbSign.visibleProperty().bind(Bindings.isNotEmpty(fpWords.getChildren()));

        this.initializeFlowPane();
    }
    
    private void initializeFlowPane() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize FlowPane"); // NOI18N
    }

    public void addTerm(Term term) {
        LoggerFacade.getDefault().debug(this.getClass(), "Add Term [" + term.getTerm() + "]"); // NOI18N

        // Check if the [Term] isn't always added
        boolean isTermAdded = false;
        for (Term flowPaneTerm : flowPaneTerms) {
            if (flowPaneTerm.getTerm().equals(term.getTerm())) {
                isTermAdded = true;
                break;
            }
        }
        
        // Add and sort it
        if (!isTermAdded) {
            flowPaneTerms.add(term);
            FXCollections.sort(flowPaneTerms);
            
            fpWords.getChildren().clear();
            flowPaneTerms.forEach(flowPaneTerm -> {
                fpWords.getChildren().add(this.getLabel(flowPaneTerm));
            });
        }
    }
    
    public void configure(ESign sign) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure sign [" + sign.name() + "]"); // NOI18N
        
        this.sign = sign;
        
        lSign.setText(sign + ":"); // NOI18N
    }
    
    public Label getLabel(Term term) {
        final Label label = new Label(term.getTerm());
        label.setUserData(term);
        label.setStyle("-fx-background-color:LIGHTGREEN;");
        
        return label;
    }
    
    public boolean isSign(char sign) {
        return this.sign.isSign(sign);
    }
    
}
