package com.rcl.excalibur.domain.guest;

public class ValidateEmailEvent {
    private boolean successful;
    private String message;

    public ValidateEmailEvent(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccesfull(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

