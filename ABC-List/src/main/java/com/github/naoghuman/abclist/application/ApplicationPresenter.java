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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private Button bNavigationCreateNewTopic;
    @FXML private Button bNavigationCreateNewWord;
    @FXML private Button bNavigationToHome;
    @FXML private Button bNavigationToNext;
    @FXML private Button bNavigationToPrevious;
    @FXML private Button bNavigationShowAll;
    @FXML private SplitPane spApplication;
    @FXML private TabPane tpNavigation;
    @FXML private TreeView<Object> tvAbcList;
    @FXML private VBox vbExercises;
    
    private final ObservableList<Navigation> navigationViews = FXCollections.observableArrayList();
    private final TreeItem<Object> rootItem = new TreeItem<> ();
    
    private int indexShownNavigationView = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        // TODO init Navigation (tabpane, buttons) - hide bcreatenewword - if switch tab then
        this.initializeNavigation();
        this.initializeWelcomeView();

        this.registerActions();
        
        this.onActionRefreshTreeView();
    }
    
    private void initializeNavigation() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize [Navigation]"); // NOI18N

        // Buttons
        bNavigationCreateNewTopic.managedProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isEqualTo(0));
        bNavigationCreateNewTopic.visibleProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isEqualTo(0));
        
        bNavigationCreateNewWord.managedProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isNotEqualTo(0));
        bNavigationCreateNewWord.visibleProperty().bind(tpNavigation.getSelectionModel().selectedIndexProperty().isNotEqualTo(0));

        // TreeView
        tvAbcList.setCellFactory((TreeView<Object> p) -> new AbcListTreeCell());
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
        vbExercises.getChildren().add(parent);
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
        
        // Show the new exercise in the TreeView
        this.onActionRefreshTreeView();
        
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
            
            // Show it
            this.onActionRefreshTreeView();
        }
    }
    
    public void onActionCreateNewWord() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create new [Word]"); // NOI18N
        
        
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
        
        vbExercises.getChildren().clear();
        
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
                    vbExercises.getChildren().add(parent);
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
        vbExercises.getChildren().add(parent);
    }

    private void onActionRefreshTreeView() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh TreeView"); // NOI18N
        
        rootItem.getChildren().clear();
        
        final ObservableList<Topic> observableListTopics = SqlProvider.getDefault().findAllTopics();
        observableListTopics.forEach(topic -> {
            final ObservableList<Exercise> observableListExercises = SqlProvider.getDefault().findAllExercisesWithParentId(topic.getId());
            final TreeItem<Object> treeItemTopic = new TreeItem<>(topic);
            observableListExercises.forEach(exercise -> {
                final TreeItem<Object> treeItemExercise = new TreeItem<>(exercise);
                treeItemTopic.getChildren().add(treeItemExercise);
            });
            
            rootItem.getChildren().add(treeItemTopic);
        });
        
        tvAbcList.setRoot(rootItem);
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
