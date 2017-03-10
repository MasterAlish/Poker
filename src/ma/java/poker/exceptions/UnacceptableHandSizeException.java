package ma.java.poker.exceptions;

/**
 * Created by master Alish on 3/10/17.
 */
public class UnacceptableHandSizeException extends RuntimeException {
    public UnacceptableHandSizeException(){
        super("Hand size must be equal to 5");
    }
}
