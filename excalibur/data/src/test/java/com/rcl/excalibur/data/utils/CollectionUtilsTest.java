package com.rcl.excalibur.data.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilsTest {
    @Test
    public void isEmpty() throws Exception {
        Assert.assertTrue("Failed isEmpty Case 1", CollectionUtils.isEmpty(new String[0]));
        Assert.assertTrue("Failed isEmpty Case 2", CollectionUtils.isEmpty(new ArrayList()));
        List list = new ArrayList();
        list.add("item1");
        Assert.assertFalse("Failed isEmpty Case 3", CollectionUtils.isEmpty(list));
    }

}