/*
 * Copyright (C) 2015 PRo
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
package com.github.naoghuman.lib.map.generator.mapwizard;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.resources.api.ResourcesFacade;
import com.github.naoghuman.lib.map.generator.mapwizard.api.IMapWizardConfiguration;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author PRo
 */
public class MapWizardPresenter implements Initializable, IMapWizardConfiguration {

    @FXML private Button bCreate;
    @FXML private ComboBox cbMap;
    @FXML private ImageView ivMap;
    @FXML private TextArea taErrorMsg;
    @FXML private TextField tfName;
    
    private BooleanBinding disableBinding = null;
    private BooleanBinding nameBinding = null;
    private BooleanBinding selectedBinding = null;
    private BooleanProperty imagesFoundProperty = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO PRO map-wizard tweak it over css
        LoggerFacade.INSTANCE.info(this.getClass(), "Initialize MapWizardPresenter"); // NOI18N
        
        assert (bCreate != null)    : "fx:id=\"bCreate\" was not injected: check your FXML file 'MapWizard.fxml'."; // NOI18N
        assert (cbMap != null)      : "fx:id=\"cbMap\" was not injected: check your FXML file 'MapWizard.fxml'."; // NOI18N
        assert (ivMap != null)      : "fx:id=\"ivMap\" was not injected: check your FXML file 'MapWizard.fxml'."; // NOI18N
        assert (taErrorMsg != null) : "fx:id=\"taErrorMsg\" was not injected: check your FXML file 'MapWizard.fxml'."; // NOI18N
        assert (tfName != null)     : "fx:id=\"tfName\" was not injected: check your FXML file 'MapWizard.fxml'."; // NOI18N
        
        this.initializeProperties();
        this.initializeTextArea();
        
        this.loadMapImages();
    }
    
    private void initializeProperties() {
        imagesFoundProperty = new SimpleBooleanProperty(Boolean.FALSE);
        nameBinding = tfName.textProperty().isEmpty();
        selectedBinding = cbMap.getSelectionModel().selectedIndexProperty().isEqualTo(-1);
        
        disableBinding = new BooleanBinding() {
            {
                super.bind(imagesFoundProperty, nameBinding, selectedBinding);
            }

            @Override
            protected boolean computeValue() {
                if (imagesFoundProperty.not().getValue()) {
                    return true;
                }
                
                return nameBinding.getValue() || selectedBinding.getValue();
            }
        };
        
        bCreate.disableProperty().bind(disableBinding);
    }
    
    private void initializeTextArea() {
        taErrorMsg.setVisible(Boolean.FALSE);
        taErrorMsg.setManaged(Boolean.FALSE);
    }
    
    private void deactivateWizardComponents() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Deactivate wizard components"); // NOI18N
        
        cbMap.setDisable(Boolean.TRUE);
        ivMap.setVisible(Boolean.FALSE);
        ivMap.setManaged(Boolean.FALSE);
        tfName.setDisable(Boolean.TRUE);
        
        taErrorMsg.setVisible(Boolean.TRUE);
        taErrorMsg.setManaged(Boolean.TRUE);
        taErrorMsg.setText("No images found..."); // NOI18N
    }

    private void loadMapImages() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Load Map images"); // NOI18N
        
        final File folder = new File(PATH_TO_FOLDER__RESOURCES_IMAGES_MAP__WITH_USER_DIR);
        if (!folder.exists()) {
            this.deactivateWizardComponents();
            return;
        }
        
        final FilenameFilter filter = (File dir, String name) ->
                name.endsWith(".jpg") // NOI18N
                || name.endsWith(".jpeg") // NOI18N
                || name.endsWith(".png") // NOI18N
                || name.endsWith(".gif"); // NOI18N
        final File[] images = folder.listFiles(filter);
        final ObservableList<String> names = FXCollections.observableArrayList();
        for (File image : images) {
            names.add(image.getName());
        }
        
        if (names.isEmpty()) {
            this.deactivateWizardComponents();
            return;
        }
        
        imagesFoundProperty.setValue(Boolean.TRUE);
        FXCollections.sort(names);
        cbMap.getItems().addAll(names);
        cbMap.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) ->
        {
            this.showPreview(String.valueOf(newValue));
        });
    }
    
    private void showPreview(String imageName) {
        LoggerFacade.INSTANCE.debug(this.getClass(), "Show preview: " + imageName); // NOI18N
        
        final Image image = ResourcesFacade.INSTANCE.loadWallpaper(
                PATH_TO_FOLDER__RESOURCES_IMAGES_MAP__WITH_FILE + imageName);
        ivMap.setImage(image);
    }
    
    public void onActionCancel() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action Cancel"); // NOI18N
        
        ActionFacade.INSTANCE.handle(ACTION__HIDE__MAP_WIZARD);
    }
    
    public void onActionCreate() {
        LoggerFacade.INSTANCE.debug(this.getClass(), "On action Create"); // NOI18N
        
        final TransferData transferData = new TransferData();
        transferData.setActionId(ACTION__CREATE__MAP_WIZARD);
        
        // TODO PRO catch data from new map
        Object dummyNewMap = new Object();
        transferData.setObject(dummyNewMap);
        
        ActionFacade.INSTANCE.handle(transferData);
    }
    
}
