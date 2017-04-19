package com.rcl.excalibur.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void getRightPrice() throws Exception {
        Float zeroPrice = 0.0f;
        Float myPrice = 21.54f;
        Float myExactPrice = 21.00f;

        Assert.assertTrue("Failed getRightPrice", "0".equals(StringUtils.getPriceFormatted(zeroPrice)));
        Assert.assertTrue("Failed getRightPrice", "21.54".equals(StringUtils.getPriceFormatted(myPrice)));
        Assert.assertTrue("Failed getRightPrice", "21".equals(StringUtils.getPriceFormatted(myExactPrice)));
    }
}
