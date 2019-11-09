package units;

public class Character extends MapObject{
    private String name;
    private double attack, defence, cooldown, speed;
    private double moveX = 0, moveY = 0;

    public Character(double x, double y, double hp, String type, String owner, String name, double attack, double defence, double cooldown, double speed) {
        super(x, y, hp, type, owner);
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.cooldown = cooldown;
        this.speed = speed;
    }

    public Character(double x, double y, String type, String owner, String name, double attack, double defence, double cooldown, double speed) {
        super(x, y, type, owner);
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.cooldown = cooldown;
        this.speed = speed;
    }

    public void move(double moveX, double moveY){
        //TODO Übersetzung von Mauskoordinaten zu Kartenkoordinaten
        /*
        double playerX = this.getX(), playerY = this.getY();
        double someX = targetX -playerX;
        double someY = targetY -playerY;

        moveX = speed/Math.sqrt(Math.pow(someX, 2)+Math.pow(someY, 2)) * someX;
        moveY = speed/Math.sqrt(Math.pow(someX, 2)+Math.pow(someY, 2)) * someY;
        */
        //lieber die Bewegungsrichtung an den Server schicken, damit dieser die Bewegungsveränderung ausrechnet und so alle Bewegungen gleich sind?
    }

    public void attack (Building b){

    }

    public void attack (Character c){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMoveX() {
        return moveX;
    }

    public void setMoveX(double moveX) {
        this.moveX = moveX;
    }

    public double getMoveY() {
        return moveY;
    }

    public void setMoveY(double moveY) {
        this.moveY = moveY;
    }
}
