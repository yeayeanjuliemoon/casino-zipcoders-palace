package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import io.zipcoder.casino.card.utilities.CardSuit;
import io.zipcoder.casino.card.utilities.Deck;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeckTest {

    @Test
    public void testDeckConstructor(){
        Deck testDeck = new Deck();
        Integer deckSize = testDeck.getDeck().size();
        Integer expectedSize = 52;

        assertEquals(deckSize, expectedSize);
    }

    @Test
    public void testDraw(){
        Deck testDeck = new Deck();
        List<Card> deckList = testDeck.getDeck();
        Card expectedCard = deckList.get(0);
        Integer expectedSize = 51;

        Card actualCard = testDeck.draw();
        Integer actualSize = testDeck.getDeck().size();

        assertEquals(expectedCard, actualCard);
        assertEquals(expectedSize, actualSize);

    }

    @Test
    public void testRemove(){
        Deck testDeck = new Deck();
        Card toBeRemoved = new Card(CardSuit.SPADE, CardRank.ACE);
        testDeck.remove(toBeRemoved);
        List<Card> deckList = testDeck.getDeck();

        assertFalse(testDeck.containsCard(toBeRemoved));

    }
}
