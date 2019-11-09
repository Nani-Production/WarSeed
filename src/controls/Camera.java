package controls;

public class Camera {
    private static double movementSpeed, camX, camY;
    private static boolean right = false, left = false, up = false, down = false;

    public static void moveCam(){
        //TODO muss für diagonal noch überarbeitet werden
        if (right || left){
            camX += movementSpeed;
            System.out.println("moved x: "+camX);
        }
        if (up || down){
            camY += movementSpeed;
            System.out.println("moved y: "+camY);
        }
    }

    //move
    public static void moveRight(double speed){
        movementSpeed = speed;
        right = true;
    }
    public static void moveLeft(double speed){
        movementSpeed = -speed;
        left = true;
    }

    public static void moveUp(double speed){
        movementSpeed = -speed;
        up = true;
    }
    public static void moveDown(double speed){
        movementSpeed = speed;
        down = true;
    }

    //stop
    public static void stopRight(){
        if (!left){
            movementSpeed = 0;
        }
        right = false;
    }
    public static void stopLeft(){
        if (!right){
            movementSpeed = 0;
        }
        left = false;
    }

    public static void stopUp(){
        if (!down){
            movementSpeed = 0;
        }
        up = false;
    }
    public static void stopDown(){
        if (!up){
            movementSpeed = 0;
        }
        down = false;
    }

    //Getter & Setter
    public static double getMovementSpeed() {
        return movementSpeed;
    }

    public static void setMovementSpeed(double movementSpeed) {
        Camera.movementSpeed = movementSpeed;
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
}
