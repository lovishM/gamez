package gamez.bs.stucts;

import gamez.bs.enums.TurnTypes;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class FeedBack {
    private TurnTypes turnType;
    private String message;

    FeedBack(TurnTypes type, String message) {
        this.turnType = type;
        this.message = message;
    }

    public TurnTypes getTurnType() { return turnType; }
    public String getMessage() { return message; }
}
