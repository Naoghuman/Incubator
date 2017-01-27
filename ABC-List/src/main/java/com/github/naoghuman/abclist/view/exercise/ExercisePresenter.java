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
package com.github.naoghuman.abclist.view.exercise;

import com.github.naoghuman.abclist.configuration.IActionConfiguration;
import com.github.naoghuman.abclist.configuration.IExerciseConfiguration;
import com.github.naoghuman.abclist.view.exercise.exercisedialog.ExerciseDialogPresenter;
import com.github.naoghuman.abclist.view.exercise.exercisedialog.ExerciseDialogView;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ExerciseTerm;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.abclist.sql.SqlProvider;
import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Naoghuman
 */
public class ExercisePresenter implements Initializable, IActionConfiguration, IExerciseConfiguration, IRegisterActions {
    
    private final ObservableList<FlowPane> flowPaneTerms = FXCollections.observableArrayList();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // NOI18N
    private final Stage dialog = new Stage();
        
    @FXML private Button bStartExercise;
    @FXML private ComboBox<ETime> cbTime;
    @FXML private FlowPane tfSignA;
    @FXML private FlowPane tfSignB;
    @FXML private FlowPane tfSignC;
    @FXML private FlowPane tfSignD;
    @FXML private FlowPane tfSignE;
    @FXML private FlowPane tfSignF;
    @FXML private FlowPane tfSignG;
    @FXML private FlowPane tfSignH;
    @FXML private FlowPane tfSignI;
    @FXML private FlowPane tfSignJ;
    @FXML private FlowPane tfSignK;
    @FXML private FlowPane tfSignL;
    @FXML private FlowPane tfSignM;
    @FXML private FlowPane tfSignN;
    @FXML private FlowPane tfSignO;
    @FXML private FlowPane tfSignP;
    @FXML private FlowPane tfSignQ;
    @FXML private FlowPane tfSignR;
    @FXML private FlowPane tfSignS;
    @FXML private FlowPane tfSignT;
    @FXML private FlowPane tfSignU;
    @FXML private FlowPane tfSignV;
    @FXML private FlowPane tfSignW;
    @FXML private FlowPane tfSignX;
    @FXML private FlowPane tfSignY;
    @FXML private FlowPane tfSignZ;
    @FXML private Label lCounterTerms;
    @FXML private Label lGenerationTime;
    @FXML private ScrollPane spSigns;
    
    private int counterTerms = 0;
    
    private Exercise exercise;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ExercisePresenter"); // NOI18N
        
        this.initializeComboBoxTime();
        this.initializeDialog();
        this.initializeFlowPaneTerms();
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
    
    private void initializeFlowPaneTerms() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [FlowPane] [Term]s"); // NOI18N
        
        spSigns.viewportBoundsProperty().addListener((ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) -> {
            final Node content = spSigns.getContent();
            spSigns.setFitToHeight(content.prefHeight(-1) < newValue.getHeight());
        });
        
        flowPaneTerms.add(tfSignA);
        flowPaneTerms.add(tfSignB);
        flowPaneTerms.add(tfSignC);
        flowPaneTerms.add(tfSignD);
        flowPaneTerms.add(tfSignE);
        flowPaneTerms.add(tfSignF);
        flowPaneTerms.add(tfSignG);
        flowPaneTerms.add(tfSignH);
        flowPaneTerms.add(tfSignI);
        flowPaneTerms.add(tfSignJ);
        flowPaneTerms.add(tfSignK);
        flowPaneTerms.add(tfSignL);
        flowPaneTerms.add(tfSignM);
        flowPaneTerms.add(tfSignN);
        flowPaneTerms.add(tfSignO);
        flowPaneTerms.add(tfSignP);
        flowPaneTerms.add(tfSignQ);
        flowPaneTerms.add(tfSignR);
        flowPaneTerms.add(tfSignS);
        flowPaneTerms.add(tfSignT);
        flowPaneTerms.add(tfSignU);
        flowPaneTerms.add(tfSignV);
        flowPaneTerms.add(tfSignW);
        flowPaneTerms.add(tfSignX);
        flowPaneTerms.add(tfSignY);
        flowPaneTerms.add(tfSignZ);
        
        lCounterTerms.setText("Quantity: " + counterTerms);
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
        LoggerFacade.getDefault().debug(this.getClass(), "  # " + exercise.toString());
        
        this.exercise = exercise;
        
        lGenerationTime.setText(simpleDateFormat.format(new Date(exercise.getGenerationTime())));
        
        if (exercise.isReady()) {
            this.onActionDisableComponents();
            this.onActionLoadAllTerms();
        }
		
