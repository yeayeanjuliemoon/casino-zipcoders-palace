package io.zipcoder.casino.card.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck;

    public Deck(){
        this.deck = new ArrayList<Card>();
        generateDeck();
    }

    public Card draw() {
        if(this.deck.isEmpty()) {
            return null;
        } else{
            Card drawCard = this.deck.get(0);
            this.deck.remove(drawCard);
            return drawCard;
        }
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public boolean remove(Card card) {
        for(Card c : this.deck){
            if(c.getSuit() == card.getSuit() && c.getRank() == card.getRank()){
                this.deck.remove(c);
                return true;
            }
        }
        return false;

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

    public List<Card> getDeck(){
        return this.deck;
    }

    public boolean containsCard(Card card) {
        for(Card c : this.deck){
            if(c.getSuit() == card.getSuit() && c.getRank() == card.getRank()){
                return true;
            }
        }
        return false;
    }
}
