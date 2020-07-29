package io.zipcoder.casino.card.utilities;

public class Card {

    private final CardSuit suit;
    private final CardRank rank;

    public Card(CardSuit suit, CardRank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        return "[" + this.rank.toString() + " " + this.suit.toString() + "]";
    }

    public CardSuit getSuit(){
        return this.suit;
    }

    public CardRank getRank(){
        return this.rank;
    }
}
