package gamez.bs.enums;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public enum ShipTypes {

    // Define SHIP types with character and size to identify the uniquely
    CARRIER(5, 'C'), BATTLESHIP(4, 'b'), CRUISER(3, 'c'), SUBMARINE(3, 's'), DINGY(2, 'd');

    int size;
    char id;

    ShipTypes(int size, char id) {
        this.size = size;
        this.id = id;
    }

    public char id() { return id; }
    public int size() { return this.size; }
}
