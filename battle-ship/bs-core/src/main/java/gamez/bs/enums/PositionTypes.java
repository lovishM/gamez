package gamez.bs.enums;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public enum PositionTypes {
    EMPTY(0), PLAYED(1), SUNK(-1);

    int type;
    PositionTypes(int type) {
        this.type = type;
    }

    public int type() { return type; }
}
