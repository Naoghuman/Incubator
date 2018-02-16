/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.naoghuman.testfascore.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Naoghuman
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Sample {
    
    public String     cssURL()        default "[undefined]"; // NOI18N
    public String     description()   default "[undefined]"; // NOI18N
    public String     javaDocURL()    default "[undefined]"; // NOI18N
    public String     overviewURL()   default "[undefined]"; // NOI18N
    public String     name();
    public Project    project();
    public int        sampleNr()      default -1;
    public SampleType sampleType()    default SampleType.NORMAL;
    public String     sourceCodeURL() default "[undefined]"; // NOI18N
    public boolean    visible()       default true;
    
}
