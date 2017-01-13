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
package com.github.naoghuman.abclist.application;

import com.github.naoghuman.abclist.configuration.IApplicationConfiguration;
import com.github.naoghuman.abclist.exercise.ExercisePresenter;
import com.github.naoghuman.abclist.exercise.ExerciseView;
import com.github.naoghuman.abclist.model.Exercise;
import com.github.naoghuman.abclist.model.ModelProvider;
import com.github.naoghuman.abclist.model.Topic;
import com.github.naoghuman.abclist.sql.SqlProvider;
import com.github.naoghuman.abclist.welcome.WelcomeView;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
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
public class ApplicationPresenter implements Initializable, IApplicationConfiguration, IRegisterActions {
    
    @FXML private Button bNavigationCreateNewTopic;
    @FXML private Button bNavigationCreateNewTerm;
    @FXML private Button bNavigationToHome;
    @FXML private Button bNavigationToNext;
    @FXML private Button bNavigationToPrevious;
    @FXML private Button bNavigationShowAll;
    @FXML private ComboBox<Topic> cbNavigationTopics;
    @FXML private ListView lvNavigationTerms;
    @FXML private SplitPane spApplication;
    @FXML private TabPane tpNavigation;
    @FXML private TreeView<Object> tvNavigationTopics;
    @FXML private VBox vbEditorArea;
    
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
        tvNavigationTopics.setCellFactory((TreeView<Object> p) -> new AbcListTreeCell());
    }
    
    private void initializeNavigationTabTerms() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [Navigation] [Term]s"); // NOI18N

        // ComboBox
        final Tooltip tooltip = new Tooltip("Show all Terms from the selected Topic"); // NOI18N
        cbNavigationTopics.setTooltip(tooltip);
        
        final Callback cellFactory = (Callback<ListView<Topic>, ListCell<Topic>>) (ListView<Topic> listView) -> new ListCell<Topic>() {
            @Override
            protected void updateItem(Topic topic, boolean empty) {
                super.updateItem(topic, empty);
                if (topic == null || empty) {
                    this.setGraphic(null);
                    this.setText(null);
                } else {
                    this.setText(topic.getTitle());
                }
            }
        };

        cbNavigationTopics.setButtonCell((ListCell) cellFactory.call(null));
        cbNavigationTopics.setCellFactory(cellFactory);
        
        // ListView
        
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
        vbEditorArea.getChildren().add(parent);
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [ApplicationPresenter] after window is showing"); // NOI18N
    
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in [ApplicationPresenter]"); // NOI18N
    }
    
    private void onActionCreateNewExercise(Topic topic) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Exercise]"); // NOI18N
        
        // Create a new Exercise
        final Exercise exercise = ModelProvider.getDefault().getExercise(topic.getId());
        SqlProvider.getDefault().createOrUpdate(exercise);
        
        // Open the new exercise
        this.onActionOpenExercise(exercise);
            
        // Update gui
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
    
    public void onActionCreateNewTopic() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Topic]"); // NOI18N
        
        // TODO replace it with AnchorPane
        final TextInputDialog dialog = new TextInputDialog(); // NOI18N
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Create Topic"); // NOI18N
        dialog.setResizable(false);
        dialog.setTitle("Topic Wizard"); // NOI18N
        
        final Optional<String> result = dialog.showAndWait();
        if (
                result.isPresent()
                && !result.get().isEmpty()
        ) {
            // Create a new Topic
            final Topic topic = ModelProvider.getDefault().getTopic(result.get());
            SqlProvider.getDefault().createOrUpdate(topic);
            
            // Update gui
            final ObservableList<Topic> observableListTopics = SqlProvider.getDefault().findAllTopics();
            this.onActionRefreshNavigationTabTopics(observableListTopics);
            this.onActionRefreshNavigationTabTerms(observableListTopics);
        }
    }
    
    public void onActionCreateNewTerm() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Term]"); // NOI18N
        
        /*
        TODO
         - show dialog
         - load all terms 
            - if title from term exists -> warning
         - user can type title
         - user can type description...
         - user can choose topic or empty
        */
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
        
        vbEditorArea.getChildren().clear();
        
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
                    vbEditorArea.getChildren().add(parent);
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
        vbEditorArea.getChildren().add(parent);
    }
    
    private void onActionRefreshNavigationTabTerms(ObservableList<Topic> observableListTopics) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation] [Term]s"); // NOI18N

        // Reload the [ComboBox]
        // TODO need selected object / have a look if always exists
