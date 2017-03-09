package com.rcl.excalibur.utils;


import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DetailModelProvider {
    public static Map<String, DiscoverItemModel> discoverItemMap;

    private DetailModelProvider() {

    }

    static {
        discoverItemMap = new HashMap<>();
        DiscoverItemModel discoverItem1 = new DiscoverItemModel();
        discoverItem1.setDiscoverId("1");
        discoverItem1.setType("Entertainment");
        discoverItem1.setImageUrl("https://s8.postimg.org/o6pm4w0n9/img_grease_musical.png");
        discoverItem1.setTitle("Grease - The Musical");
        discoverItem1.setSubtitle("Royale Theatre, Deck 12 AFT");
        discoverItem1.setReservationRequired(true);
        discoverItem1.setPromotionTitle("50% off Matinee showtimes");
        discoverItem1.setPromotionDescription("\"Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, "
                + "Guess, G-Shock and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself "
                + "or as a gift, we have an amazing selection for you to choose from. All tax and duty free.");
        List<String> items = new ArrayList<>();
        items.add("Day 3");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 4");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 5");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 6");
        items.add("10 AM - 12 PM - 4 PM");
        discoverItem1.setDiscoverItemTimes(items);
        discoverItem1.setPriceRange(new String[]{"Adults", "$40", "Children", "$20"});
        Map<String, String> properties = new HashMap<>();
        properties.put("Duration", "90 mins");
        properties.put("Age", "12+");
        properties.put("HeightRestriction", "None");
        properties.put("Attire", "Semi-formal");
        discoverItem1.setProperties(properties);
        discoverItem1.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat "
                + "est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, "
                + "in lobortis felis. - Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem1.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem1.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. "
                + "- You can't sue us if you die - You might die</p></body></html>");
        discoverItemMap.put("1", discoverItem1);

        ////////////////////////////////////////////////////////////////////////////////
        DiscoverItemModel discoverItem2 = new DiscoverItemModel();
        discoverItem2.setDiscoverId("2");
        discoverItem2.setType("Shorex");
        discoverItem2.setImageUrl("https://s17.postimg.org/yftzz3qun/img_caribbean_kitchen.png");
        discoverItem2.setTitle("Caribean Kitchen");
        discoverItem2.setSubtitle("St Martin");
        discoverItem2.setReservationRequired(true);
        discoverItem2.setPromotionTitle("Promotional title long version");
        discoverItem2.setPromotionDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. "
                + "Praesent quis feugiat est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla "
                + "venenatis justo dolor, in lobortis felis.");
        items = new ArrayList<>();
        items.add("Day 3");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 4");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 5");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 6");
        items.add("10 AM - 12 PM - 4 PM");
        discoverItem2.setDiscoverItemTimes(items);
        discoverItem2.setPriceRange(new String[]{"Adults", "$40", "Children", "$20"});
        properties = new HashMap<>();
        properties.put("Cuisine Types", "Latin American");
        properties.put("Duration", "90 mins");
        properties.put("Age", "All ages");
        properties.put("HeightRestriction", "None");
        properties.put("Attire", "Casual");
        properties.put("Activity Level", "Low");
        discoverItem2.setProperties(properties);
        discoverItem2.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat "
                + "est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, "
                + "in lobortis felis. - Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem2.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem2.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. "
                + "- You can't sue us if you die - You might die</p></body></html>");
        discoverItemMap.put("2", discoverItem2);

        ///////////////////////////////////////////////////////////////////////////////////
        DiscoverItemModel discoverItem3 = new DiscoverItemModel();
        discoverItem3.setDiscoverId("3");
        discoverItem3.setType("Spa");
        discoverItem3.setImageUrl("https://s11.postimg.org/4neqgqroz/img_hot_stone_massage.png");
        discoverItem3.setTitle("Hot Stone Massage");
        discoverItem3.setSubtitle("Vitality at Sea Spa. Deck 12 AFT");
        discoverItem3.setReservationRequired(true);
        discoverItem3.setPromotionTitle("50% off Matinee showtimes");
        discoverItem3.setPromotionDescription("Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, "
                + "Guess, G-Shock, "
                + "and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself or as a gift, we have "
                + "an amazing selection for you to choose from. All tax and duty free.");
        items = new ArrayList<>();
        items.add("Day 3");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 4");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 5");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 6");
        items.add("10 AM - 12 PM - 4 PM");
        discoverItem3.setDiscoverItemTimes(items);
        discoverItem3.setPriceRange(new String[]{"Min", "$40", "Max", "$20"});
        properties = new HashMap<>();
        properties.put("Age", "18+");
        properties.put("ActivityLevel", "Low");
        discoverItem3.setProperties(properties);
        discoverItem3.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat "
                + "est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, "
                + "in lobortis felis. - Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem3.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem3.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. "
                + "- You can't sue us if you die - You might die</p></body></html>");
        discoverItemMap.put("3", discoverItem3);

        /////////////////////////////////////////////////////////////////////////
        DiscoverItemModel discoverItem4 = new DiscoverItemModel();
        discoverItem4.setDiscoverId("4");
        discoverItem4.setType("Dining");
        discoverItem4.setImageUrl("https://s22.postimg.org/jjfafr7pd/img_sams_bbq_wild_house.png");
        discoverItem4.setTitle("Sam's BBQ Wild House");
        discoverItem4.setSubtitle("Deck 12, AFT");
        discoverItem4.setReservationRequired(false);
        discoverItem4.setPromotionTitle("50% off Matinee showtimes");
        discoverItem4.setPromotionDescription("Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, Guess, "
                + "G-Shock, "
                + "and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself or as a gift, we have "
                + "an amazing selection for you to choose from. All tax and duty free.");
        items = new ArrayList<>();
        items.add("LunchTime");
        items.add("12:00 pm - 3:00 pm");
        items.add("DinnerTime");
        items.add("5:00 pm - 10:00 pm");
        discoverItem4.setDiscoverItemTimes(items);
        discoverItem4.setPriceRange(new String[]{"2"});
        properties = new HashMap<>();
        properties.put("Cuisine", "American");
        properties.put("Age", "All ages");
        properties.put("Attire", "Casual");
        discoverItem4.setProperties(properties);
        discoverItem4.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat "
                + "est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, "
                + "in lobortis felis. - Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem4.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem4.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. "
                + "- You can't sue us if you die - You might die</p></body></html>");
        discoverItemMap.put("4", discoverItem4);
    }
}
