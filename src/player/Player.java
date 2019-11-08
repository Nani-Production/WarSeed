package player;

import units.Building;
import units.Character;

import java.util.ArrayList;

public class Player {
    private ArrayList <Building> buildings = new ArrayList<Building>();
    private ArrayList <Character> characters = new ArrayList<Character>();

    public void addBuilding(Building b){
        buildings.add(b);
    }

    public void addCharacter(Character c){
        characters.add(c);
    }

    public void removeBuilding(int i){
        buildings.remove(i);
    }

    public void removeCharacter(int i){
        characters.remove(i);
    }

    public void removeBuilding(Building b){
        buildings.remove(b);
    }

    public void removeCharacter(Character c){
        characters.remove(c);
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
