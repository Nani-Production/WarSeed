package controls;

import gui.Game_Gui;

public class Camera {
    private static double movementSpeedX = 0, movementSpeedY, camX = 0, camY = 0, mapwidth = 0, mapHeight = 0; //cam ist mapKoordinate
    private static boolean right = false, left = false, up = false, down = false;
    private static Game_Gui gui;

    public static void moveCam(){
        if (up || down) {
            if (camY+movementSpeedY < 0){
                camY = 0;
            } else if (camY+movementSpeedY+gui.getHeight() >= mapHeight){
                camY = mapHeight-gui.getHeight();
            } else {
                camY += movementSpeedY;
            }
        }
        if (right || left) {
            if (camX+movementSpeedX < 0) {
                camX = 0;
            } else if (camX+movementSpeedX+gui.getWidth() >= mapwidth){
                camX = mapwidth-gui.getWidth();
            } else {
                camX += movementSpeedX;
            }
        }
    }

    //move
    public static void moveRight(double speed){
        movementSpeedX = speed;
        right = true;
    }
    public static void moveLeft(double speed){
        movementSpeedX = -speed;
        left = true;
    }

    public static void moveUp(double speed){
        movementSpeedY = -speed;
        up = true;
    }
    public static void moveDown(double speed){
        movementSpeedY = speed;
        down = true;
    }

    //stop
    public static void stopRight(){
        if (!left){
            movementSpeedX = 0;
        }
        right = false;
    }
    public static void stopLeft(){
        if (!right){
            movementSpeedX = 0;
        }
        left = false;
    }

    public static void stopUp(){
        if (!down){
            movementSpeedY = 0;
        }
        up = false;
    }
    public static void stopDown(){
        if (!up){
            movementSpeedY = 0;
        }
        down = false;
    }

    //Getter & Setter
    public static double getMovementSpeedX() {
        return movementSpeedX;
    }

    public static void setMovementSpeedX(double movementSpeed) {
        Camera.movementSpeedX = movementSpeed;
    }

    public static double getMovementSpeedY() {
        return movementSpeedY;
    }

    public static void setMovementSpeedY(double movementSpeedY) {
        Camera.movementSpeedY = movementSpeedY;
    }

    public static double getCamX() {
        return camX;
    }

    public static void setCamX(double camX) {
        Camera.camX = camX;
    }

    public static double getCamY() {
        return camY;
    }

    public static void setCamY(double camY) {
        Camera.camY = camY;
    }

    public static void setMapSize(double mapWidth, double mapHeight){
        Camera.mapwidth = mapWidth;
        Camera.mapHeight = mapHeight;
    }

    public static void setStartCoordinates (double x, double y){
        Camera.camX = x;
        Camera.camY = y;
    }

    public static Game_Gui getGui() {
        return gui;
    }

    public static void setGui(Game_Gui gui) {
        Camera.gui = gui;
    }
}
