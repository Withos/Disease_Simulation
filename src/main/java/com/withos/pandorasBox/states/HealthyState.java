package com.withos.pandorasBox.states;

import com.withos.pandorasBox.entities.Person;

public class HealthyState implements IState{

    private boolean symptPerson=false;
    private int timer=0;


    public void setSymptPerson(boolean symptPerson) {
        this.symptPerson = symptPerson;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public IState contagion(Person person) {
        if(Math.random()<0.5)
            person.setState(new SymptomsState());
        else
            person.setState(new NoSymptomsState());
        return person.getState();
    }

    public void encounter(Person person){
        if(this.timer>=3){
            if(this.symptPerson)
                this.contagion(person);
            else
                if(Math.random()>0.5)
                    this.contagion(person);

        }
        this.timer++;
    }
}
