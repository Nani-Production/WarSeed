package data;

import units.Building;
import units.Character;

import java.util.ArrayList;

public class Data { //Enthält alle Daten vom Spielgeschehen
    private ArrayList <ArrayList<String>> listofLists = new ArrayList <ArrayList<String>>(); //x, y, name ??;

    public ArrayList<ArrayList<String>> getListofLists() {
        return listofLists;
    }

    public void setListofLists(ArrayList<ArrayList<String>> listofLists) {
        this.listofLists = listofLists;
    }

    public void addData(Building b){
        ArrayList <String> newData = new ArrayList<>();
        newData.add(b.getOwner());
        newData.add(b.getType());
        newData.add(Double.toString(b.getHp()));
        newData.add(Double.toString(b.getX()));
        newData.add(Double.toString(b.getY()));
        System.out.println("Building Data Add"+newData.size());
        listofLists.add(newData);
    }

    public void addData(Character c){
        ArrayList <String> newData = new ArrayList<>();
        newData.add(c.getOwner());
        newData.add(c.getType());
        newData.add(c.getName());
        newData.add(Double.toString(c.getHp()));
        newData.add(Double.toString(c.getX()));
        newData.add(Double.toString(c.getY()));
        System.out.println("Character Data Add"+newData.size());
        listofLists.add(newData);
    }
}