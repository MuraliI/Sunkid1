package com.rcl.excalibur.utils;

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

@RunWith(PowerMockRunner.class)
@PrepareForTest({Base64.class})
public class IntentExtraUtilsTest {

    private final String EXTRA_ID = "Some Id";
    private final String ENCODE = "U29tZSBJRA==";
    private final String OUTPUT_CHARSET = "UTF-8";

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Base64.class);
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
    }
    @Test
    public void ExtraString() throws Exception {
        String encriptString = IntentExtraUtils.putExtraString(EXTRA_ID);
        String extraString = IntentExtraUtils.getExtraString(encriptString);
        assertEquals(extraString, EXTRA_ID);
    }

}