package player;

import units.Building;
import units.Character;

import java.util.ArrayList;

public class Player {
    private static ArrayList <Building> buildings = new ArrayList<Building>();
    private static ArrayList <Character> characters = new ArrayList<Character>();

    public static void addBuilding(Building b){
        buildings.add(b);
    }

    public static void addCharacter(Character c){
        characters.add(c);
    }

    public static void removeBuilding(int i){
        buildings.remove(i);
    }

    public static void removeCharacter(int i){
        characters.remove(i);
    }

    public static void removeBuilding(Building b){
        buildings.remove(b);
    }

    public static void removeCharacter(Character c){
        characters.remove(c);
    }

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }

    public static void setBuildings(ArrayList<Building> buildings) {
        Player.buildings = buildings;
    }

    public static ArrayList<Character> getCharacters() {
        return characters;
    }

    public static void setCharacters(ArrayList<Character> characters) {
        Player.characters = characters;
    }
}
