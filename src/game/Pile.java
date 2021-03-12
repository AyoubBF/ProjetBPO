package game;

public class Pile {
    private Player owner;
    private int actualValue;
    private String direction;

    /**
     * @brief Constructeur pour la pile en fonction de son propriétaire, sa
     * direction, et sa valeur initiale
     * @param owner le propriétaire de la pile
     * @param startingValue la valeur initiale de la pile
     * @param direction la direction de la pile (ascendante ou descendante)
     */
    public Pile(Player owner, int startingValue, String direction) {
        this.owner = owner;
        this.actualValue = startingValue;
        this.direction = direction;
    }

    /**
     * @brief permet de mettre à jour la valeur de la pile (attribut privé)
     * @param value représente la nouvelle valeur de pile
     */
    public void setActualValue(int value) {
        this.actualValue = value;
    }

    /**
     * @brief permet de récupérer la valeur de la pile (attribut privé)
     * @return la valeur de la pile
     */
    public int getActualValue() {
        return this.actualValue;
    }

    /**
     * @brief permet de récupérer la direction de la pile (attribut privé)
     * @return la direction (ascendante ou descendante)
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * @brief permet de récupérer le propriétaire de la pile (attribut privé)
     * @return le propriétaire de la pile
     */
    public Player getOwner(){
        return this.owner;
    }
}
