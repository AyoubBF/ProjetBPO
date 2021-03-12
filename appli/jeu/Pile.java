package appli.jeu;

public class Pile {
    private Player owner;
    private int actualValue;
    private String direction;

    public Pile(Player owner, int startingValue, String direction) {
        this.owner = owner;
        this.actualValue = startingValue;
        this.direction = direction;
    }

    public void setActualValue(int value) {
        this.actualValue = value;
    }

    public int getActualValue() {
        return this.actualValue;
    }

    public String getDirection() {
        return this.direction;
    }
}
