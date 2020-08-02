package io.zipcoder.casino.card.utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlackjackCardValuesTest {

    BlackjackCardValues blackjack;

    @Test
    public void getValue() {
    }

    @Test
    public void valueOfRank1() {
        Card card = new Card(CardSuit.SPADE, CardRank.ACE);

        Integer expectedValue = 11;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank2() {
        Card card = new Card(CardSuit.SPADE, CardRank.TWO);

        Integer expectedValue = 2;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank3() {
        Card card = new Card(CardSuit.SPADE, CardRank.THREE);

        Integer expectedValue = 3;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank4() {
        Card card = new Card(CardSuit.SPADE, CardRank.FOUR);

        Integer expectedValue = 4;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank5() {
        Card card = new Card(CardSuit.SPADE, CardRank.FIVE);

        Integer expectedValue = 5;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank6() {
        Card card = new Card(CardSuit.SPADE, CardRank.SIX);

        Integer expectedValue = 6;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank7() {
        Card card = new Card(CardSuit.SPADE, CardRank.SEVEN);

        Integer expectedValue = 7;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank8() {
        Card card = new Card(CardSuit.SPADE, CardRank.EIGHT);

        Integer expectedValue = 8;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank9() {
        Card card = new Card(CardSuit.SPADE, CardRank.NINE);

        Integer expectedValue = 9;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank10() {
        Card card = new Card(CardSuit.SPADE, CardRank.TEN);

        Integer expectedValue = 10;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank11() {
        Card card = new Card(CardSuit.SPADE, CardRank.JACK);

        Integer expectedValue = 10;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank12() {
        Card card = new Card(CardSuit.SPADE, CardRank.QUEEN);

        Integer expectedValue = 10;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void valueOfRank13() {
        Card card = new Card(CardSuit.SPADE, CardRank.KING);

        Integer expectedValue = 10;
        Integer actualValue = BlackjackCardValues.valueOfRank(card.getRank().toString());

        assertEquals(expectedValue, actualValue);
    }
}