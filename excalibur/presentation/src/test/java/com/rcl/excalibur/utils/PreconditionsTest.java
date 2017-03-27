package com.rcl.excalibur.utils;

import org.junit.Test;

public class PreconditionsTest {
    @Test(expected = RuntimeException.class)
    public void unrecheable() throws Exception {
        Preconditions.unrecheable();
    }

}