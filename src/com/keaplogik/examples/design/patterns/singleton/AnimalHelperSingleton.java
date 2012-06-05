package com.keaplogik.examples.design.patterns.singleton;

import com.keaplogik.examples.model.animals.Animal;
import com.keaplogik.examples.model.animals.impl.SimpleAnimal;
import java.awt.Color;

/**
 * Example of a Java Singleton.
 * It is suggested to use an enum as a singleton. The Class
 * cannot be instantiated more then once, specifically when
 * using reflection.
 * 
 * @author keaplogik
 */
public enum AnimalHelperSingleton {
    
    INSTANCE;
    
    private AnimalHelperSingleton(){
        
    }
    
    public Animal[] buildAnimalList(){
        final Animal[] animals = new Animal[10];

        animals[0] = new SimpleAnimal(Animal.AnimalClass.MAMMAL, 
                "Dog", true, Color.GRAY);
        animals[1] = new SimpleAnimal(Animal.AnimalClass.MAMMAL, 
                "Cat", true, Color.YELLOW);
        animals[2] = new SimpleAnimal(Animal.AnimalClass.AMPHIBIAN,
                "Frog", true, Color.GREEN);
        animals[3] = new SimpleAnimal(Animal.AnimalClass.BIRD,
                "Crow", true, Color.BLACK);
        animals[4] = new SimpleAnimal(Animal.AnimalClass.BIRD,
                "Cardinal", true, Color.RED);
        animals[5] = new SimpleAnimal(Animal.AnimalClass.ARTHROPOD,
                "Mantis", false, Color.GREEN);
        animals[6] = new SimpleAnimal(Animal.AnimalClass.ARTHROPOD,
                "Spider", false, Color.ORANGE);
        animals[7] = new SimpleAnimal(Animal.AnimalClass.MAMMAL, 
                "Tiger", true, Color.ORANGE);
        animals[8] = new SimpleAnimal(Animal.AnimalClass.MAMMAL, 
                "Bear", true, Color.BLACK);
        animals[9] = new SimpleAnimal(Animal.AnimalClass.BIRD, 
                "Owl", true, Color.BLACK);
                
        return animals;
    }
    
}
