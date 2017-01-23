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
package com.github.naoghuman.abclist.view.application;

import com.github.naoghuman.abclist.configuration.IActionConfiguration;
import com.github.naoghuman.abclist.configuration.IApplicationConfiguration;
import com.github.naoghuman.abclist.configuration.IDefaultConfiguration;
import com.github.naoghuman.abclist.view.exercise.ExercisePresenter;
import com.github.naoghuman.abclist.view.exercise.ExerciseView;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Term;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.abclist.sql.SqlProvider;
import com.github.naoghuman.abclist.view.term.TermPresenter;
import com.github.naoghuman.abclist.view.term.TermView;
import com.github.naoghuman.abclist.view.topic.TopicPresenter;
import com.github.naoghuman.abclist.view.topic.TopicView;
import com.github.naoghuman.abclist.view.welcome.WelcomeView;
import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.util.Callback;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IActionConfiguration, 
        IApplicationConfiguration, IDefaultConfiguration, IRegisterActions 
{
    
    @FXML private Button bNavigationCreateNewTopic;
    @FXML private Button bNavigationCreateNewTerm;
    @FXML private Button bNavigationToHome;
    @FXML private Button bNavigationToNext;
    @FXML private Button bNavigationToPrevious;
    @FXML private Button bNavigationShowAll;
    @FXML private ComboBox<Topic> cbNavigationTopics;
    @FXML private Label lInfoFoundedTerms;
    @FXML private ListView<Term> lvNavigationTerms;
    @FXML private SplitPane spApplication;
    @FXML private TabPane tpNavigation;
    @FXML private TreeView<Object> tvNavigationTopics;
    @FXML private VBox vbWorkingArea;
    
    private final ObservableList<Navigation> navigationViews = FXCollections.observableArrayList();
    private final TreeItem<Object> rootItem = new TreeItem<> ();
    
    private int indexShownNavigationView = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeNavigation();
        this.initializeNavigationTabTopics();
        this.initializeNavigationTabTerms();
        this.initializeWelcomeView();

        this.registerActions();
            
        // Update gui
        final ObservableList<Topic> observableListTopics = SqlProvider.getDefault().findAllTopics();
        this.onActionRefreshNavigationTabTopics(observableListTopics);
        this.onActionRefreshNavigationTabTerms(observableListTopics);
    }
    
    private void initializeNavigation() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [Navigation]"); // NOI18N

        // Buttons
        bNavigationCreateNewTopic.managedProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isEqualTo(TAB_INDEX__TOPICS));
        bNavigationCreateNewTopic.visibleProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isEqualTo(TAB_INDEX__TOPICS));
        
        bNavigationCreateNewTerm.managedProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isEqualTo(TAB_INDEX__TERMS));
        bNavigationCreateNewTerm.visibleProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isEqualTo(TAB_INDEX__TERMS));
    }
    
    private void initializeNavigationTabTopics() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [Navigation] [Topic]s"); // NOI18N
        
        // TreeView
        tvNavigationTopics.setCellFactory((TreeView<Object> p) -> new NavigationListTreeCell());
    }
    
    private void initializeNavigationTabTerms() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [Navigation] [Term]s"); // NOI18N

        // ComboBox
        final Tooltip tooltip = new Tooltip("Show all Terms from the selected Topic"); // NOI18N
        cbNavigationTopics.setTooltip(tooltip);
        cbNavigationTopics.setDisable(true);
        
        final Callback callbackTopics = (Callback<ListView<Topic>, ListCell<Topic>>) (ListView<Topic> listView) -> new ListCell<Topic>() {
            @Override
            protected void updateItem(Topic topic, boolean empty) {
                super.updateItem(topic, empty);
                
                this.setGraphic(null);
                
                if (topic == null || empty) {
                    this.setText(null);
                } else {
                    this.setText(topic.getTitle());
                }
            }
        };

        cbNavigationTopics.setButtonCell((ListCell) callbackTopics.call(null));
        cbNavigationTopics.setCellFactory(callbackTopics);
        
        // ListView
        final Callback callbackTerms = (Callback<ListView<Term>, ListCell<Term>>) (ListView<Term> listView) -> new ListCell<Term>() {
            @Override
            protected void updateItem(Term term, boolean empty) {
                super.updateItem(term, empty);
                
                this.setGraphic(null);
                
                if (term == null || empty) {
                    this.setText(null);
                } else {
                    this.setText(term.getTitle());
                }
            }
        };
        
        lvNavigationTerms.setCellFactory(callbackTerms);
        lvNavigationTerms.setOnMouseClicked(event -> {
            if (
                    event.getClickCount() == 2
                    && !lvNavigationTerms.getSelectionModel().isEmpty()
            ) {
                final Term term = lvNavigationTerms.getSelectionModel().getSelectedItem();
                this.onActionOpenTerm(term);
            }
        });
    }
    
    private void initializeWelcomeView() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [WelcomeView]"); // NOI18N
        
        final Navigation<WelcomeView> navigation = new Navigation<>();
        final WelcomeView welcomeView = new WelcomeView();
        navigation.setView(welcomeView);
        navigationViews.add(navigation);
        indexShownNavigationView = 0;
        LoggerFacade.getDefault().debug(this.getClass(), "Add [WelcomeView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
        final Parent parent = welcomeView.getView();
        VBox.setVgrow(parent, Priority.ALWAYS);
        vbWorkingArea.getChildren().add(parent);
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [ApplicationPresenter] after window is showing"); // NOI18N
    
    }
    
    private String getInfoFoundedTerms(int foundedTerms) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Found "); // NOI18N
        sb.append(foundedTerms);
        sb.append(" Terms"); // NOI18N
        
        return sb.toString();
    }
    
    private void onActionCreateNewExercise(Topic topic) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Exercise]"); // NOI18N
        
        // Create a new Exercise
        final Exercise exercise = ModelProvider.getDefault().getExercise(IDefaultConfiguration.DEFAULT_ID, topic.getId());
        SqlProvider.getDefault().createExercise(exercise);
        
        // Open the new exercise
        this.onActionOpenExercise(exercise);
            
        // Update the gui
        final ObservableList<Topic> observableListTopics = SqlProvider.getDefault().findAllTopics();
        this.onActionRefreshNavigationTabTopics(observableListTopics);
        
        // Expand the TreeItem
        final Optional<TreeItem<Object>> optionalTreeItem = rootItem.getChildren().stream()
                .filter(treeItem -> ((Topic) treeItem.getValue()).equals(topic))
                .findFirst();
        if (optionalTreeItem.isPresent()) {
            optionalTreeItem.get().setExpanded(true);
        }
    }
    
    public void onActionCreateNewTerm() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Term]"); // NOI18N
        
        // TODO replace it with AnchorPane
        final TextInputDialog dialog = new TextInputDialog(); // NOI18N
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Create Term"); // NOI18N
        dialog.setResizable(false);
        dialog.setTitle("Simple Term Wizard"); // NOI18N
        
        final Optional<String> result = dialog.showAndWait();
        if (
                result.isPresent()
                && !result.get().isEmpty()
        ) {
            // Check if the [Term] always exists
            final ObservableList<Term> terms = SqlProvider.getDefault().findAllTerms();
            final String title = result.get();
            for (Term term : terms) {
                if (term.getTitle().equals(title)) {
                    return;
                }
            }
            
            // Create a new [Term]
            final Term term = ModelProvider.getDefault().getTerm(title);
            SqlProvider.getDefault().createTerm(term);
            
            // Update gui
            final int selectedIndex = cbNavigationTopics.getSelectionModel().getSelectedIndex();
            cbNavigationTopics.getSelectionModel().clearSelection();
            cbNavigationTopics.getSelectionModel().select(selectedIndex);
        }
    }
    
    public void onActionCreateNewTopic() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Topic]"); // NOI18N
        
        // TODO replace it with AnchorPane
        final TextInputDialog dialog = new TextInputDialog(); // NOI18N
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Create Topic"); // NOI18N
        dialog.setResizable(false);
        dialog.setTitle("Simple Topic Wizard"); // NOI18N
        
        final Optional<String> result = dialog.showAndWait();
        if (
                result.isPresent()
                && !result.get().isEmpty()
        ) {
            // Check if the [Topic] always exists
            final ObservableList<Topic> topics = SqlProvider.getDefault().findAllTopics();
            final String title = result.get();
            for (Topic topic : topics) {
                if (topic.getTitle().equals(title)) {
                    return;
                }
            }
            
            // Create a new [Topic]
            final Topic topic = ModelProvider.getDefault().getTopic(title);
            SqlProvider.getDefault().createTopic(topic);
            
            // Update gui
            topics.clear();
            topics.addAll(SqlProvider.getDefault().findAllTopics());
            this.onActionRefreshNavigationTabTopics(topics);
            this.onActionRefreshNavigationTabTerms(topics);
        }
    }
    
    /*
    TODO Navigation handling
    
    @FXML private Button bNavigationToHome;
    @FXML private Button bNavigationToNext;
    @FXML private Button bNavigationToPrevious;
    @FXML private Button bNavigationShowAll;
    
    bNavigationToHome
     - If [indexShownNavigationView] != 0
        -> activate button bNavigationToHome
        - else disable the button.
    
    bNavigationToNext
     - If [navigationViews.size()] > 0 && [indexShownNavigationView] < [navigationViews.size() - 1] 
        -> activate button bNavigationToNext
        - else disable the button.
    
    bNavigationToPrevious
     - If [navigationViews.size()] > 0 && [indexShownNavigationView] > 0
        -> activate button bNavigationToPrevious
        - else disable the button.
    
    bNavigationShowAll
     - If the size from [navigationViews] > 1
        -> activate button bNavigationShowAll
    */
    
    public void onActionNavigationToHome() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [Navigation] to [Home]"); // NOI18N
        
        /*
        TODO
         - show the [WelcomeView] (index == 0)
         - set [indexShownNavigationView] to 0
        */
    }
    
    public void onActionNavigationToNext() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [Navigation] to [Next]"); // NOI18N
        /*
        TODO
         - show the [XyView] ([indexShownNavigationView] + 1)
            - if ([indexShownNavigationView] + 1) >= [navigationViews.size()] throw error
         - set [indexShownNavigationView] to ([indexShownNavigationView] + 1)
        */
    }
    
    public void onActionNavigationToPrevious() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [Navigation] to [Previous]"); // NOI18N
        /*
        TODO
         - show the [XyView] ([indexShownNavigationView] - 1)
            - if ([indexShownNavigationView] - 1) < [0] throw error
         - set [indexShownNavigationView] to ([indexShownNavigationView] - 1)
        */
    }
    
    public void onActionNavigationShowAll() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action [Navigation] show all"); // NOI18N
        
        /*
        TODO
         - show little popup with all [NameFromView] in the order they was added.
            - use [navigationViews]
         - User click on one open the [XyView]
         - set [indexShownNavigationView] to user click index
        */
    }

    private void onActionOpenExercise(Exercise exercise) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action open Exercise"); // NOI18N
        
        vbWorkingArea.getChildren().clear();
        
        // Was the [Exercise] previously open?
        int index = 0;
        for (Navigation navigation : navigationViews) {
            final Object object = navigation.getView();
            if (object instanceof ExerciseView) {
                final ExerciseView exerciseView = (ExerciseView) object;
                final ExercisePresenter exercisePresenter = exerciseView.getRealPresenter();
                if (Objects.equals(exercisePresenter.getId(), exercise.getId())) {
                    indexShownNavigationView = index;
                    LoggerFacade.getDefault().debug(this.getClass(), "Show [ExerciseView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
                    
                    final Parent parent = exerciseView.getView();
                    VBox.setVgrow(parent, Priority.ALWAYS);
                    vbWorkingArea.getChildren().add(parent);
                    return;
                }
            }
            
            ++index;
        }
        
        // Generate new ExerciseView
        final Navigation<ExerciseView> navigation = new Navigation<>();
        final ExerciseView exerciseView = new ExerciseView();
        final ExercisePresenter exercisePresenter = exerciseView.getRealPresenter();
        exercisePresenter.configure(exercise);
        navigation.setView(exerciseView);
        navigationViews.add(navigation);
        indexShownNavigationView = navigationViews.size() - 1;
        LoggerFacade.getDefault().debug(this.getClass(), "Add [ExerciseView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
        final Parent parent = exerciseView.getView();
        VBox.setVgrow(parent, Priority.ALWAYS);
        vbWorkingArea.getChildren().add(parent);
    }
    
    private void onActionOpenTerm(Term term) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show [Term]"); // NOI18N
        LoggerFacade.getDefault().debug(this.getClass(), "  # " + term.toString());

        vbWorkingArea.getChildren().clear();
        
        // Was the [Term] previously open?
        int index = 0;
        for (Navigation navigation : navigationViews) {
            final Object object = navigation.getView();
            if (object instanceof TermView) {
                final TermView termView = (TermView) object;
                final TermPresenter termPresenter = termView.getRealPresenter();
                if (Objects.equals(termPresenter.getId(), term.getId())) {
                    indexShownNavigationView = index;
                    LoggerFacade.getDefault().debug(this.getClass(), "Show [TermView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
                    final Parent parent = termView.getView();
                    VBox.setVgrow(parent, Priority.ALWAYS);
                    vbWorkingArea.getChildren().add(parent);
                    return;
                }
            }
            
            ++index;
        }
        
        // Generate new TermView
        final Navigation<TermView> navigation = new Navigation<>();
        final TermView termView = new TermView();
        final TermPresenter termPresenter = termView.getRealPresenter();
        termPresenter.configure(term);
        navigation.setView(termView);
        navigationViews.add(navigation);
        indexShownNavigationView = navigationViews.size() - 1;
        LoggerFacade.getDefault().debug(this.getClass(), "Add [TermView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
        final Parent parent = termView.getView();
        VBox.setVgrow(parent, Priority.ALWAYS);
        vbWorkingArea.getChildren().add(parent);
    }
    
    private void onActionOpenTopic(Topic topic) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action open [Topic]"); // NOI18N
        LoggerFacade.getDefault().debug(this.getClass(), "  # " + topic.toString());
        
        vbWorkingArea.getChildren().clear();
        
        // Was the [Term] previously open?
        int index = 0;
        for (Navigation navigation : navigationViews) {
            final Object object = navigation.getView();
            if (object instanceof TopicView) {
                final TopicView topicView = (TopicView) object;
                final TopicPresenter topicPresenter = topicView.getRealPresenter();
                if (Objects.equals(topicPresenter.getId(), topic.getId())) {
                    indexShownNavigationView = index;
                    LoggerFacade.getDefault().debug(this.getClass(), "Show [TopicView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
                    final Parent parent = topicView.getView();
                    VBox.setVgrow(parent, Priority.ALWAYS);
                    vbWorkingArea.getChildren().add(parent);
                    return;
                }
            }
            
            ++index;
        }
        
        // Generate new TermView
        final Navigation<TopicView> navigation = new Navigation<>();
        final TopicView topicView = new TopicView();
        final TopicPresenter topicPresenter = topicView.getRealPresenter();
        topicPresenter.configure(topic);
        navigation.setView(topicView);
        navigationViews.add(navigation);
        indexShownNavigationView = navigationViews.size() - 1;
        LoggerFacade.getDefault().debug(this.getClass(), "Add [TopicView (index=" + indexShownNavigationView + ")]"); // NOI18N
        
        final Parent parent = topicView.getView();
        VBox.setVgrow(parent, Priority.ALWAYS);
        vbWorkingArea.getChildren().add(parent);
    }
    
    private void onActionRefreshNavigationTabTerms(ObservableList<Topic> observableListTopics) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation] [Term]s"); // NOI18N

        // Reload the [ComboBox]
        // TODO need selected object / have a look if always exists
//        final boolean isAnyIndexSelected = !cbNavigationTopics.getSelectionModel().isEmpty();
//        final int selectedIndex = cbNavigationTopics.getSelectionModel().getSelectedIndex();
        cbNavigationTopics.getItems().clear();
        
        final Topic topicShowAllExistingTerms = ModelProvider.getDefault().getTopic(
                DEFAULT_ID__TOPIC__SHOW_ALL_EXISTING_TERMS,
                "=== Show all existing Terms ==="); // NOI18N
        observableListTopics.add(0, topicShowAllExistingTerms);
        
        final Topic topicShowAllTermsWithoutExercise = ModelProvider.getDefault().getTopic(
                DEFAULT_ID__TOPIC__SHOW_ALL_TERMS_WITHOUT_PARENT,
                "=== Show all Terms without Parent ==="); // NOI18N
        observableListTopics.add(1, topicShowAllTermsWithoutExercise);
        
        cbNavigationTopics.getItems().addAll(observableListTopics);
        cbNavigationTopics.setDisable(observableListTopics.size() <= 1);
        
//        if (isAnyIndexSelected) {
//            // How to avoid the [Selection Event]?
//            cbNavigationTopics.getSelectionModel().select(selectedIndex);
//        }
    }

    private void onActionRefreshNavigationTabTermsWithSelection() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation] tab [Terms] with selection"); // NOI18N
        
        final int selectedIndex = cbNavigationTopics.getSelectionModel().getSelectedIndex();
        
        final ObservableList<Topic> observableListTopics = SqlProvider.getDefault().findAllTopics();
