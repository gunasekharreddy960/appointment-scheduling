package com.app.appointment.exception;
public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) { super(msg); }
}
