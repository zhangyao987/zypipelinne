package cn.com.zy.util.exceptions;

public class ControllerHandlerGlobalException extends RuntimeException {

    public ControllerHandlerGlobalException() {
        super();
    }

    public ControllerHandlerGlobalException(String message) {
        super(message);
    }

    public ControllerHandlerGlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerHandlerGlobalException(Throwable cause) {
        super(cause);
    }

    protected ControllerHandlerGlobalException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
