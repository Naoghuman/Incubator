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
package com.github.naoghuman.abclist.exercise;

import com.github.naoghuman.abclist.configuration.IExerciseConfiguration;
import com.github.naoghuman.abclist.exercise.exercisedialog.ExerciseDialogPresenter;
import com.github.naoghuman.abclist.exercise.exercisedialog.ExerciseDialogView;
import com.github.naoghuman.abclist.exercise.sign.SignPresenter;
import com.github.naoghuman.abclist.exercise.sign.SignView;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ExerciseTerm;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.abclist.sql.SqlProvider;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Naoghuman
 */
public class ExercisePresenter implements Initializable, IExerciseConfiguration, PropertyChangeListener {
    
    final Stage dialog = new Stage();
        
    @FXML private Button bStartExercise;
    @FXML private ComboBox<ETime> cbTime;
    @FXML private VBox vbSigns;
    
    private Exercise exercise;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ExercisePresenter"); // NOI18N
        
        this.initializeComboBoxTime();
        this.initializeDialog();
        this.initializeSigns();
    }
    
    private void initializeComboBoxTime() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ComboBox Time"); // NOI18N
        
        cbTime.setCellFactory((ListView<ETime> listview) -> new ListCell<ETime>() {
            @Override
            public void updateItem(ETime item, boolean empty) {
                super.updateItem(item, empty);
                this.setGraphic(null);
                this.setText(!empty ? item.toString() : null);
            }
        });
        
        final ObservableList<ETime> observableListTimes = FXCollections.observableArrayList();
        observableListTimes.addAll(ETime.values());
        cbTime.getItems().addAll(observableListTimes);
        cbTime.getSelectionModel().selectFirst();
    }
    
    private void initializeDialog() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Dialog"); // NOI18N
        
        // TODO add AnchorPane
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.setAlwaysOnTop(true);
        dialog.setTitle("Exercise"); // NOI18N
        dialog.setResizable(false);
    }
    
    private void initializeSigns() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Signs"); // NOI18N
        
        vbSigns.getChildren().clear();
        for (ESign sign : ESign.values()) {
            final SignView signView = new SignView();
            final SignPresenter signPresenter = signView.getRealPresenter();
            signPresenter.configure(sign);
            
            final Parent view = signView.getView();
            view.setUserData(signPresenter);
            vbSigns.getChildren().add(view);
        }
    }
    
    private char computeFirstChar(String term) {
        char firstSign = term.charAt(0);
        if (firstSign == 'ä') { // NOI18N
            firstSign = 'a'; // NOI18N
        }
        
        if (firstSign == 'ö') { // NOI18N
            firstSign = 'o'; // NOI18N
        }
        
        if (firstSign == 'ü') { // NOI18N
            firstSign = 'u'; // NOI18N
        }
        
        return firstSign;
    }
    
    public void configure(Exercise exercise) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure"); // NOI18N
        
        this.exercise = exercise;
        
        if (exercise.isReady()) {
            this.onActionDisableComponents();
            this.onActionLoadAllTerms();
        }
    }
    
    private void onActionDisableComponents() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action disable [Component]s"); // NOI18N
        
        bStartExercise.setDisable(true);
        cbTime.setDisable(true);
    }
    
    private void onActionExerciseIsReady() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [Exercise] is ready"); // NOI18N

        // Save new state
        exercise.setReady(true);
        SqlProvider.getDefault().createOrUpdate(exercise);
        
        // Reflect the new state in the gui
        this.onActionDisableComponents();
        
        // Close dialog
        dialog.close();
    }
    
    private void onActionLoadAllTerms() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action load all [Term]s"); // NOI18N

        final ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllTermsWithExerciseId(exercise.getId());
        final ObservableList<Term> terms = SqlProvider.getDefault().findAllTermsInExerciseTerms(exerciseTerms);
        terms.stream()
                .forEach(term -> {
                    vbSigns.getChildren().stream()
                        .filter((node) -> (node.getUserData() instanceof SignPresenter))
                        .forEach((node) -> {
                            final SignPresenter presenter = (SignPresenter) node.getUserData();
                            final char firstChar = this.computeFirstChar(term.getTitle().toLowerCase());
                            if (presenter.isSign(firstChar)) {
                                presenter.addTerm(term);
                            }
                        });
                });
    }
    
    public void onActionStartExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action start [Exercise]"); // NOI18N
        
        final ExerciseDialogView v = new ExerciseDialogView();
        final ExerciseDialogPresenter p = v.getRealPresenter();
        
        final ETime time = cbTime.getSelectionModel().getSelectedItem();
        p.configure(time, this);
        
        final Scene scene = new Scene(v.getView());
        dialog.setScene(scene);
        dialog.show();
    }
    
    private void onActionUserStopExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [User] stop [Exercise]"); // NOI18N
        
        /*
        TODO
         - all added [Term]s form this [Exercise] have to be deleted in the [Database]
        */
        
        // Close dialog
        dialog.close();
    }
    
    private void onActionUserTypedTerm(Term term) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [User] typed [Term]"); // NOI18N
        
        // Check if the [Term] in the [Database] exists
        final ObservableList<Term> observableListTerms = SqlProvider.getDefault().findAllTermsWithTitle(term.getTitle());
        boolean isTermExists = false;
        for (Term observableListTerm : observableListTerms) {
            if (observableListTerm.getTitle().equals(term.getTitle())) {
                isTermExists = true;
                break;
            }
        }
        
        if (!isTermExists) {
            SqlProvider.getDefault().createOrUpdate(term);
        }
        
        // Check if the [Term] is associated with the [Exercise]
        final ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllTermsWithExerciseId(exercise.getId());
        boolean isExerciseTermExists = false;
        for (ExerciseTerm exerciseTerm : exerciseTerms) {
            if (
                    Objects.equals(exerciseTerm.getExerciseId(), exercise.getId())
                    && Objects.equals(exerciseTerm.getTermId(), term.getId())
            ) {
                isExerciseTermExists = true;
                break;
            }
        }
        
        if (!isExerciseTermExists) {
            final ExerciseTerm exerciseTerm = ModelProvider.getDefault().getExerciseTerm(exercise.getId(), term.getId());
            SqlProvider.getDefault().create(exerciseTerm);
        }
        
        // Show the [Term] in the [FlowPane]
        vbSigns.getChildren().stream()
                .filter((node) -> (node.getUserData() instanceof SignPresenter))
                .forEach((node) -> {
                    final SignPresenter presenter = (SignPresenter) node.getUserData();
                    final char firstChar = this.computeFirstChar(term.getTitle().toLowerCase());
                    if (presenter.isSign(firstChar)) {
                        presenter.addTerm(term);
                    }
                });
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final String propertyName = event.getPropertyName();
        if (propertyName.equals(PROP__EXERCISE_DIALOG__EXERCISE_IS_READY)) {
            this.onActionExerciseIsReady();
        }
        
        if (propertyName.equals(PROP__EXERCISE_DIALOG__USER_STOP_EXERCISE)) {
            this.onActionUserStopExercise();
        }
        
        if (propertyName.equals(PROP__EXERCISE_DIALOG__USER_TYPED_TERM)) {
            final Term term = (Term) event.getNewValue();
            this.onActionUserTypedTerm(term);
        }
    }
    
}
