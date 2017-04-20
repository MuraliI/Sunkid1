package com.rcl.excalibur.utils;


import android.content.Context;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public final class JsonUtils {
    private static final String FILE_NAME = "shipName.json";
    private static final String SHIP_CODE = "shipCode";
    private static final String SHIP_TITLE = "title";
    private static final String ENCODE = "UTF-8";


    private JsonUtils() {
    }

    @Nullable
    public static String getShipNameFromJson(Context context, String shipCodeResponse) {
        String jsonArrayString = loadJSONFromAsset(context);
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            String shipName = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String shipCode = jsonObj.getString(SHIP_CODE);
                String title = jsonObj.getString(SHIP_TITLE);
                if (shipCode.equals(shipCodeResponse)) {
                    shipName = title;
                    break;
                }
            }
            return shipName;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open(FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, ENCODE);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
