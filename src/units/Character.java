package units;

public class Character extends MapObject{
    private String name;            //vllcht individueller Name
    private double attack, defence, cooldown, speed;

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
}
