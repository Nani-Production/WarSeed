package data;

import player.Player;

import java.util.ArrayList;

public class Data { //Enthält alle Daten vom Spielgeschehen
    private static ArrayList <ArrayList<String>> listofLists = new ArrayList <ArrayList<String>>(); //x, y, name ??;
    private static ArrayList <ArrayList<String>> projectiles = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getListofLists() {
        return listofLists;
    }

    public static void setListofLists(ArrayList<ArrayList<String>> listofLists) {
        Data.listofLists = listofLists;
    }

    public static void addData(String line){
        //Buildings
        int index = 0;
        index = line.indexOf("#");
        index += 3;

        int startIndex = 0;
        int i = 0;
        while ( startIndex < line.indexOf("//characters")) {
            if ((startIndex = line.indexOf("*", startIndex+1)) != -1){
                i++;
            } else {
                startIndex = line.indexOf("//characters");
            }
        }
        if (i > 0){
            i--;
        }
        String substring [] = new String [i];

        startIndex = 0;
        for (int j = 0; j < i; j++){
            startIndex = line.indexOf("*", index+1);
            substring [j] = line.substring(index, startIndex);
            index = startIndex+3;
        }

        for (int k = 0; k < substring.length; k++){
            int index1 = 0, index2 = 0;
            String owner, type, hp, name, x, y, canAttack;
            owner = substring[k].substring(0, (index1 = substring[k].indexOf("+++")));
            index2 = substring[k].indexOf("+++", index1+1);
            index1 += 3;
            type = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            hp = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            name = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            x = substring[k].substring(index1, index2);
            y = substring[k].substring(index2+3);

            ArrayList<String> list = new ArrayList<>();
            list.add("building");
            list.add(owner);
            list.add(type);
            list.add(hp);
            list.add(name);
            list.add(x);
            list.add(y);

            if (owner == Player.getUsername()){
                for (int j = 0; j < Player.getBuildings().size(); j++){
                    if (Player.getBuildings().get(j).get(4) == name && Player.getBuildings().get(j).get(4) != null){
                        //Player.getBuildings().set(j, new Building(Double.parseDouble(x), Double.parseDouble(y), 64, 64, Double.parseDouble(hp), type, owner));
                        Player.getBuildings().set(j, list);
                    } else if (Player.getBuildings().get(j).get(4) == null){
                        //TODO Lösung finden
                        if (Player.getBuildings().get(j).get(2) == type && Player.getBuildings().get(j).get(5) == x && Player.getBuildings().get(j).get(6) == y){
                            Player.getBuildings().set(j, list);
                        }
                    }
                }
            }

            if (!doubling(list)){
                if (!actualising(list)){
                    listofLists.add(list);
                }
            }
        }

        //Character
        index = line.indexOf("#", index+1);
        index += 4;
        startIndex = index+1;

        i = 0;
        while (startIndex < line.indexOf("//projectiles") && startIndex > index) {
            startIndex = line.indexOf("*", startIndex+1);
            if (startIndex != -1){
                i++;
            } else {
                startIndex = line.indexOf("//projectiles");
            }
        }
        substring = new String [i];

        for (int j = 0; j < i; j++){
            startIndex = line.indexOf("*", index+1);
            substring [j] = line.substring(index, startIndex);
            index = startIndex+4;
        }

        for (int k = 0; k < substring.length; k++){
            int index1 = 0, index2 = 0;
            String owner, type, hp, x, y, name, moveX, moveY, angle, canAttack;
            owner = substring[k].substring(0, (index1 = substring[k].indexOf("+++")));
            index2 = substring[k].indexOf("+++", index1+1);
            index1 += 3;
            type = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            hp = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            name = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            x = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            y = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            moveX = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            moveY = substring[k].substring(index1, index2);
            index1 = index2+3;
            index2 = substring[k].indexOf("+++", index1+1);
            angle = substring[k].substring(index1, index2);
            canAttack = substring[k].substring(index2+3);

            ArrayList<String> list = new ArrayList<>();
            list.add("character");
            list.add(owner);
            list.add(type);
            list.add(hp);
            list.add(name);
            list.add(x);
            list.add(y);
            list.add(moveX);
            list.add(moveY);
            list.add(angle);
            list.add(canAttack);

            if (owner == Player.getUsername()){
                for (int j = 0; j < Player.getCharacters().size(); j++){
                    if (Player.getCharacters().get(j).get(4) == name && Player.getCharacters().get(j).get(4) != null){
                        Player.getCharacters().set(j, list);
                    } else if (Player.getCharacters().get(j).get(4) == null){
                        //TODO Lösung finden
                        if (Player.getCharacters().get(j).get(2) == type && Player.getCharacters().get(j).get(5) == x && Player.getCharacters().get(j).get(6) == y){
                            Player.getCharacters().set(j, list);
                        }
                    }
                }
            }

            if (!doubling(list)){
                if (!actualising(list)){
                    listofLists.add(list);
                }
            }
        }

        //projectile
        index = line.indexOf("#", index + 1);
        index += 4;
        startIndex = index + 1;

        i = 0;
        while (startIndex < line.indexOf("//end") && startIndex > index) {
            if ((startIndex = line.indexOf("*", startIndex + 1)) != -1) {
                i++;
            }
        }
        substring = new String[i];

        for (int j = 0; j < i; j++) {
            startIndex = line.indexOf("*", index + 1);
            substring[j] = line.substring(index, startIndex);
            index = startIndex + 4;
        }

        for (int k = 0; k < substring.length; k++) {
            int index1 = 0, index2 = 0;
            String owner, name, nowX, nowY, moveX, moveY, arrived;
            owner = substring[k].substring(0, (index1 = substring[k].indexOf("+++")));
            index2 = substring[k].indexOf("+++", index1 + 1);
            index1 += 3;
            name = substring[k].substring(index1, index2);
            index2 = substring[k].indexOf("+++", index1 + 1);
            index1 += 3;
            nowX = substring[k].substring(index1, index2);
            index2 = substring[k].indexOf("+++", index1 + 1);
            index1 += 3;
            nowY = substring[k].substring(index1, index2);
            index1 = index2 + 3;
            index2 = substring[k].indexOf("+++", index1 + 1);
            moveX = substring[k].substring(index1, index2);
            index1 = index2 + 3;
            index2 = substring[k].indexOf("+++", index1 + 1);
            moveY = substring[k].substring(index1, index2);
            arrived = substring[k].substring(index2 + 3);

            ArrayList<String> list = new ArrayList<>();
            list.add(owner);
            list.add(name);
            list.add(nowX);
            list.add(nowY);
            list.add(moveX);
            list.add(moveY);
            list.add(arrived);

            boolean doubling = false, actualising = false;
            for (int j = 0; j < listofLists.size(); j++) {
                if (projectiles.get(j).equals(list)) {
                    doubling = true;
                }
                if (!doubling && projectiles.get(j).get(0) == owner && projectiles.get(j).get(1) == name){
                    list.set(2, nowX);
                    list.set(3, nowY);
                    list.set(4, moveX);
                    list.set(5, moveY);
                    list.set(6, arrived);
                    actualising = true;
                }
            }
            if (!doubling && !actualising){
                projectiles.add(list);
            }
        }

        for (int j = 0; j < Player.getCharacters().size(); j++){
            if (Player.getCharacters().get(j).get(4) == null){
                System.out.println("scheisse der character ist null: "+j);
            }
        }
        for (int j = 0; j < Player.getBuildings().size(); j++){
            if (Player.getBuildings().get(j).get(4) == null){
                System.out.println("scheisse dat building ist null: "+j);
            }
        }
    }

