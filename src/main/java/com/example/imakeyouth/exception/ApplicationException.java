package com.example.imakeyouth.exception;


/**
 * 应用异常
 */
public class ApplicationException extends RuntimeException {

    public static final String MESSAGE = "应用异常";
    private static final long serialVersionUID = 1L;

    public ApplicationException() {
        super(MESSAGE);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }


    public ApplicationException(Throwable cause) {
        super(cause);
    }


}
