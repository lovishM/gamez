package gamez.bs.enums;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public enum PositionTypes {
    EMPTY(0), SUNK(-1);

    int type;
    PositionTypes(int type) {
        this.type = type;
    }

    public int getType() { return type; }
}
