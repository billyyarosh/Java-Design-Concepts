package com.keaplogik.examples.model.animals;

import java.awt.Color;

public interface Animal {
    
    public enum AnimalClass {
        MAMMAL,
        BIRD,
        FISH,
        REPTILE,
        AMPHIBIAN,
        ARTHROPOD;
        private AnimalClass(){}
    }

    boolean isVertebrate();

    String getSpecies();
    
    Color getColor();
    
    AnimalClass getAnimalClass();
}
