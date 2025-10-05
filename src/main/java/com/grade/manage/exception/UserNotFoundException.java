package com.grade.manage.exception;

/**
 *
 * @author Studios TKOH!
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
