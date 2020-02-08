package cn.com.zy.util.exceptions;

public class ControllerFieldCheckException extends RuntimeException {

    public ControllerFieldCheckException(){
        super();
    }

    public ControllerFieldCheckException(String message){
        super(message);
    }

    public ControllerFieldCheckException(String message,Throwable cause){
        super(message,cause);
    }

    public ControllerFieldCheckException(Throwable cause){
        super(cause);
    }

    protected ControllerFieldCheckException(String message,Throwable cause,
                                            boolean enableSuppression,
                                            boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }






}
