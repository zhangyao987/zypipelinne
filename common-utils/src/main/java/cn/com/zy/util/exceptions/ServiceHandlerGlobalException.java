package cn.com.zy.util.exceptions;


public class ServiceHandlerGlobalException extends RuntimeException {
    public ServiceHandlerGlobalException() {
        super();
    }

    public ServiceHandlerGlobalException(String message) {
        super(message);
    }

    public ServiceHandlerGlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceHandlerGlobalException(Throwable cause) {
        super(cause);
    }

    protected ServiceHandlerGlobalException(String message, Throwable cause,
                                               boolean enableSuppression,
                                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
