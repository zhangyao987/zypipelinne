package cn.com.zy.util.exceptions;

public class DaoHandlerGlobalException extends RuntimeException {
    public DaoHandlerGlobalException() {
        super();
    }

    public DaoHandlerGlobalException(String message) {
        super(message);
    }

    public DaoHandlerGlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoHandlerGlobalException(Throwable cause) {
        super(cause);
    }

    protected DaoHandlerGlobalException(String message, Throwable cause,
                                               boolean enableSuppression,
                                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
