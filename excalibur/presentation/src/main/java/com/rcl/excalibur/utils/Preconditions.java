package com.rcl.excalibur.utils;


public final class Preconditions {
    private static final String MESSAGE_UNRECHEABLE = "Should not have access here";

    private Preconditions() {
    }

    /**
     * Unrechable code.
     */
    public static void unrecheable() {
        throw new RuntimeException(MESSAGE_UNRECHEABLE);
    }
}
