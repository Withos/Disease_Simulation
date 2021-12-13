package com.withos.pandorasBox.caretaker;

import com.withos.pandorasBox.entities.Person;
import com.withos.pandorasBox.entities.Population;

import java.util.ArrayList;
import java.util.List;

public class Memento {

    private List<Person> population;

    public Memento(Population population) {
        this.population = new ArrayList<>(population.getPeople());
    }

    public List<Person> getPopulation() {
        return population;
    }
}
