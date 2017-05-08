package com.rcl.excalibur.utils;


public final class CompassUtils {
    private static final String SHIP_DIRECTION_NORTH = "N";
    private static final String SHIP_DIRECTION_NORTH_EAST = "NE";
    private static final String SHIP_DIRECTION_EAST = "E";
    private static final String SHIP_DIRECTION_SOUTH_EAST = "SE";
    private static final String SHIP_DIRECTION_SOUTH = "S";
    private static final String SHIP_DIRECTION_SOUTH_WEST = "SE";
    private static final String SHIP_DIRECTION_WEST = "W";
    private static final String SHIP_DIRECTION_NORTH_WEST = "NW";

    private CompassUtils() {
    }

    public static String getCompassByUnit(int compass) {
        if (compass == 0)
            return SHIP_DIRECTION_NORTH;
        else if (compass > 0 && compass < 89)
            return SHIP_DIRECTION_NORTH_EAST;
        else if (compass == 90)
            return SHIP_DIRECTION_EAST;
        else if (compass > 90 && compass < 179)
            return SHIP_DIRECTION_SOUTH_EAST;
        else if (compass == 180)
            return SHIP_DIRECTION_SOUTH;
        else if (compass > 180 && compass < 269)
            return SHIP_DIRECTION_SOUTH_WEST;
        else if (compass == 270)
            return SHIP_DIRECTION_WEST;
        else
            return SHIP_DIRECTION_NORTH_WEST;
    }
}
