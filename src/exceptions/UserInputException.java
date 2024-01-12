package exceptions;

public class UserInputException extends RuntimeException{
    public UserInputException(String msg) {
        super(msg);
    }
}
