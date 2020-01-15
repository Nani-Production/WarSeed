package draw;

import controls.Camera;
import data.Data;
import data.UnitDatabank;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import player.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Draw_Main {
    public static double frame1, frame2, frame3;
    private double mapwidth, mapheight;
    private final static Affine normalAngle = new Affine(new Rotate(0));
    private double conOpacity = 0.7;
    private boolean opacDarker = false;
    private ArrayList <ArrayList<String>> drawingList;

    public void draw(GraphicsContext g, Game_Gui gui) {
        try {
            drawingList = Data.getListofLists();
            mapwidth = gui.getWidth();
            mapheight = gui.getMapHeight();

            g.setTransform(normalAngle);
            //Menu
            if (Gamestate.state == Gamestate_e.menu) {
                drawMenu(g, gui);
            } else if (Gamestate.state == Gamestate_e.reconnect) {
                if (Gamestate.lastState == Gamestate_e.menu) {
                    drawMenu(g, gui);
                }
            }

            //Ingame
            if (Gamestate.state == Gamestate_e.ingame || Gamestate.state == Gamestate_e.pause) {
                drawIngame(g, gui);
            } else if (Gamestate.state == Gamestate_e.reconnect) {
                if (Gamestate.lastState == Gamestate_e.pause || Gamestate.lastState == Gamestate_e.ingame) {
                    drawIngame(g, gui);
                }
            }

            //Pause
            if (Gamestate.state == Gamestate_e.pause) {
                drawPauseMenu(g, gui);
            } else if (Gamestate.state == Gamestate_e.reconnect && Gamestate.lastState == Gamestate_e.pause) {
                drawPauseMenu(g, gui);
            }

            //ReconnectScreen
            if (Gamestate.state == Gamestate_e.reconnect) {
                drawReconnectScreen(g, gui);
            }

            g.transform(normalAngle);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void drawCharacter(GraphicsContext g, Image img, int i) {
        double x = Double.parseDouble(drawingList.get(i).get(5)), y = Double.parseDouble(drawingList.get(i).get(6)), angle = Double.parseDouble(drawingList.get(i).get(9));
        double moveX = -1, moveY = -1;
        /*
        try {
            if (!Data.getListofLists().get(i).get(7).equals("null")) {
                moveX = Double.parseDouble(Data.getListofLists().get(i).get(7));
            }
            if (!Data.getListofLists().get(i).get(8).equals("null")) {
                moveY = Double.parseDouble(Data.getListofLists().get(i).get(8));
            }
        } catch (NullPointerException ignore) {
        }
         */

        //double newCam[] = rotatePoint(Camera.getCamX(), Camera.getCamY(), 0, 0, 180-angle);
        //rotate die cam auch für die Abfrage, wo der character in der cam ist und ob er gedrawt werden soll ^^
        g.setTransform(normalAngle);
        if (x != moveX || y != moveY) {
            g.drawImage(img, x - Camera.getCamX(), y - Camera.getCamY(), 64, 64);
            g.setTransform(normalAngle);
        }
        /*
        g.setTransform(new Affine(new Rotate(angle, x+32, y+32)));
        g.setFill(Color.PURPLE);
        g.fillRect(x-Camera.getCamX(), y-Camera.getCamY(), 64, 64);
        */
        /*
        g.setStroke(Color.YELLOW);
        g.strokeRect(x-Camera.getCamX(), y-Camera.getCamY(), 64, 64);
        g.setStroke(Color.ORANGE);
        g.strokeRect(x-newCam[0], y-newCam[1], 64, 64);
         */
    }

    private double[] rotatePoint(double x, double y, double angle) {
        double[] ergebnis = new double[2];
        ergebnis[0] = (Math.cos(angle) * x + (-Math.sin(angle)) * y);
        ergebnis[1] = (Math.sin(angle) * x + Math.cos(angle) * y);
        return ergebnis;
    }

    private double[] rotatePoint(double x1, double y1, double x2, double y2, double angle) {
        //xy1 ist der Punkt der gedreht wird und xy2 der Drehmittelpunkt
        double[] ergebnis = new double[2];
        ergebnis[0] = (Math.cos(angle) * x1 + (-Math.sin(angle)) * y1);
        ergebnis[1] = (Math.sin(angle) * x1 + Math.cos(angle) * y1);
        ergebnis[0] += x2;
        ergebnis[1] += y2;
        return ergebnis;
    }
    /*
    //Drehmatrix bitches!!!
    double [][] lol2 = new double[2][2];
        lol2 [0][0] = Math.cos(angle);
        lol2 [0][1] = -Math.sin(angle);
        lol2 [1][0] = Math.sin(angle);
        lol2 [1][1] = Math.cos(angle);
        double ergebnis [] = new double[2];
        ergebnis [0] = (lol2[0][0]*x+lol2[0][1]*y);
        ergebnis [1] = (lol2[1][0]*x+lol2[1][1]*y);
        return ergebnis;
     */

    private void drawMenu(GraphicsContext g, Game_Gui gui) {
        g.drawImage(ImageLoader.menubg, 0, 0, gui.getWidth(), gui.getHeight());
        g.setFill(new Color(0, 0, 0, 0.4));
        g.fillRect(0, 0, gui.getWidth(), gui.getHeight());

        gui.getConnect().draw(g);
        gui.getReady().draw(g);
        gui.getIptf().draw(g);
        gui.getNametf().draw(g);

        g.setFill(new Color(0, 0, 0, 0.4));
        g.fillRect((gui.getWidth() * (2. / 5.)) - 10, 150 - 64 - 10, (gui.getWidth() * (1. / 5.)) + 20, 94);
        g.setFill(Color.GREEN);
        g.setStroke(Color.DARKGREEN);
        g.setFont(gui.getFontBig());
        g.fillText("WarSeed", (gui.getWidth() * (2. / 5.)), 150, (gui.getWidth() * (1. / 5.)));
        g.strokeText("WarSeed", (gui.getWidth() * (2. / 5.)), 150, (gui.getWidth() * (1. / 5.)));
    }

    private void drawIngame(GraphicsContext g, Game_Gui gui) {
//Content
        g.setTransform(normalAngle);
        g.drawImage(ImageLoader.map, Camera.getCamX(), Camera.getCamY(), gui.getWidth(), gui.getHeight(), 0, 0, gui.getWidth(), gui.getHeight());

        //drawMap
        int a = (int) Camera.getCamX() / 200, b = (int) Camera.getCamY() / 200, image = 0;
        for (int i = a; i < mapwidth / 200; i++) {
            for (int j = b; j < mapheight / 200; j++) {

                switch (image) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        }


        //drawUnits
        for (int i = 0; i < drawingList.size(); i++) {
            //test
            /*
            if (Integer.parseInt(drawingList.get(i).get(2)) == UnitDatabank.SPEEDER) {
                StringBuilder line = new StringBuilder();
                for (int j = 4; j < drawingList.get(i).size(); j++){
                    line.append(drawingList.get(i).get(j)+" +++ ");
                }
                writeFile(line.toString());
            }
             */
            //test
            double size = 64;
            Image unit = ImageLoader.image2;
            if (drawingList.get(i).get(0).equals("character")) {
                //g.setTransform(new Affine(new Rotate(Double.parseDouble(Player.getSelectedUnit().get(9)), Double.parseDouble(Player.getSelectedUnit().get(5)) + 32, Double.parseDouble(Player.getSelectedUnit().get(6)) + 32)));
                if (Integer.parseInt(drawingList.get(i).get(2)) == UnitDatabank.DAMAGEDEALER) {
                    unit = ImageLoader.damageDealer;
                } else if (Integer.parseInt(drawingList.get(i).get(2)) == UnitDatabank.SPEEDER) {
                    //System.out.println("lolig "+Data.getListofLists().get(i).get(4)+"   "+Data.getListofLists().get(i).get(5)+"   "+Data.getListofLists().get(i).get(6));
                    unit = ImageLoader.speeder;
                } else if (Integer.parseInt(drawingList.get(i).get(2)) == UnitDatabank.TANK) {
                    unit = ImageLoader.tank;
                } else if (Integer.parseInt(drawingList.get(i).get(2)) == UnitDatabank.SETTLER) {
                    unit = ImageLoader.settler;
                } else {
                    unit = ImageLoader.image;
                    size = 64;
                }
            } else if (drawingList.get(i).get(0).equals("building")){
                if (drawingList.get(i).get(2).equals(String.valueOf(UnitDatabank.NEXUS))){
                    size = 200;
                    unit = ImageLoader.base;
                } else if (drawingList.get(i).get(2).equals(String.valueOf(UnitDatabank.VILLAGE))){
                    size = 100;
                    unit = ImageLoader.village;
                } else {
                    size = 100;
                    unit = ImageLoader.image2;
                }
            }
            if (Double.parseDouble(drawingList.get(i).get(5)) + size > Camera.getCamX() && Double.parseDouble(drawingList.get(i).get(5))  <= Camera.getCamX() + gui.getWidth() && Double.parseDouble(drawingList.get(i).get(6)) + size > Camera.getCamY() && Double.parseDouble(drawingList.get(i).get(6)) <= Camera.getCamY() + gui.getHeight()) {
                    /*
                    double newCam[] = {0, 0};
                    if (Data.getListofLists().get(i).get(0) == "character"){
                            double angle = Double.parseDouble(Data.getListofLists().get(i).get(9));
                        double newCoord[] = rotatePoint(Double.parseDouble(Data.getListofLists().get(i).get(5)), Double.parseDouble(Data.getListofLists().get(i).get(6)), angle);
                        newCam = rotatePoint(Camera.getCamX(), Camera.getCamY(), angle);
                    }
                     */
                //if (Double.parseDouble(Data.getListofLists().get(i).get(5)) > newCam[0] && Double.parseDouble(Data.getListofLists().get(i).get(5))+64 <= newCam[0]+gui.getWidth() && Double.parseDouble(Data.getListofLists().get(i).get(6)) > newCam[1] && Double.parseDouble(Data.getListofLists().get(i).get(6))+64 <= newCam[1]+gui.getHeight()){
                if (drawingList.get(i).get(0).equals("building")) {
                    g.drawImage(unit, Double.parseDouble(drawingList.get(i).get(5)) - Camera.getCamX(), Double.parseDouble(drawingList.get(i).get(6)) - Camera.getCamY(), size, size);
                } else if (drawingList.get(i).get(0).equals("character")) {
                    g.drawImage(unit,  Double.parseDouble(drawingList.get(i).get(5)) - Camera.getCamX(),  Double.parseDouble(drawingList.get(i).get(6)) - Camera.getCamY(), size, size);
                }
                double health = Double.parseDouble(drawingList.get(i).get(3)), maxHealth = Double.parseDouble(UnitDatabank.getUnitInfo(Integer.parseInt(drawingList.get(i).get(2)), "maxHealth"));
                if (health < maxHealth){
                    g.setFill(Color.DARKRED);
                    g.fillRect(Double.parseDouble(drawingList.get(i).get(5))- Camera.getCamX(), Double.parseDouble(drawingList.get(i).get(6))-20- Camera.getCamY(), size, 5);
                    g.setFill(Color.RED);
                    double ratio = health/maxHealth;
                    g.fillRect(Double.parseDouble(drawingList.get(i).get(5))- Camera.getCamX(), Double.parseDouble(drawingList.get(i).get(6))-20- Camera.getCamY(), size*ratio, 5);
                }

                g.setStroke(new Color(1, 0, 0, 0.5));
                if (!drawingList.get(i).get(1).equals(Player.getUsername())) {
                    //g.setTransform(new Affine(new Rotate(Double.parseDouble(Data.getListofLists().get(i).get(9)), Double.parseDouble(Data.getListofLists().get(i).get(5)) + 32, Double.parseDouble(Data.getListofLists().get(i).get(6)) + 32)));
                    g.strokeRect(Double.parseDouble(drawingList.get(i).get(5)) - Camera.getCamX(), Double.parseDouble(drawingList.get(i).get(6)) - Camera.getCamY(), size, size);
                    g.setTransform(normalAngle);
                }
            }
        }
        if (Player.unitIsSelected()) {
            g.setStroke(Color.YELLOW);
            double size = 64;
            if (Player.getSelectedUnit().get(0).equals("character")) {
                //g.setTransform(new Affine(new Rotate(Double.parseDouble(Player.getSelectedUnit().get(9)), Double.parseDouble(Player.getSelectedUnit().get(5)) + 32, Double.parseDouble(Player.getSelectedUnit().get(6)) + 32)));
                size = 64;
            } else if (Player.getSelectedUnit().get(0).equals("building")){
                if (Player.getSelectedUnit().get(2).equals(String.valueOf(UnitDatabank.NEXUS))){
                    size = 200;
                } else if (Player.getSelectedUnit().get(2).equals(String.valueOf(UnitDatabank.VILLAGE))){
                    size = 100;
                }
            }
            g.strokeRect(Double.parseDouble(Player.getSelectedUnit().get(5)) - Camera.getCamX(), Double.parseDouble(Player.getSelectedUnit().get(6)) - Camera.getCamY(), size, size);
            g.setTransform(normalAngle);
        }

        //Projectiles
        double size = 10;
        //System.out.println("ProjectileSize in draw "+Data.getProjectiles().size());
        for (int i = 0; i < Data.getProjectiles().size(); i++) {
            if (Double.parseDouble(Data.getProjectiles().get(i).get(2)) + size > Camera.getCamX() && Double.parseDouble(Data.getProjectiles().get(i).get(2))  <= Camera.getCamX() + gui.getWidth() && Double.parseDouble(Data.getProjectiles().get(i).get(3)) + size > Camera.getCamY() && Double.parseDouble(Data.getProjectiles().get(i).get(3)) <= Camera.getCamY() + gui.getHeight()) {
                g.drawImage(ImageLoader.image, Double.parseDouble(Data.getProjectiles().get(i).get(2)) - Camera.getCamX(), Double.parseDouble(Data.getProjectiles().get(i).get(3)) - Camera.getCamY(), size, size);
            }
        }

        //Interface
        g.setTransform(normalAngle);
        gui.getMinimap().draw(g);
        gui.getUnitinfo().draw(g);
        gui.getResInfo().draw(g);

        if (Player.isDeveloperMode()){
            //Die Maus movement Stufen
            g.setStroke(Color.LAVENDER);
            g.strokeRect(gui.getWidth() * frame1, gui.getHeight() * frame1, gui.getWidth() - (2 * (gui.getWidth() * frame1)), gui.getHeight() - (2 * (gui.getHeight() * frame1)));
            g.strokeRect(gui.getWidth() * frame2, gui.getHeight() * frame2, gui.getWidth() - (2 * (gui.getWidth() * frame2)), gui.getHeight() - (2 * (gui.getHeight() * frame2)));
            g.strokeRect(gui.getWidth() * frame3, gui.getHeight() * frame3, gui.getWidth() - (2 * (gui.getWidth() * frame3)), gui.getHeight() - (2 * (gui.getHeight() * frame3)));
        }
    }

    private void drawPauseMenu(GraphicsContext g, Game_Gui gui) {
        if (Gamestate.state != Gamestate_e.reconnect) {
            g.setFill(new Color(0, 0, 0, 0.4));
            g.fillRect(0, 0, gui.getWidth(), gui.getHeight());
        }
        //Hier Pausecontent
    }

    private void drawReconnectScreen(GraphicsContext g, Game_Gui gui) {
        g.transform(normalAngle);
        g.setFill(new Color(0, 0, 0, conOpacity));
        if (opacDarker) {
            conOpacity += 0.005;
        } else {
            conOpacity -= 0.005;
        }
        if (conOpacity <= 0.03 && !opacDarker) {
            opacDarker = true;
        } else if (conOpacity >= 0.7 && opacDarker) {
            opacDarker = false;
        }

        g.fillRect(0, 0, gui.getWidth(), gui.getHeight());
        g.setFill(new Color(0, 0, 0, 0.4));
        g.fillRect(gui.getWidth() * (1. / 3.) - 10, gui.getHeight() * (2. / 5.) - 74, gui.getWidth() * (2. / 5.), 175);
        g.setStroke(Color.DARKRED);
        g.setFill(Color.RED);
        g.setFont(gui.getFontBig());
        g.fillText("Connection lost\ntry to reconnect", gui.getWidth() * (1. / 3.), gui.getHeight() * (2. / 5.), gui.getWidth() * (2. / 3.));
        g.strokeText("Connection lost\ntry to reconnect", gui.getWidth() * (1. / 3.), gui.getHeight() * (2. / 5.), gui.getWidth() * (2. / 3.));

    }

    private void writeFile(String output) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        String outputWithNewLine = output + System.getProperty("line.separator");

        try {
            fileWriter = new FileWriter("log.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(outputWithNewLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}