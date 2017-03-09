package com.rcl.excalibur.utils;


import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DetailModelProvider {
    public static Map<String, DiscoverItemModel> discoverItemMap;

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
        //discoverItem1.setDiscoverItemTimes(items);
        discoverItem1.setPriceRange(new String[]{"Adults", "40", "Children", "20"});
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

        List<String[]> daysAndTimes = new ArrayList<>();
        daysAndTimes.add(new String[]{"Day 3", "10 AM"});
        daysAndTimes.add(new String[]{
                "Day 4",
                "10 AM - 12 PM"
        });
        discoverItem1.setStandardTimesTitle("Presentations");
        discoverItem1.setStandardTimesDaysAndTimes(daysAndTimes);
        discoverItemMap.put(discoverItem1.getDiscoverId(), discoverItem1);

        ////////////////////////////////////////////////////////////////////////////////
        DiscoverItemModel discoverItem2 = new DiscoverItemModel();
        discoverItem2.setDiscoverId("2");
        discoverItem2.setType("Shorex");
        discoverItem2.setImageUrl("https://s17.postimg.org/yftzz3qun/img_caribbean_kitchen.png");
        discoverItem2.setTitle("Caribbean Kitchen");
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
        //discoverItem2.setDiscoverItemTimes(items);
        discoverItem2.setPriceRange(new String[]{"Adults", "40", "Children", "20"});
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
        discoverItem2.setStandardTimesTitle("Times");
        discoverItem2.setStandardTimesDaysAndTimes(daysAndTimes);
        discoverItemMap.put(discoverItem2.getDiscoverId(), discoverItem2);

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
        //discoverItem3.setDiscoverItemTimes(items);
        discoverItem3.setPriceRange(new String[]{"Min", "40", "Max", "20"});
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
        discoverItem3.setStandardTimesTitle("Sessions");
        discoverItem3.setStandardTimesDaysAndTimes(daysAndTimes);
        discoverItemMap.put(discoverItem3.getDiscoverId(), discoverItem3);

        /////////////////////////////////////////////////////////////////////////
        DiscoverItemModel diningItem = new DiscoverItemModel();
        diningItem.setDiscoverId("4");
        diningItem.setType("Dining");
        diningItem.setImageUrl("https://s22.postimg.org/jjfafr7pd/img_sams_bbq_wild_house.png");
        diningItem.setTitle("Sam's BBQ Wild House");
        diningItem.setSubtitle("Deck 12, AFT");
        diningItem.setReservationRequired(false);
        diningItem.setPromotionTitle("50% off Matinee showtimes");
        diningItem.setPromotionDescription("Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, Guess, "
                + "G-Shock, "
                + "and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself or as a gift, we have "
                + "an amazing selection for you to choose from. All tax and duty free.");
        diningItem.setLunchTime("12:00 pm - 3:00 pm");
        diningItem.setLunchMenu("http://www.crucerosvips.com/catalogos/cat17_09.pdf");
        diningItem.setDinnerTime("5:00 pm - 10:00 pm");
        diningItem.setDinnerMenu("https://secure.royalcaribbean.com.au/royal/content/en_OC_reg/pdf/worldwide-brochure-2016-2017.pdf");
        diningItem.setPriceRange(new String[]{"2"});
        properties = new HashMap<>();
        properties.put("Cuisine", "American");
        properties.put("Age", "All ages");
        properties.put("Attire", "Casual");
        diningItem.setProperties(properties);
        diningItem.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat "
                + "est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, "
                + "in lobortis felis. - Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        diningItem.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        diningItem.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. "
                + "- You can't sue us if you die - You might die</p></body></html>");
        diningItem.setStandardTimesTitle("Excursion times");
        diningItem.setStandardTimesDaysAndTimes(daysAndTimes);
        discoverItemMap.put(diningItem.getDiscoverId(), diningItem);
        //////////////////////////////////////////////////////
        DiscoverItemModel discoverItem5 = new DiscoverItemModel();
        discoverItem5.setDiscoverId("6");
        discoverItem5.setType("Activity");
        discoverItem5.setImageUrl("https://s24.postimg.org/p050yxrg5/img_library.png");
        discoverItem5.setTitle("Library");
        discoverItem5.setSubtitle("Deck 12 AFT");
        discoverItem5.setReservationRequired(false);
        discoverItem5.setPromotionTitle("Promotional title long version");
        discoverItem5.setPromotionDescription("Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, "
                + "Guess, G-Shock, and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself or as "
                + "a gift, we have an amazing selection for you to choose from. All tax and duty free.");
        items = new ArrayList<>();


        items.add("Day 3");
        items.add("10AM - 12PM, 3PM - 8PM");
        items.add("Day 4");
        items.add("Closed");
        items.add("Day 5");
        items.add("10 AM - 12 PM");
        items.add("Day 6");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 7");
        items.add("9:30 AM - 12:30 PM");
        items.add("Day 8");
        items.add("Closed");
        items.add("Day 9");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 10");
        items.add("10 AM - 12 PM - 4 PM");

        //discoverItem5.setDiscoverItemTimes(items);
        discoverItem5.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful."
                + " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat est. Aliquam "
                + "non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, in lobortis felis. - "
                + "Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem5.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem5.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. "
                + "- You can't sue us if you die - You might die</p></body></html>");

        discoverItemMap.put(discoverItem5.getDiscoverId(), discoverItem5);


        DiscoverItemModel discoverItem6 = new DiscoverItemModel();
        discoverItem6.setDiscoverId("7");
        discoverItem6.setType("Activity");
        discoverItem6.setImageUrl("https://s17.postimg.org/qtlpsu3z3/img_pool_voleball.png");
        discoverItem6.setTitle("Pool Volleyball");
        discoverItem6.setSubtitle("Deck 12 AFT");
        discoverItem6.setReservationRequired(false);
        discoverItem6.setPromotionTitle("Promotional title long version");
        discoverItem6.setPromotionDescription("Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, Guess, "
                + "G-Shock, and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself or as a gift"
                + ", we have an amazing selection for you to choose from. All tax and duty free.");
        items = new ArrayList<>();

        items.add("Day 3");
        items.add("10AM");
        items.add("Day 4");
        items.add("11AM");
        items.add("Day 5");
        items.add("10 AM - 12 PM");
        items.add("Day 6");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 7");
        items.add("9:30 AM - 12:30 PM");
        items.add("Day 8");
        items.add("Closed");
        items.add("Day 9");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 10");
        items.add("10 AM - 12 PM - 4 PM");

        //discoverItem6.setDiscoverItemTimes(items);
        discoverItem6.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful."
                + " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat est. Aliquam "
                + "non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, in lobortis felis. - "
                + "Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem6.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem6.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. - "
                + "You can't sue us if you die - You might die</p></body></html>");

        properties = new HashMap<>();
        properties.put("Duration", "90 mins");
        properties.put("AgeRestriction", "All Ages");
        properties.put("Restriction", "None");
        properties.put("Attire", "Active Wear");
        properties.put("ActivityLevel", "Low");
        discoverItem6.setProperties(properties);
        discoverItemMap.put(discoverItem6.getDiscoverId(), discoverItem6);
        //////////////////////////////////////////////////////


        DiscoverItemModel discoverItem8 = new DiscoverItemModel();
        discoverItem8.setDiscoverId("5");
        discoverItem8.setType("Shopping");
        discoverItem8.setImageUrl("https://s10.postimg.org/o3d9asfqh/img_tiffanys_jewelry.png");
        discoverItem8.setTitle("Tiffany`s Jewelry");
        discoverItem8.setSubtitle("Deck 12 AFT");
        discoverItem8.setReservationRequired(true);
        discoverItem8.setPromotionTitle("Promotional title long version");
        discoverItem8.setPromotionDescription("Save on Invicta, Movado, Tissot, Citizen, Bulova, Michael Kors, Ferrari, Fossil, Guess, "
                + "G-Shock, and more. All purchases have a 30-day price match guarantee! Whether you're shopping for yourself or as "
                + "a gift, "
                + "we have an amazing selection for you to choose from. All tax and duty free.");
        items = new ArrayList<>();

        items.add("Day 3");
        items.add("10 AM - 12 PM, 3PM - 8PM");
        items.add("Day 4");
        items.add("Closed");
        items.add("Day 5");
        items.add("10 AM - 12 PM");
        items.add("Day 6");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 7");
        items.add("9:30 AM - 12:30 PM");
        items.add("Day 8");
        items.add("Closed");
        items.add("Day 9");
        items.add("10 AM - 12 PM - 4 PM");
        items.add("Day 10");
        items.add("10 AM - 12 PM - 4 PM");

        //discoverItem8.setDiscoverItemTimes(items);
        discoverItem8.setPriceRange(new String[]{"Adults", "40", "Children", "20"});
        properties = new HashMap<>();
        properties.put("AgeRestriction", "All Ages");
        discoverItem8.setProperties(properties);
        discoverItem8.setDescription("<html><body><p>This description is shot enough to whet one's apetite but long enough to be "
                + "meaningful. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet purus turpis. Praesent quis feugiat "
                + "est. Aliquam non erat tempor, egestas dui eget, rutrum velit. Donec eu turpis dui. Nulla venenatis justo dolor, in "
                + "lobortis felis. - Arrive 15 mins early - Wear closed toed shoes</p></body></html>");
        discoverItem8.setAccessibility("Wheelchair accessible \n Wear closed toed shoes");
        discoverItem8.setLegal("<html><body><p>This legal information is short enough to comfort you but long enough to be meaningful. - "
                + "You can't sue us if you die - You might die</p></body></html>");

        discoverItemMap.put(discoverItem8.getDiscoverId(), discoverItem8);

    }

    private DetailModelProvider() {

    }
}
