package com.rcl.excalibur.data.utils;

import org.junit.Assert;
import org.junit.Test;

public class DBUtilTest {

    @Test
    public void and() throws Exception {
        Assert.assertEquals("cond1 AND cond2", DBUtil.and("cond1", "cond2"));
    }

    @Test
    public void or() throws Exception {
        Assert.assertEquals("cond1 OR cond2", DBUtil.or("cond1", "cond2"));
    }

    @Test
    public void eqId() throws Exception {
        Assert.assertEquals("Id = " + 12, DBUtil.eqId(12));

    }

    @Test
    public void eq() throws Exception {
        Assert.assertEquals("col = 'aa'", DBUtil.eq("col", "aa"));
        Assert.assertEquals("col = " + 1, DBUtil.eq("col", 1));
        Assert.assertEquals("col = " + new Float(1).floatValue(), DBUtil.eq("col", new Float(1).floatValue()));
    }


    @Test
    public void eqSingle() throws Exception {
        Assert.assertEquals("col = ?", DBUtil.eq("col"));
    }

    @Test
    public void notEq() throws Exception {
        Assert.assertEquals("col != 'a'", DBUtil.notEq("col", "a"));
        Assert.assertEquals("col != " + 1, DBUtil.notEq("col", 1));
        Assert.assertEquals("col != " + new Float(1).floatValue(), DBUtil.notEq("col", new Float(1).floatValue()));
    }

    @Test
    public void isTrue() throws Exception {
        Assert.assertEquals("col = '1'", DBUtil.isTrue("col"));
    }

    @Test
    public void isFalse() throws Exception {
        Assert.assertEquals("col = '0'", DBUtil.isFalse("col"));
    }

    @Test
    public void asc() throws Exception {
        Assert.assertEquals("col ASC", DBUtil.asc("col"));
    }

    @Test
    public void desc() throws Exception {
        Assert.assertEquals("col DESC", DBUtil.desc("col"));
    }

    @Test
    public void like() throws Exception {
        Assert.assertEquals("col LIKE '%aa%'", DBUtil.like("col", "aa"));
    }


}