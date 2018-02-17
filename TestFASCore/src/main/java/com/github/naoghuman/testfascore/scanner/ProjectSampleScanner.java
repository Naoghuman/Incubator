/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.naoghuman.testfascore.scanner;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.testfascore.annotation.Project;
import com.github.naoghuman.testfascore.annotation.Sample;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void scan(List<String> projectWhiteAndBlackList, List<String> sampleWhiteAndBlackList) {
        LoggerFacade.getDefault().debug(this.getClass(), "#scan(): void"); // NOI18N
        
        LoggerFacade.getDefault().debug(this.getClass(), "Found following projects:"); // NOI18N
        final List<Class<?>> projects = this.scanFor(Project.class, projectWhiteAndBlackList.toArray(new String[] {}));
        projects.stream()
                .forEach(project -> {
                    LoggerFacade.getDefault().debug(this.getClass(), " -> " + project.getName()); // NOI18N
                });
        
        LoggerFacade.getDefault().debug(this.getClass(), "Found following samples:"); // NOI18N
        final List<Class<?>> samples = this.scanFor(Sample.class, sampleWhiteAndBlackList.toArray(new String[] {}));
        samples.stream()
                .forEach(sample -> {
                    LoggerFacade.getDefault().debug(this.getClass(), " -> " + sample.getName()); // NOI18N
                });
        
    }
    
    private List<Class<?>> scanFor(Class annotationClass, String[] scanWhiteAndBlackList) {
        LoggerFacade.getDefault().debug(this.getClass(), "#scanFor(Class, String[]): List<Class<?>>"); // NOI18N
        
        final List<Class<?>> annotationClasses = FXCollections.observableArrayList();
        
        try {
            Constructor constructor = FastClasspathScanner.class.getConstructor(String[].class);
            FastClasspathScanner fastClasspathScanner = (FastClasspathScanner) constructor.newInstance(new Object[] {scanWhiteAndBlackList});
            fastClasspathScanner
//                    .verbose()
                    .matchClassesWithAnnotation(annotationClass, annotationClasses::add)
                    .scan();
        } catch (
                IllegalAccessException
                | IllegalArgumentException
                | InstantiationException
                | NoSuchMethodException
                | SecurityException
                | InvocationTargetException ex
        ) {
            Logger.getLogger(ProjectSampleScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annotationClasses;
    }
    
}
