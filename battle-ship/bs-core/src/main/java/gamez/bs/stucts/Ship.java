package gamez.bs.stucts;

import gamez.bs.enums.ShipTypes;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Ship {

    private ShipTypes type;
    private int health;

    Ship(ShipTypes type) {
        this.type = type;
        this.health = type.size();
    }

    ShipTypes type() { return type; }
    int hit() { return (this.health > 0) ? --health : 0; }

    // Needed ??
    public int health() { return health; }
}
