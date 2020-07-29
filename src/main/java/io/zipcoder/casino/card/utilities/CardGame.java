package io.zipcoder.casino.card.utilities;

import io.zipcoder.casino.Game;
import io.zipcoder.casino.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CardGame implements Game {

    private Deck deck;
    private Map<Player, List<Card>> playerHands;

    public CardGame(Integer handSize, Player... players){
        this.deck = new Deck();
        this.deck.shuffle();
        for(Player p : players){
            this.playerHands.put(p, new ArrayList<Card>());
        }
        dealHands(handSize);
    }

    public String showHand(Player player) {
        StringBuilder sb = new StringBuilder();
        for(Card c : playerHands.get(player)){
            sb.append(c.toString());
        }
        return sb.toString();
    }

    public void dealHands(Integer handSize) {
        for(Player p : playerHands.keySet()){
            for(int i = 0; i < handSize; i++) {
                playerHands.get(p).add(this.deck.draw());
            }
        }
    }
}
