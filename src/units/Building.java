package units;

public class Building extends MapObject {

    public Building(double x, double y, double hp, String type, String owner) {
        super(x, y, hp, type, owner);
    }

    public Building(double x, double y, String type, String owner) {
        super(x, y, type, owner);
    }
}