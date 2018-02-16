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
public @interface Project {
    
    public String  name();
    public String  description() default "[undefined]"; // NOI18N
    public int     projectNr()   default -1;
    public String  projectURL()  default "[undefined]"; // NOI18N
    public String  version()     default "[undefined]"; // NOI18N
    public boolean visible()     default true;
    
}
