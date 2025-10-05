package com.grade.manage.exception;

/**
 *
 * @author Studios TKOH!
 */
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
