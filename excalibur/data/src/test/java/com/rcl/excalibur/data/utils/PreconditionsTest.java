package com.rcl.excalibur.data.utils;

import org.junit.Test;

public class PreconditionsTest {
    @Test(expected = RuntimeException.class)
    public void testUnreachableThrowsException() throws Exception {
        Preconditions.unreachable();
    }

    @Test(expected = RuntimeException.class)
    public void testNotNull() throws Exception {
        Preconditions.notNull(null);
    }


}