//        final boolean isAnyIndexSelected = !cbNavigationTopics.getSelectionModel().isEmpty();
//        final int selectedIndex = cbNavigationTopics.getSelectionModel().getSelectedIndex();
        cbNavigationTopics.getItems().clear();
        
        final Topic topic = ModelProvider.getDefault().getTopic("-- Show all existing Terms --"); // NOI18N
        observableListTopics.add(0, topic);
        
        cbNavigationTopics.getItems().addAll(observableListTopics);
        
//        if (isAnyIndexSelected) {
//            // How to avoid the [Selection Event]?
//            cbNavigationTopics.getSelectionModel().select(selectedIndex);
//        }
    }

    private void onActionRefreshNavigationTabTopics(ObservableList<Topic> observableListTopics) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation] [Topic]s"); // NOI18N
        
        rootItem.getChildren().clear();
        
        observableListTopics.forEach(topic -> {
            final ObservableList<Exercise> observableListExercises = SqlProvider.getDefault().findAllExercisesWithParentId(topic.getId());
            final TreeItem<Object> treeItemTopic = new TreeItem<>(topic);
            observableListExercises.forEach(exercise -> {
                final TreeItem<Object> treeItemExercise = new TreeItem<>(exercise);
                treeItemTopic.getChildren().add(treeItemExercise);
            });
            
            rootItem.getChildren().add(treeItemTopic);
        });
        
        tvNavigationTopics.setRoot(rootItem);
    }
    
    public void onActionShowTermsFromSelectedTopic() {
        // If any [Topic] in the [ComboBox] selected?
        if (cbNavigationTopics.getSelectionModel().isEmpty()) {
            return;
        }
        
        LoggerFacade.getDefault().debug(this.getClass(), "On action show [Terms]s from selected [Topic]"); // NOI18N
        
        /*
        TODO
         - Catch selected [Topic] from the [ComboBox]
         - Load all [Word]s from the [Topic]
         - Show the loaded [Word]s in the [ListView]
         - Double click on a [Term] opens the [TermView] for information/editing
        */
    }
    
    private final class AbcListTreeCell extends TreeCell<Object> {
        
        private final ContextMenu contextMenu = new ContextMenu();
        private final MenuItem menuItem = new MenuItem();
        
        public AbcListTreeCell() {
            contextMenu.getItems().add(menuItem);
        }
        
        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);

            this.configureMenuItem(item);
            this.configureMouseClick(item);
            this.setContextMenu(!empty ? contextMenu : null);
            this.setGraphic(null);
            this.setText(!empty ? this.getDisplayText(item) : null);
        }
        
        private String getDisplayText(Object item) {
            if (item instanceof Exercise) {
                final Exercise exercise = (Exercise) item;
                return exercise.toString(); // TODO
            }
            
            if (item instanceof Topic) {
                final Topic topic = (Topic) item;
                return topic.getTitle();
            }
            
            return null;
        }
        
        private void configureMenuItem(Object item) {
            if (item instanceof Exercise) {
                menuItem.setText("Open exercise"); // NOI18N
                menuItem.setOnAction(value -> {
                    final Exercise exercise = (Exercise) item;
                    ApplicationPresenter.this.onActionOpenExercise(exercise);
                });
            }
            
            if (item instanceof Topic) {
                menuItem.setText("New exercise"); // NOI18N
                menuItem.setOnAction(value -> {
                    final Topic topic = (Topic) item;
                    ApplicationPresenter.this.onActionCreateNewExercise(topic);
                });
            }
        }
        
        private void configureMouseClick(Object item) {
            if (item instanceof Exercise) {
                this.setOnMouseClicked(value -> {
                    final int mouseClickCount = value.getClickCount();
                    if (mouseClickCount >= 2) {
                        final Exercise exercise = (Exercise) item;
                        ApplicationPresenter.this.onActionOpenExercise(exercise);
                    }
                });
            }
        }
        
    }
    
}
