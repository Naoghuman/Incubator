/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.naoghuman.testfascore.annotation;

/**
 *
 * @author PRo
 */
public enum SampleType {
    
    NORMAL,
    OVERVIEW;
    
    public static boolean isNormal(SampleType sampleType) {
        return sampleType.equals(NORMAL);
    }
    
}
