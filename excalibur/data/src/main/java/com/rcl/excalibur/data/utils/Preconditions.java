package com.rcl.excalibur.data.utils;


public final class Preconditions {
    private static final String MESSAGE_UNRECHEABLE = "Should not have access here";

    private Preconditions() {
    }

    /**
     * Unreachable code.
     */
    public static void unreachable() {
        throw new RuntimeException(MESSAGE_UNRECHEABLE);
    }
}
