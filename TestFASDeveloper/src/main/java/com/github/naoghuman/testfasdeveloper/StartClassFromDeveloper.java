package com.github.naoghuman.testfasdeveloper;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.testfascore.StartClassFromProjectSampler;
import com.github.naoghuman.testfascore.scanner.ProjectSampleScanner;
import com.github.naoghuman.testfascore.test.Project1;
import com.github.naoghuman.testfascore.test.Sample1_1;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PRo
 */
public class StartClassFromDeveloper extends Application {
    
    public static void main(String[] args) {
        System.out.println("--##--: 1");
        launch(args);
        System.out.println("--##--: 2");
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("--##--: 3");
        
        final List<String> projectWhiteAndBlackList = FXCollections.observableArrayList();
        projectWhiteAndBlackList.addAll(this.initProjectBlackList());
        projectWhiteAndBlackList.addAll(this.initProjectWhiteList());
        
        final List<String> sampleWhiteAndBlackList = FXCollections.observableArrayList();
        sampleWhiteAndBlackList.addAll(this.initSampleBlackList());
        sampleWhiteAndBlackList.addAll(this.initSampleWhiteList());
        
        StartClassFromProjectSampler.getDefault().init(projectWhiteAndBlackList, sampleWhiteAndBlackList);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("--##--: 4");
        
        StartClassFromProjectSampler.getDefault().start(primaryStage);
    }
    
    private List<String> initProjectBlackList() {
        LoggerFacade.getDefault().info(this.getClass(), "#initProjectBlackList(): List<String>"); // NOI18N
        
        final List<String> projectBlackList = FXCollections.observableArrayList();
        
        return projectBlackList;
    }
    
    private List<String> initProjectWhiteList() {
        LoggerFacade.getDefault().info(this.getClass(), "#initProjectWhiteList(): List<String>"); // NOI18N
        
        final List<String> projectWhiteList = FXCollections.observableArrayList();
        projectWhiteList.add(Project1.class.getName());
        
        return projectWhiteList;
    }
    
    private List<String> initSampleBlackList() {
        LoggerFacade.getDefault().info(this.getClass(), "#initSampleBlackList(): List<String>"); // NOI18N
        
        final List<String> sampleBlackList = FXCollections.observableArrayList();
        
        return sampleBlackList;
    }
    
    private List<String> initSampleWhiteList() {
        LoggerFacade.getDefault().info(this.getClass(), "#initSampleWhiteList(): List<String>"); // NOI18N
        
        final List<String> sampleWhiteList = FXCollections.observableArrayList();
        sampleWhiteList.add(Sample1_1.class.getName());
        
        return sampleWhiteList;
    }
    
}
