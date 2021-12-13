package com.withos.pandorasBox.states;

import com.withos.pandorasBox.entities.Person;

public class NoSymptomsState implements ISick{

    int timer = 0;

    @Override
    public int getTimer() {
        return timer;
    }

    @Override
    public void setTimer(int timer) {
        this.timer=timer;
    }

    @Override
    public void recovery(Person person) {
        if(this.timer >= 20){
            if(Math.random()>0.5 || timer>=30){
                person.setState(new ImmuneState());
            }
        }
        this.timer++;
    }
}
