package gamez.bs.exceptions;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class StateException extends Exception {

    private String message;

    public StateException(String message) {
        super(message);

        this.message = message;
    }

    @Override
    public String getMessage() { return message; }
}
