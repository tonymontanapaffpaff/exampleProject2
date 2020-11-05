package by.epamlab.exceptions;

public class InitException extends Exception {
    public InitException() {
    }

    public InitException(String s) {
        super(s);
    }

    public InitException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitException(Throwable cause) {
        super(cause);
    }
}
