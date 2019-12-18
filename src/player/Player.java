package player;

import units.Building;
import units.Character;

import java.util.ArrayList;

public class Player {
    private static ArrayList<String> selectedUnit;
    private static String username;
    private static ArrayList <ArrayList<String>> buildings = new ArrayList<>();
    private static ArrayList <ArrayList<String>> characters = new ArrayList<>();
    private static ArrayList <String[]> attacks = new ArrayList<>();
    private static boolean ready = false, gameRunning = false, messageSent= true;
    public static boolean lol = false; //test

    public static boolean isMessageSent() {
        return messageSent;
    }

    public static void setMessageSent(boolean messageSent) {
        Player.messageSent = messageSent;
    }

    public static boolean isGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean gameRunning) {
        Player.gameRunning = gameRunning;
    }

    public static boolean isReady() {
        return ready;
    }

    public static void setReady(boolean ready) {
        Player.ready = ready;
        messageSent = false;
    }

    public static ArrayList<String[]> getAttacks() {
        return attacks;
    }

    public static void setAttacks(ArrayList<String[]> attacks) {
        Player.attacks = attacks;
    }

    public static void addBuilding(ArrayList<String> b){
        buildings.add(b);
    }

    public static void addCharacter(ArrayList<String> c){
        characters.add(c);
    }

    public static void removeBuilding(int i){
        buildings.remove(i);
    }

    public static void removeCharacter(int i){
        characters.remove(i);
    }

    public static void removeBuilding(ArrayList<String> b){
        buildings.remove(b);
    }

    public static void removeCharacter(ArrayList<String> c){
        characters.remove(c);
    }

    public static void selectUnit(ArrayList<String> unit){
        selectedUnit = unit;
    }

    public static boolean unitIsSelected(){
        if (selectedUnit != null && (selectedUnit.get(0).equals("building") || selectedUnit.get(0).equals("character"))){
            return true;
        } else {
            return false;
        }
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Player.username = username;
    }

    public static ArrayList<String> getSelectedUnit() {
        return selectedUnit;
    }

    public static void setSelectedUnit(ArrayList<String> selectedUnit) {
        Player.selectedUnit = selectedUnit;
    }

    public static ArrayList<ArrayList<String>> getBuildings() {
        return buildings;
    }

    public static void setBuildings(ArrayList<ArrayList<String>> buildings) {
        Player.buildings = buildings;
    }

    public static ArrayList<ArrayList<String>> getCharacters() {
        return characters;
    }

    public static void setCharacters(ArrayList<ArrayList<String>> characters) {
        Player.characters = characters;
    }
}