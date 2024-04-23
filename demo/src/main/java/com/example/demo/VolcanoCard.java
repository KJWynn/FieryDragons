package com.example.demo;


public class VolcanoCard {
    private int segmentID;
    private int ringID;
    private AnimalType animal;
    private boolean occupied;
    public VolcanoCard(int newSegmentID, int newRingID, AnimalType animalType){
        segmentID =newSegmentID;
        ringID = newRingID;
        animal = animalType;

    }

    public boolean getOccupiedStatus(){
        return occupied;
    }

    public int getRingID(){
        return ringID;
    }

    public AnimalType getAnimal(){
        return animal;
    }

    public void flipOccupiedStatus(){
        occupied = !occupied;
    }
}
