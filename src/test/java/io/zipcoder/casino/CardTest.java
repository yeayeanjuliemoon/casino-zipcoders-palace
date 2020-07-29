package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import io.zipcoder.casino.card.utilities.CardSuit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

    @Test
    public void testCardConstructor(){
        CardRank expectedRank = CardRank.ACE;
        CardSuit expectedSuit = CardSuit.SPADE;

        Card testCard = new Card(expectedSuit, expectedRank);

        assertEquals(expectedRank, testCard.getRank());
        assertEquals(expectedSuit, testCard.getSuit());
    }

    @Test
    public void testCardtoString(){
        CardRank expectedRank = CardRank.ACE;
        CardSuit expectedSuit = CardSuit.SPADE;

        Card testCard = new Card(expectedSuit, expectedRank);
        String expectedString = "[ACE SPADE]";
        String actual = testCard.toString();

        assertEquals(expectedString, actual);

    }
}