        this.registerActions();
    }
    
    public long getId() {
        return exercise.getId();
    }
    
    private FlowPane getFlowPane(char firstChar) {
        FlowPane fl = new FlowPane();
        for (FlowPane flowPane : flowPaneTerms) {
            if (flowPane.getId().toLowerCase().charAt(0) == firstChar) {
                fl = flowPane;
            }
        }
        
        return fl;
    }
    
    private Label getLabel(Term term) {
        // Check in db if isMarkAsWrong
        final boolean isMarkAsWrong = SqlProvider.getDefault().isExerciseTermMarkAsWrong(exercise.getId(), term.getId());
        
        // Create the label
        final Label label = new Label(term.getTitle());
        label.setUserData(term); // TODO tweak it
        label.setStyle(
                "-fx-background-color:"
                + (isMarkAsWrong ? "ORANGERED;" : "LIGHTGREEN;")); // NOI18N
        
        final Font font = label.getFont();
        label.setFont(Font.font(
                font.getName(),
                (isMarkAsWrong ? FontPosture.ITALIC : FontPosture.REGULAR),
                font.getSize()));
        label.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                final TransferData transferData = new TransferData();
                transferData.setActionId(ACTION__APPLICATION__OPEN_TERM);
                transferData.setObject(term);
                
                ActionFacade.getDefault().handle(transferData);
            }
        });
        
        // Create [ContextMenu]
        final ContextMenu cm = new ContextMenu();
        final Optional<ExerciseTerm> optional = SqlProvider.getDefault().findExerciseTerm(exercise.getId(), term.getId());
        if (isMarkAsWrong) {
            final MenuItem mi2 = new MenuItem("Mark as right"); // NOI18N
            mi2.setOnAction((ActionEvent event) -> {
                if (optional.isPresent()) {
                    final ExerciseTerm exerciseTerm = optional.get();
                    exerciseTerm.setMarkAsWrong(false);
                    SqlProvider.getDefault().updateExerciseTerm(exerciseTerm);
                    
                    // Refresh flowpane
                    // TODO Reload only the relevant [FlowPane]
                    this.onActionResetFlowPanes();
                    this.onActionLoadAllTerms();
                }
            });
            cm.getItems().add(mi2);
        }
        else {
            final MenuItem mi = new MenuItem("Mark as wrong"); // NOI18N
            mi.setOnAction((ActionEvent event) -> {
                if (optional.isPresent()) {
                    final ExerciseTerm exerciseTerm = optional.get();
                    exerciseTerm.setMarkAsWrong(true);
                    SqlProvider.getDefault().updateExerciseTerm(exerciseTerm);
                    
                    // Refresh flowpane
                    // TODO Reload only the relevant [FlowPane]
                    this.onActionResetFlowPanes();
                    this.onActionLoadAllTerms();
                }
            });
            cm.getItems().add(mi);
        }

        label.setContextMenu(cm);
        
        return label;
    }
    
    private void onActionAddTerm(Term term) {
        final char firstChar = this.computeFirstChar(term.getTitle().toLowerCase());
        System.out.println(" #firstchar: " + firstChar); // XXX
        
        final FlowPane fp = this.getFlowPane(firstChar);
        final AtomicBoolean isTermAdded = new AtomicBoolean(false);
        for (Node node : fp.getChildren()) {
            if (node instanceof Label) {
                final Label label = (Label) node;
                if (label.getUserData() instanceof Term) {
                    final Term addedTerm = (Term) label.getUserData();
                    if (addedTerm.getTitle().equals(term.getTitle())) {
                        isTermAdded.set(true);
        System.out.println(" #isTermAdded: true"); // XXX
                        break;
                    }
                }
            }
        }
        
        if (!isTermAdded.get()) {
        System.out.println(" #isTermAdded: false"); // XXX
            fp.getChildren().add(this.getLabel(term));
            FXCollections.sort(fp.getChildren(), (Node node1, Node node2) -> {
                int compare = 0;
                if (
                        node1 instanceof Label
                        && node2 instanceof Label
                        && node1.getUserData() instanceof Term
                        && node2.getUserData() instanceof Term
                ) {
                    final Term term1 = (Term) node1.getUserData();
                    final Term term2 = (Term) node2.getUserData();
                    compare = term1.getTitle().compareTo(term2.getTitle());
                }
                
                return compare;
            });
        }
    }
    
    private void onActionCountTerms() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action count [Term]s"); // NOI18N
        
        counterTerms = 0;
        flowPaneTerms.stream()
                .forEach(flowPane -> {
                    counterTerms += flowPane.getChildren().size();
                });
        lCounterTerms.setText("Quantity: " + counterTerms);
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
        SqlProvider.getDefault().updateExercise(exercise);
        LoggerFacade.getDefault().debug(this.getClass(), "  # " + exercise.toString());
        
        // Reflect the new state in the gui
        this.onActionDisableComponents();
        this.onActionCountTerms();
        ActionFacade.getDefault().handle(ACTION__APPLICATION__REFRESH_NAVIGATION_TAB_TOPICS);
        
        // Close dialog
        dialog.close();
    }
    
    private void onActionLoadAllTerms() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action load all [Term]s"); // NOI18N

        // Compute all [Term] from this [Exercise]
        final ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(exercise.getId());
        final ObservableList<Term> terms = SqlProvider.getDefault().findAllTermsInExerciseTerm(exerciseTerms);
        terms.stream()
                .forEach(term -> {
                    this.onActionAddTerm(term);
                    this.onActionCountTerms();
                });
    }
    
    private void onActionResetFlowPanes() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action reset [FlowPane]s"); // NOI18N
        
        flowPaneTerms.stream()
                .forEach(flowPane -> {
                    flowPane.getChildren().clear();
                });
    }
    
    public void onActionStartExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action start [Exercise]"); // NOI18N
        
        final ExerciseDialogView exerciseDialogView = new ExerciseDialogView();
        final ExerciseDialogPresenter exerciseDialogPresenter = exerciseDialogView.getRealPresenter();
        
        final ETime time = cbTime.getSelectionModel().getSelectedItem();
        exerciseDialogPresenter.configure(exercise.getId(), time);
        
        final Scene scene = new Scene(exerciseDialogView.getView());
        dialog.setScene(scene);
        dialog.show();
    }
    
    private void onActionUserStopExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [User] stop [Exercise]"); // NOI18N

        // Delete all existing [ExerciseTerm]s
        SqlProvider.getDefault().deleteAllExerciseTermsWithExerciseId(exercise.getId());
        
        // Reset the gui
        this.onActionResetFlowPanes();
        this.onActionCountTerms();
        
        // Close dialog
        dialog.close();
    }
    
    private void onActionUserTypedTerm(String userInput) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [User] typed [Term]"); // NOI18N
        System.out.println("  -> " + ACTION__EXERCISE_DIALOG__USER_TYPED_TERM + exercise.getId());// XXX
        
        // Check if the [Term] with the [title] in the [Database] exists
        final Term term = ModelProvider.getDefault().getTerm(userInput);
        final ObservableList<Term> observableListTerms = SqlProvider.getDefault().findAllTermsWithTitle(userInput);
        if (observableListTerms.isEmpty()) {
            SqlProvider.getDefault().createTerm(term);
        } else {
            term.copy(observableListTerms.get(0));
        }
        
        // Check if the [Term] is associated with the [Exercise]
        final ObservableList<ExerciseTerm> exerciseTerms = SqlProvider.getDefault().findAllExerciseTermsWithExerciseId(exercise.getId());
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
            SqlProvider.getDefault().createExerciseTerm(exerciseTerm);
            
            LoggerFacade.getDefault().debug(this.getClass(), "  # " + exerciseTerm.toString());
        }
        
        // Show the [Term] in the [FlowPane]
        this.onActionAddTerm(term);
        this.onActionCountTerms();
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in [ExercisePresenter]"); // NOI18N
		
        this.registerOnActionExerciseIsReady();
        this.registerOnActionUserStopExercise();
        this.registerOnActionUserTypedTerm();
    }
	
    private void registerOnActionExerciseIsReady() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action [Exercise] is ready"); // NOI18N

        ActionFacade.getDefault().register(
                ACTION__EXERCISE_DIALOG__EXERCISE_IS_READY + exercise.getId(),
                (ActionEvent event) -> {
                    this.onActionExerciseIsReady();
                });
    }
	
    private void registerOnActionUserStopExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action [User] stop [Exercise]"); // NOI18N

        ActionFacade.getDefault().register(
                ACTION__EXERCISE_DIALOG__USER_STOP_EXERCISE + exercise.getId(),
                (ActionEvent event) -> {
                    this.onActionUserStopExercise();
                });
    }
	
    private void registerOnActionUserTypedTerm() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action [User] typed [Term]"); // NOI18N

        ActionFacade.getDefault().register(
                ACTION__EXERCISE_DIALOG__USER_TYPED_TERM + exercise.getId(),
                (ActionEvent event) -> {
                    final TransferData transferData = (TransferData) event.getSource();
                    final String userInput = transferData.getString();
                    this.onActionUserTypedTerm(userInput);
                });
    }

}
