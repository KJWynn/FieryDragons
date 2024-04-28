package com.example.demo;

import javafx.scene.shape.Circle;

import java.util.HashMap;

/**
 * Used to adapt Circle into ChitCard by maintaining a mapping
 */
public class ChitCardAdapter {

    private  static final HashMap<Circle, ChitCard> viewControllerMapping = new HashMap<>();


    public static void addMapping(Circle viewComponent, ChitCard chitCard){
        viewControllerMapping.put(viewComponent, chitCard);
    }

    public static HashMap<Circle, ChitCard> getViewControllerMapping(){
        return viewControllerMapping;
    }
}
