package units;

public class Character extends MapObject{
    private double attack, defence, cooldown, speed;
    private double moveX = 0, moveY = 0;
    //TODO die kampfwerte aus der Liste machen und lieber aus einer Datenbank auslesen

    public Character(double x, double y, double hp, String type, String name, String owner, double moveX, double moveY) {
        super(x, y, hp, type, name, owner);
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public Character(double x, double y, double width, double height, double hp, String type, String owner, double attack, double defence, double cooldown, double speed) {
        super(x, y, width, height, hp, type, owner);
        this.attack = attack;
        this.defence = defence;
        this.cooldown = cooldown;
        this.speed = speed;
    }

    public Character(double x, double y, String type, String owner, double attack, double defence, double cooldown, double speed) {
        super(x, y, type, owner);
        this.attack = attack;
        this.defence = defence;
        this.cooldown = cooldown;
        this.speed = speed;
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
