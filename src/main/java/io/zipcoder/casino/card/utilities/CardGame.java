package io.zipcoder.casino.card.utilities;

import io.zipcoder.casino.Game;
import io.zipcoder.casino.Player;

import java.util.List;
import java.util.Map;

public abstract class CardGame implements Game {

    private Deck deck;
    private Map<Player, List<Card>> playerHands;

    public String showHand(Player player) {
        return "";
    }

    public void dealHands() {

    }
}
