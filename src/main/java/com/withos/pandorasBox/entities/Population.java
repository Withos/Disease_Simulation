package com.withos.pandorasBox.entities;

import com.withos.pandorasBox.states.HealthyState;
import com.withos.pandorasBox.states.ISick;
import com.withos.pandorasBox.states.ImmuneState;
import com.withos.pandorasBox.states.SymptomsState;
import com.withos.pandorasBox.vectors.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Population {

    private List<Person> people;
    private Box box;

    public Population(){
        this.people = new ArrayList<Person>();
    }

    public Population(Box box) {
        this.people = new ArrayList<Person>();
        this.box = box;
    }

    public Population(Box box, int x, boolean immunity) {
        this.people = new ArrayList<Person>();
        this.box = box;
        this.startPopulation(x, immunity);
    }

    public void addPerson(Person person) {
        this.people.add(person);
        this.people.indexOf(person);
    }

    public void removePerson(Person person) {
        this.people.remove(person);
    }

    public  List<Person> getPeople() {
        return this.people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void spread() {
        List<Person> sick = new ArrayList<>();

        for (Person person : this.people) {
            if (person.getState() instanceof ISick) {
                sick.add(person);
            }
        }

        for (Person person : this.people) {
            if (person.getState() instanceof HealthyState) {
                boolean access = false;
                for (Person sickPerson : sick) {
                    if (person.distanceTo(sickPerson) <= 45.0) {
                        access = true;
                        if (sickPerson.getState() instanceof SymptomsState)
                            ((HealthyState) person.getState()).setSymptPerson(true);
                        ((HealthyState) person.getState()).encounter(person);
                        break;
                    }
                }
                if (!access) {
                    ((HealthyState) person.getState()).setTimer(0);
                    ((HealthyState) person.getState()).setSymptPerson(false);
                }
            }
        }

    }

    public void populationRecovery(){
        for (Person person : this.people) {
            if (person.getState() instanceof ISick) {
                ((ISick)person.getState()).recovery(person);
            }
        }
    }

    public void newPeople(int x){
        int i;
        double chance;
        for(i=0;i<x;i++) {
            chance = Math.random();
            if (chance < 0.25) //top
                this.addPerson(new Person(Math.random() * this.box.getWidth(), (double) box.getHeight()-20));
            else if(chance < 0.5)//right
                this.addPerson(new Person((double) this.box.getWidth()-20, Math.random() * box.getHeight()));
            else if(chance < 0.75)//bottom
                this.addPerson(new Person(Math.random() * this.box.getWidth(), 20.));
            else//left
                this.addPerson(new Person(20., Math.random() * box.getHeight()));
        }
    }

    public void startPopulation(int x, boolean immunity){
        if(immunity) {
            for (int i = 0; i < x; i++)
                if (Math.random() < 0.1)
                    this.addPerson(new Person(Math.random() * this.box.getWidth(), (double) box.getHeight() * Math.random(), new ImmuneState()));
                else
                    this.addPerson(new Person(Math.random() * this.box.getWidth(), (double) box.getHeight() * Math.random(), new HealthyState()));
        }
        else{
            for (int i = 0; i < x; i++)
                this.addPerson(new Person(Math.random() * this.box.getWidth(), (double) box.getHeight() * Math.random(), new HealthyState()));
        }

    }

    public void move(Vector2D vector, Person person){
        Double[] comp = vector.getComponents();
        double newX = person.getX()+comp[0], newY=person.getY()+comp[1];
        if(newX >= this.box.getWidth()-10 || newX <= 10) {
            if (Math.random()<0.5)
            {
                this.removePerson(person);
            }
            vector.setX(-comp[0]);
        }
        if(newY >= this.box.getHeight()-10 || newY <= 10) {
            if (Math.random()<0.5)
            {
                this.removePerson(person);
            }
            vector.setY(-comp[1]);
        }
        person.setX(person.getX()+vector.getX());
        person.setY(person.getY()+vector.getY());
    }

    public void moveAll(Map<Person, Vector2D> moves){
        for (var entry : moves.entrySet()){
            this.move(entry.getValue(), entry.getKey());
        }
    }


    public Map<Person, Vector2D> moveRandom() {
        double dist = 1, mult = 10;
        Map<Person,Vector2D> moves = new HashMap();
        Vector2D vector;
        for(Person person : this.people){
        do {
            vector = new Vector2D((Math.random() - 0.5) / 5 * mult, (Math.random() - 0.5) / 5 * mult);
            dist = vector.abs();
        } while (dist > 0.1 * mult);
        moves.put(person, new Vector2D(vector));
        }
        return moves;
    }
}
