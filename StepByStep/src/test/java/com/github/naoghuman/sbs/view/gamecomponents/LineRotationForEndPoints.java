/*
 * Copyright (C) 2016 PRo
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
package com.github.naoghuman.sbs.view.gamecomponents;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author PRo
 */
public class LineRotationForEndPoints extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane ap = new AnchorPane();
        final Scene scene = new Scene(ap, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Line l = new Line();
        l.setFill(Color.AQUAMARINE);
//        l.setLayoutX(300);
//        l.setLayoutY(300);
        l.setStartX(300);
        l.setStartY(300);
        l.setEndX(300);
        l.setEndY(100);
        ap.getChildren().add(l);
        System.out.println("1: " + l.toString() + " tx=" + l.getTranslateX() + ", ty="+l.getTranslateY());
        
//        l.setRotate(45);
//        System.out.println("2: " + l.toString() + " tx=" + l.getTranslateX() + ", ty="+l.getTranslateY());
        // 300, 200
//        l.getTransforms().add(new Rotate(72, 300, 300));
        l.getTransforms().add(new Rotate(144, 300, 300));
//        l.getTransforms().add(new Rotate(216, 300, 300));
//        l.getTransforms().add(new Rotate(288, 300, 300));
        System.out.println("3: " + l.toString() + " tx=" + l.getBoundsInParent().toString() + ", ty="+l.getTranslateY());
        
        /*
            1=centerx,                      centery - 200
            2=centerx + 192.47137451171875, centery - 64.0634765625
            3=centerx + 119.953857421875,   centery + 164.2001953125
            4=centerx - 119.953857421875,   centery + 164.2001953125
            5=centerx - 192.47137451171875, centery - 64.0634765625
        */
    }
    
    
}
