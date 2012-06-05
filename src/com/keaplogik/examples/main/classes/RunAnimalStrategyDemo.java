package com.keaplogik.examples.main.classes;

import com.keaplogik.examples.design.patterns.singleton.AnimalHelperSingleton;
import com.keaplogik.examples.design.patterns.strategy.AnimalListStrategy;
import com.keaplogik.examples.model.animals.Animal;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * Run a demo for supplying different strategies to sort a list
 * of animals.
 * 
 * @author keaplogik
 */
public class RunAnimalStrategyDemo {

    public static void main(String[] args) {
        
        //Call singleton to build the animal list.
        Animal[] animals = AnimalHelperSingleton.INSTANCE.buildAnimalList();
        
        System.out.println("\tGROUP Animals by same class type");
        System.out.println("----------------------------------------------");

        /**
         * This is an example of implementing strategy to sort 
         * Animals by their animal class. Order doesn't matter, but we want to 
         * ensure that animals are grouped by their animal class property.
         */
        Arrays.sort(animals, new Comparator() {
            public int compare(Object t, Object t1) {
                Animal firstAnimal = (Animal) t;
                Animal secondAnimal = (Animal) t1;
                return firstAnimal.getAnimalClass().hashCode() - 
                        secondAnimal.getAnimalClass().hashCode();
            }
        });

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }

        System.out.println("----------------------------------------------");
        System.out.println("\tSORT Alphabetically (Ascending) By Species");
        System.out.println("----------------------------------------------");

        //CONCRETE STRATEGY Species sort
        Arrays.sort(animals, AnimalListStrategy.INSTANCE.speciesStrategyFunc);

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
        
        System.out.println("----------------------------------------------");
        System.out.println("\tGROUP by Vertebrate");
        System.out.println("----------------------------------------------");

        //CONCRETE STRATEGY Vertebray sort
        Arrays.sort(animals, AnimalListStrategy.INSTANCE.vertebrayStrategyFunc);

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }
}
