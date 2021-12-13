package com.withos.pandorasBox.states;

import com.withos.pandorasBox.entities.Person;

public interface ISick extends IState {



    public void recovery(Person person);
    public int getTimer();
    public void setTimer(int timer);

}
