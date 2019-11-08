package units;

public class MapObject {
    private double x, y, hp;
    private String type;     //Info welches Gebäude oder welche Einheit das ist oder so
    public String owner;   //dann muss man sichergehen, dass keine 2 Spieler gleich heißen

    public MapObject(double x, double y, double hpNow, String type, String owner) {
        this.x = x;
        this.y = y;
        this.hp = hpNow;
        this.type = type;
        this.owner = owner;
    }

    public MapObject(double x, double y, String type, String owner) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.owner = owner;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
