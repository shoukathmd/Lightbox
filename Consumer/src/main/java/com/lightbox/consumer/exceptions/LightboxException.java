package com.lightbox.consumer.exceptions;

public class LightboxException extends RuntimeException {
    static final long serialVersionUID = 1L;

    private LightboxException() {
        super("Not authorized to the resource.");
    }

    public LightboxException(String msg) {
        super(msg);
    }

}
