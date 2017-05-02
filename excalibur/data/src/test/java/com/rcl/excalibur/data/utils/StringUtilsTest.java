package com.rcl.excalibur.data.utils;

import android.text.TextUtils;
import android.util.Base64;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.UnsupportedEncodingException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Base64.class, TextUtils.class})
public class StringUtilsTest {

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Base64.class);
        PowerMockito.mockStatic(TextUtils.class);
    }

    @Test
    public void encodeDecode() throws Exception {
        String EXTRA_ID = "Some Id";
        String ENCODE = "U29tZSBJRA==";
        String OUTPUT_CHARSET = "UTF-8";
        byte[] dataout =  new byte[7];
        dataout[0]=83;
        dataout[1]=111;
        dataout[2]=109;
        dataout[3]=101;
        dataout[4]=32;
        dataout[5]=73;
        dataout[6]=100;
        try {
            byte[] data = EXTRA_ID.getBytes(OUTPUT_CHARSET);
            PowerMockito.when(Base64.encodeToString(data, Base64.DEFAULT)).thenReturn(ENCODE);
            PowerMockito.when(Base64.decode(ENCODE, Base64.DEFAULT)).thenReturn(dataout);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PowerMockito.when(TextUtils.isEmpty(any())).thenReturn(false);
        String encryptString = StringUtils.encodeString(EXTRA_ID);
        String extraString = StringUtils.decodeString(encryptString);
        assertEquals(extraString, EXTRA_ID);

        PowerMockito.when(TextUtils.isEmpty(null)).thenReturn(true);
        assertEquals(null, StringUtils.encodeString(null));
        PowerMockito.when(TextUtils.isEmpty(null)).thenReturn(true);
        assertEquals(null, StringUtils.decodeString(null));
    }


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
