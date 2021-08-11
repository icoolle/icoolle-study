package com.icoolle.component.exception;


public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum responseEnum, String message, Object... args) {
        super(responseEnum, args, message);
    }

    public BusinessException(IResponseEnum responseEnum, String message, Throwable cause, Object... args) {
        super(responseEnum, args, message, cause);
    }
}