    private static boolean doubling (ArrayList<String> list) {
        boolean doubling = false;
        for (int i = 0; i < listofLists.size(); i++){
            if (listofLists.get(i).equals(list)){
                doubling = true;
            }
        }
        return doubling;
    }

    private static boolean actualising (ArrayList<String> list){
        boolean actualising = false;
        for (int i = 0; i < listofLists.size(); i++){
            if (listofLists.get(i).get(1).equals(list.get(1)) && listofLists.get(i).get(2).equals(list.get(2)) && !listofLists.get(i).equals(list) && listofLists.get(i).size() == list.size()){
                if (listofLists.get(i).get(0).equals("character") && listofLists.get(i).get(4).equals(list.get(4))){
                    listofLists.get(i).set(3, list.get(3));
                    listofLists.get(i).set(5, list.get(5));
                    listofLists.get(i).set(6, list.get(6));
                    listofLists.get(i).set(7, list.get(7));
                    listofLists.get(i).set(8, list.get(8));
                    actualising = true;
                } else if (listofLists.get(i).get(0).equals("building") && listofLists.get(i).get(4).equals(list.get(4))){
                    listofLists.get(i).set(3, list.get(3));
                    listofLists.get(i).set(5, list.get(5));
                    listofLists.get(i).set(6, list.get(6));
                    actualising = true;
                }
            }
        }
        return actualising;
    }

    public static ArrayList<ArrayList<String>> getProjectiles() {
        return projectiles;
    }

    public static void setProjectiles(ArrayList<ArrayList<String>> projectiles) {
        Data.projectiles = projectiles;
    }
}