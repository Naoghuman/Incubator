/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.naoghuman.testfascore.scanner;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.testfascore.annotation.Project;
import com.github.naoghuman.testfascore.test.Project1;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.ClassAnnotationMatchProcessor;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;

/**
 *
 * @author PRo
 */
public final class ProjectSampleScanner {
    
    private static final Optional<ProjectSampleScanner> INSTANCE = Optional.of(new ProjectSampleScanner());

    /**
     * Returns a singleton instance from the class {@code ProjectScanner}.
     * 
     * @return a singleton instance from the class {@code ProjectScanner}.
     */
    public static final ProjectSampleScanner getDefault() {
        return INSTANCE.get();
    }

    private ProjectSampleScanner() {
        
    }

    public void scan() {
        LoggerFacade.getDefault().debug(this.getClass(), "#scan(): void"); // NOI18N
        
        LoggerFacade.getDefault().debug(this.getClass(), "Found following projects:"); // NOI18N
        final List<Class<?>> projects = this.scanForProjects();
        projects.stream()
                .forEach(project -> {
                    LoggerFacade.getDefault().debug(this.getClass(), " -> " + project.getName()); // NOI18N
                });
        
    }
    
    private List<Class<?>> scanForProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "#scanForProjects(): List<Class<?>>"); // NOI18N
        
        final List<Class<?>> projects = FXCollections.observableArrayList();
        new FastClasspathScanner(Project1.class.getPackage().getName())
                .verbose()
                .matchClassesWithAnnotation(Project.class, projects::add)
//                .strictWhitelist()
                .scan();

        return projects;
    }
    
}
