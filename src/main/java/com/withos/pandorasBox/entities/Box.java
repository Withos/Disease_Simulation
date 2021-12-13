package com.withos.pandorasBox.entities;

public class Box {

    private final int height;
    private final int width;
    private Population population;

    public Box(int w, int h, boolean immunity){
        this.height = h;
        this.width = w;
        this.population = new Population(this, 150, immunity);
    }

    public Box(int w, int h, Population population){
        this.height = h;
        this.width = w;
        this.population = population;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }
}
