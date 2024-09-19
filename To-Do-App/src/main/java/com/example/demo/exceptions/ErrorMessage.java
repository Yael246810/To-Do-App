package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    CUSTOMER_ID_NOT_FOUND("cannot find customer's Id"),
    TASK_ID_NOT_FOUND("cannot find task's Id"),
    CUSTOMER_DIDNT_LOGIN("customer didn't do login"),
    EMAIL_NOT_FOUND("cannot find customer's email"),
    PASSWORD_ALREADY_IN_USE("password is already in use"),
    LOGIN_DETAILS_INCORRECT("your login details are incorrect. check again your email/password"),
    SECURITY_EXCEPTION("user does not exist"),
    SECURITY_EXCEPTION_USER_NOT_ALLOWED("user not allowed. try to login again"),
    TASK_DOESNT_BELONG_TO_CUSTOMER("customer doesn't have this task"),
    END_DATE_DOES_NOT_EXIST("there is no time limit for this task"),
    END_DATE_ID_NOT_VALID("end date is before today, not valid"),
    START_DATE_IS_AFTER_TODAY("start date cannot be after today"),
    NO_TASKS_AVAILABLE_UNTIL_END_DATE("no tasks available until this end date");
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
