package ru.segam.user_service.exception;

public class NickNameBusyException extends RuntimeException {
    public NickNameBusyException(String message) {
        super(message);
    }
}