//        this.onActionRefreshNavigationTabTopics(observableListTopics);
        this.onActionRefreshNavigationTabTerms(observableListTopics);
        
        cbNavigationTopics.getSelectionModel().clearSelection();
        cbNavigationTopics.getSelectionModel().select(selectedIndex);
    }
    
    private void onActionRefreshNavigationTabTopics() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation] [Topics]"); // NOI18N
        
        final ObservableList<Topic> observableListTopics = SqlProvider.getDefault().findAllTopics();
        this.onActionRefreshNavigationTabTopics(observableListTopics);
    }

    private void onActionRefreshNavigationTabTopics(ObservableList<Topic> observableListTopics) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation] [Topics] with list"); // NOI18N
        
        rootItem.getChildren().clear();
        
        observableListTopics.forEach(topic -> {
            LoggerFacade.getDefault().debug(this.getClass(), "  # " + topic.toString());
            
            final ObservableList<Exercise> observableListExercises = SqlProvider.getDefault().findAllExercisesWithTopicId(topic.getId());
            topic.setExercises(observableListExercises.size());
            
            final TreeItem<Object> treeItemTopic = new TreeItem<>(topic);
            observableListExercises.forEach(exercise -> {
                LoggerFacade.getDefault().debug(this.getClass(), "  # " + exercise.toString());
            
                final TreeItem<Object> treeItemExercise = new TreeItem<>(exercise);
                treeItemTopic.getChildren().add(treeItemExercise);
            });
            
            rootItem.getChildren().add(treeItemTopic);
        });
        
        tvNavigationTopics.setRoot(rootItem);
    }
    
    public void onActionShowTermsFromSelectedTopic() {
        // Is any [Topic] in the [ComboBox] selected?
        if (cbNavigationTopics.getSelectionModel().isEmpty()) {
            lInfoFoundedTerms.setText(this.getInfoFoundedTerms(0));
            return;
        }
        
        LoggerFacade.getDefault().debug(this.getClass(), "On action show [Terms]s from selected [Topic]"); // NOI18N

        // Catch which [Term] should be loaded
        final ObservableList<Term> terms = FXCollections.observableArrayList();
        final Topic topic = cbNavigationTopics.getSelectionModel().getSelectedItem();
        
        final long topicId = topic.getId();
        if (Objects.equals(topicId, DEFAULT_ID__TOPIC__SHOW_ALL_EXISTING_TERMS)) {
            terms.addAll(SqlProvider.getDefault().findAllTerms());
        }
        else if (Objects.equals(topicId, DEFAULT_ID__TOPIC__SHOW_ALL_TERMS_WITHOUT_PARENT)) {
            terms.addAll(SqlProvider.getDefault().findAllTermsInExerciseTermWithoutParent());
        }
        else {
            terms.addAll(SqlProvider.getDefault().findAllTermsWithTopicId(topicId));
        }
        
        // Show them in the gui
        lInfoFoundedTerms.setText(this.getInfoFoundedTerms(terms.size()));
        lvNavigationTerms.getItems().clear();
        lvNavigationTerms.getItems().addAll(terms);
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in [ApplicationPresenter]"); // NOI18N
        
        this.registerOnActionCreateNewExercise();
        
        this.registerOnActionOpenExercise();
        this.registerOnActionOpenTerm();
        this.registerOnActionOpenTopic();
        
        this.registerOnActionRefreshNavigationTabTermsWithSelection();
        this.registerOnActionRefreshNavigationTabTopics();
    }
    
    private void registerOnActionCreateNewExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action create new [Exercise]"); // NOI18N
        
        ActionFacade.getDefault().register(
                ACTION__APPLICATION__CREATE_NEW_EXERCISE,
                (ActionEvent event) -> {
                    final TransferData transferData = (TransferData) event.getSource();
                    final Topic topic = (Topic) transferData.getObject();
                    this.onActionCreateNewExercise(topic);
                });
    }
    
    private void registerOnActionOpenExercise() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action open [Exercise]"); // NOI18N
        
        ActionFacade.getDefault().register(
                ACTION__APPLICATION__OPEN_EXERCISE,
                (ActionEvent event) -> {
                    final TransferData transferData = (TransferData) event.getSource();
                    final Exercise exercise = (Exercise) transferData.getObject();
                    this.onActionOpenExercise(exercise);
                });
    }
    
    private void registerOnActionOpenTerm() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action open [Term]"); // NOI18N
        
        ActionFacade.getDefault().register(
                ACTION__APPLICATION__OPEN_TERM,
                (ActionEvent event) -> {
                    final TransferData transferData = (TransferData) event.getSource();
                    final Term term = (Term) transferData.getObject();
                    this.onActionOpenTerm(term);
                    // TODO select tab terms, select index from the topic in the combobox
                });
    }
    
    private void registerOnActionOpenTopic() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action open [Topic]"); // NOI18N
        
        ActionFacade.getDefault().register(
                ACTION__APPLICATION__OPEN_TOPIC,
                (ActionEvent event) -> {
                    final TransferData transferData = (TransferData) event.getSource();
                    final Topic topic = (Topic) transferData.getObject();
                    this.onActionOpenTopic(topic);
                });
    }

    private void registerOnActionRefreshNavigationTabTermsWithSelection() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action refresh [Navigation] tab [Terms] with selection"); // NOI18N
        
        ActionFacade.getDefault().register(
                ACTION__APPLICATION__REFRESH_NAVIGATION_TAB_TERMS_WITH_SELECTION,
                (ActionEvent event) -> {
                    this.onActionRefreshNavigationTabTermsWithSelection();
                });
    }
    
    private void registerOnActionRefreshNavigationTabTopics() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action refresh [Navigation] tab [Topics]"); // NOI18N
        
        ActionFacade.getDefault().register(
                ACTION__APPLICATION__REFRESH_NAVIGATION_TAB_TOPICS,
                (ActionEvent event) -> {
                    this.onActionRefreshNavigationTabTopics();
                });
    }
    
}
