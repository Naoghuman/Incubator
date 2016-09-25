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
package com.github.naoghuman.lib.tag.demo.application;

import com.github.naoghuman.lib.action.api.ActionFacade;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.action.api.TransferData;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.tag.demo.configuration.IActionConfiguration;
import com.github.naoghuman.lib.tag.demo.view.ELibTagType;
import com.github.naoghuman.lib.tag.demo.view.description.DescriptionPresenter;
import com.github.naoghuman.lib.tag.demo.view.description.DescriptionView;
import com.github.naoghuman.lib.tag.demo.view.libtag.LibTagView;
import com.github.naoghuman.lib.tag.demo.view.libtagcomponents.LibTagComponentsView;
import com.github.naoghuman.lib.tag.demo.view.libtagcore.LibTagCoreView;
import com.github.naoghuman.lib.tag.demo.view.libtagfontawesomefx.LibTagFontAwesomeFXView;
import com.github.naoghuman.lib.tag.demo.view.libtagikonli.LibTagIkonliView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {

    @FXML private Tab tLibTag;
    @FXML private Tab tLibTagComponents;
    @FXML private Tab tLibTagCore;
    @FXML private Tab tLibTagFontAwesomeFX;
    @FXML private Tab tLibTagIkonli;
    @FXML private TabPane tpLibTag;
    
    @FXML private VBox vbDescription;
    @FXML private VBox vbLibTag;
    @FXML private VBox vbLibTagComponents;
    @FXML private VBox vbLibTagCore;
    @FXML private VBox vbLibTagFontAwesomeFX;
    @FXML private VBox vbLibTagIkonli;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeLibTag();
        this.initializeLibTagComponents();
        this.initializeLibTagCore();
        this.initializeLibTagFontAwesomeFX();
        this.initializeLibTagIkonli();
        this.initializeDescription();
        
        this.registerActions();
    }

    private void initializeDescription() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Description"); // NOI18N
        
        final DescriptionView view = new DescriptionView();
        final DescriptionPresenter presenter = view.getRealPresenter();
        presenter.registerActions();
        
        vbDescription.getChildren().add(view.getView());
        VBox.setVgrow(view.getView(), Priority.ALWAYS);
        
        final TransferData data = new TransferData();
        data.setActionId(IActionConfiguration.ON_ACTION__SHOW_DESCRIPTION);
        data.setObject(ELibTagType.LIB_TAG);
        
        ActionFacade.getDefault().handle(data);
    }
    
    private void initializeLibTag() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTag"); // NOI18N
        
        tpLibTag.getSelectionModel()
                .selectedItemProperty()
                .addListener((ov, oldTab, newTab) -> {
        
                    final TransferData data = new TransferData();
                    data.setActionId(IActionConfiguration.ON_ACTION__SHOW_DESCRIPTION);
                    
                    ELibTagType libTagType = (ELibTagType) newTab.getUserData();
                    data.setObject(libTagType);

                    ActionFacade.getDefault().handle(data);
                });
        
        tLibTag.setUserData(ELibTagType.LIB_TAG);
        
        final LibTagView view = new LibTagView();
        vbLibTag.getChildren().add(view.getView());
        VBox.setVgrow(view.getView(), Priority.ALWAYS);
    }

    private void initializeLibTagComponents() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTagComponents"); // NOI18N
        
        tLibTagComponents.setUserData(ELibTagType.LIB_TAG_COMPONENTS);
        
        final LibTagComponentsView view = new LibTagComponentsView();
        vbLibTagComponents.getChildren().add(view.getView());
        VBox.setVgrow(view.getView(), Priority.ALWAYS);
    }

    private void initializeLibTagCore() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTagCore"); // NOI18N
        
        tLibTagCore.setUserData(ELibTagType.LIB_TAG_CORE);
        
        final LibTagCoreView view = new LibTagCoreView();
        vbLibTagCore.getChildren().add(view.getView());
        VBox.setVgrow(view.getView(), Priority.ALWAYS);
    }

    private void initializeLibTagFontAwesomeFX() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTagFontAwesomeFX"); // NOI18N
        
        tLibTagFontAwesomeFX.setUserData(ELibTagType.LIB_TAG_FONTAWESOMEFX);
        
        final LibTagFontAwesomeFXView view = new LibTagFontAwesomeFXView();
        vbLibTagFontAwesomeFX.getChildren().add(view.getView());
        VBox.setVgrow(view.getView(), Priority.ALWAYS);
    }

    private void initializeLibTagIkonli() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize LibTagIkonli"); // NOI18N
        
        tLibTagIkonli.setUserData(ELibTagType.LIB_TAG_IKONLI);
        
        final LibTagIkonliView view = new LibTagIkonliView();
        vbLibTagIkonli.getChildren().add(view.getView());
        VBox.setVgrow(view.getView(), Priority.ALWAYS);
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
