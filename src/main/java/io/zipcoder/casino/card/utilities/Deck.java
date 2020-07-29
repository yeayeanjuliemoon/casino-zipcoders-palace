package io.zipcoder.casino.card.utilities;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> deck;

    public Deck(){
        this.deck = new ArrayList<Card>();
        generateDeck();
    }

    public Card draw() {
        return null;
    }

    public void shuffle() {

    }

    public void remove(Card card) {

    }

    public void generateDeck() {
        CardSuit[] suits = CardSuit.values();
        CardRank[] ranks = CardRank.values();
        for(CardRank rank : ranks){
            for(CardSuit suit : suits){
                this.deck.add(new Card(suit, rank));
            }
        }

    }

}
