package com.withos.pandorasBox.entities;

import com.withos.pandorasBox.caretaker.Caretaker;
import com.withos.pandorasBox.Vectors.Vector2D;
import com.withos.pandorasBox.xmlparser.XMLHandle;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class Simulation {

    private final Box box;
    private  BufferedImage image;
    private final Display window;
    private static Population population = null;
    private final static Caretaker caretaker = new Caretaker();
    private final static XMLHandle xml = new XMLHandle();

    public Simulation(int width, int height, boolean immunity){
        this.box = new Box(width, height, immunity);
        population = box.getPopulation();
        this.window = new Display(width, height);
    }

    public Simulation(int width, int height, String path){
        population = new Population();
        new XMLHandle().fromXML(path);
        this.box = new Box(width, height, population);
        box.setPopulation(population);
        population.setBox(box);
        this.window = new Display(width, height);

    }

    public static Population getPeople() {
        return population;
    }

    public void startSimulation() {
        int i, j, h = 2;


        List<Person> people = population.getPeople();
        Map<Person, Vector2D> moves;

        while (true) {
            if (h % 4 == 0)
                population.newPeople(20);

            population.spread();
            moves = population.moveRandom();

            for (i = 0; i < 25; i++) {

                window.displayPopulation(population);
                for (j = 0; j < people.size(); j++) {
                    Person person = people.get(j);
                }
                population.moveAll(moves);
                try {
                    Thread.sleep(40);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            population.populationRecovery();
            h++;
        }
    }

    public static void saveSimulation(){
        caretaker.addMemento(population);
        xml.saveXML(caretaker.getMemento(caretaker.getMementos().size() - 1));
    }
}
