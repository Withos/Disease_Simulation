package com.withos.pandorasBox.entities;

import com.withos.pandorasBox.states.HealthyState;
import com.withos.pandorasBox.states.IState;

public class Person {

    private Double x;
    private Double y;
    private IState state;

    public Person(Double x, Double y, IState state){
        this.x=x;
        this.y=y;
        this.state = state;
    }

    public Person(Double x, Double y){
        this.x=x;
        this.y=y;
        this.setState(new HealthyState());
        if (Math.random()<0.1)
            ((HealthyState)this.getState()).contagion(this);
    }

    public Double[] getXY(){
        return new Double[]{this.x, this.y};
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setXY(Double x, Double y){
        this.setX(x);
        this.setY(y);
    }

    public IState getState(){
        return this.state;
    }

    public void setState(IState state){
        this.state = state;
    }

    public Double distanceTo(Person person){
        return Math.sqrt((this.x - person.getX())*(this.x-person.getX()) + (this.y- person.getY())*(this.y- person.getY()));
    }
}
