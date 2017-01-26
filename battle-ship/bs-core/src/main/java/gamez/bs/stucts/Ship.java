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

    public Ship(ShipTypes type) {
        this.type = type;
        this.health = type.size();
    }

    public ShipTypes type() { return type; }
    public int hit() { return (this.health > 0) ? --health : 0; }
    public int health() { return health; }
}
