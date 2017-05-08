package com.rcl.excalibur.utils.factory;


import android.util.Pair;

import com.rcl.excalibur.R;

import java.util.ArrayList;
import java.util.List;

public final class DeckMapFactoryImpl implements DeckMapFactory {

    private static final int NUMBER_OF_DECKS = 15;
    private static final int DECK_1 = 1;
    private static final int DECK_2 = 2;
    private static final int DECK_3 = 3;
    private static final int DECK_4 = 4;
    private static final int DECK_5 = 5;
    private static final int DECK_6 = 6;
    private static final int DECK_7 = 7;
    private static final int DECK_8 = 8;
    private static final int DECK_9 = 9;
    private static final int DECK_10 = 10;
    private static final int DECK_11 = 11;
    private static final int DECK_12 = 12;
    private static final int DECK_13 = 13;
    private static final int DECK_14 = 14;
    private static final int DECK_15 = 15;

    private List<Pair<Integer, Integer>> deckImages;

    public DeckMapFactoryImpl() {
        deckImages = new ArrayList<>(NUMBER_OF_DECKS);
        deckImages.add(new Pair<>(DECK_1, R.drawable.deck1));
        deckImages.add(new Pair<>(DECK_2, R.drawable.deck2));
        deckImages.add(new Pair<>(DECK_3, R.drawable.deck3));
        deckImages.add(new Pair<>(DECK_4, R.drawable.deck4));
        deckImages.add(new Pair<>(DECK_5, R.drawable.deck5));
        deckImages.add(new Pair<>(DECK_6, R.drawable.deck6));
        deckImages.add(new Pair<>(DECK_7, R.drawable.deck7));
        deckImages.add(new Pair<>(DECK_8, R.drawable.deck8));
        deckImages.add(new Pair<>(DECK_9, R.drawable.deck9));
        deckImages.add(new Pair<>(DECK_10, R.drawable.deck10));
        deckImages.add(new Pair<>(DECK_11, R.drawable.deck11));
        deckImages.add(new Pair<>(DECK_12, R.drawable.deck12));
        deckImages.add(new Pair<>(DECK_13, R.drawable.deck13));
        deckImages.add(new Pair<>(DECK_14, R.drawable.deck14));
        deckImages.add(new Pair<>(DECK_15, R.drawable.deck15));
    }

    public List<Pair<Integer, Integer>> getDeckImages() {
        return deckImages;
    }
}
