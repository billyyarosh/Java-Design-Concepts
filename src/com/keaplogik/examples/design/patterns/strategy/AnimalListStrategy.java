package com.keaplogik.examples.design.patterns.strategy;

import com.keaplogik.examples.model.animals.Animal;
import java.util.Comparator;

/**
 * Holds concrete strategies for working with animal lists.
 * @keaplogik
 */
public enum AnimalListStrategy {
    
    INSTANCE;
    
    private AnimalListStrategy(){}

    public final Comparator vertebrayStrategyFunc = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            Animal firstAnimal = (Animal) t;
            Animal secondAnimal = (Animal) t1;

            if (firstAnimal.isVertebrate()
                    == secondAnimal.isVertebrate()) {
                return 0;
            } else if (firstAnimal.isVertebrate()
                    || secondAnimal.isVertebrate()) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    public final Comparator speciesStrategyFunc = new Comparator() {

        @Override
        public int compare(Object t, Object t1) {
            Animal firstAnimal = (Animal) t;
            Animal secondAnimal = (Animal) t1;

            //In this case we can use the inherited Comparable.compareTo
            //method within the String class to ensure proper ascending order
            return firstAnimal.getSpecies().
                    compareTo(secondAnimal.getSpecies());
        }
    };
}
