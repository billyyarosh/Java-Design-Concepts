package com.keaplogik.examples.model.animals.impl;

import com.keaplogik.examples.model.animals.Animal;
import java.awt.Color;

/**
 * A direct implementation of an Animal. This animal
 * is simple, because it does not extend out
 * nay further then the implemented interface <code>Animal</code>
 * @author keaplogik
 */
public class SimpleAnimal implements Animal {

    private final boolean vertebrate;
    private final String species;
    private final Color color;
    private final AnimalClass animalClass;
    
    public SimpleAnimal(){
       throw new AssertionError("Do not implement generic constructor on SimpleAnimal"); 
    }

    public SimpleAnimal(final AnimalClass animalClass, final String species,
            final boolean vertebrate, final Color color) {
        this.species = species;
        this.color = color;
        this.vertebrate = vertebrate;
        this.animalClass = animalClass;
    }

    @Override
    public boolean isVertebrate() {
        return vertebrate;
    }

    @Override
    public String getSpecies() {
        return species;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public AnimalClass getAnimalClass() {
        return animalClass;
    }
    
    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Animal: {\n");
        strBuilder.append("\tClass: ")
                .append(this.getAnimalClass()).append("\n");
        strBuilder.append("\tSpecies: ")
                .append(species).append("\n");
        strBuilder.append("\tVertebrate: ")
                .append(vertebrate).append("\n");
        strBuilder.append("\tColor: ")
                .append(color.toString()).append("\n");
        strBuilder.append("\t};");
        
        return strBuilder.toString();
    }
}
