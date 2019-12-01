package units;

public class MapObject {
    private double x, y, width, height, hp;
    private String type, id, name;     //Info welches Gebäude oder welche Einheit das ist oder so
    private String owner;   //dann muss man sichergehen, dass keine 2 Spieler gleich heißen

    public MapObject(double x, double y, double hp, String type, String name, String owner) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.type = type;
        this.name = name;
        this.owner = owner;
    }

    public MapObject(double x, double y, double width, double height, double hpNow, String type, String owner) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hp = hpNow;
        this.name = null;
        this.type = type;
        this.owner = owner;
    }

    public MapObject(double x, double y, String type, String owner) {
        this.x = x;
        this.y = y;
        this.name = null;
        this.type = type;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
